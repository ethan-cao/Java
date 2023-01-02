package algorithm.leetCode;

/*
A sequence of numbers is called a wiggle sequence
if the differences between successive numbers strictly alternate between positive and negative.
The first difference (if one exists) may be either positive or negative.
A sequence with fewer than two elements is trivially a wiggle sequence.

[1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative.
In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences,
the first because its first two differences are positive and the second because its last difference is zero.

Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence.
A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence,
leaving the remaining elements in their original order.

1 <= nums.length <= 1000
0 <= nums[i] <= 1000


### Example
[1,7,4,9,2,5] ->  6
Explanation: difference is [6, -3, 5, -7, 3], so the entire sequence is a wiggle sequence.

[1,17,5,10,13,15,10,5,16,8] ->  7
Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].

[1,2,3,4,5,6,7,8,9] -> 2
*/

public class M_Greedy_DP_Array_376 {

    public static void main(String... args) {
//        System.out.println(wiggleMaxLength2(new int[]{0, 0}));                               // 1
//        System.out.println(wiggleMaxLength2(new int[]{0, 0, 0}));                            // 1
//        System.out.println(wiggleMaxLength2(new int[]{381, 157, 157, 134}));                 // 2
        System.out.println(wiggleMaxLength(new int[]{219, 271, 36, 245, 20, 238, 238, 89, 105}));  // 8, corner case
//        System.out.println(wiggleMaxLength2(new int[]{3, 3, 3, 2, 5}));                      // 3
//        System.out.println(wiggleMaxLength2(new int[]{1, 7, 4, 9, 2, 5}));                   // 6
//        System.out.println(wiggleMaxLength(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8})); // 7
//        System.out.println(wiggleMaxLength2(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));          // 2
    }

    // DP, iterative
    // Time: O(N), Space: O(N),  0ms
    public static int wiggleMaxLength(int[] nums) {
        int L = nums.length;

        // positiveEnds[i]: length of the longest wiggle subsequence for nums[0]...nums[i], when nums[i] > nums[i-1]
        int[] positiveEnds = new int[L];
        positiveEnds[0] = 1;

        // negativeEnds[i]: length of the longest wiggle subsequence for nums[0]...nums[i], when nums[i] < nums[i-1]
        int[] negativeEnds = new int[L];
        negativeEnds[0] = 1;

        for (int i = 1; i < L; ++i) {
            int diff = nums[i] - nums[i - 1];

            if (diff > 0) {
                positiveEnds[i] = negativeEnds[i - 1] + 1;
                negativeEnds[i] = negativeEnds[i - 1];
            } else if (diff < 0) {
                positiveEnds[i] = positiveEnds[i - 1];
                negativeEnds[i] = positiveEnds[i - 1] + 1;
            } else {
                positiveEnds[i] = positiveEnds[i - 1];
                negativeEnds[i] = negativeEnds[i - 1];
            }
        }

        return Math.max(positiveEnds[L -1], negativeEnds[L - 1]);
    }

    // DP, iterative
    // Time: O(N), Space: O(N)
    // 0ms
    public static int wiggleMaxLength0(int[] nums) {
        int L = nums.length;

        int[] counts = new int[L];
        counts[0] = 1;

        int preDiff = 0;

        for (int i = 1; i < L; ++i) {
            int diff = nums[i] - nums[i - 1];

            if (diff == 0) {
                counts[i] = counts[i - 1];
            } else if (diff > 0) {
                if (preDiff == 0) {
                    counts[i] = 2;
                } else if (preDiff < 0) {
                    counts[i] = counts[i - 1] + 1;
                } else {
                    counts[i] = counts[i - 1];
                }
            } else {
                if (preDiff == 0) {
                    counts[i] = 2;
                } else if (preDiff > 0) {
                    counts[i] = counts[i - 1] + 1;
                } else {
                    counts[i] = counts[i - 1];
                } 
            }

            // !!! update preDiff iff current isn't 0, otherwise we reset the length to 2
            // the question is about sequence (discrete), so we can skip updating previous difference
            // e.g. [1, 5, 2, 3, 3, 1] -> 5
            if (diff != 0) {
                preDiff = diff;
            }
        }

        return counts[L - 1];
    }

    // Greedy
    public static int wiggleMaxLength2(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        int positiveWiggleLength = 1;
        int negativeWiggleLength = 1;

        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[i - 1]) {
                positiveWiggleLength = negativeWiggleLength + 1;
            } else if (nums[i] < nums[i - 1]) {
                negativeWiggleLength = positiveWiggleLength + 1;
            }
        }

        return Math.max(positiveWiggleLength, negativeWiggleLength);
    }

    // Greedy
    public int wiggleMaxLength3(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        int wiggleSequenceLength = 1;
        int previousDiff = 0;

        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];

            if ((diff > 0 && previousDiff <= 0) || (diff < 0 && previousDiff >= 0)) {
                wiggleSequenceLength++;
                previousDiff = diff;
            }
        }

        return wiggleSequenceLength;
    }

}