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

### Example
[1,7,4,9,2,5] ->  6
Explanation: The entire sequence is a wiggle sequence.

[1,17,5,10,13,15,10,5,16,8] ->  7
Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].

[1,2,3,4,5,6,7,8,9] -> 2
*/

public class M_DP_Greedy_Array_376 {

    public static void main(String... args) {
        System.out.println(wiggleMaxLength2(new int[]{0, 0}));                               // 1, corner case
        System.out.println(wiggleMaxLength2(new int[]{0, 0, 0}));                            // 1, corner case
        System.out.println(wiggleMaxLength2(new int[]{381, 157, 157, 134}));                 // 2
        System.out.println(wiggleMaxLength2(new int[]{219, 271, 36, 245, 20, 238, 238, 89, 105}));  // 8, corner case
        System.out.println(wiggleMaxLength2(new int[]{3, 3, 3, 2, 5}));                      // 3
        System.out.println(wiggleMaxLength2(new int[]{1, 7, 4, 9, 2, 5}));                   // 6
        System.out.println(wiggleMaxLength2(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8})); // 7
        System.out.println(wiggleMaxLength2(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));          // 2
    }

    // DP
    // Time: O(N), Space: O(N)
    public static int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        // maxWiggleLength[i]: length of the longest wiggle subsequence for nums[0]...nums[i]
        // maxWiggleLength[i] =  maxWiggleLength[i-1] + (wiggle ? 1 : 0)
        int[] maxWiggleLength = new int[nums.length];
        maxWiggleLength[0] = 1;

        boolean previousDelta = false;
        for (int i = 1; i < maxWiggleLength.length; ++i) {

            // if the current value equals to the previous, skip equal case
            if (nums[i] == nums[i - 1]) {
                maxWiggleLength[i] = maxWiggleLength[i - 1];
            } else {
                boolean currentDelta = nums[i] - nums[i - 1] > 0;

                if (maxWiggleLength[i - 1] == 1) {
                    // just like start from nums[i-1], no need to consider previousDelta
                    maxWiggleLength[i] = 2;
                } else {
                    maxWiggleLength[i] = maxWiggleLength[i - 1] + (previousDelta == currentDelta ? 0 : 1);
                }

                previousDelta = currentDelta;
            }
        }

        return maxWiggleLength[maxWiggleLength.length - 1];
    }

    // DP
    // Time: O(N), Space: O(N)
    public static int wiggleMaxLength1(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        // positiveWiggleLength[i]: length of the longest wiggle subsequence for nums[0]...nums[i], when nums[i] > nums[i-1]
        int[] positiveWiggleLength = new int[nums.length];
        positiveWiggleLength[0] = 1;

        // negativeWiggleLength[i]: length of the longest wiggle subsequence for nums[0]...nums[i], when nums[i] < nums[i-1]
        int[] negativeWiggleLength = new int[nums.length];
        negativeWiggleLength[0] = 1;

        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[i - 1]) {
                positiveWiggleLength[i] = negativeWiggleLength[i - 1] + 1;
                negativeWiggleLength[i] = negativeWiggleLength[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                positiveWiggleLength[i] = positiveWiggleLength[i - 1];
                negativeWiggleLength[i] = positiveWiggleLength[i - 1] + 1;
            } else {
                positiveWiggleLength[i] = positiveWiggleLength[i - 1];
                negativeWiggleLength[i] = negativeWiggleLength[i - 1];
            }
        }

        return Math.max(positiveWiggleLength[nums.length - 1], negativeWiggleLength[nums.length - 1]);
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
        if (nums.length == 0) return 0;
        int count = 1;
        int prevDiff = 0;
        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prevDiff <= 0) ||
                    (diff < 0 && prevDiff >= 0)) {
                count++;
                prevDiff = diff;
            }
        }
        return count;
    }

}