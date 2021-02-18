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

    // DP, 2ms
    // Time: O(N)
    // write the first 10 ugly numbers, we can find any ugly number is min(2 * i, 3 * j, 5 * k)
    // i, j, k points to previous ugly numbers, starting from the 1st ugly number 1, once used move to the next
    public static int nthUglyNumber(int n) {
        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;

        int idx2 = 0;
        int idx3 = 0;
        int idx5 = 0;

        for (int i = 1; i < n; ++i) {
            uglyNumbers[i] = Math.min(uglyNumbers[idx2] * 2, Math.min(uglyNumbers[idx3] * 3, uglyNumbers[idx5] * 5));

            // Move according index to avoid getting existing number
            if (uglyNumbers[i] == uglyNumbers[idx2] * 2) idx2++;
            if (uglyNumbers[i] == uglyNumbers[idx3] * 3) idx3++;
            if (uglyNumbers[i] == uglyNumbers[idx5] * 5) idx5++;
        }

        return uglyNumbers[n - 1];
    }

    // 51ms
    // Time: O
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

    // Heap, 67ms
    // Time: O
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