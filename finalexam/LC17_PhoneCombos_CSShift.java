import java.io.*;

public class LC17_PhoneCombos_CSShift {
    static final String[] MAP = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    static char[] digits;
    static BufferedWriter out;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        String s = br.readLine();
        if (s == null) return;
        if (!s.isEmpty() && s.charAt(0) == '\uFEFF') s = s.substring(1);
        if (s.isEmpty()) return;
        digits = s.toCharArray();
        out = new BufferedWriter(new OutputStreamWriter(System.out, "UTF-8"));
        dfs(0, new StringBuilder());
        out.flush();
    }

    static void dfs(int idx, StringBuilder cur) throws IOException {
        if (idx == digits.length) {
            out.write(cur.toString());
            out.newLine();
            return;
        }
        int d = digits[idx] - '2';
        String letters = MAP[d];
        for (int i = 0; i < letters.length(); i++) {
            cur.append(letters.charAt(i));
            dfs(idx + 1, cur);
            cur.setLength(cur.length() - 1);
        }
    }
}
// 時間複雜度：O(3^m·4^n)
