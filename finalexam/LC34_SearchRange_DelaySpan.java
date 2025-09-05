import java.io.*;

public class LC34_SearchRange_DelaySpan {
    static class FastScanner {
        private final InputStream in; private final byte[] buf = new byte[1<<16]; private int ptr=0,len=0;
        FastScanner(InputStream is){ in=is; }
        private int read() throws IOException { if(ptr>=len){ len=in.read(buf); ptr=0; if(len<=0) return -1; } return buf[ptr++]; }
        String next() throws IOException { StringBuilder sb=new StringBuilder(); int c; while((c=read())!=-1 && c<=' '){ } if(c==-1) return null; do{ sb.append((char)c); c=read(); }while(c!=-1 && c>' '); return sb.toString(); }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
        long nextLong() throws IOException { return Long.parseLong(next()); }
    }
    static int lowerBound(long[] a, long x) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] < x) l = m + 1; else r = m;
        }
        return l;
    }
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        String t = fs.next(); if (t == null) { System.out.println("-1 -1"); return; }
        int n = Integer.parseInt(t);
        long target = fs.nextLong();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextLong();
        int L = lowerBound(a, target);
        if (L == n || a[L] != target) { System.out.println("-1 -1"); return; }
        int R = lowerBound(a, target + 1L) - 1;
        System.out.println(L + " " + R);
    }
}
// 時間複雜度：O(log n)
