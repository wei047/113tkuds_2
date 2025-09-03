// 題目：Implement strStr()
// 回傳子字串 needle 在 haystack 的第一個索引，無則 -1（KMP）。
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        int m = needle.length();
        int[] lps = buildLPS(needle);                    // 失配表
        int i = 0, j = 0;
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) { i++; j++;  // 匹配前進
                if (j == m) return i - m;                             // 命中
            } else if (j > 0) j = lps[j-1];                           // 依 lps 回退
              else i++;
        }
        return -1;
    }
    private int[] buildLPS(String p){
        int n = p.length(), j = 0; int[] lps = new int[n];
        for (int i = 1; i < n; i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j)) j = lps[j-1];
            if (p.charAt(i) == p.charAt(j)) j++;
            lps[i] = j;
        }
        return lps;
    }
}
/* 解題思路：KMP 將失配回退到最長前後綴；時間 O(n+m)，空間 O(m)。 */
