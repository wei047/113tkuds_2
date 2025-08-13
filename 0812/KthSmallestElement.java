import java.util.*;

public class KthSmallestElement {

    public static int kthSmallest(int[] nums, int k) {
        if (k < 1 || k > nums.length) throw new IllegalArgumentException("k out of range");
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // max-heap
        for (int x : nums) {
            if (pq.size() < k) pq.offer(x);
            else if (x < pq.peek()) { pq.poll(); pq.offer(x); }
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        int[] a = {7, 10, 4, 3, 20, 15};
        System.out.println(kthSmallest(a, 3)); // 7
        System.out.println(kthSmallest(new int[]{1}, 1)); // 1
        System.out.println(kthSmallest(new int[]{3,1,4,1,5,9,2,6}, 4)); // 3
    }
}
