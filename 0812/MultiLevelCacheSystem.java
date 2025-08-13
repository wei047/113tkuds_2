import java.util.*;

public class MultiLevelCacheSystem {

    // 使用 LinkedHashMap(accessOrder=true) 做 LRU；滿則往下一層「下放」
    static class LRUCache<K,V> extends LinkedHashMap<K,V> {
        private final int capacity;
        LRUCache(int capacity) { super(capacity, 0.75f, true); this.capacity = capacity; }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K,V> eldest) { // ← 加上 @Override
            return size() > capacity;
        }
    }

    private final LRUCache<String, String> L1;
    private final LRUCache<String, String> L2;
    private final LRUCache<String, String> L3;

    public MultiLevelCacheSystem(int c1, int c2, int c3) {
        if (c1 <= 0 || c2 <= 0 || c3 <= 0) throw new IllegalArgumentException();
        L1 = new LRUCache<>(c1);
        L2 = new LRUCache<>(c2);
        L3 = new LRUCache<>(c3);
    }

    public String get(String key) {
        if (L1.containsKey(key)) return L1.get(key);
        if (L2.containsKey(key)) {
            String v = L2.remove(key);
            cascadePut(L1, L2, L3, key, v); // 提升到 L1
            return v;
        }
        if (L3.containsKey(key)) {
            String v = L3.remove(key);
            cascadePut(L1, L2, L3, key, v); // 提升到 L1
            return v;
        }
        return null;
    }

    public void put(String key, String value) {
        L1.remove(key); L2.remove(key); L3.remove(key);
        cascadePut(L1, L2, L3, key, value);
    }

    private static void cascadePut(LRUCache<String,String> L1, LRUCache<String,String> L2, LRUCache<String,String> L3,
                                   String k, String v) {
        L1.put(k, v);
        if (L1.size() > L1.capacity) {
            Map.Entry<String,String> ev = eldest(L1);
            String ek = ev.getKey(), evv = ev.getValue();
            L1.remove(ek);
            L2.put(ek, evv);
            if (L2.size() > L2.capacity) {
                Map.Entry<String,String> ev2 = eldest(L2);
                String ek2 = ev2.getKey(), evv2 = ev2.getValue();
                L2.remove(ek2);
                L3.put(ek2, evv2);
                if (L3.size() > L3.capacity) {
                    Map.Entry<String,String> ev3 = eldest(L3);
                    L3.remove(ev3.getKey()); // 最底層淘汰
                }
            }
        }
    }

    private static Map.Entry<String,String> eldest(LinkedHashMap<String,String> m) {
        Iterator<Map.Entry<String,String>> it = m.entrySet().iterator();
        return it.hasNext() ? it.next() : null;
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem(2, 3, 10);
        cache.put("1","A"); cache.put("2","B"); cache.put("3","C");
        System.out.println(cache.get("1"));
        cache.put("4","D"); cache.put("5","E"); cache.put("6","F");
    }
}
