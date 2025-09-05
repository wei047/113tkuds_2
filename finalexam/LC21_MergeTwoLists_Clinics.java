import java.io.*;

public class LC21_MergeTwoLists_Clinics {
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { this.in = is; }
        private int read() throws IOException { if (ptr >= len) { len = in.read(buffer); ptr = 0; if (len <= 0) return -1; } return buffer[ptr++]; }
        String next() throws IOException { StringBuilder sb = new StringBuilder(); int c; while ((c = read()) != -1 && c <= ' ') {} if (c == -1) return null; do { sb.append((char)c); c = read(); } while (c != -1 && c > ' '); return sb.toString(); }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
    static class ListNode { int val; ListNode next; ListNode(int v){ val = v; } }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        String t = fs.next();
        if (t == null) return;
        int n = Integer.parseInt(t);
        int m = fs.nextInt();
        ListNode a = null, ta = null, b = null, tb = null;
        for (int i = 0; i < n; i++) { int v = fs.nextInt(); ListNode node = new ListNode(v); if (a == null) { a = ta = node; } else { ta.next = node; ta = node; } }
        for (int i = 0; i < m; i++) { int v = fs.nextInt(); ListNode node = new ListNode(v); if (b == null) { b = tb = node; } else { tb.next = node; tb = node; } }

        ListNode dummy = new ListNode(0), cur = dummy, p = a, q = b;
        while (p != null && q != null) {
            if (p.val <= q.val) { cur.next = p; p = p.next; }
            else { cur.next = q; q = q.next; }
            cur = cur.next;
        }
        cur.next = (p != null) ? p : q;

        StringBuilder sb = new StringBuilder();
        for (ListNode x = dummy.next; x != null; x = x.next) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(x.val);
        }
        System.out.println(sb.toString());
    }
}
// 時間複雜度：O(n+m)
