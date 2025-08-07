import java.util.*;

public class AdvancedStringRecursion {
    public static void permute(String s) {
        permuteHelper("", s);
    }

    private static void permuteHelper(String prefix, String remaining) {
        if (remaining.isEmpty()) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < remaining.length(); i++) {
                permuteHelper(prefix + remaining.charAt(i), remaining.substring(0, i) + remaining.substring(i + 1));
            }
        }
    }

    public static boolean match(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean firstMatch = (!text.isEmpty() &&
                              (text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.'));
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return match(text, pattern.substring(2)) || (firstMatch && match(text.substring(1), pattern));
        } else {
            return firstMatch && match(text.substring(1), pattern.substring(1));
        }
    }

    public static String removeDuplicates(String s) {
        if (s.length() <= 1) return s;
        if (s.charAt(0) == s.charAt(1)) return removeDuplicates(s.substring(1));
        return s.charAt(0) + removeDuplicates(s.substring(1));
    }

    public static void allSubstrings(String s) {
        allSubstringsHelper(s, 0, "");
    }

    private static void allSubstringsHelper(String s, int index, String current) {
        if (index == s.length()) {
            if (!current.isEmpty()) System.out.println(current);
            return;
        }
        allSubstringsHelper(s, index + 1, current + s.charAt(index));
        allSubstringsHelper(s, index + 1, current);
    }
}