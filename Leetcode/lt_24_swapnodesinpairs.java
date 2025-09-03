// 題目：Swap Nodes in Pairs
// 兩兩交換鏈結串列節點（值不可改）。
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head), p = dummy;
        while (p.next != null && p.next.next != null) {
            ListNode a = p.next, b = a.next;              // p -> a -> b -> ...
            a.next = b.next; b.next = a; p.next = b;      // 交換 a、b
            p = a;                                        // 前進兩步
        }
        return dummy.next;
    }
}
/* 解題思路：指標重接；每次處理兩個。時間 O(n)，空間 O(1)。 */
