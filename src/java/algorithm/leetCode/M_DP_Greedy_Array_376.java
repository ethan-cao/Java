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
        System.out.println(wiggleMaxLength(new int[]{1, 7, 4, 9, 2, 5}));                   // 6
        System.out.println(wiggleMaxLength(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8})); // 7
        System.out.println(wiggleMaxLength(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));          // 2
    }

    // DP
    // Time: O(), Space: O()
    public static int wiggleMaxLength(int[] nums) {
        int longestWiggleLength = 0;

        return longestWiggleLength;
    }

    public static int wiggleMaxLength1(int[] nums) {
        int longestWiggleLength = 0;

        return longestWiggleLength;
    }
}
