import java.io.*;

public class M10_RBPropertiesCheck {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int[] val = new int[n];
        char[] col = new char[n]; 
        for (int i = 0; i < n; i++) {
            val[i] = fs.nextInt();
            String c = fs.next();
            char cc = (c == null || c.isEmpty()) ? 'B' : Character.toUpperCase(c.charAt(0));
            if (val[i] == -1) cc = 'B';        
            col[i] = (cc == 'R') ? 'R' : 'B';
        }

        if (n > 0 && val[0] != -1 && col[0] != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        for (int i = 1; i < n; i++) {
            if (val[i] == -1) continue;                 
            if (col[i] == 'R') {
                int p = (i - 1) / 2;
                if (p >= 0 && val[p] != -1 && col[p] == 'R') {
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
            }
        }

        if (blackHeight(0, val, col) == BAD) {
            System.out.println("BlackHeightMismatch");
        } else {
            System.out.println("RB Valid");
        }
    }

    private static final int BAD = Integer.MIN_VALUE / 2;

    private static int blackHeight(int i, int[] val, char[] col) {
        if (i >= val.length || val[i] == -1) return 1; 
        int li = 2 * i + 1, ri = 2 * i + 2;

        int lh = blackHeight(li, val, col);
        if (lh == BAD) return BAD;
        int rh = blackHeight(ri, val, col);
        if (rh == BAD) return BAD;

        if (lh != rh) return BAD;

        int add = (col[i] == 'B') ? 1 : 0;
        return lh + add;
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { in = is; }
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
            do { c = read(); } while (c <= ' ' && c != -1);
            if (c == -1) return null;
            while (c > ' ') {
                sb.append((char)c);
                c = read();
            }
            return sb.toString();
        }
        int nextInt() throws IOException {
            int c, sgn = 1, v = 0;
            do { c = read(); } while (c <= ' ');
            if (c == '-') { sgn = -1; c = read(); }
            while (c > ' ') { v = v * 10 + (c - '0'); c = read(); }
            return v * sgn;
        }
    }
}

