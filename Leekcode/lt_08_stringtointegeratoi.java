import java.io.*;

public class lt_08_stringtointegeratoi {

    public static int myAtoi(String s) {
        int i = 0, n = s.length();
        while (i < n && s.charAt(i) == ' ') i++;
        if (i == n) return 0;
        int sign = 1;
        char c = s.charAt(i);
        if (c == '+' || c == '-') { sign = (c == '-') ? -1 : 1; i++; }
        int res = 0, maxDiv10 = Integer.MAX_VALUE / 10;
        while (i < n) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '9') break;
            int d = ch - '0';
            if (sign == 1) {
                if (res > maxDiv10 || (res == maxDiv10 && d > 7)) return Integer.MAX_VALUE;
            } else {
                if (res > maxDiv10 || (res == maxDiv10 && d > 8)) return Integer.MIN_VALUE;
            }
            res = res * 10 + d;
            i++;
        }
        return sign * res;
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
            if (s == null) s = "42";
        }
        System.out.println(myAtoi(normalize(s)));
    }
}
