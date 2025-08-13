import java.util.*;

public class StockMaximizer {

    public static int maxProfitKTransactions(int[] prices, int K) {
        int n = prices.length;
        if (n == 0 || K == 0) return 0;
        if (K >= n / 2) {
            int p = 0;
            for (int i = 1; i < n; i++) if (prices[i] > prices[i-1]) p += prices[i] - prices[i-1];
            return p;
        }
        int[] buy = new int[K + 1];
        int[] sell = new int[K + 1];
        Arrays.fill(buy, Integer.MIN_VALUE / 4);
        buy[0] = -prices[0];
        for (int i = 1; i < n; i++) {
            buy[0] = Math.max(buy[0], -prices[i]);
            for (int k = 1; k <= K; k++) {
                buy[k]  = Math.max(buy[k],  sell[k]  - prices[i]);
                sell[k] = Math.max(sell[k], buy[k-1] + prices[i]);
            }
        }
        return sell[K];
    }

    public static void main(String[] args) {
        System.out.println(maxProfitKTransactions(new int[]{2,4,1}, 2)); // 2
        System.out.println(maxProfitKTransactions(new int[]{3,2,6,5,0,3}, 2)); // 7
        System.out.println(maxProfitKTransactions(new int[]{1,2,3,4,5}, 2)); // 4
    }
}
