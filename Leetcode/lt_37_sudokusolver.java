class Solution {
    int[] row = new int[9], col = new int[9], box = new int[9];
    char[][] b;
    public void solveSudoku(char[][] board) {
        b = board;
        // 預處理已有數字
        for(int i=0;i<9;i++) for(int j=0;j<9;j++)
            if(b[i][j]!='.') mark(i,j,b[i][j]-'1', true);
        dfs(0,0);
    }
    private boolean dfs(int i, int j){
        if (i == 9) return true;                        // 填完
        if (b[i][j] != '.') return dfs(j==8?i+1:i, (j+1)%9);
        int bi = (i/3)*3 + j/3;
        int mask = ~(row[i] | col[j] | box[bi]) & 0x1FF; // 可用位（1..9）
        while (mask != 0) {
            int pick = mask & -mask;                    // 取最低位
            int d = Integer.numberOfTrailingZeros(pick); // 0..8
            b[i][j] = (char)('1'+d);
            mark(i,j,d,true);
            if (dfs(j==8?i+1:i, (j+1)%9)) return true;  // 成功即回傳
            mark(i,j,d,false);
            b[i][j] = '.';
            mask ^= pick;                                // 嘗試下一位
        }
        return false;                                   // 無解回溯
    }
    private void mark(int i,int j,int d,boolean add){
        int bit = 1<<d, bi=(i/3)*3+j/3;
        if(add){ row[i]|=bit; col[j]|=bit; box[bi]|=bit; }
        else   { row[i]^=bit; col[j]^=bit; box[bi]^=bit; }
    }
}
/* 解題思路：回溯 + 位元遮罩剪枝。先建三類約束（行/列/宮），對空格用可用數字位集合試填；失敗回溯。 */
