// 題目：Remove Element
// 原地移除等於 val 的元素，回傳新長度（順序可保持）。
class Solution {
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int x : nums) if (x != val) nums[slow++] = x;  // 過濾保留
        return slow;
    }
}
/* 解題思路：線性掃描把非 val 覆寫到前面。時間 O(n)，空間 O(1)。 */
