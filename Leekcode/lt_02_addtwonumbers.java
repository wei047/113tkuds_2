import java.io.*;

public class lt_02_addtwonumbers {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int v){ val = v; }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int a = (l1 != null) ? l1.val : 0;
            int b = (l2 != null) ? l2.val : 0;
            int sum = a + b + carry;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return dummy.next;
    }

    private static int[] parseArray(String s) {
        s = s.trim();
        if (s.startsWith("[") && s.endsWith("]")) s = s.substring(1, s.length() - 1);
        s = s.replace(",", " ").trim();
        if (s.isEmpty()) return new int[0];
        String[] parts = s.split("\\s+");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) arr[i] = Integer.parseInt(parts[i]);
        return arr;
    }

    private static ListNode fromArray(int[] a) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : a) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    private static String toBracketString(ListNode n) {
        StringBuilder sb = new StringBuilder("[");
        while (n != null) {
            sb.append(n.val);
            n = n.next;
            if (n != null) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String a, b;
        if (args.length >= 2) { a = args[0]; b = args[1]; }
        else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            a = br.readLine(); b = br.readLine();
            if (a == null || b == null || a.isBlank() || b.isBlank()) { a = "[2,4,3]"; b = "[5,6,4]"; }
        }
        int[] x = parseArray(a), y = parseArray(b);
        ListNode l1 = fromArray(x), l2 = fromArray(y);
        ListNode ans = addTwoNumbers(l1, l2);
        System.out.println(toBracketString(ans));
    }
}
