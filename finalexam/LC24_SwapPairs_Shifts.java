import java.io.*;
import java.util.*;

public class LC24_SwapPairs_Shifts {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        String line = br.readLine();
        if (line == null) return;
        StringTokenizer st = new StringTokenizer(line);
        ArrayList<Long> a = new ArrayList<>();
        while (st.hasMoreTokens()) a.add(Long.parseLong(st.nextToken()));
        for (int i = 0; i + 1 < a.size(); i += 2) {
            long tmp = a.get(i); a.set(i, a.get(i+1)); a.set(i+1, tmp);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(a.get(i));
        }
        if (sb.length() > 0) System.out.println(sb.toString());
    }
}
// 時間複雜度：O(n)
