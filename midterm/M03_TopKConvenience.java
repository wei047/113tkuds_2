import java.io.*;
import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        Item(String n, int q) { name = n; qty = q; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(nextNonEmpty(br));
        int n = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Comparator<Item> worseFirst = (a, b) -> {
            if (a.qty != b.qty) return Integer.compare(a.qty, b.qty);
            return b.name.compareTo(a.name);
        };
        PriorityQueue<Item> pq = new PriorityQueue<>(worseFirst);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(nextNonEmpty(br));
            String name = st.nextToken();           
            int qty = Integer.parseInt(st.nextToken());
            Item cur = new Item(name, qty);

            if (pq.size() < K) pq.offer(cur);
            else if (worseFirst.compare(cur, pq.peek()) > 0) { 
                pq.poll();
                pq.offer(cur);
            }
        }

        List<Item> top = new ArrayList<>(pq);
        top.sort((a, b) -> {
            if (a.qty != b.qty) return Integer.compare(b.qty, a.qty);
            return a.name.compareTo(b.name);
        });

        StringBuilder sb = new StringBuilder();
        for (Item it : top) sb.append(it.name).append(' ').append(it.qty).append('\n');
        System.out.print(sb.toString());
    }

    private static String nextNonEmpty(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) return line;
        }
        return null;
    }
}

/*
計算複雜度
- 維護大小 K 的 min-heap：O(n log K)
- 輸出時排序 K 筆：O(K log K)
- 總時間：O(n log K)；空間：O(K)
- 同量時穩定性：以品名字典序升冪決定輸出順序。
*/
