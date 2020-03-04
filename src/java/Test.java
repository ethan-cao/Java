import java.util.*;

public class Test {

    public static void main(String... args) {
        System.out.println(minMeetingRooms(new int[][]{{1, 2}, {2, 3}, {3,4}, {1,3}}));  // 1
    }

    public static int minMeetingRooms(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int room = 0;
        for(int i = 0; i < intervals.length; i++){
            pq.offer(intervals[i][1]);
            if(intervals[i][0] < pq.peek())  room++;
            else{
                pq.poll();
            }
        }
        return room;
    }
}