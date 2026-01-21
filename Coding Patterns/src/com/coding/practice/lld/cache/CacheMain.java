package com.coding.practice.lld.cache;

import java.util.HashMap;
import java.util.PriorityQueue;

public class CacheMain {
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

        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer> (k/2 +1, (a,b) ->{
            return Integer.compare(b,a);
        } );
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer> (k/2 +1, Integer::compare);
        double[] medianArr = new double[nums.length + 1 - k] ;
        HashMap<Integer, Integer> toDeleteMap = new HashMap<Integer, Integer> ();
        for(int i = 0 ; i< k; i++) {

        }
        if(k%2 == 0)
            medianArr[0] = (maxHeap.peek() + minHeap.peek()) /2;

        int startWindow = 0;
        // [1,3,-1,-3,5,3,6,7] .. -313
        // 1, 1
        // maxH -1 , 1
        // minH 3
        for(int currIndex = 0; currIndex < nums.length ; currIndex++) {
            int currNum = nums[currIndex];
            if(maxHeap.isEmpty()) {
                maxHeap.add(currNum);
                continue;
            }
            if(currNum <= maxHeap.peek()) {
                maxHeap.add(currNum);
            } else
                minHeap.add(currNum);
            // 2,2.. 1,2.. 1,1.. 2,1.. 2,0 .. 3,0 -> 2,1, .... 1,3

            if(currIndex >= k-1) {
                //if(currINdex == k)
                //findMedian
                if(currIndex >= k) {
                    int startWindowNum = nums[startWindow++];
                    toDeleteMap.put(startWindowNum, toDeleteMap.getOrDefault(startWindowNum,0)+1);
                }
                if(maxHeap.size() +1 < minHeap.size() || maxHeap.size() > minHeap.size() + 1) {
                    rebalance(maxHeap, minHeap, toDeleteMap);
                }
                int median = median(maxHeap, minHeap,k);
                medianArr[currIndex - k] = median;
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

    private void rebalance(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap, HashMap<Integer, Integer>  toDeleteMap) {
        if(maxHeap.size() > minHeap.size()) {
            while(maxHeap.size() > minHeap.size() + 1) {
                if(maxHeap.peek()!= null) {
                    if(toDeleteMap.containsKey(maxHeap.peek())) {
                        int toDelete = maxHeap.poll();
                        int deleteNumCount = toDeleteMap.get(toDelete);
                        if(deleteNumCount-1 > 0 ){
                            toDeleteMap.put(toDelete, toDeleteMap.get(toDelete) -1);
                        } else
                            toDeleteMap.remove(toDelete);
                    } else {
                        minHeap.add(maxHeap.poll());
                    }
                }
            }
        } else {
            while(maxHeap.size() + 1 <= minHeap.size()) {
                if(minHeap.peek() != null) {
                    if(toDeleteMap.containsKey(minHeap.peek())) {
                        int toDelete = minHeap.poll();
                        int deleteNumCount = toDeleteMap.get(toDelete);
                        if(deleteNumCount -1 >0){
                            toDeleteMap.put(toDelete, toDeleteMap.get(toDelete) -1);
                        } else
                            toDeleteMap.remove(toDelete);
                    } else
                        maxHeap.add(minHeap.poll());
                }
            }
        }
    }
}
