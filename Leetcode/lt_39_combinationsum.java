import java.util.*;
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);                         // 方便剪枝
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }
    private void dfs(int[] a, int idx, int remain, List<Integer> path, List<List<Integer>> res){
        if (remain == 0) { res.add(new ArrayList<>(path)); return; }   // 命中
        for (int i = idx; i < a.length && a[i] <= remain; i++) {        // 可重複取：i 不 +1
            path.add(a[i]);
            dfs(a, i, remain - a[i], path, res);                        // 繼續取當前
            path.remove(path.size()-1);
        }
    }
}
/* 解題思路：回溯；排序後針對剩餘值剪枝，允許重複取同元素（索引不前進）。 */
