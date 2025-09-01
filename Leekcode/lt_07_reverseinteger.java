import java.io.*;

public class lt_07_reverseinteger {

    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) throws Exception {
        int x;
        if (args.length >= 1) x = Integer.parseInt(args[0]);
        else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s = br.readLine();
            x = (s == null || s.isBlank()) ? 123 : Integer.parseInt(s.trim());
        }
        System.out.println(reverse(x));
    }
}
