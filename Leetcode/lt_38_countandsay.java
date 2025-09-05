class Solution {
    public String countAndSay(int n) {
        String s = "1";
        for (int t = 2; t <= n; t++) {
            StringBuilder nxt = new StringBuilder();
            for (int i = 0; i < s.length(); ) {
                int j = i;
                while (j < s.length() && s.charAt(j) == s.charAt(i)) j++; // 一段連續
                nxt.append(j - i).append(s.charAt(i));                    // 次數 + 字元
                i = j;
            }
            s = nxt.toString();
        }
        return s;
    }
}
/* 解題思路：從 "1" 迭代 n-1 次，每次把連續相同字元壓成「次數+字元」。時間 O(總長度)。 */
