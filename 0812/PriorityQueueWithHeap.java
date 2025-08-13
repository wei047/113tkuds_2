import java.util.*;

public class PriorityQueueWithHeap {

    private static class Task {
        String name;
        int priority;
        long seq;
        Task(String n, int p, long s) { name = n; priority = p; seq = s; }
    }

    private final List<Task> heap = new ArrayList<>();
    private final Map<String, Integer> pos = new HashMap<>();
    private long tick = 0;

    public void addTask(String name, int priority) {
        if (pos.containsKey(name)) throw new IllegalArgumentException("duplicate task name");
        Task t = new Task(name, priority, tick++);
        heap.add(t);
        pos.put(name, heap.size() - 1);
        up(heap.size() - 1);
    }

    public String executeNext() {
        if (heap.isEmpty()) return null;
        String ans = heap.get(0).name;
        removeTop();
        return ans;
    }

    public String peek() { return heap.isEmpty() ? null : heap.get(0).name; }

    public void changePriority(String name, int newPriority) {
        Integer i = pos.get(name);
        if (i == null) return;
        int old = heap.get(i).priority;
        heap.get(i).priority = newPriority;
        if (newPriority > old) up(i); else down(i);
    }

    private int cmp(int i, int j) {
        Task a = heap.get(i), b = heap.get(j);
        if (a.priority != b.priority) return Integer.compare(a.priority, b.priority);
        return -Long.compare(a.seq, b.seq);
    }

    private void swap(int i, int j) {
        Task tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
        pos.put(heap.get(i).name, i);
        pos.put(heap.get(j).name, j);
    }

    private void up(int i) {
        while (i > 0) {
            int p = (i - 1) / 2;
            if (cmp(p, i) >= 0) break;
            swap(p, i);
            i = p;
        }
    }

    private void down(int i) {
        int n = heap.size();
        while (true) {
            int l = i * 2 + 1, r = i * 2 + 2, m = i;
            if (l < n && cmp(l, m) > 0) m = l;
            if (r < n && cmp(r, m) > 0) m = r;
            if (m == i) break;
            swap(i, m);
            i = m;
        }
    }

    private void removeTop() {
        int lastIdx = heap.size() - 1;
        Task top = heap.get(0);
        Task last = heap.remove(lastIdx);
        pos.remove(top.name);                
        if (lastIdx == 0) return;            
        heap.set(0, last);
        pos.put(last.name, 0);
        down(0);
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap q = new PriorityQueueWithHeap();
        q.addTask("備份", 1);
        q.addTask("緊急修復", 5);
        q.addTask("更新", 3);
        System.out.println(q.peek()); 
        q.changePriority("更新", 6);
        System.out.println(q.executeNext());
        System.out.println(q.executeNext()); 
        System.out.println(q.executeNext()); 
    }
}

