// 題目：Merge Two Sorted Lists
// 合併兩個升序鏈結串列。
class Solution {
    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0), cur = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) { cur.next = a; a = a.next; }        // 小者先接
            else { cur.next = b; b = b.next; }
            cur = cur.next;
        }
        cur.next = (a != null) ? a : b;                              // 掛上剩餘
        return dummy.next;
    }
}
/* 解題思路：雙指針線性合併。時間 O(m+n)，空間 O(1)。 */
