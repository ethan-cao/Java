package algorithm.leetCode;

/*
Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 100

### Example
nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]  -> 5
nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]  ->  3
Explanation: The repeated subarray with maximum length is [3,2,1].

*/

import java.util.Arrays;

public class M_DP_718 {

    public static void main(String[] args) {
        System.out.println(findLength(new int[] { 1, 2, 3, 2, 1 }, new int[] { 3, 2, 1, 4, 7 })); // 3
    }
    
    // DP, iterative
    // Time: O(M*N), 50ms
    public static int findLength(int[] nums1, int[] nums2) {
        int L1 = nums1.length;
        int L2 = nums2.length;

        // counts[i][j]: longest common subarray length, containing nums1[i] and nums2[j]
        int[][] counts = new int[L1][L2];
        int maxCount = 0;

        // BASE
        for (int i = 0; i < L1; ++i) {
            counts[i][0] = nums1[i] == nums2[0] ? 1 : 0;
            maxCount = Math.max(maxCount, counts[i][0]);
        }

        // BASE
        for (int i = 0; i < L2; ++i) {
            counts[0][i] = nums1[0] == nums2[i] ? 1 : 0;
            maxCount = Math.max(maxCount, counts[0][i]);
        }

        for (int idx1 = 1; idx1 < L1; ++idx1) {
            for (int idx2 = 1; idx2 < L2; ++idx2) {

                if (nums1[idx1] == nums2[idx2]) {
                    // this is correct for longest common array, contiguous match
                    counts[idx1][idx2] = counts[idx1 - 1][idx2 - 1] + 1;

                    // this is correct for longest common subsequence
                    // taking the maximum of counts[i-1][j] and counts[i][j-1] allows us to build on non-contiguous match
                    // counts[i][j] = Math.max(counts[i-1][j], counts[i][j-1]) + 1
                
                } else {
                    counts[idx1][idx2] = 0;
                }

                maxCount = Math.max(maxCount, counts[idx1][idx2]);
            }
        }

        return maxCount;
    }

    // DP, iterative, condensed space
    // Time: O(M*N), 67ms
    public int findLength1(int[] nums1, int[] nums2) {
        int L1 = nums1.length;
        int L2 = nums2.length;

        // since we only need 2 rows
        int[] cur = new int[L1];
        int[] pre = new int[L1];

        int max = 0;

        for (int i = 0; i < L1; ++i) {
            for (int j = 0; j < L2; ++j) {

                if (nums1[i] == nums2[j]) {
                    cur[j] = j >= 1 ? pre[j - 1] + 1 : 1;
                    max = Math.max(max, cur[j]);
                } else {
                    cur[j] = 0;
                }

            }

            pre = Arrays.copyOf(cur, cur.length);
        }

        return max;
    }    


    // DP, recursive, memo
    // 404ms
    int max = 0;

    public int findLength0(int[] nums1, int[] nums2) {
        int L1 = nums1.length;
        int L2 = nums2.length;

        Integer[][] memo = new Integer[L1][L2];

        count(nums1, nums2, L1 - 1, L2 - 1, memo);

        return this.max;
    }

    private int count(int[] nums1, int[] nums2, int idx1, int idx2, Integer[][] memo) {
        if (idx1 < 0 || idx2 < 0) {
            return 0;
        }

        if (memo[idx1][idx2] != null) {
            return memo[idx1][idx2];
        }

        int length = 0;

        if (nums1[idx1] == nums2[idx2]) {
            length = 1 + count(nums1, nums2, idx1 - 1, idx2 - 1, memo);
            this.max = Math.max(this.max, length);
        }

        // DFS
        count(nums1, nums2, idx1 - 1, idx2, memo);
        count(nums1, nums2, idx1, idx2 - 1, memo);

        memo[idx1][idx2] = length;

        return memo[idx1][idx2];
    }

}
