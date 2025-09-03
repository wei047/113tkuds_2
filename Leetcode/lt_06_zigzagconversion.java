import java.io.*;

public class lt_06_zigzagconversion {

    public static String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;
        StringBuilder[] rows = new StringBuilder[Math.min(numRows, s.length())];
        for (int i = 0; i < rows.length; i++) rows[i] = new StringBuilder();
        int cur = 0; boolean down = false;
        for (int i = 0; i < s.length(); i++) {
            rows[cur].append(s.charAt(i));
            if (cur == 0 || cur == numRows - 1) down = !down;
            cur += down ? 1 : -1;
        }
        StringBuilder ans = new StringBuilder();
        for (StringBuilder sb : rows) ans.append(sb);
        return ans.toString();
    }

    private static String normalize(String s) {
        if (s != null && s.length() >= 2) {
            char a = s.charAt(0), b = s.charAt(s.length()-1);
            if ((a == '"' && b == '"') || (a == '\'' && b == '\'')) return s.substring(1, s.length()-1);
        }
        return s == null ? "" : s;
    }

    public static void main(String[] args) throws Exception {
        String s; int numRows;
        if (args.length >= 2) { s = args[0]; numRows = Integer.parseInt(args[1]); }
        else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            s = br.readLine(); String r = br.readLine();
            if (s == null || r == null || s.isBlank() || r.isBlank()) { s = "PAYPALISHIRING"; r = "3"; }
            numRows = Integer.parseInt(r.trim());
        }
        s = normalize(s);
        System.out.println(convert(s, numRows));
    }
}
