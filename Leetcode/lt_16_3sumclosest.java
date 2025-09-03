// 題目：3Sum Closest
// 在陣列中找三數，使其和最接近 target，回傳該和。
import java.util.*;
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);                               // 先排序
        int n = nums.length, best = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < n - 2; i++) {
            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (Math.abs(sum - target) < Math.abs(best - target)) best = sum; // 更接近就更新
                if (sum == target) return sum;            // 已最接近
                if (sum < target) l++; else r--;          // 雙指針移動
            }
        }
        return best;
    }
}
/* 解題思路：排序 + 雙指針，固定一數後在右側找最接近的兩數。時間 O(n^2)，空間 O(1)。 */
