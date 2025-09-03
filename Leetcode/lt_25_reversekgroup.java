// 題目：Reverse Nodes in k-Group
// 每 k 個節點一組反轉，不足 k 保持不變。
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head), pre = dummy;
        while (true) {
            ListNode end = pre;
            for (int i = 0; i < k && end != null; i++) end = end.next; // 找到一組尾
            if (end == null) break;
            ListNode start = pre.next, nxt = end.next;
            end.next = null;                           // 斷開，單獨反轉
            pre.next = reverse(start);                 // 反轉後接回
            start.next = nxt;
            pre = start;                               // 移到下一組前驅
        }
        return dummy.next;
    }
    private ListNode reverse(ListNode head){           // 迭代反轉
        ListNode prev = null, cur = head;
        while (cur != null) { ListNode t = cur.next; cur.next = prev; prev = cur; cur = t; }
        return prev;
    }
}
/* 解題思路：每次截出長度 k 的子串列反轉後接回；不足 k 則停止。時間 O(n)，空間 O(1)。 */
