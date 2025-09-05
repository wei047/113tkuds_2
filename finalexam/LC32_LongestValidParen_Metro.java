import java.io.*;
import java.util.*;

public class LC32_LongestValidParen_Metro {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        String s = br.readLine();
        if (s == null) s = "";
        if (!s.isEmpty() && s.charAt(0) == '\uFEFF') s = s.substring(1);

        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1);
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                st.push(i);
            } else if (c == ')') {
                st.poll();
                if (st.isEmpty()) {
                    st.push(i);
                } else {
                    ans = Math.max(ans, i - st.peek());
                }
            }
        }
        System.out.println(ans);
    }
}
// 時間複雜度：O(n)
