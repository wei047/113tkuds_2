public class RecursionVsIteration {
    public static int factorialRecursive(int n) {
        if (n == 0) return 1;
        return n * factorialRecursive(n - 1);
    }

    public static int factorialIterative(int n) {
        int res = 1;
        for (int i = 2; i <= n; i++) res *= i;
        return res;
    }

    public static int productRecursive(int[] arr, int index) {
        if (index == arr.length) return 1;
        return arr[index] * productRecursive(arr, index + 1);
    }

    public static int productIterative(int[] arr) {
        int product = 1;
        for (int num : arr) product *= num;
        return product;
    }

    public static int countVowelsRecursive(String s, int index) {
        if (index == s.length()) return 0;
        char c = Character.toLowerCase(s.charAt(index));
        int count = "aeiou".indexOf(c) >= 0 ? 1 : 0;
        return count + countVowelsRecursive(s, index + 1);
    }

    public static int countVowelsIterative(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if ("aeiou".indexOf(Character.toLowerCase(c)) >= 0) count++;
        }
        return count;
    }

    public static boolean isBalanced(String s) {
        return isBalancedHelper(s, 0, 0);
    }

    private static boolean isBalancedHelper(String s, int index, int balance) {
        if (balance < 0) return false;
        if (index == s.length()) return balance == 0;
        if (s.charAt(index) == '(') return isBalancedHelper(s, index + 1, balance + 1);
        else if (s.charAt(index) == ')') return isBalancedHelper(s, index + 1, balance - 1);
        return isBalancedHelper(s, index + 1, balance);
    }
}