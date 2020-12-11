package algorithm.leetCode;

/*
You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. 
Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. 
If it does not exist, output -1 for this number.

for x in nums1, find the same number in nums2 and check if there is any number larger than x on its right side in nums2. 

All elements in nums1 and nums2 are unique.
The length of both nums1 and nums2 would not exceed 1000.

### Example
nums1 = [4,1,2], nums2 = [1,3,4,2] -> [-1,3,-1]
nums1 = [2,4], nums2 = [1,2,3,4]   -> [3,-1]

*/

import java.util.*;

public class M_Stack_496 {

    // Stack & HashMap, 3ms
    // Time: O(N)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];

        Map<Integer, Integer> nextGreater = new HashMap<>(); // item -> item's next greater one
        Deque<Integer> stack = new ArrayDeque<>();  // monotonic decreasing

        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                int smaller = stack.pop();
                nextGreater.put(smaller, num);
            }

            stack.push(num);
        }

        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreater.getOrDefault(nums1[i], -1);
        }

        return result;
    }

}
