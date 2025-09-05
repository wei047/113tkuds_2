class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (nums[m] >= target) r = m;               // 往左找第一個 >= target
            else l = m + 1;
        }
        return l;
    }
}
/* 解題思路：典型 lower_bound。時間 O(log n)。 */
