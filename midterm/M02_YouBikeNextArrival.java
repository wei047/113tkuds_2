import java.io.*;

public class M02_YouBikeNextArrival {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(nextNonEmpty(br));
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = parse(nextNonEmpty(br)); 
        }
        int query = parse(nextNonEmpty(br));

        int idx = upperBound(times, query);   
        if (idx == n) {
            System.out.println("No bike");
        } else {
            System.out.println(format(times[idx]));
        }
    }

    private static int upperBound(int[] a, int key) {
        int lo = 0, hi = a.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (a[mid] > key) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    private static int parse(String s) {
        String[] p = s.split(":");
        int h = Integer.parseInt(p[0]);
        int m = Integer.parseInt(p[1]);
        return h * 60 + m;
    }

    private static String format(int t) {
        int h = t / 60, m = t % 60;
        return String.format("%02d:%02d", h, m);
    }

    private static String nextNonEmpty(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) return line;
        }
        return null;
    }
}

