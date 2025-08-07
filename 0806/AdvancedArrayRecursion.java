import java.util.*;

public class AdvancedArrayRecursion {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static int[] mergeSortedArrays(int[] a, int[] b) {
        return mergeHelper(a, 0, b, 0, new ArrayList<>());
    }

    private static int[] mergeHelper(int[] a, int i, int[] b, int j, List<Integer> result) {
        if (i == a.length) {
            while (j < b.length) result.add(b[j++]);
        } else if (j == b.length) {
            while (i < a.length) result.add(a[i++]);
        } else if (a[i] < b[j]) {
            result.add(a[i]);
            mergeHelper(a, i + 1, b, j, result);
        } else {
            result.add(b[j]);
            mergeHelper(a, i, b, j + 1, result);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int kthSmallest(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length - 1, k - 1);
    }

    private static int quickSelect(int[] arr, int low, int high, int k) {
        if (low == high) return arr[low];
        int pi = partition(arr, low, high);
        if (k == pi) return arr[pi];
        else if (k < pi) return quickSelect(arr, low, pi - 1, k);
        else return quickSelect(arr, pi + 1, high, k);
    }

    public static boolean subsetSum(int[] arr, int index, int target) {
        if (target == 0) return true;
        if (index == arr.length) return false;
        return subsetSum(arr, index + 1, target - arr[index]) || subsetSum(arr, index + 1, target);
    }
}