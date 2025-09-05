import java.io.*;

public class LC26_RemoveDuplicates_Scores {
    static class FastScanner {
        private final InputStream in; private final byte[] buf = new byte[1<<16]; private int ptr=0,len=0;
        FastScanner(InputStream is){ in=is; }
        private int read() throws IOException { if (ptr>=len){ len=in.read(buf); ptr=0; if (len<=0) return -1; } return buf[ptr++]; }
        String next() throws IOException { StringBuilder sb=new StringBuilder(); int c; while((c=read())!=-1 && c<=' '){ } if(c==-1) return null; do{ sb.append((char)c); c=read(); }while(c!=-1 && c>' '); return sb.toString(); }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        String t = fs.next(); if (t == null) return;
        int n = Integer.parseInt(t);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();
        int w = 0;
        for (int i = 0; i < n; i++) {
            if (w == 0 || a[i] != a[w-1]) a[w++] = a[i];
        }
        System.out.println(w);
        if (w > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < w; i++) { if (i > 0) sb.append(' '); sb.append(a[i]); }
            System.out.println(sb.toString());
        }
    }
}
// 時間複雜度：O(n)
