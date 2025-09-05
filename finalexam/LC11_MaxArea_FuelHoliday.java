import java.io.*;

public class LC11_MaxArea_FuelHoliday {
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { this.in = is; }
        private int read() throws IOException {
            if (ptr >= len) { len = in.read(buffer); ptr = 0; if (len <= 0) return -1; }
            return buffer[ptr++];
        }
        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) != -1 && c <= ' ') {}
            if (c == -1) return null;
            do { sb.append((char) c); c = read(); } while (c != -1 && c > ' ');
            return sb.toString();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
        long nextLong() throws IOException { return Long.parseLong(next()); }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        String t = fs.next();
        if (t == null) return;
        int n = Integer.parseInt(t);
        long[] h = new long[n];
        for (int i = 0; i < n; i++) h[i] = fs.nextLong();

        int l = 0, r = n - 1;
        long ans = 0;
        while (l < r) {
            long minH = Math.min(h[l], h[r]);
            ans = Math.max(ans, minH * (r - l));
            if (h[l] <= h[r]) l++;
            else r--;
        }
        System.out.println(ans);
    }
}
// 時間複雜度：O(n)
