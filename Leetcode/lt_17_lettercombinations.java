// 題目：Letter Combinations of a Phone Number
// 給一串 2–9 的數字，回傳所有字母組合。
import java.util.*;
class Solution {
    private static final String[] MAP = {
        "", "", "abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"
    };
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return ans;
        backtrack(digits, 0, new StringBuilder(), ans);
        return ans;
    }
    private void backtrack(String d, int i, StringBuilder path, List<String> ans){
        if (i == d.length()) { ans.add(path.toString()); return; }   // 收集
        String letters = MAP[d.charAt(i)-'0'];                        // 取對應字母
        for (char c : letters.toCharArray()){
            path.append(c);                                           // 選
            backtrack(d, i+1, path, ans);                             // 走
            path.deleteCharAt(path.length()-1);                       // 撤
        }
    }
}
/* 解題思路：回溯，每位數字展開其對應字母。時間 O(乘積字母數)，空間 O(深度)。 */
