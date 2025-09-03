// 題目：Substring with Concatenation of All Words
// 在 s 中找所有起點，使得連續子字串由 words 中所有單字恰好各一次拼接而成。
import java.util.*;
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s.isEmpty() || words.length == 0) return ans;
        int m = words[0].length(), k = words.length, window = m * k;
        if (s.length() < window) return ans;

        Map<String,Integer> need = new HashMap<>();
        for (String w : words) need.put(w, need.getOrDefault(w, 0) + 1); // 需求頻次

        for (int offset = 0; offset < m; offset++) {                      // 對齊每個偏移
            int left = offset, count = 0;
            Map<String,Integer> have = new HashMap<>();
            for (int right = offset; right + m <= s.length(); right += m) {
                String w = s.substring(right, right + m);                 // 取一個詞
                if (need.containsKey(w)) {
                    have.put(w, have.getOrDefault(w, 0) + 1);
                    count++;
                    while (have.get(w) > need.get(w)) {                   // 收縮（多了）
                        String lw = s.substring(left, left + m);
                        have.put(lw, have.get(lw) - 1);
                        left += m; count--;
                    }
                    if (count == k) {                                     // 命中
                        ans.add(left);
                        String lw = s.substring(left, left + m);          // 左移一格繼續找
                        have.put(lw, have.get(lw) - 1);
                        left += m; count--;
                    }
                } else {                                                  // 非目標詞，重置視窗
                    have.clear(); count = 0; left = right + m;
                }
            }
        }
        return ans;
    }
}
/* 解題思路：固定單字長度 m，以 m 個偏移做滑窗；維護詞頻 have 與 need，長度達到 k*m 且頻次吻合即記錄。時間 O(n)。 */
