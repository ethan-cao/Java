package algorithm.leetCode;

/*
Given an unsorted array of integers, find the length of longest increasing subsequence.
There may be more than one LIS combination, it is only necessary for you to return the length.

1 <= nums.length <= 2500
-104 <= nums[i] <= 104

### Example
[10,9,2,5,3,7,101,18] -> 4
[0,1,0,3,2,3] -> 4
[7,7,7,7,7,7,7] -> 1

*/

import java.util.*;

public class M_DP_Array_300 {

    public static void main(String... args) {
        // System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18})); // 4
        System.out.println(lengthOfLIS(new int[] { 4, 10, 4, 3, 8, 9 })); // 3
        // System.out.println(lengthOfLIS(new int[]{1, 1})); // 1
        // System.out.println(lengthOfLIS(new int[]{2, 5, 3, 6, 5, 6, 80})); // 5
    }

    // Greedy, Patience sorting + Binary search
    // Time: O(NlogN) 2ms
    // https://youtu.be/22s1xxRvy28
    public static int lengthOfLIS(int[] nums) {
        int L = nums.length;

        int[] piles = new int[L];
        int pileCount = 0;

        for (int i = 0; i < L; ++i) {
            int num = nums[i];

            int idx = findPileIdx(piles, 0, pileCount - 1, num);
            piles[idx] = num;

            if (idx == pileCount) {
                pileCount++;
            }
        }

        // pile count is >= length of increasing sequence
        return pileCount;
    }

    // Binary search, find left boundary
    // find from left, the 1st one that is > target
    private static int findPileIdx(int[] piles, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (piles[mid] < target) {
                left = mid + 1;
            } else if (piles[mid] > target) {
                right = mid - 1;
            } else if (piles[mid] == target) {
                right = mid - 1;
            }
        }

        return left;
    }

    // DP iterative, 91ms
    // Time: O(N^2),
    public static int lengthOfLIS1(int[] nums) {
        int L = nums.length;

        // counts[i]: length of longest increasing subsequence ending at i
        int[] counts = new int[L];
        Arrays.fill(counts, 1);

        int maxCount = 1;

        for (int i = 1; i < L; ++i) {
            // check each item before i 
            
            for (int j = i - 1; j >= 0; --j) {
            // alternatively 
            // for (int j = 0; j < i; ++j) {

                if (nums[j] < nums[i]) {
                    // if subsequence [0, j] can connect with i, the subsequence is [0...j, i]
                    // that is counts[j] + 1
                    counts[i] = Math.max(counts[i], counts[j] + 1);
                }

                maxCount = Math.max(maxCount, counts[i]);
            }
        }

        return maxCount;
    }
}
