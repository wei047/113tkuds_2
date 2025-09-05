import java.io.*;
import java.util.*;

public class LC25_ReverseKGroup_Shifts {
    static class ListNode { long val; ListNode next; ListNode(long v){ val = v; } }

    static ListNode reverse(ListNode head){
        ListNode prev = null, cur = head;
        while (cur != null){
            ListNode nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        return prev;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        String l1 = br.readLine();
        if (l1 == null) return;
        int k = Integer.parseInt(l1.trim());
        String l2 = br.readLine();
        ArrayList<Long> vals = new ArrayList<>();
        if (l2 != null) {
            StringTokenizer st = new StringTokenizer(l2);
            while (st.hasMoreTokens()) vals.add(Long.parseLong(st.nextToken()));
        }
        ListNode head = null, tail = null;
        for (long v : vals) {
            ListNode node = new ListNode(v);
            if (head == null) head = tail = node; else { tail.next = node; tail = node; }
        }
        if (k <= 1 || head == null) {
            StringBuilder sb = new StringBuilder();
            for (ListNode p = head; p != null; p = p.next) { if (sb.length() > 0) sb.append(' '); sb.append(p.val); }
            if (sb.length() > 0) System.out.println(sb.toString());
            return;
        }
        ListNode dummy = new ListNode(0); dummy.next = head;
        ListNode pre = dummy, end = dummy;
        while (true) {
            for (int i = 0; i < k && end != null; i++) end = end.next;
            if (end == null) break;
            ListNode start = pre.next, next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        StringBuilder sb = new StringBuilder();
        for (ListNode p = dummy.next; p != null; p = p.next) { if (sb.length() > 0) sb.append(' '); sb.append(p.val); }
        if (sb.length() > 0) System.out.println(sb.toString());
    }
}
// 時間複雜度：O(n)
