import java.io.*;
import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        String s = br.readLine();
        if (s == null) s = "";
        // 防 BOM
        if (!s.isEmpty() && s.charAt(0) == '\uFEFF') s = s.substring(1);

        int n = s.length();
        Map<Character, Integer> last = new HashMap<>();
        int ans = 0, l = 0;

        for (int r = 0; r < n; r++) {
            char c = s.charAt(r);
            Integer prev = last.get(c);
            if (prev != null && prev >= l) {
                l = prev + 1; // 收縮左界，排除重複
            }
            last.put(c, r);
            ans = Math.max(ans, r - l + 1);
        }

        System.out.println(ans);
    }
}

