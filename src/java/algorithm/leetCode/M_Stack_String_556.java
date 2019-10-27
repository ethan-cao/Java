package algorithm.leetCode;

/*
Given a circular array (the next element of the last element is the first element of the array),
print the Next Greater Number for every element.
The Next Greater Number of a number x is the first greater number to its traversing-order next in the array,
which means you could search circularly to find its next greater number.
If it doesn't exist, output -1 for this number.

The length of given array won't exceed 10000.

### Example
[1,2,1] -> [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number;
The second 1's next greater number needs to search circularly, which is also 2.

*/

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class M_Stack_Array_503 {

    public static void main(String... args) {
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{1, 2, 1}))); // 2, -1, 2
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{1, 2, 14, 2, 1, 0}))); // 2,14,-1,14,2,1
    }

    // Stack
    // Time: O(N), Space: O(N)
    public static int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }

        // default value is 0, not good. use -1, assume no larger element by default
        int[] nextGreaterElements = new int[nums.length];
        Arrays.fill(nextGreaterElements, -1);

        // store indexes of decreasing subsequence
        Deque<Integer> decreasingElementIndices = new ArrayDeque<>();

        // !!! extend the array to handle circular array
        for (int i = 0; i < nums.length * 2; ++i) {
            int num = nums[i % nums.length];

            while (!decreasingElementIndices.isEmpty() && nums[decreasingElementIndices.peekFirst()] < num) {
                nextGreaterElements[decreasingElementIndices.pop()] = num;
            }

            // store index only once
            if (i < nums.length) {
                decreasingElementIndices.push(i);
            }
        }

        return nextGreaterElements;
    }


    // Stack
    // Time: O(N), Space: O(N)
    public static int[] nextGreaterElements1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }

        // default value is 0, not good. use -1, assume no larger element by default
        int[] nextGreaterElements = new int[nums.length];
        Arrays.fill(nextGreaterElements, -1);


        
        return nextGreaterElements;
    }

    // Binary search
}