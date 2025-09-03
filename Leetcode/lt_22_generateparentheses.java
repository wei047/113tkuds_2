// 題目：Generate Parentheses
// 產生 n 對括號的所有合法組合。
import java.util.*;
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(n, 0, 0, new StringBuilder(), ans);
        return ans;
    }
    private void dfs(int n, int open, int close, StringBuilder sb, List<String> ans){
        if (sb.length() == 2*n) { ans.add(sb.toString()); return; }  // 生成一組
        if (open < n) { sb.append('('); dfs(n, open+1, close, sb, ans); sb.deleteCharAt(sb.length()-1); }
        if (close < open) { sb.append(')'); dfs(n, open, close+1, sb, ans); sb.deleteCharAt(sb.length()-1); }
    }
}
/* 解題思路：回溯；約束是 close 不能超過 open，open 不超過 n。時間 ~ O(Catalan(n))。 */
