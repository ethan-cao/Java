package algorithm.leetCode;

/*
Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

### Example
[2,2,1] -> 1
[4,1,2,1,2] -> 4

### Condition

### Essential problem

### Corner case

*/

public class E_Array_BitManipulation_136 {

    public static void main(String... args) {
        int[] a1 = {2, 2, 1};
        System.out.println(singleNumber(a1)); // 1

        int[] a2 = {4, 1, 2, 1, 2};
        System.out.println(singleNumber(a2)); // 4
    }

    // known that A XOR A = 0 and the XOR operator is commutative
    public static int singleNumber(int[] nums) {
        int singleNum = nums[0];

        for (int i = 1; i< nums.length; ++i) {
            singleNum ^= nums[i];
        }

        return singleNum;
    }
}