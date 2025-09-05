import java.util.*;
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }
    private void dfs(int[] a, int start, int remain, List<Integer> path, List<List<Integer>> res){
        if (remain == 0) { res.add(new ArrayList<>(path)); return; }
        for (int i = start; i < a.length; i++) {
            if (i > start && a[i] == a[i-1]) continue;   // 同層去重
            if (a[i] > remain) break;                    // 剪枝
            path.add(a[i]);
            dfs(a, i + 1, remain - a[i], path, res);     // 只能用一次 → i+1
            path.remove(path.size() - 1);
        }
    }
}
/* 解題思路：回溯 + 排序 + 同層去重；因每數僅一次，遞迴下一層從 i+1 開始。 */
