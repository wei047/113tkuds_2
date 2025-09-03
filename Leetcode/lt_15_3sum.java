// 題目：3Sum
// 找出所有和為 0 的「不重複」三元組。
import java.util.*;
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);                            // 先排序便於去重與雙指針
        List<List<Integer>> res = new ArrayList<>();  // 結果集
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])      // 固定數值去重
                continue;
            if (nums[i] > 0) break;                   // 之後皆 >0 無法湊 0
            int l = i + 1, r = n - 1;                 // 雙指針尋找補數
            int target = -nums[i];
            while (l < r) {
                int sum = nums[l] + nums[r];          // 檢查兩數之和
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r])); // 收集三元組
                    int a = nums[l], b = nums[r];     // 去重：跳過相同值
                    while (l < r && nums[l] == a) l++;
                    while (l < r && nums[r] == b) r--;
                } else if (sum < target) l++;         // 太小 → 左指針右移
                else r--;                              // 太大 → 右指針左移
            }
        }
        return res;                                   // 回傳所有不重複解
    }
}
/*
解題思路：
1. 排序後，固定索引 i，對區間 (i+1..n-1) 用雙指針尋找和為 -nums[i] 的兩數。
2. 透過排序與跳過重複元素（i、l、r）確保不重複三元組。
3. 時間複雜度 O(n^2)，空間複雜度 O(1)（不計輸出）。
*/
