import java.util.*;

/** 練習 5：Interval Tree（以紅黑樹為底，維護 max 高端點） */
public class IntervalTreeExercise {

    static final boolean RED = true, BLACK = false;

    /** 對外公開的區間型別（避免「Exporting non-public type」警告） */
    public static final class Interval {
        public final int lo, hi;
        public Interval(int lo, int hi){
            if (lo > hi) throw new IllegalArgumentException("lo>hi");
            this.lo = lo; this.hi = hi;
        }
        public boolean overlaps(Interval o){ return !(this.hi < o.lo || o.hi < this.lo); }
        @Override public String toString(){ return "["+lo+","+hi+"]"; }
    }

    /** 內部節點型別（不對外暴露） */
    private static final class Node {
        Interval iv;
        int max;
        boolean color = RED;
        Node left, right, parent;
        Node(Interval iv){ this.iv = iv; this.max = iv.hi; }
    }

    private final Node NIL = new Node(new Interval(Integer.MIN_VALUE, Integer.MIN_VALUE));
    private Node root = NIL;

    public IntervalTreeExercise(){
        NIL.color = BLACK;
        NIL.left = NIL.right = NIL.parent = NIL;
        NIL.max = Integer.MIN_VALUE;
    }

    /* ========== Public API ========== */
    public void insert(int lo, int hi){ insert(new Interval(lo, hi)); }
    public boolean delete(int lo, int hi){ return delete(new Interval(lo, hi)); }
    public Interval searchAnyOverlap(int lo, int hi){ return searchAnyOverlap(new Interval(lo, hi)); }
    public List<Interval> searchAllOverlap(int lo, int hi){
        ArrayList<Interval> ans = new ArrayList<>();
        searchAllOverlap(root, new Interval(lo,hi), ans);
        return ans;
    }
    public void inorderPrint(){ inorderPrint(root); System.out.println(); }

    /* ========== 基礎工具 ========== */
    private void pull(Node x){ x.max = Math.max(x.iv.hi, Math.max(x.left.max, x.right.max)); }

    private void leftRotate(Node x){
        Node y = x.right;
        x.right = y.left; if (y.left!=NIL) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent==NIL) root=y; else if (x==x.parent.left) x.parent.left=y; else x.parent.right=y;
        y.left = x; x.parent = y;
        pull(x); pull(y);
    }

    private void rightRotate(Node x){
        Node y = x.left;
        x.left = y.right; if (y.right!=NIL) y.right.parent = x;
        y.parent = x.parent;
        if (x.parent==NIL) root=y; else if (x==x.parent.right) x.parent.right=y; else x.parent.left=y;
        y.right = x; x.parent = y;
        pull(x); pull(y);
    }

    /* ========== 插入 ========== */
    public void insert(Interval zIv){
        Node z = new Node(zIv);
        z.left = z.right = z.parent = NIL;

        Node y = NIL, x = root;
        while (x != NIL){ y = x; x = (z.iv.lo < x.iv.lo) ? x.left : x.right; }
        z.parent = y;
        if (y == NIL) root = z;
        else if (z.iv.lo < y.iv.lo) y.left = z;
        else y.right = z;

        for (Node t = z; t != NIL; t = t.parent) pull(t);
        insertFixup(z);
    }

    private void insertFixup(Node z){
        while (z.parent.color == RED){
            if (z.parent == z.parent.parent.left){
                Node y = z.parent.parent.right;
                if (y.color == RED){
                    z.parent.color = BLACK; y.color = BLACK;
                    z.parent.parent.color = RED; z = z.parent.parent;
                }else{
                    if (z == z.parent.right){ z = z.parent; leftRotate(z); }
                    z.parent.color = BLACK; z.parent.parent.color = RED; rightRotate(z.parent.parent);
                }
            }else{
                Node y = z.parent.parent.left;
                if (y.color == RED){
                    z.parent.color = BLACK; y.color = BLACK;
                    z.parent.parent.color = RED; z = z.parent.parent;
                }else{
                    if (z == z.parent.left){ z = z.parent; rightRotate(z); }
                    z.parent.color = BLACK; z.parent.parent.color = RED; leftRotate(z.parent.parent);
                }
            }
        }
        root.color = BLACK;
    }

    /* ========== 刪除 ========== */
    public boolean delete(Interval key){
        Node z = searchExact(key);
        if (z == null) return false;

        Node y = z, x; boolean yColor = y.color;
        if (z.left == NIL){
            x = z.right; transplant(z, z.right);
        }else if (z.right == NIL){
            x = z.left; transplant(z, z.left);
        }else{
            y = minimum(z.right); yColor = y.color; x = y.right;
            if (y.parent == z){
                x.parent = y;
            }else{
                transplant(y, y.right);
                y.right = z.right; y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left; y.left.parent = y;
            y.color = z.color;
            pull(y);
        }
        for (Node t = x; t != NIL; t = t.parent) pull(t);
        if (yColor == BLACK) deleteFixup(x);
        return true;
    }

    private void transplant(Node u, Node v){
        if (u.parent == NIL) root = v;
        else if (u == u.parent.left) u.parent.left = v;
        else u.parent.right = v;
        v.parent = u.parent;
        if (v != NIL) pull(v);
        if (u.parent != NIL) pull(u.parent);
    }

    private Node minimum(Node x){ while (x.left != NIL) x = x.left; return x; }

    private void deleteFixup(Node x){
        while (x != root && x.color == BLACK){
            if (x == x.parent.left){
                Node w = x.parent.right;
                if (w.color == RED){ w.color = BLACK; x.parent.color = RED; leftRotate(x.parent); w = x.parent.right; }
                if (w.left.color == BLACK && w.right.color == BLACK){ w.color = RED; x = x.parent; }
                else{
                    if (w.right.color == BLACK){ w.left.color = BLACK; w.color = RED; rightRotate(w); w = x.parent.right; }
                    w.color = x.parent.color; x.parent.color = BLACK; w.right.color = BLACK; leftRotate(x.parent); x = root;
                }
            }else{
                Node w = x.parent.left;
                if (w.color == RED){ w.color = BLACK; x.parent.color = RED; rightRotate(x.parent); w = x.parent.left; }
                if (w.left.color == BLACK && w.right.color == BLACK){ w.color = RED; x = x.parent; }
                else{
                    if (w.left.color == BLACK){ w.right.color = BLACK; w.color = RED; leftRotate(w); w = x.parent.left; }
                    w.color = x.parent.color; x.parent.color = BLACK; w.left.color = BLACK; rightRotate(x.parent); x = root;
                }
            }
        }
        x.color = BLACK;
    }

    /* ========== 查詢 ========== */
    private Node searchExact(Interval q){
        Node x = root;
        while (x != NIL){
            if (q.lo < x.iv.lo) x = x.left;
            else if (q.lo > x.iv.lo) x = x.right;
            else {
                if (q.hi == x.iv.hi) return x;
                x = (q.hi < x.iv.hi) ? x.left : x.right;
            }
        }
        return null;
    }

    private Interval searchAnyOverlap(Interval q){
        Node x = root;
        while (x != NIL && !x.iv.overlaps(q)){
            if (x.left != NIL && x.left.max >= q.lo) x = x.left;
            else x = x.right;
        }
        return (x == NIL) ? null : x.iv;
    }

    private void searchAllOverlap(Node x, Interval q, List<Interval> ans){
        if (x == NIL || x.max < q.lo) return;
        if (x.left != NIL) searchAllOverlap(x.left, q, ans);
        if (x.iv.overlaps(q)) ans.add(x.iv);
        if (x.right != NIL && x.iv.lo <= q.hi) searchAllOverlap(x.right, q, ans);
    }

    private void inorderPrint(Node x){
        if (x == NIL) return;
        inorderPrint(x.left);
        System.out.print(x.iv + "{max=" + x.max + "," + (x.color? "R":"B") + "} ");
        inorderPrint(x.right);
    }

    /* ========== Demo ========== */
    public static void main(String[] args){
        IntervalTreeExercise it = new IntervalTreeExercise();
        int[][] arr = {{15,20},{10,30},{17,19},{5,20},{12,15},{30,40}};
        for (int[] a : arr) it.insert(a[0], a[1]);
        it.inorderPrint();

        System.out.println("Any overlap [14,16] => " + it.searchAnyOverlap(14,16));
        System.out.println("All overlap [14,16] => " + it.searchAllOverlap(14,16));

        System.out.println("Delete [10,30] => " + it.delete(10,30));
        it.inorderPrint();
    }
}
