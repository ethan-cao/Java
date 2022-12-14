package algorithm.leetCode;

/*
Given an unsorted array of integers, find the length of longest increasing subsequence.
There may be more than one LIS combination, it is only necessary for you to return the length.

### Example
[10,9,2,5,3,7,101,18] -> 4
[0,1,0,3,2,3] -> 4
[7,7,7,7,7,7,7] -> 1

*/

import java.util.*;

public class M_DP_Array_300 {

    public static void main(String... args) {
        System.out.println(lengthOfLIS1(new int[]{10, 9, 2, 5, 3, 7, 101, 18})); // 4
        System.out.println(lengthOfLIS1(new int[]{4, 10, 4, 3, 8, 9})); // 3
        System.out.println(lengthOfLIS1(new int[]{1, 1})); // 1
        System.out.println(lengthOfLIS1(new int[]{2, 5, 3, 6, 5, 6, 80})); // 5
    }

    // DP iterative, 91ms
    // Time: O(N^2),
    public static int lengthOfLIS(int[] nums) {
        int maxLength = 0;

        // length[i]: length of longest increasing subsequence from nums[0] to nums[i]
        int[] length = new int[nums.length];
        Arrays.fill(length, 1);

        for (int i = 1; i < nums.length; ++i) {
            for (int j = 0; j  < i; ++j) {
                if (nums[j] < nums[i]) {
                    length[i] = Math.max(length[i], length[j] + 1);
                }
            }

            maxLength = Math.max(maxLength, length[i]);
        }

        return maxLength;
    }

    // Greedy, Patience sort, 1ms
    // Time: O(NlogN)
    // https://youtu.be/22s1xxRvy28
    // https://www.cs.princeton.edu/courses/archive/spring13/cos423/lectures/LongestIncreasingSubsequence.pdf
    public static int lengthOfLIS0(int[] nums) {
        List<Integer> piles = new ArrayList<>();

        for (int num : nums) {
            int pileIdx = Collections.binarySearch(piles, num);

            // check binarySearch() doc
            // if not found, pile = - insertion_position - 1, so insertion_position = -(pile + 1)
            if (pileIdx < 0) {
                pileIdx = -(pileIdx + 1);
            }

            if (pileIdx == piles.size()) {
                piles.add(num);
            } else {
                piles.set(pileIdx, num);
            }
        }

        return piles.size();
    }

    // use own binarySearch implementation
    public static int lengthOfLIS1(int[] nums) {
        List<Integer> piles = new ArrayList<>();

        for (int num : nums) {
            int pileIdx = binarySearch(piles, num);

            if (pileIdx == piles.size()) {
                piles.add(num);
            } else {
                piles.set(pileIdx, num);
            }
        }

        return piles.size();
    }

    // binary search to find the 1st position that contains value >= target
    private static int binarySearch(List<Integer> piles, int target) {
        int left = 0;
        int right = piles.size() - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            int middleNum = piles.get(middle);

//            if (middleNum >= target && (middle == 0 || (middle - 1 >= left && piles.get(middle - 1) < target)) ) {
//                return middle;
//            }
//
//            if (middleNum > target) {
//                right = middle - 1;
//            } else {
//                left = middle + 1;
//            }
        }

        return left;
    }

}
