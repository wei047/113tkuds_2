// 題目：Valid Parentheses
// 判斷括號字串是否有效。
import java.util.*;
class Solution {
    public boolean isValid(String s) {
        Deque<Character> st = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c=='('||c=='{'||c=='[') st.push(c);      // 左括號入棧
            else {
                if (st.isEmpty()) return false;          // 無配對
                char t = st.pop();
                if (!((t=='('&&c==')')||(t=='['&&c==']')||(t=='{'&&c=='}'))) return false;
            }
        }
        return st.isEmpty();                              // 棧空才有效
    }
}
/* 解題思路：堆疊配對括號。時間 O(n)，空間 O(n)。 */
