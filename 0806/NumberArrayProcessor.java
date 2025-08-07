import java.util.*;

public class NumberArrayProcessor {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 3, 4, 4, 5};
        int[] arr2 = {3, 4, 5, 6, 7};
        Set<Integer> set = new LinkedHashSet<>();
        for (int i : arr1) set.add(i);
        int[] noDuplicates = set.stream().mapToInt(i -> i).toArray();
        System.out.println("移除重複元素: " + Arrays.toString(noDuplicates));

        int[] merged = mergeSortedArrays(new int[]{1, 3, 5}, new int[]{2, 4, 6});
        System.out.println("合併排序後: " + Arrays.toString(merged));

        int mode = findMode(arr1);
        System.out.println("最常出現的元素: " + mode);

        int[] split = splitArray(arr1);
        System.out.println("分割點: " + Arrays.toString(split));
    }

    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) result[k++] = a[i++];
            else result[k++] = b[j++];
        }
        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];
        return result;
    }

    public static int findMode(int[] array) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : array) freq.put(num, freq.getOrDefault(num, 0) + 1);
        int maxCount = 0, mode = array[0];
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mode = entry.getKey();
            }
        }
        return mode;
    }

    public static int[] splitArray(int[] array) {
        int total = Arrays.stream(array).sum();
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            if (sum >= total / 2) return new int[]{i + 1};
        }
        return new int[]{array.length};
    }
}