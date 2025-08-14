import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class ConcurrentRBTreeExercise {

    static class ConcurrentRBTree<K extends Comparable<K>, V> {
        private final RBTree<K,V> tree = new RBTree<>();
        private final ReadWriteLock rw = new ReentrantReadWriteLock();
        private int size = 0;

        public V get(K key){
            rw.readLock().lock();
            try {
                RBTree.Node<K,V> n = tree.search(key);
                return (n==null)? null : n.value;
            } finally { rw.readLock().unlock(); }
        }

        public V put(K key, V value){
            rw.writeLock().lock();
            try {
                RBTree.Node<K,V> n = tree.search(key);
                V old = (n==null)? null : n.value;
                boolean created = tree.insert(key, value);
                if (created) size++;
                return old;
            } finally { rw.writeLock().unlock(); }
        }

        public V remove(K key){
            rw.writeLock().lock();
            try {
                RBTree.Node<K,V> n = tree.search(key);
                if (n==null) return null;
                V old = n.value;
                if (tree.delete(key)) size--;
                return old;
            } finally { rw.writeLock().unlock(); }
        }

        public int size(){
            rw.readLock().lock();
            try { return size; }
            finally { rw.readLock().unlock(); }
        }

        public RBTree.ValidationResult validate(){
            rw.readLock().lock();
            try { return tree.validate(); }
            finally { rw.readLock().unlock(); }
        }

        public List<Map.Entry<K,V>> snapshotInorder(){
            rw.readLock().lock();
            try {
                ArrayList<Map.Entry<K,V>> list = new ArrayList<>();
                for (Map.Entry<K,V> e : tree.inorder()) list.add(e);
                return list;
            } finally { rw.readLock().unlock(); }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrentRBTree<Integer,Integer> m = new ConcurrentRBTree<>();
        int threads = 4, N = 20000;
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        CountDownLatch latch = new CountDownLatch(threads);

        for (int t=0; t<threads; t++){
            final int seed = t;
            pool.submit(() -> {
                Random r = new Random(1234 + seed);
                for (int i=0;i<N;i++){
                    int k = r.nextInt(50000);
                    int op = r.nextInt(3);
                switch (op) {
                   case 0 -> m.put(k, k);
                   case 1 -> m.get(k);
                   case 2 -> m.remove(k);
                   default -> {} 
                }
                }
                latch.countDown();
            });
        }
        latch.await();
        pool.shutdown();

        System.out.println("size=" + m.size());
        System.out.println("validate=" + m.validate());

        List<Map.Entry<Integer,Integer>> list = m.snapshotInorder();
        boolean sorted = true;
        for (int i=1;i<list.size();i++) if (list.get(i-1).getKey() > list.get(i).getKey()) { sorted=false; break; }
        System.out.println("Inorder sorted: " + sorted);
    }
}
