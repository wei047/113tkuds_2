import java.io.*;

public class M09_AVLValidate {

    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = fs.nextInt();   

        Node root = buildFromLevelArray(arr);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
            return;
        }
        if (heightOrFail(root) == BAD) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }

    private static Node buildFromLevelArray(int[] a) {
        int n = a.length;
        if (n == 0 || a[0] == -1) return null;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) if (a[i] != -1) nodes[i] = new Node(a[i]);
        for (int i = 0; i < n; i++) {
            if (nodes[i] == null) continue;
            int li = 2 * i + 1, ri = 2 * i + 2;
            if (li < n) nodes[i].left  = nodes[li];
            if (ri < n) nodes[i].right = nodes[ri];
        }
        return nodes[0];
    }

    private static boolean isBST(Node cur, long lo, long hi) {
        if (cur == null) return true;
        if (!(lo < cur.val && cur.val < hi)) return false;
        return isBST(cur.left, lo, cur.val) && isBST(cur.right, cur.val, hi);
    }

    private static final int BAD = Integer.MIN_VALUE / 2;

    private static int heightOrFail(Node cur) {
        if (cur == null) return -1;                
        int lh = heightOrFail(cur.left);
        if (lh == BAD) return BAD;
        int rh = heightOrFail(cur.right);
        if (rh == BAD) return BAD;
        if (Math.abs(lh - rh) > 1) return BAD;     
        return Math.max(lh, rh) + 1;
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { in = is; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do { c = read(); } while (c <= ' ');
            if (c == '-') { sign = -1; c = read(); }
            while (c > ' ') { val = val * 10 + (c - '0'); c = read(); }
            return val * sign;
        }
    }
}

/*
計算複雜度註解
- 建樹：掃一次陣列並連結左右子，時間 O(n)，空間 O(n)。
- 驗證 BST：每節點恰訪一次，時間 O(n)，遞迴深度 O(h)。
- 驗證 AVL：後序一次遍歷，時間 O(n)，遞迴深度 O(h)。
- 總時間複雜度：O(n)；總空間複雜度：O(n)（含節點儲存與遞迴堆疊）。
- 備註：BST 驗證採嚴格不重複（min < val < max）。空樹視為 Valid。
*/
