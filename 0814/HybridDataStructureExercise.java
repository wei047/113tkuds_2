import java.util.*;

/** 練習 7：HybridMap = HashMap + RBTree，兼顧 O(1) get 與 O(log n) 有序操作。 */
public class HybridDataStructureExercise {

    public static class HybridMap<K extends Comparable<K>, V> {
        private final HashMap<K,V> dict = new HashMap<>();
        private final RBTree<K,V> order = new RBTree<>();

        public int size(){ return dict.size(); }
        public boolean containsKey(K k){ return dict.containsKey(k); }
        public V get(K k){ return dict.get(k); }

        public V put(K k, V v){
            V old = dict.put(k, v);
            order.insert(k, v); // RBTree.insert 若已存在會更新 value
            return old;
        }

        public V remove(K k){
            V old = dict.remove(k);
            if (old != null) order.delete(k);
            return old;
        }

        public K firstKey(){
            for (Map.Entry<K,V> e : order.inorder()) return e.getKey();
            return null;
        }

        public K lastKey(){
            for (Map.Entry<K,V> e : order.reverse()) return e.getKey();
            return null;
        }

        public K ceilingKey(K k){
            for (Map.Entry<K,V> e : order.range(k, null, true, true)) return e.getKey();
            return null;
        }

        public K floorKey(K k){
    for (Map.Entry<K,V> e : order.reverse()) {
        if (e.getKey().compareTo(k) <= 0) return e.getKey();
    }
    return null;
}

        public Iterable<Map.Entry<K,V>> inorder(){ return order.inorder(); }
    }

    /* ===== Demo ===== */
    public static void main(String[] args){
        HybridMap<Integer,String> hm = new HybridMap<>();
        hm.put(5,"a"); hm.put(2,"b"); hm.put(8,"c"); hm.put(1,"d"); hm.put(3,"e");

        System.out.println("size="+hm.size()+", first="+hm.firstKey()+", last="+hm.lastKey());
        System.out.println("get(3)="+hm.get(3)+", ceilingKey(4)="+hm.ceilingKey(4)+", floorKey(4)="+hm.floorKey(4));

        System.out.print("inorder keys: ");
        for (Map.Entry<Integer,String> e : hm.inorder()) System.out.print(e.getKey()+" ");
        System.out.println();

        hm.remove(2);
        System.out.print("after remove(2) inorder keys: ");
        for (Map.Entry<Integer,String> e : hm.inorder()) System.out.print(e.getKey()+" ");
        System.out.println();
    }
}
