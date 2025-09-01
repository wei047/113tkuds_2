import java.io.*;
import java.util.*;

public class lt_01_twosum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            Integer j = seen.get(need);
            if (j != null) return new int[]{j, i};
            if (!seen.containsKey(nums[i])) seen.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    private static int[] parseArray(String s) {
        s = s.trim();
        if (s.startsWith("[") && s.endsWith("]")) s = s.substring(1, s.length() - 1);
        s = s.replace(",", " ").trim();
        if (s.isEmpty()) return new int[0];
        String[] parts = s.split("\\s+");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) arr[i] = Integer.parseInt(parts[i]);
        return arr;
    }

    public static void main(String[] args) throws Exception {
        int[] nums;
        int target;

        if (args.length >= 2) {
            nums = parseArray(args[0]);
            target = Integer.parseInt(args[1]);
        } else {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line1 = br.readLine();
            String line2 = br.readLine();
            if (line1 == null || line2 == null || line1.isBlank() || line2.isBlank()) {
                nums = new int[]{2, 7, 11, 15};
                target = 9;
            } else {
                nums = parseArray(line1);
                target = Integer.parseInt(line2.trim());
            }
        }

        int[] ans = twoSum(nums, target);
        System.out.println("[" + ans[0] + "," + ans[1] + "]");
    }
}
