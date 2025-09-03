// 題目：Divide Two Integers
// 不使用乘除與取模，回傳商（截斷 toward zero）。
class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE; // 溢位鉗制
        long a = Math.abs((long) dividend), b = Math.abs((long) divisor);              // 轉 long
        int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;                          // 決定正負
        int ans = 0;
        for (int i = 31; i >= 0; i--) {                                               // 位移減法
            if ((a >> i) >= b) { a -= (b << i); ans += (1 << i); }
        }
        return sign > 0 ? ans : -ans;
    }
}
/* 解題思路：用位移找最大 2^i*b ≤ a，累加商並減去；處理邊界溢位。時間 O(32)，空間 O(1)。 */
