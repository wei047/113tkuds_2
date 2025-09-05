class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = lowerBound(nums, target);            // 第一個 >= target
        if (left == nums.length || nums[left] != target) return new int[]{-1,-1};
        int right = upperBound(nums, target) - 1;       // 第一個 > target 再 -1
        return new int[]{left, right};
    }
    private int lowerBound(int[] a, int x){
        int l=0, r=a.length;
        while(l<r){ int m=(l+r)>>>1; if(a[m]>=x) r=m; else l=m+1; } return l;
    }
    private int upperBound(int[] a, int x){
        int l=0, r=a.length;
        while(l<r){ int m=(l+r)>>>1; if(a[m]>x) r=m; else l=m+1; } return l;
    }
}
/* 解題思路：兩次二分：lower_bound 與 upper_bound。時間 O(log n)。 */
