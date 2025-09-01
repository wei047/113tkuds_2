import java.io.*;
import java.util.*;

public class lt_03_longestsubstringwithoutrepeatingcharacters {

    public static int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> last = new HashMap<>();
        int ans = 0, left = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer p = last.get(c);
            if (p != null && p >= left) left = p + 1;
            last.put(c, i);
            ans = Math.max(ans, i - left + 1);
        }
        return ans;
    }

    private static String normalize(String s) {
        if (s != null && s.length() >= 2) {
            char a = s.charAt(0), b = s.charAt(s.length()-1);
            if ((a == '"' && b == '"') || (a == '\'' && b == '\'')) {
                return s.substring(1, s.length()-1);
            }
        }
        return s == null ? "" : s;
    }

    public static void main(String[] args) throws Exception {
        String s;
        if (args.length >= 1) {
            s = String.join(" ", args);
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            s = br.readLine();
            if (s == null || s.isEmpty()) s = "abcabcbb";
        }
        s = normalize(s);
        System.out.println(lengthOfLongestSubstring(s));
    }
}
