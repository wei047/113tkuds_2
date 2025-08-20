import java.io.*;
import java.util.*;

public class M08_BSTRangedSum {

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
        int L = fs.nextInt();
        int R = fs.nextInt();

        Node root = buildFromLevelArray(arr);
        long sum = rangedSum(root, L, R);
        System.out.println("Sum: " + sum);
    }

    private static Node buildFromLevelArray(int[] a) {
        int n = a.length;
        if (n == 0 || a[0] == -1) return null;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) if (a[i] != -1) nodes[i] = new Node(a[i]);
        for (int i = 0; i < n; i++) {
            if (nodes[i] == null) continue;
            int li = 2 * i + 1, ri = 2 * i + 2;
            if (li < n) nodes[i].left = nodes[li];
            if (ri < n) nodes[i].right = nodes[ri];
        }
        return nodes[0];
    }

    private static long rangedSum(Node root, int L, int R) {
        if (root == null) return 0L;
        long sum = 0L;
        ArrayDeque<Node> st = new ArrayDeque<>();
        st.push(root);
        while (!st.isEmpty()) {
            Node cur = st.pop();
            if (cur.val < L) {
                if (cur.right != null) st.push(cur.right);   
            } else if (cur.val > R) {
                if (cur.left != null) st.push(cur.left);     
            } else {
                sum += cur.val;                               
                if (cur.right != null) st.push(cur.right);
                if (cur.left  != null) st.push(cur.left);
            }
        }
        return sum;
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

