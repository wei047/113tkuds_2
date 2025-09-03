class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";              // 邊界處理
        String pre = strs[0];                         // 當前前綴
        for (int i = 1; i < strs.length; i++) {       // 逐一比對
            while (!strs[i].startsWith(pre)) {        // 不是前綴就縮短
                pre = pre.substring(0, pre.length() - 1);
                if (pre.isEmpty()) return "";         // 縮到空字串即結束
            }
        }
        return pre;                                   // 回傳共同前綴
    }
}
/*
解題思路：
1. 水平掃描：以第一個字串為初始前綴，依序與後續字串比對。
2. 若不匹配則將前綴逐字縮短直到匹配或為空。
3. 時間複雜度 O(總字元數)，空間複雜度 O(1)。
*/