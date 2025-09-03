import java.io.*;

public class lt_09_palindromenumber {

    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return x == rev || x == rev / 10;
    }

    public static void main(String[] args) throws Exception {
        int x;
        if (args.length >= 1) x = Integer.parseInt(args[0]);
        else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s = br.readLine();
            x = (s == null || s.isBlank()) ? 121 : Integer.parseInt(s.trim());
        }
        System.out.println(isPalindrome(x));
    }
}
