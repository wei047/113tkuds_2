class Solution {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;              // 兩指針：左右端點
        int ans = 0;                                   // 目前最大面積
        while (l < r) {                                // 夾逼直到相遇
            int area = Math.min(height[l], height[r])  // 面積 = 較短邊
                       * (r - l);                      //          × 寬度
            if (area > ans) ans = area;                // 更新最大值
            if (height[l] < height[r]) l++;            // 移動較短邊，才可能變大
            else r--;                                  // 否則移動右邊
        }
        return ans;                                    // 回傳答案
    }
}
/*
解題思路：
1. 兩指針從兩端往中間夾逼，面積 = min(左右高度) × 寬度。
2. 每次移動較短邊，因為移動較長邊不會增加最小高度。
3. 時間複雜度 O(n)，空間複雜度 O(1)。
*/