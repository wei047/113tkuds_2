// 題目：Remove Nth Node From End of List
// 刪除倒數第 n 個節點並回傳頭節點。
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);          // 假頭
        ListNode fast = dummy, slow = dummy;
        for (int i = 0; i < n; i++) fast = fast.next;    // 先走 n 步
        while (fast.next != null) {                      // 拉開距離
            fast = fast.next; slow = slow.next;
        }
        slow.next = slow.next.next;                      // 刪除
        return dummy.next;
    }
}
/* 解題思路：雙指針 + 假頭，fast 先走 n 步後同步前進，slow 停在待刪前一格。時間 O(L)。 */
