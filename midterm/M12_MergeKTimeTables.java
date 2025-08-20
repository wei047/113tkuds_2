import java.io.*;
import java.util.*;


public class M12_MergeKTimeTables {
    static boolean outputAsHHmm = false; 

    static class Node {
        int time;   
        int li;      
        int idx;    
        Node(int t, int l, int i) { time = t; li = l; idx = i; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int K = fs.nextInt();
        List<int[]> lists = new ArrayList<>(K);

        for (int i = 0; i < K; i++) {
            int len = fs.nextInt();
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) {
                String tok = fs.next();
                arr[j] = parseTime(tok); 
            }
            lists.add(arr);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            if (a.time != b.time) return Integer.compare(a.time, b.time);
            if (a.li != b.li)     return Integer.compare(a.li, b.li);
            return Integer.compare(a.idx, b.idx);
        });

        for (int i = 0; i < K; i++) {
            int[] arr = lists.get(i);
            if (arr.length > 0) pq.offer(new Node(arr[0], i, 0));
        }

        StringBuilder out = new StringBuilder();
        boolean first = true;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (!first) out.append(' ');
            first = false;
            out.append(formatTime(cur.time));  

            int[] arr = lists.get(cur.li);
            int nextIdx = cur.idx + 1;
            if (nextIdx < arr.length) {
                pq.offer(new Node(arr[nextIdx], cur.li, nextIdx));
            }
        }

        System.out.println(out.toString());
    }

    private static int parseTime(String s) {
        if (s.indexOf(':') >= 0) {
            outputAsHHmm = true;
            String[] p = s.split(":");
            int h = Integer.parseInt(p[0]);
            int m = Integer.parseInt(p[1]);
            return h * 60 + m;
        } else {
            return Integer.parseInt(s);
        }
    }

    private static String formatTime(int minutes) {
        if (!outputAsHHmm) return Integer.toString(minutes);
        int h = minutes / 60, m = minutes % 60;
        return String.format("%02d:%02d", h, m);
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
            do { c = read(); } while (c <= ' ' && c != -1);
            if (c == -1) return null;
            while (c > ' ') { sb.append((char)c); c = read(); }
            return sb.toString();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
}

