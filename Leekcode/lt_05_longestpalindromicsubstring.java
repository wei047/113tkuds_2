import java.io.*;

public class lt_05_longestpalindromicsubstring {

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(s, i, i);
            int len2 = expand(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expand(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) { l--; r++; }
        return r - l - 1;
    }

    private static String normalize(String s) {
        if (s != null && s.length() >= 2) {
            char a = s.charAt(0), b = s.charAt(s.length()-1);
            if ((a == '"' && b == '"') || (a == '\'' && b == '\'')) return s.substring(1, s.length()-1);
        }
        return s == null ? "" : s;
    }

    public static void main(String[] args) throws Exception {
        String s;
        if (args.length >= 1) s = String.join(" ", args);
        else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            s = br.readLine();
            if (s == null || s.isEmpty()) s = "babad";
        }
        s = normalize(s);
        System.out.println(longestPalindrome(s));
    }
}
