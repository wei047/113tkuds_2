import java.io.*;

public class LC28_StrStr_NoticeSearch {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        String haystack = br.readLine();
        String needle = br.readLine();
        if (haystack == null) haystack = "";
        if (needle == null) needle = "";
        if (needle.length() == 0) { System.out.println(0); return; }
        int n = haystack.length(), m = needle.length();
        int[] lps = new int[m];
        for (int i = 1, len = 0; i < m; ) {
            if (needle.charAt(i) == needle.charAt(len)) lps[i++] = ++len;
            else if (len != 0) len = lps[len - 1];
            else lps[i++] = 0;
        }
        for (int i = 0, j = 0; i < n; ) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++; j++;
                if (j == m) { System.out.println(i - m); return; }
            } else if (j != 0) j = lps[j - 1];
            else i++;
        }
        System.out.println(-1);
    }
}
// 時間複雜度：O(n+m)
