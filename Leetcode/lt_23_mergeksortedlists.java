// 題目：Merge k Sorted Lists
// 合併 k 個升序鏈結串列。
import java.util.*;
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((x,y)->x.val-y.val); // 小根堆
        for (ListNode node : lists) if (node != null) pq.offer(node);
        ListNode dummy = new ListNode(0), cur = dummy;
        while (!pq.isEmpty()) {
            ListNode t = pq.poll();                       // 取最小
            cur.next = t; cur = cur.next;
            if (t.next != null) pq.offer(t.next);         // 下一個入堆
        }
        return dummy.next;
    }
}
/* 解題思路：優先佇列每次彈出最小節點。時間 O(N log k)，空間 O(k)。 */
