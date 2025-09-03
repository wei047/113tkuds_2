import java.util.*;
class Solution {
    public int romanToInt(String s) {
        Map<Character,Integer> m = new HashMap<>();    // 符號對應表
        m.put('I',1); m.put('V',5); m.put('X',10); m.put('L',50);
        m.put('C',100); m.put('D',500); m.put('M',1000);
        int ans = 0;                                   // 累計結果
        for (int i = 0; i < s.length(); i++) {
            int v = m.get(s.charAt(i));               // 當前值
            if (i + 1 < s.length() &&                 // 若左小右大 → 減法記號
                v < m.get(s.charAt(i + 1))) ans -= v; // 減去
            else ans += v;                            // 否則加上
        }
        return ans;                                   // 回傳整數
    }
}
/*
解題思路：
1. 由左到右掃描：若當前值 < 右側值，採減法記號；否則加總。
2. 僅一次線性掃描即可完成轉換。
3. 時間複雜度 O(n)，空間複雜度 O(1)。
*/