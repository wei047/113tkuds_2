import java.io.*;
import java.util.*;

public class LC40_CombinationSum2_Procurement {
    static int n, target;
    static int[] a;
    static ArrayDeque<Integer> path = new ArrayDeque<>();
    static StringBuilder out = new StringBuilder();

    static void dfs(int start, int remain) {
        if (remain == 0) {
            int i = 0;
            for (int v : path) { if (i++ > 0) out.append(' '); out.append(v); }
            out.append('\n');
            return;
        }
        for (int i = start; i < n; i++) {
            if (i > start && a[i] == a[i - 1]) continue;
            if (a[i] > remain) break;
            path.addLast(a[i]);
            dfs(i + 1, remain - a[i]);
            path.removeLast();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st1.nextToken());
        target = Integer.parseInt(st1.nextToken());
        a = new int[n];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(st2.nextToken());
        Arrays.sort(a);
        dfs(0, target);
        System.out.print(out.toString());
    }
}
// 時間複雜度：指數
