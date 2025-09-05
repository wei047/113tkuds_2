import java.io.*;
import java.util.*;


public class LC01_TwoSum_THSRHoliday {
    static class FastScanner {
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

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            // skip whitespaces
            while ((c = read()) != -1 && c <= ' ') {}
            if (c == -1) return null;
            // read token
            do {
                sb.append((char) c);
                c = read();
            } while (c != -1 && c > ' ');
            return sb.toString();
        }

        int nextInt() throws IOException {
            String s = next();
            return s == null ? Integer.MIN_VALUE : Integer.parseInt(s);
        }

        long nextLong() throws IOException {
            String s = next();
            return s == null ? Long.MIN_VALUE : Long.parseLong(s);
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        String tok = fs.next();
        if (tok == null) {
            System.out.println("-1 -1");
            return;
        }
        int n = Integer.parseInt(tok);
        long target = fs.nextLong();

        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextLong();

        int initialCap = (int)Math.ceil(n / 0.75) + 1;
        HashMap<Long, Integer> needIdx = new HashMap<>(Math.max(initialCap, 16));

        for (int i = 0; i < n; i++) {
            long x = a[i];
            Integer j = needIdx.get(x);
            if (j != null) {
                System.out.println(j + " " + i);
                return;
            }
            long need = target - x;
            needIdx.put(need, i);
        }

        System.out.println("-1 -1");
    }
}
/**
 * 時間複雜度：O(n)
 * 空間複雜度：O(n)
 */
