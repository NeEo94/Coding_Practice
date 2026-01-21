import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class MeetingRooms{
    public static int findSets(int[][] intervals) {
//        Comparator<int[]> comparator1 = new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if(o1[0] < o2[0])
//                    return -1;
//                else if(o1[0] == o2[0])
//                    return Integer.compare(o1[1], o2[1]);
//                else
//                    return 1;
//            }
//        };
//
//        //Collections.sort(Arrays.asList(intervals), comparator1);
//
//        Arrays.sort(intervals, comparator1);
        PriorityQueue<Integer> pq = new PriorityQueue<>(5, (a,b)->{
            return Integer.compare(b,a);
        });
        pq.size();
        //AtomicLong
        Queue<Integer> queue = new LinkedList<>();
        HashMap mp = new HashMap();
        //for()
        Arrays.sort(intervals, (o1,o2) -> {
            if(o1[0] != o2[0]) {
                return Integer.compare(o1[0], o2[0]);
            } else
                return Integer.compare(o1[1], o2[1]);
        });

        int currentIndex = 0, start =0;

        int roomsRequired = 0;
        int maxRoomsRequired = 0;

        while(currentIndex < intervals.length) {
            int[] startInterval = intervals[start];
            int[] currInterval = intervals[currentIndex];
            // current interval is inside start or current interval overlaps start ineterval
            if(currInterval[0] == startInterval[0] || currInterval[1] <= startInterval[1]) {
                roomsRequired++;
                maxRoomsRequired = Math.max(roomsRequired, maxRoomsRequired);
            } else if(currInterval[1] > startInterval[0] && currInterval[0] < startInterval[1]) {
                roomsRequired++;
                maxRoomsRequired = Math.max(roomsRequired, maxRoomsRequired);
            } else if(currInterval[0] >= startInterval[1]) {
                while(currInterval[0] >= startInterval[1]) {
                    start++;
                    startInterval = intervals[start];
                    roomsRequired --;
                }
            }
            currentIndex++;
        }
        return maxRoomsRequired;
    }
}