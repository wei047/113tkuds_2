import java.io.*;

public class lt_10_regularexpressionmatching {

    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 2; j <= n; j++) if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 2];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1);
                if (pc != '*') {
                    if (pc == '.' || pc == s.charAt(i - 1)) dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 2];
                    char prev = p.charAt(j - 2);
                    if (prev == '.' || prev == s.charAt(i - 1)) dp[i][j] |= dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }

    private static String norm(String x) {
        if (x != null && x.length() >= 2) {
            char a = x.charAt(0), b = x.charAt(x.length()-1);
            if ((a == '"' && b == '"') || (a == '\'' && b == '\'')) return x.substring(1, x.length()-1);
        }
        return x == null ? "" : x;
    }

    public static void main(String[] args) throws Exception {
        String s, p;
        if (args.length >= 2) { s = args[0]; p = args[1]; }
        else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            s = br.readLine(); p = br.readLine();
            if (s == null || p == null || s.isBlank() || p.isBlank()) { s = "aa"; p = "a*"; }
        }
        System.out.println(isMatch(norm(s), norm(p)));
    }
}
