import java.io.*;

public class M04_TieredTaxSimple {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(nextNonEmpty(br));
        long[] incomes = new long[n];
        for (int i = 0; i < n; i++) incomes[i] = Long.parseLong(nextNonEmpty(br));

        long sumTax = 0;
        StringBuilder out = new StringBuilder();
        for (long x : incomes) {
            long tax = calcTax(x);
            sumTax += tax;
            out.append("Tax: ").append(tax).append('\n');
        }
        long avg = Math.round(sumTax * 1.0 / n);  
        out.append("Average: ").append(avg).append('\n');
        System.out.print(out.toString());
    }

    private static long calcTax(long x) {
        final long[] cap = {120_000L, 500_000L, 1_000_000L};
        final int[] rate = {5, 12, 20, 30}; 
        long tax = 0, prev = 0;

        for (int i = 0; i < cap.length; i++) {
            if (x <= prev) break;
            long use = Math.min(x, cap[i]) - prev;           
            tax += use * rate[i] / 100;                      
            prev = cap[i];
        }
        if (x > prev) tax += (x - prev) * rate[3] / 100;     
        return tax;
    }

    private static String nextNonEmpty(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null) {
            s = s.trim();
            if (!s.isEmpty()) return s;
        }
        return null;
    }
}

/*
計算複雜度註解
- 對每一筆收入僅做常數級距的運算，時間複雜度 O(n)。
- 僅使用少量變數，空間複雜度 O(1)。
- 穩定性／四捨五入說明：
  每段稅額以整數除法計算（向下取整），最後平均以 Math.round 四捨五入至整數。
*/