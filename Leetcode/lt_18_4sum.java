// 題目：4Sum
// 找出所有和為 target 的不重複四元組。
import java.util.*;
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;             // 去重
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j-1]) continue;     // 去重
                int l = j + 1, r = n - 1;
                long need = (long)target - nums[i] - nums[j];        // 防溢位
                while (l < r) {
                    long sum = nums[l] + nums[r];
                    if (sum == need) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        int a = nums[l], b = nums[r];
                        while (l < r && nums[l] == a) l++;            // 去重
                        while (l < r && nums[r] == b) r--;
                    } else if (sum < need) l++; else r--;
                }
            }
        }
        return res;
    }
}
/* 解題思路：排序後固定 i、j，內層雙指針找兩數和，注意去重與 long 防溢。時間 O(n^3)。 */
