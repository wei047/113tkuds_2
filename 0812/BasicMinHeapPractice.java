import java.util.*;

public class BasicMinHeapPractice {

    static class MinHeap {
        private final List<Integer> a = new ArrayList<>();

        public void insert(int val) {
            a.add(val);
            heapifyUp(a.size() - 1);
        }

        public int extractMin() {
            if (a.isEmpty()) throw new NoSuchElementException("heap is empty");
            int min = a.get(0);
            int last = a.remove(a.size() - 1);
            if (!a.isEmpty()) {
                a.set(0, last);
                heapifyDown(0);
            }
            return min;
        }

        public int getMin() {
            if (a.isEmpty()) throw new NoSuchElementException("heap is empty");
            return a.get(0);
        }

        public int size() { return a.size(); }
        public boolean isEmpty() { return a.isEmpty(); }

        private void heapifyUp(int i) {
            while (i > 0) {
                int p = (i - 1) / 2;
                if (a.get(p) <= a.get(i)) break;
                swap(p, i);
                i = p;
            }
        }

        private void heapifyDown(int i) {
            int n = a.size();
            while (true) {
                int l = 2 * i + 1, r = 2 * i + 2, m = i;
                if (l < n && a.get(l) < a.get(m)) m = l;
                if (r < n && a.get(r) < a.get(m)) m = r;
                if (m == i) break;
                swap(i, m);
                i = m;
            }
        }

        private void swap(int i, int j) {
            int t = a.get(i);
            a.set(i, a.get(j));
            a.set(j, t);
        }
    }

    public static void main(String[] args) {
        MinHeap h = new MinHeap();
        int[] nums = {15, 10, 20, 8, 25, 5};
        for (int x : nums) h.insert(x);
        List<Integer> out = new ArrayList<>();
        while (!h.isEmpty()) out.add(h.extractMin());
        System.out.println(out); // 期望: [5, 8, 10, 15, 20, 25]
    }
}
