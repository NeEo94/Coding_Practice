import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

class SlidingWindowMedian {

    public static void main(String[] args){
        int[] arr = {1,2,3,4,2,3,1,4,2};
        SlidingWindowMedian swm = new SlidingWindowMedian();
        System.out.println(swm.medianSlidingWindow(arr,1));
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        // median one max heap one min to order the elements and top two representing midle elements.. also need to validate how to figure out which one is middle incase of odd length array
        // 0 - max, 1- if larger than top move to min, else
        // 4  5   , 3       if curr < topMax move to maxHeap else minHeap
        // 4  5             if minHeap.size > maxHeap.size +1 then adjust the sizes
        // 3
        // // how to remove out of heaps the start of sliding window
        // // first median done,
        // // window moves, if the top of heaps != start of sliding window then add to toDelete Set
        // // 2,3,3,4         1,
        // todelete contains nums

        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer> ((a, b) ->{
            return Integer.compare(b,a);
        } );
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer> (Integer::compare);
        double[] medianArr = new double[nums.length + 1 - k] ;
        HashMap<Integer, Integer> toDeleteMap = new HashMap<Integer, Integer> ();
        for(int i = 0 ; i< k; i++) {

        }
        if(k%2 == 0)
            medianArr[0] = (maxHeap.peek() + minHeap.peek()) /2;

        int startWindow = 0;
        // [1,3,-1,-3,5,3,6,7] .. -313
        //           -1,3,
        // currIn = 3
        // startWindow = 1
        // maxH -3, -1 , 1
        // minH  3
        // todel - 1
        for(int currIndex = 0; currIndex < nums.length ; currIndex++) {
            int currNum = nums[currIndex];
            if(maxHeap.isEmpty()) {
                maxHeap.add(currNum);
              //  continue;
            } else {
                if(currNum <= maxHeap.peek()) {
                    maxHeap.add(currNum);
                } else
                    minHeap.add(currNum);
            }

            // 2,2.. 1,2.. 1,1.. 2,1.. 2,0 .. 3,0 -> 2,1, .... 1,3

            if(currIndex >= k-1) {
                //if(currINdex == k)
                //findMedian
                if(currIndex >= k) {
                    int startWindowNum = nums[startWindow]; //
                    //toDeleteMap.put(startWindowNum, toDeleteMap.getOrDefault(startWindowNum,0)+1);
                    if(startWindowNum <= maxHeap.peek()) {
                        maxHeap.remove(startWindowNum);
                    } else if(startWindowNum >= minHeap.peek()) {
                        minHeap.remove(startWindowNum);
                    }
                    startWindow++;
                }
                System.out.println("currInd "+ currIndex+ ", maxHeapPeek "+maxHeap.peek());

                if(maxHeap.size() +1 < minHeap.size() || maxHeap.size() > minHeap.size() + 1 || maxHeap.size() < minHeap.size()) {
                    System.out.println("rebalancing");
                    rebalance(maxHeap, minHeap);
                }
                int median = median(maxHeap, minHeap,k);
                if(currIndex >= k-1) {
                    System.out.println(median+":"+ (currIndex-k));
                    medianArr[currIndex - k+1] = median;
                }
            }
        }
        return  medianArr;
    }

    private int median(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap, int k) {
        if(k %2 ==0)
            return (maxHeap.peek() + minHeap.peek()) / 2;
        else
            return maxHeap.peek();
    }

    private void deleteItems(PriorityQueue<Integer> heap, HashMap<Integer, Integer> toDeleteMap) {
        while(heap.peek() != null && toDeleteMap.containsKey(heap.peek())) {
            int toDelete = heap.poll();
            int deleteNumCount = toDeleteMap.get(toDelete);
            if(deleteNumCount-1 > 0 ){
                toDeleteMap.put(toDelete, toDeleteMap.get(toDelete) -1);
            } else
                toDeleteMap.remove(toDelete);
        }
    }

    private void rebalance(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        if(maxHeap.size() > minHeap.size()) {
            while(maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            }
        } else {
            while(maxHeap.size() + 1 <= minHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
        }
        System.out.println("REBALANCED maxHeap "+ maxHeap);
        System.out.println("REBALANCED minHeap "+ minHeap);
    }
}