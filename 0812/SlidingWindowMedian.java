import java.util.*;

public class SlidingWindowMedian {

    private final PriorityQueue<Integer> small =
            new PriorityQueue<>(Comparator.reverseOrder()); // max-heap
    private final PriorityQueue<Integer> large = new PriorityQueue<>(); // min-heap
    private final Map<Integer, Integer> delayed = new HashMap<>();
    private int smallSize = 0, largeSize = 0;

    public double[] medianSlidingWindow(int[] nums, int k) {
        small.clear(); large.clear(); delayed.clear();
        smallSize = largeSize = 0;
        double[] ans = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if (small.isEmpty() || nums[i] <= small.peek()) { small.offer(nums[i]); smallSize++; }
            else { large.offer(nums[i]); largeSize++; }
            balance();

            if (i >= k) {
                int out = nums[i - k];
                delayed.put(out, delayed.getOrDefault(out, 0) + 1);
                if (!small.isEmpty() && out <= small.peek()) smallSize--; else largeSize--;
                if (!small.isEmpty() && out <= small.peek()) prune(small);
                else prune(large);
                balance();
            }

            if (i >= k - 1) {
                int idx = i - k + 1;
                int left = Objects.requireNonNull(small.peek()); // 避免可能的 null unboxing
                if ((k & 1) == 1) ans[idx] = (double) left;
                else {
                    int right = Objects.requireNonNull(large.peek());
                    ans[idx] = (left + right) / 2.0;
                }
            }
        }
        return ans;
    }

    private void balance() {
        if (smallSize > largeSize + 1) {
            large.offer(small.poll()); smallSize--; largeSize++;
            prune(small);
        } else if (smallSize < largeSize) {
            small.offer(large.poll()); largeSize--; smallSize++;
            prune(large);
        }
    }

    private void prune(PriorityQueue<Integer> pq) {
        while (!pq.isEmpty()) {
            int x = pq.peek();
            int c = delayed.getOrDefault(x, 0);
            if (c > 0) {
                pq.poll();
                if (c == 1) delayed.remove(x);
                else delayed.put(x, c - 1);
            } else break;
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian s = new SlidingWindowMedian();
        System.out.println(Arrays.toString(s.medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
        // [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]
    }
}
