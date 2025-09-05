import java.util.*;
class Solution {
    public int longestValidParentheses(String s) {
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1);                                    // 基準索引
        int best = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') st.push(i);         // 左括號：壓入索引
            else {
                st.pop();                                // 嘗試匹配
                if (st.isEmpty()) st.push(i);            // 當前作為新基準
                else best = Math.max(best, i - st.peek()); // 有效長度
            }
        }
        return best;
    }
}
/* 解題思路：用棧存放「未匹配左括號索引」與一個基準 -1；遇到右括號彈出，若空則重設基準，否則更新長度。時間 O(n)。 */
