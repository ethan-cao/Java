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

        int[] b = {5, 25};  // 28
        System.out.println(findMaximumXOR1(b));

//        int[] c = {3, 10, 5, 25, 2, 8};  // 28
//        System.out.println(findMaximumXOR1(c));
//
//        int[] a = {3, 32, 5, 25, 10}; //57
//        System.out.println(findMaximumXOR1(a));
    }

    public static int findMaximumXOR(int[] nums) {
        return 0;
    }

    // Bit manipulation
    // https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/91049/Java-O(n)-solution-using-bit-manipulation-and-HashMap/95535
    public static int findMaximumXOR1(int[] nums) {
        // maxResult is a record of the largest XOR we got so far
        int maxXOR = 0;
        int mask = 0;

        // Since we need largest XOR, we start from the very beginning, greedy approach
        for (int i = 31; i >= 0; --i) {
            mask = mask | (1 << i);  // each time, mask cover 1 bit, from left to right

            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                int inspectingDigits = num & mask;
                set.add(inspectingDigits);
            }

            // for current iteration, the possible maxXOR is greedyBet
            // given the previous maxXOR, the largest possible number is to append 1
            int greedyBet = maxXOR | (1 << i);

            // for each inspectingDigits, greedyBet would be the largest xor
            // then we are looking for a target, which suffice inspectingDigits ^ target = greedyBet
            // since a xor b = c   ⇒   a xor c = b   and    b xor c = a
            // so inspectingDigits ^ greedy = target
            // if target exists in set, then greedyBet is possible to get
            for (int inspectingDigits : set) {
                int target = inspectingDigits ^ greedyBet;

                if (set.contains(target)) {
                    maxXOR = greedyBet;
                    break;
                }

            }
        }

        return maxXOR;
    }

}
