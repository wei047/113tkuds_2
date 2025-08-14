import java.util.*;

/** 練習 9：簡易 Benchmark，對比 RBTree vs TreeMap/HashMap 的 insert/get/remove。 */
public class BenchmarkFrameworkExercise {

    static class Timer {
        long t0;
        void start(){ t0 = System.nanoTime(); }
        long stopNs(){ return System.nanoTime() - t0; }
        String ms(long ns){ return String.format("%.3f ms", ns/1_000_000.0); }
    }

    public static void main(String[] args){
        final int N = 100_000;
        int[] keys = new int[N];
        Random r = new Random(42);
        for (int i=0;i<N;i++) keys[i] = r.nextInt(N*5);

        // ===== RBTree =====
        RBTree<Integer,Integer> rb = new RBTree<>();
        Timer trb = new Timer();

        trb.start();
        for (int k : keys) rb.insert(k, k);
        long rbIns = trb.stopNs();

        trb.start();
        for (int k : keys) { RBTree.Node<Integer,Integer> n = rb.search(k); if (n!=null && n.value!=k) throw new AssertionError(); }
        long rbGet = trb.stopNs();

        trb.start();
        for (int k : keys) rb.delete(k);
        long rbDel = trb.stopNs();

        // ===== TreeMap =====
        TreeMap<Integer,Integer> tm = new TreeMap<>();
        Timer ttm = new Timer();

        ttm.start();
        for (int k : keys) tm.put(k, k);
        long tmIns = ttm.stopNs();

        ttm.start();
        for (int k : keys) { Integer v = tm.get(k); if (v!=null && v!=k) throw new AssertionError(); }
        long tmGet = ttm.stopNs();

        ttm.start();
        for (int k : keys) tm.remove(k);
        long tmDel = ttm.stopNs();

        // ===== HashMap（參考）=====
        HashMap<Integer,Integer> hm = new HashMap<>();
        Timer thm = new Timer();

        thm.start();
        for (int k : keys) hm.put(k, k);
        long hmIns = thm.stopNs();

        thm.start();
        for (int k : keys) { Integer v = hm.get(k); if (v!=null && v!=k) throw new AssertionError(); }
        long hmGet = thm.stopNs();

        thm.start();
        for (int k : keys) hm.remove(k);
        long hmDel = thm.stopNs();

        // ===== 結果 =====
        System.out.println("RBTree     insert=" + trb.ms(rbIns) + ", get=" + trb.ms(rbGet) + ", delete=" + trb.ms(rbDel));
        System.out.println("TreeMap    insert=" + trb.ms(tmIns) + ", get=" + trb.ms(tmGet) + ", delete=" + trb.ms(tmDel));
        System.out.println("HashMap(*) insert=" + trb.ms(hmIns) + ", get=" + trb.ms(hmGet) + ", delete=" + trb.ms(hmDel));
        System.out.println("* HashMap 無排序，僅作速度參考。");

        // 小檢查：RBTree 維持有效
        System.out.println("RBTree validate after empty: " + rb.validate());
    }
}
