import java.io.*;

public class M05_GCD_LCM_Recursive {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        long a = fs.nextLong();
        long b = fs.nextLong();

        long g = gcd(a, b);            
        long l = (a / g) * b;          

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }

    private static long gcd(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) { this.in = is; }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            do { c = read(); } while (c <= ' '); 
            boolean neg = false;
            if (c == '-') { neg = true; c = read(); }
            long val = 0;
            while (c > ' ') { val = val * 10 + (c - '0'); c = read(); }
            return neg ? -val : val;
        }
    }
}

/*
計算複雜度註解
- GCD（遞迴歐幾里得）：時間複雜度 O(log min(a, b))，遞迴深度同階。
- LCM 計算：O(1)；用 a/g*b 先除後乘避免乘法溢位（a,b ≤ 1e9，long 足夠）。
- 總時間複雜度：O(log min(a, b))。
- 空間複雜度：O(log min(a, b))（遞迴呼叫堆疊）；額外變數為 O(1)。
*/
