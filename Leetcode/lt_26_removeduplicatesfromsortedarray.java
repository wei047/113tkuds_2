// 題目：Remove Duplicates from Sorted Array
// 原地移除重複，回傳新長度（保留相對順序）。
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int slow = 1;                                    // 下一個要寫入的位置
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[fast-1]) nums[slow++] = nums[fast];
        }
        return slow;                                     // 新長度
    }
}
/* 解題思路：排序已保證相等相鄰；快慢指標覆寫。時間 O(n)，空間 O(1)。 */
