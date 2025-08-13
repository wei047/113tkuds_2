
public class ValidMaxHeapChecker {

    public static int firstViolationIndex(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int parent = (i - 1) / 2;
            if (a[i] > a[parent]) return i; 
        }
        return -1;
    }

    public static boolean isValidMaxHeap(int[] a) {
        return firstViolationIndex(a) == -1;
    }

    public static void main(String[] args) {
        int[] ok = {100, 90, 80, 70, 60, 75, 65};
        int[] bad = {100, 90, 80, 95, 60, 75, 65}; // 索引3的95 > 父90
        System.out.println(isValidMaxHeap(ok));   // true
        int v = firstViolationIndex(bad);
        System.out.println(v + " -> child=" + bad[v] + ", parent=" + bad[(v - 1) / 2]); // 3 -> 95 vs 90
    }
}
