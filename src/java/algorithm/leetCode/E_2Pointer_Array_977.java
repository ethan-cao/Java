package algorithm.leetCode;

/*
Given an array of integers A sorted in non-decreasing order,
return an array of the squares of each number, also in sorted non-decreasing order.

1 <= A.length <= 10000
-10000 <= A[i] <= 10000

### Example
[-4,-1,0,3,10] -> [0,1,9,16,100]
[-7,-3,2,3,11] -> [4,9,9,49,121]

### Review:

*/

import java.util.*;

public class E_2Pointer_Array_977 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortedSquares2(new int[]{-4, -1, 0, 3, 10})));
        System.out.println(Arrays.toString(sortedSquares2(new int[]{-7, -3, 2, 3, 11})));
    }

    // Time: O(NlogN), 2ms
    public static int[] sortedSquares0(int[] A) {
        int[] sortedSquares = new int[A.length];

        for (int i = 0; i < A.length; ++i) {
            sortedSquares[i] = A[i] * A[i];
        }

        Arrays.sort(sortedSquares);

        return sortedSquares;
    }

    // Time: O(NlogN), 15ms
    public static int[] sortedSquares1(int[] A) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // N
        for (int i = 0; i < A.length; ++i) {
            heap.add(A[i] * A[i]);  // logN
        }

        Integer[] newA = heap.toArray(new Integer[heap.size()]);
        int[] sortedSquares = new int[A.length];

        for (int i = 0; i < A.length; ++i) {
            sortedSquares[i] = heap.remove();
        }

        return sortedSquares;
    }

    // Time: O(N), 2ms
    public static int[] sortedSquares2(int[] A) {
        int[] sortedSquares = new int[A.length];

        int left = 0;
        int right = A.length - 1;

        // !!! left can equal to right for once, as long as left does not across right
        for (int i = A.length - 1; i >= 0 && left <= right; --i) {
            if (Math.abs(A[left]) < Math.abs(A[right])) {
                sortedSquares[i] = A[right] * A[right];
                right--;
            } else {
                sortedSquares[i] = A[left] * A[left];
                left++;
            }
        }

        return sortedSquares;
    }

}
