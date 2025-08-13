import java.util.*;

public class MergeKSortedArrays {

    static class Node {
        int val, ai, bi;
        Node(int v, int a, int b) { val=v; ai=a; bi=b; }
    }

    public static int[] merge(int[][] arrays) {
        int n = 0;
        for (int[] arr : arrays) n += arr.length;
        int[] ans = new int[n];
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.val));

        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) pq.offer(new Node(arrays[i][0], i, 0));
        }

        int idx = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            ans[idx++] = cur.val;
            int ni = cur.bi + 1;
            if (ni < arrays[cur.ai].length) {
                pq.offer(new Node(arrays[cur.ai][ni], cur.ai, ni));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] in = {{1,4,5}, {1,3,4}, {2,6}};
        System.out.println(java.util.Arrays.toString(merge(in))); // [1,1,2,3,4,4,5,6]
    }
}
