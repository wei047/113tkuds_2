class Solution {
    public String intToRoman(int num) {
        int[] val  = {1000,900,500,400,100,90,50,40,10,9,5,4,1};      // 面額（含減法記號）
        String[] sym={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"}; // 對應符號
        StringBuilder sb = new StringBuilder();                        // 組字結果
        for (int i = 0; i < val.length; i++) {                         // 由大到小貪婪
            while (num >= val[i]) {                                    // 能扣就扣
                num -= val[i];                                         // 扣面額
                sb.append(sym[i]);                                     // 拼符號
            }
        }
        return sb.toString();                                          // 回傳字串
    }
}
/*
解題思路：
1. 列出固定 13 個面額與符號（含 900/400/90/40/9/4），由大到小貪婪兌換。
2. 每次能減就減並附上符號，直到數字為 0。
3. 時間複雜度 O(1)（常數面額），空間複雜度 O(1)。
*/