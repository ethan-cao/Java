package leetCode;

/*
Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 2^31.
Find the maximum result of ai XOR aj, where  0 ≤ i,j < n.

Could you do this in O(n) runtime?

### Example
[3, 10, 5, 25, 2, 8] -> 28
Explanation: The maximum result is 5 ^(XOR) 25 = 28.

### Condition

### Essential problem

### Corner case

*/

import java.util.HashSet;
import java.util.Set;

public class M_BitManipulation_Trie_421 {

    public static void main(String[] args) {
        int[] a = {5, 25};  // 28
//        int[] a = {3, 10, 5, 25, 2, 8};  // 28
//        int[] a = {3, 32, 5, 25, 10}; //57

        System.out.println(findMaximumXOR1(a));
    }

    public static int findMaximumXOR(int[] nums) {
        return 0;
    }

    // Bit manipulation
    // https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/91049/Java-O(n)-solution-using-bit-manipulation-and-HashMap/95535
    public static int findMaximumXOR1(int[] nums) {
        int maxXOR = 0;
        int mask = 1;

        for (int i = 0; i < 32; ++i) {
            mask = mask | (mask << i);

            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                int inspectingDigits = num & mask;
                set.add(inspectingDigits);
            }

            int greedyTry = maxXOR | (1 << i);

            for (int num : nums) {
                int target = num ^ greedyTry;

                if (set.contains(target)) {
                    maxXOR = greedyTry;
                    break;
                }
            }
        }

        return maxXOR;
    }
}
