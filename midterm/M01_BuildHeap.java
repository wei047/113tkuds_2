import java.io.*;

public class M01_BuildHeap {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        String type = fs.next();              
        if (type == null) return;             
        boolean isMax = type.trim().equalsIgnoreCase("max");

        int n = Integer.parseInt(fs.next());
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(fs.next());
        }

        buildHeap(a, isMax);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i]);
        }
        System.out.println(sb.toString());
    }

    private static void buildHeap(int[] a, boolean isMax) {
        int n = a.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(a, n, i, isMax);
        }
    }

    private static void heapifyDown(int[] a, int n, int i, boolean isMax) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int best = i;

            if (left < n && better(a[left], a[best], isMax)) best = left;
            if (right < n && better(a[right], a[best], isMax)) best = right;

            if (best == i) break;
            swap(a, i, best);
            i = best;
        }
    }

    private static boolean better(int x, int y, boolean isMax) {
        return isMax ? x > y : x < y; 
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
    }

    private static class FastScanner {
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
            while ((c = read()) != -1 && c <= ' ') {}
            if (c == -1) return null;
            do {
                sb.append((char) c);
                c = read();
            } while (c != -1 && c > ' ');
            return sb.toString();
        }
    }
}

/*
計算複雜度註解
時間複雜度：O(n)。自底向上建堆從 n/2-1 到 0 進行 heapifyDown，
            各層節點數隨高度遞減，總工作量為 Σ (n/2^{h+1}) * O(h) = O(n)。
空間複雜度：O(1)。原地堆化，只使用常數額外空間。
*/
