import java.util.*;

public class MovingAverageStream {

    static class MovingAverage {
        private final int size;
        private final Deque<Integer> q = new ArrayDeque<>();
        private long sum = 0;

        private final PriorityQueue<Integer> small = new PriorityQueue<>(Comparator.reverseOrder()); // lower half
        private final PriorityQueue<Integer> large = new PriorityQueue<>(); // upper half
        private final Map<Integer, Integer> delayed = new HashMap<>();
        private int smallSize = 0, largeSize = 0;

        private final TreeMap<Integer, Integer> multi = new TreeMap<>();

        public MovingAverage(int size) {
            if (size <= 0) throw new IllegalArgumentException("size must be > 0");
            this.size = size;
        }

        public double next(int val) {
            q.addLast(val);
            sum += val;
            addMedian(val);
            addMulti(val);

            if (q.size() > size) {
                int out = q.removeFirst();
                sum -= out;
                removeMedian(out);
                removeMulti(out);
            }
            return sum * 1.0 / q.size();
        }

        public double getMedian() {
            if (q.isEmpty()) throw new NoSuchElementException();
            if ((q.size() & 1) == 1) return small.peek();
            return (small.peek() + large.peek()) / 2.0;
        }

        public int getMin() {
            if (q.isEmpty()) throw new NoSuchElementException();
            return multi.firstKey();
        }

        public int getMax() {
            if (q.isEmpty()) throw new NoSuchElementException();
            return multi.lastKey();
        }

        // ----- helpers for median -----
        private void addMedian(int x) {
            if (small.isEmpty() || x <= small.peek()) { small.offer(x); smallSize++; }
            else { large.offer(x); largeSize++; }
            balance();
        }

        private void removeMedian(int x) {
            delayed.put(x, delayed.getOrDefault(x, 0) + 1);
            if (!small.isEmpty() && x <= small.peek()) smallSize--; else largeSize--;
            if (!small.isEmpty() && x <= small.peek()) prune(small); else prune(large);
            balance();
        }

        private void balance() {
            if (smallSize > largeSize + 1) { large.offer(small.poll()); smallSize--; largeSize++; prune(small); }
            else if (smallSize < largeSize) { small.offer(large.poll()); largeSize--; smallSize++; prune(large); }
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

        private void addMulti(int x) { multi.put(x, multi.getOrDefault(x, 0) + 1); }
        private void removeMulti(int x) {
            int c = multi.getOrDefault(x, 0);
            if (c <= 1) multi.remove(x); else multi.put(x, c - 1);
        }
    }

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage(3);
        System.out.println(ma.next(1));   // 1.0
        System.out.println(ma.next(10));  // 5.5
        System.out.println(ma.next(3));   // 4.67
        System.out.println(ma.next(5));   // 6.0
        System.out.println(ma.getMedian()); // 5.0
        System.out.println(ma.getMin());    // 3
        System.out.println(ma.getMax());    // 10
    }
}
