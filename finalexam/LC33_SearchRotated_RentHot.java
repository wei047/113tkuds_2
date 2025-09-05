import java.io.*;

public class LC33_SearchRotated_RentHot {
    static class FastScanner {
        private final InputStream in; private final byte[] buf = new byte[1<<16]; private int ptr=0,len=0;
        FastScanner(InputStream is){ in=is; }
        private int read() throws IOException { if(ptr>=len){ len=in.read(buf); ptr=0; if(len<=0) return -1; } return buf[ptr++]; }
        String next() throws IOException { StringBuilder sb=new StringBuilder(); int c; while((c=read())!=-1 && c<=' '){ } if(c==-1) return null; do{ sb.append((char)c); c=read(); }while(c!=-1 && c>' '); return sb.toString(); }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
        long nextLong() throws IOException { return Long.parseLong(next()); }
    }
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        String t = fs.next(); if (t == null) return;
        int n = Integer.parseInt(t);
        long target = fs.nextLong();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextLong();
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (a[mid] == target) { System.out.println(mid); return; }
            if (a[l] <= a[mid]) {
                if (a[l] <= target && target < a[mid]) r = mid - 1;
                else l = mid + 1;
            } else {
                if (a[mid] < target && target <= a[r]) l = mid + 1;
                else r = mid - 1;
            }
        }
        System.out.println(-1);
    }
}
// 時間複雜度：O(log n)
