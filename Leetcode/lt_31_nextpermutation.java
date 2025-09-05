class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;   // 從右找第一個下降位
        if (i >= 0) {
            int j = n - 1;
            while (nums[j] <= nums[i]) j--;             // 右側找比 nums[i] 大的最小者
            swap(nums, i, j);                           // 交換
        }
        reverse(nums, i + 1, n - 1);                    // 反轉後綴為最小
    }
    private void swap(int[] a, int i, int j){ int t=a[i]; a[i]=a[j]; a[j]=t; }
    private void reverse(int[] a, int l, int r){ while(l<r) swap(a,l++,r--); }
}
/* 解題思路：標準三步：找下降位 i → 右側找最小更大元素 j 並交換 → 反轉 i 之後使後綴最小。時間 O(n)。 */
