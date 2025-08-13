import java.util.*;

public class MeetingRoomScheduler {

    public static int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> endHeap = new PriorityQueue<>();
        for (int[] itv : intervals) {
            if (!endHeap.isEmpty() && endHeap.peek() <= itv[0]) endHeap.poll();
            endHeap.offer(itv[1]);
        }
        return endHeap.size();
    }

    public static int maxTotalDurationOneRoom(int[][] intervals) {
        int n = intervals.length;
        int[][] a = intervals.clone();
        Arrays.sort(a, Comparator.comparingInt(x -> x[1])); 
        int[] end = new int[n];
        int[] start = new int[n];
        int[] w = new int[n];
        for (int i = 0; i < n; i++) { start[i] = a[i][0]; end[i] = a[i][1]; w[i] = end[i] - start[i]; }
        int[] p = new int[n]; 
        for (int i = 0; i < n; i++) {
            int lo = 0, hi = i - 1, ans = -1;
            while (lo <= hi) {
                int mid = (lo + hi) >>> 1;
                if (end[mid] <= start[i]) { ans = mid; lo = mid + 1; } else hi = mid - 1;
            }
            p[i] = ans;
        }
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int take = w[i] + (p[i] >= 0 ? dp[p[i]] : 0);
            int skip = (i > 0 ? dp[i-1] : 0);
            dp[i] = Math.max(take, skip);
        }
        return n == 0 ? 0 : dp[n-1];
    }

    public static void main(String[] args) {
        int[][] a = {{0,30},{5,10},{15,20}};
        System.out.println(minMeetingRooms(a)); // 2

        int[][] b = {{1,4},{2,3},{4,6}};
        System.out.println(maxTotalDurationOneRoom(b)); // 5（選 [2,3],[4,6]）
    }
}
