package algorithm.leetCode;

/*
Given an integer array nums, return the number of longest increasing subsequences.
Notice that the sequence has to be strictly increasing.

1 <= nums.length <= 2000
-106 <= nums[i] <= 106

### Example
nums = [1,3,5,4,7] -> 2
longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].

nums = [2,2,2,2,2] -> 5
length of longest continuous increasing subsequence is 1,
and there are 5 subsequences' length is 1, so output 5.

*/

import java.util.Arrays;

public class M_DP_673 {

    public static void main(String[] args) {
    }

    // DP, 17ms
    // Time: O(N^2)
    public int findNumberOfLIS(int[] nums) {
        int L = nums.length;

        int count = 0;
        int longestLength = 0;

        // the length of the Longest Increasing Subsequence which ends with nums[i]
        int[] length = new int[L];
        // the number of the Longest Increasing Subsequence which ends with nums[i]
        int[] counts = new int[L];

        for (int i = 0; i < L; ++i) {
            length[i] = 1;
            counts[i] = 1;

            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {

                    if (length[i] == length[j] + 1) {
                        // if combining with i makes another longest increasing subsequence
                        counts[i] += counts[j];
                    } else if (length[i] < length[j] + 1) {
                        // if combining with i makes a longer increasing subsequence
                        length[i] = length[j] + 1;
                        counts[i] = counts[j];
                    }
                }
            }

            if (length[i] == longestLength) {
                count += counts[i];
            } else if (length[i] > longestLength) {
                longestLength = length[i];
                count = counts[i];
            }
        }

        return count;
    }
}

