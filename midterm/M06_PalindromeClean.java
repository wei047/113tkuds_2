import java.io.*;

public class M06_PalindromeClean {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) return;

        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !isAsciiLetter(s.charAt(i))) i++;
            while (i < j && !isAsciiLetter(s.charAt(j))) j--;

            if (i < j) {
                char L = toLowerAscii(s.charAt(i));
                char R = toLowerAscii(s.charAt(j));
                if (L != R) {
                    System.out.println("No");
                    return;
                }
                i++; j--;
            }
        }
        System.out.println("Yes");
    }

    private static boolean isAsciiLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    private static char toLowerAscii(char c) {
        return (c >= 'A' && c <= 'Z') ? (char) (c - 'A' + 'a') : c;
    }
}

