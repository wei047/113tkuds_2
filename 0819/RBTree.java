import java.util.*;
import java.util.Map.Entry;

/** 通用紅黑樹（含插入/刪除修復、驗證、三種迭代器） */
public class RBTree<K extends Comparable<K>, V> {

    public enum Color { RED, BLACK }

    public static final class Node<K, V> {
        public K key;
        public V value;
        public Color color;
        public Node<K,V> left, right, parent;

        Node(K key, V value, Color color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
        public boolean isRed()   { return color == Color.RED; }
        public boolean isBlack() { return color == Color.BLACK; }
        @Override public String toString() { return "(" + key + "," + value + "," + color + ")"; }
    }

    private final Node<K,V> NIL = new Node<>(null, null, Color.BLACK);
    private Node<K,V> root = NIL;

    public RBTree() { NIL.left = NIL.right = NIL.parent = NIL; }

    public Node<K,V> root() { return root; }
    public boolean isEmpty() { return root == NIL; }

    private void leftRotate(Node<K,V> x) {
        Node<K,V> y = x.right;
        x.right = y.left;
        if (y.left != NIL) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == NIL) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node<K,V> x) {
        Node<K,V> y = x.left;
        x.left = y.right;
        if (y.right != NIL) y.right.parent = x;
        y.parent = x.parent;
        if (x.parent == NIL) root = y;
        else if (x == x.parent.right) x.parent.right = y;
        else x.parent.left = y;
        y.right = x;
        x.parent = y;
    }

    private int cmp(K a, K b) { return a.compareTo(b); }

    public Node<K,V> search(K key) {
        Node<K,V> x = root;
        while (x != NIL) {
            int c = cmp(key, x.key);
            if (c == 0) return x;
            x = (c < 0) ? x.left : x.right;
        }
        return null;
    }

    private Node<K,V> minimum(Node<K,V> x) { while (x.left != NIL) x = x.left; return x; }

    private void transplant(Node<K,V> u, Node<K,V> v) {
        if (u.parent == NIL) root = v;
        else if (u == u.parent.left) u.parent.left = v;
        else u.parent.right = v;
        v.parent = u.parent;
    }

    /* ============ 插入 ============ */
    public boolean insert(K key, V value) {
        Node<K,V> z = new Node<>(key, value, Color.RED);
        z.left = z.right = z.parent = NIL;

        Node<K,V> y = NIL, x = root;
        while (x != NIL) {
            y = x;
            int c = cmp(key, x.key);
            if (c == 0) { x.value = value; return false; }
            x = (c < 0) ? x.left : x.right;
        }
        z.parent = y;
        if (y == NIL) root = z;
        else if (cmp(key, y.key) < 0) y.left = z;
        else y.right = z;

        insertFixup(z);
        return true;
    }

    private void insertFixup(Node<K,V> z) {
        while (z.parent.isRed()) {
            if (z.parent == z.parent.parent.left) {
                Node<K,V> y = z.parent.parent.right;
                if (y.isRed()) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) { z = z.parent; leftRotate(z); }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                Node<K,V> y = z.parent.parent.left;
                if (y.isRed()) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) { z = z.parent; rightRotate(z); }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
    }

    /* ============ 刪除 ============ */
    public boolean delete(K key) {
        Node<K,V> z = search(key);
        if (z == null) return false;

        Node<K,V> y = z;
        Color yOriginalColor = y.color;
        Node<K,V> x;

        if (z.left == NIL) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == NIL) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right; y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left; y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == Color.BLACK) deleteFixup(x);
        return true;
    }

    private void deleteFixup(Node<K,V> x) {
        while (x != root && x.isBlack()) {
            if (x == x.parent.left) {
                Node<K,V> w = x.parent.right;
                if (w.isRed()) {
                    w.color = Color.BLACK; x.parent.color = Color.RED; leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.isBlack() && w.right.isBlack()) {
                    w.color = Color.RED; x = x.parent;
                } else {
                    if (w.right.isBlack()) {
                        w.left.color = Color.BLACK; w.color = Color.RED; rightRotate(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color; x.parent.color = Color.BLACK; w.right.color = Color.BLACK;
                    leftRotate(x.parent); x = root;
                }
            } else {
                Node<K,V> w = x.parent.left;
                if (w.isRed()) {
                    w.color = Color.BLACK; x.parent.color = Color.RED; rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.left.isBlack() && w.right.isBlack()) {
                    w.color = Color.RED; x = x.parent;
                } else {
                    if (w.left.isBlack()) {
                        w.right.color = Color.BLACK; w.color = Color.RED; leftRotate(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color; x.parent.color = Color.BLACK; w.left.color = Color.BLACK;
                    rightRotate(x.parent); x = root;
                }
            }
        }
        x.color = Color.BLACK;
    }

    /* ============ 驗證 ============ */
    public static final class ValidationResult {
        public final boolean ok;
        public final int blackHeight;
        public final String message;
        ValidationResult(boolean ok, int blackHeight, String message) {
            this.ok = ok; this.blackHeight = blackHeight; this.message = message;
        }
        @Override public String toString() {
            return ok ? "OK (blackHeight=" + blackHeight + ")" : "INVALID: " + message;
        }
    }

    public ValidationResult validate() {
        if (root == NIL) return new ValidationResult(true, 1, "empty tree");
        if (root.isRed()) return new ValidationResult(false, -1, "Root must be BLACK");
        StringBuilder err = new StringBuilder();
        int h = dfsCheck(root, err);
        if (h < 0) return new ValidationResult(false, -1, err.toString());
        return new ValidationResult(true, h, "valid red-black tree");
    }

    private int dfsCheck(Node<K,V> n, StringBuilder err) {
        if (n == NIL) return 1;
        if (n.isRed() && (n.left.isRed() || n.right.isRed())) {
            err.append("Two consecutive RED nodes at key=").append(n.key); return -1;
        }
        int lh = dfsCheck(n.left, err); if (lh < 0) return -1;
        int rh = dfsCheck(n.right, err); if (rh < 0) return -1;
        if (lh != rh) {
            err.append("Black-height mismatch at key=").append(n.key)
               .append(" (left=").append(lh).append(", right=").append(rh).append(")");
            return -1;
        }
        return lh + (n.isBlack() ? 1 : 0);
    }

    /* ============ 迭代器 ============ */
    public Iterable<Entry<K,V>> inorder()      { return () -> new RBIterator(false, null, null, true, true); }
    public Iterable<Entry<K,V>> reverse()      { return () -> new RBIterator(true,  null, null, true, true); }
    public Iterable<Entry<K,V>> range(K lo, K hi, boolean incLo, boolean incHi) {
        return () -> new RBIterator(false, lo, hi, incLo, incHi);
    }

    private final class RBIterator implements Iterator<Entry<K,V>> {
        private final boolean reverse;
        private final K lo, hi; private final boolean incLo, incHi;
        private final Deque<Node<K,V>> stack = new ArrayDeque<>();
        private Node<K,V> cur = root; private Entry<K,V> nextEntry;

        RBIterator(boolean reverse, K lo, K hi, boolean incLo, boolean incHi) {
            this.reverse = reverse; this.lo = lo; this.hi = hi; this.incLo = incLo; this.incHi = incHi;
            advance();
        }
        private boolean inRange(K k) {
            if (lo != null) { int c = cmp(k, lo); if (c < 0 || (c == 0 && !incLo)) return false; }
            if (hi != null) { int c = cmp(k, hi); if (c > 0 || (c == 0 && !incHi)) return false; }
            return true;
        }
        private void push(Node<K,V> x) { while (x != NIL) { stack.push(x); x = reverse ? x.right : x.left; } }
        private void advance() {
            nextEntry = null;
            while (cur != NIL || !stack.isEmpty()) {
                if (cur != NIL) { push(cur); cur = NIL; }
                else {
                    Node<K,V> n = stack.pop();
                    cur = reverse ? n.left : n.right;
                    if (inRange(n.key)) {
                        nextEntry = new AbstractMap.SimpleImmutableEntry<>(n.key, n.value);
                        return;
                    }
                }
            }
        }
        @Override public boolean hasNext() { return nextEntry != null; }
        @Override public Entry<K,V> next() {
            if (nextEntry == null) throw new NoSuchElementException();
            Entry<K,V> ans = nextEntry; advance(); return ans;
        }
    }
}
