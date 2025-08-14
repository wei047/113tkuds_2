import java.util.*;

/** 練習 8：把 RBTree 用 ASCII 輸出（顏色 R/B、層級縮排）。 */
public class TreeVisualizerExercise {

    public static <K extends Comparable<K>, V> void printTree(RBTree<K,V> t){
        RBTree.Node<K,V> r = t.root();
        if (r == null) { System.out.println("(empty)"); return; }
        // BFS 分層列印；忽略 NIL 子節點
        Queue<RBTree.Node<K,V>> q = new ArrayDeque<>();
        Queue<Integer> lvl = new ArrayDeque<>();
        q.add(r); lvl.add(0);
        int cur = -1;
        while (!q.isEmpty()){
            RBTree.Node<K,V> n = q.poll();
            int L = lvl.poll();
            if (L != cur){ cur = L; System.out.println(); System.out.print("L"+L+": "); }
            System.out.print(format(n) + "  ");
            // 左右孩子若不是 NIL 就加入。以是否有 key 判斷 NIL（我們的 NIL.key==null）
            if (n.left!=null && n.left.key!=null) { q.add(n.left); lvl.add(L+1); }
            if (n.right!=null && n.right.key!=null){ q.add(n.right); lvl.add(L+1); }
        }
        System.out.println();
    }

    private static <K,V> String format(RBTree.Node<K,V> n){
        String c = n.isRed()? "R":"B";
        return n.key + "(" + c + ")";
    }

    /* ===== Demo ===== */
    public static void main(String[] args){
        RBTree<Integer,Integer> t = new RBTree<>();
        int[] a = {41,38,31,12,19,8,50,60,55,65,45};
        for (int x : a) t.insert(x, x);
        System.out.println("validate: " + t.validate());
        printTree(t);

        t.delete(31); t.delete(38);
        System.out.println("\nAfter deletes validate: " + t.validate());
        printTree(t);
    }
}
