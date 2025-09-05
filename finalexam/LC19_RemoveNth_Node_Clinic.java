import java.io.*;

public class LC19_RemoveNth_Node_Clinic {
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
        ListNode head = null, tail = null;
        for (int i = 0; i < n; i++) {
            int v = fs.nextInt();
            ListNode node = new ListNode(v);
            if (head == null) { head = tail = node; } else { tail.next = node; tail = node; }
        }
        int k = fs.nextInt();

        ListNode dummy = new ListNode(0); dummy.next = head;
        ListNode fast = head, slow = dummy;
        for (int i = 0; i < k; i++) fast = fast.next;
        while (fast != null) { fast = fast.next; slow = slow.next; }
        slow.next = slow.next.next;

        StringBuilder sb = new StringBuilder();
        for (ListNode p = dummy.next; p != null; p = p.next) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(p.val);
        }
        if (sb.length() > 0) System.out.println(sb.toString());
    }
}
// 時間複雜度：O(n)
