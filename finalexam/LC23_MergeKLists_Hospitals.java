import java.io.*;
import java.util.*;

public class LC23_MergeKLists_Hospitals {
    static class Item {
        long val; int li, idx;
        Item(long v, int li, int idx){ this.val=v; this.li=li; this.idx=idx; }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        String t = br.readLine();
        if (t == null) return;
        int k = Integer.parseInt(t.trim());
        List<long[]> lists = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            String line = br.readLine();
            if (line == null) { lists.add(new long[0]); continue; }
            StringTokenizer st = new StringTokenizer(line);
            ArrayList<Long> tmp = new ArrayList<>();
            while (st.hasMoreTokens()) {
                long v = Long.parseLong(st.nextToken());
                if (v == -1) break;
                tmp.add(v);
            }
            long[] arr = new long[tmp.size()];
            for (int j = 0; j < tmp.size(); j++) arr[j] = tmp.get(j);
            lists.add(arr);
        }
        PriorityQueue<Item> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.val));
        for (int i = 0; i < k; i++) {
            long[] arr = lists.get(i);
            if (arr.length > 0) pq.offer(new Item(arr[0], i, 0));
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Item it = pq.poll();
            if (sb.length() > 0) sb.append(' ');
            sb.append(it.val);
            long[] arr = lists.get(it.li);
            int ni = it.idx + 1;
            if (ni < arr.length) pq.offer(new Item(arr[ni], it.li, ni));
        }
        if (sb.length() > 0) System.out.println(sb.toString());
    }
}
// 時間複雜度：O(N log k)
