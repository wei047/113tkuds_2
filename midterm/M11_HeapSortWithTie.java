import java.io.*;

public class M11_HeapSortWithTie {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();                 
        int[] score = new int[n];
        for (int i = 0; i < n; i++) score[i] = fs.nextInt();

        int[] idx = new int[n];               
        for (int i = 0; i < n; i++) idx[i] = i;

        buildMaxHeap(score, idx, n);
        for (int end = n - 1; end > 0; end--) {
            swap(score, idx, 0, end);
            heapifyDown(score, idx, 0, end);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(score[i]);
        }
        System.out.println(sb.toString());
    }

    private static boolean greater(int[] s, int[] id, int i, int j) {
        if (s[i] != s[j]) return s[i] > s[j];
        return id[i] > id[j];
    }

    private static void buildMaxHeap(int[] s, int[] id, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) heapifyDown(s, id, i, n);
    }

    private static void heapifyDown(int[] s, int[] id, int i, int size) {
        while (true) {
            int left = 2 * i + 1;
            if (left >= size) break;
            int right = left + 1;
            int best = left;
            if (right < size && greater(s, id, right, left)) best = right;
            if (greater(s, id, best, i)) {
                swap(s, id, i, best);
                i = best;
            } else break;
        }
    }

    private static void swap(int[] s, int[] id, int i, int j) {
        int ts = s[i]; s[i] = s[j]; s[j] = ts;
        int ti = id[i]; id[i] = id[j]; id[j] = ti;
    }
    private static class FastScanner {
        private final BufferedReader br;
        private String[] toks = new String[0];
        private int p = 0;

        FastScanner(InputStream is) { br = new BufferedReader(new InputStreamReader(is)); }

        private String next() throws IOException {
            while (p >= toks.length) {
                String line = br.readLine();
                if (line == null) return null;
                line = line.trim();
                if (line.isEmpty()) continue;
                toks = line.split("\\s+");
                p = 0;
            }
            return toks[p++];
        }

        int nextInt() throws IOException {
            String s = next();
            if (s == null) throw new EOFException("Missing integer");
            return Integer.parseInt(s);      
        }
    }
}

/*
計算複雜度註解
- 建堆：O(n)
- 取最大 + 下濾共 n-1 次：O(n log n)
- 總時間：O(n log n)
- 空間：O(1)（就地排序；僅兩個平行陣列）
- 平手規則：同分時以較小索引優先（穩定性由 (score, index) 比較鍵保證）。
*/
