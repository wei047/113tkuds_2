import java.io.*;

public class lt_04_medianoftwosortedarrays {

    public static double findMedianSortedArrays(int[] a, int[] b) {
        if (a.length > b.length) { int[] t = a; a = b; b = t; }
        int m = a.length, n = b.length, lo = 0, hi = m;
        while (true) {
            int i = (lo + hi) / 2;
            int j = (m + n + 1) / 2 - i;
            int l1 = (i == 0) ? Integer.MIN_VALUE : a[i - 1];
            int r1 = (i == m) ? Integer.MAX_VALUE : a[i];
            int l2 = (j == 0) ? Integer.MIN_VALUE : b[j - 1];
            int r2 = (j == n) ? Integer.MAX_VALUE : b[j];
            if (l1 <= r2 && l2 <= r1) {
                long L = Math.max(l1, l2), R = Math.min(r1, r2);
                return ((m + n) & 1) == 1 ? (double)L : (L + R) / 2.0;
            } else if (l1 > r2) hi = i - 1;
            else lo = i + 1;
        }
    }

    private static int[] parseArray(String s) {
        s = s.trim();
        if (s.startsWith("[") && s.endsWith("]")) s = s.substring(1, s.length() - 1);
        s = s.replace(",", " ").trim();
        if (s.isEmpty()) return new int[0];
        String[] parts = s.split("\\s+");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) arr[i] = Integer.parseInt(parts[i]);
        return arr;
    }

    public static void main(String[] args) throws Exception {
        String a, b;
        if (args.length >= 2) { a = args[0]; b = args[1]; }
        else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            a = br.readLine(); b = br.readLine();
            if (a == null || b == null || a.isBlank() || b.isBlank()) { a = "[1,3]"; b = "[2]"; }
        }
        int[] x = parseArray(a), y = parseArray(b);
        System.out.println(findMedianSortedArrays(x, y));
    }
}
