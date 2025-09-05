class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[] row = new int[9], col = new int[9], box = new int[9]; // 位元遮罩
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                int bit = 1 << (c - '1');
                int b = (i/3)*3 + j/3;                               // 3x3 區編號
                if ((row[i]&bit)!=0 || (col[j]&bit)!=0 || (box[b]&bit)!=0) return false;
                row[i]|=bit; col[j]|=bit; box[b]|=bit;               // 記錄出現
            }
        }
        return true;
    }
}
/* 解題思路：用位元遮罩記錄行/列/九宮格已出現的數字，重覆則非法。時間 O(81)。 */
