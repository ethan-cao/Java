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
    }

    // Stack
    public static int[] nextGreaterElements(int[] nums) {
        return null;
    }
}