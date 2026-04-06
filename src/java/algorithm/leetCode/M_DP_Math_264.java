package algorithm.leetCode;

/*
Write a program to find the n-th ugly number.
Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
1 is typically treated as an ugly number.
n does not exceed 1690.

### Example
10 -> 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

*/

import java.util.*;

public class M_DP_Math_264 {

    public static void main(String... args) {
        System.out.println(nthUglyNumber(1)); // 1
        System.out.println(nthUglyNumber(2)); // 2
        System.out.println(nthUglyNumber(3)); // 3
        System.out.println(nthUglyNumber(10)); // 12
    }

    static int PRIME_FACTOR_2 = 2;
    static int PRIME_FACTOR_3 = 3;
    static int PRIME_FACTOR_5 = 5;

    // DP, 2ms,   Time: O(N)
    // write the first 10 ugly numbers, you will find
    // any ugly number is min(2 * uglyNums[i], 3 * uglyNums[j], 5 * uglyNums[k])
    // i, j, k points to a smaller ugly number that just needs to multiple 2,3 or 5
    public static int nthUglyNumber(int n) {
        int[] uglyNums = new int[n];
        uglyNums[0] = 1;
        
        // keep index seperate for 2, 3, 5
        int idx2 = 0;        
        int idx3 = 0;        
        int idx5 = 0;        

        for (int i = 1; i < n; ++i) { 
            int nextUglyNum2 = 2 * uglyNums[idx2];
            int nextUglyNum3 = 3 * uglyNums[idx3];
            int nextUglyNum5 = 5 * uglyNums[idx5];

            uglyNums[i] = Math.min(nextUglyNum2, Math.min(nextUglyNum3, nextUglyNum5));
        
            // move the index once we found a match
            // !!! DO NOT use else, might need to multiple index
            if (uglyNums[i] == nextUglyNum2) idx2++;
            if (uglyNums[i] == nextUglyNum3) idx3++;
            if (uglyNums[i] == nextUglyNum5) idx5++;
        }


        return uglyNums[n - 1];
    }

    //----------------------------------------------------------------------------------------------
    // 51ms
    // Time: 
    public int nthUglyNumber2(int n) {
        TreeSet<Long> sortedSet = new TreeSet<>();
        sortedSet.add(1L);

        // i is the index of the first index in sortedSet
        for (int i = 0; i < n - 1; ++i) {
            long num = sortedSet.pollFirst();

            sortedSet.add(num * 2);
            sortedSet.add(num * 3);
            sortedSet.add(num * 5);
        }

        return sortedSet.pollFirst().intValue();
    }

    //----------------------------------------------------------------------------------------------
    // Heap, 67ms
    // Time: 
    public int nthUglyNumber1(int n) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        minHeap.offer(1L);

        // i is the index of the root in heap
        for (int i = 0; i < n - 1; ++i) {
            long num = minHeap.poll();

            while (!minHeap.isEmpty() && minHeap.peek() == num) {
                minHeap.poll();
            }

            minHeap.offer(num * 2);
            minHeap.offer(num * 3);
            minHeap.offer(num * 5);
        }

        return minHeap.peek().intValue();
    }

}