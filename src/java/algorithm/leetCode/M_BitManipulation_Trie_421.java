package algorithm.leetCode;

/*
Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 2^31.
Find the maximum result of ai XOR aj, where  0 ≤ i,j < n.
Could you do this in O(n) runtime?

### Example
[3, 10, 5, 25, 2, 8] -> 28
Explanation: The maximum result is 5 (XOR) 25 = 28.

*/

import java.util.*;

public class M_BitManipulation_Trie_421 {

    public static void main(String[] args) {

        int[] b = {5, 25};  // 28
        System.out.println(findMaximumXOR(b));

        int[] c = {3, 10, 5, 25, 2, 8};  // 28
        System.out.println(findMaximumXOR(c));

        int[] a = {3, 32, 5, 25, 10}; //57
        System.out.println(findMaximumXOR(a));
    }

    // Trie
    static class TrieNode {
        int val;
        TrieNode leftChild; // 0
        TrieNode rightChild; // 1

        TrieNode(int val) {
            this.val = val;
        }
    }

    // https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/130427/()-92
    public static int findMaximumXOR(int[] nums) {

        // build the tree, storing binary form nums
        // 0 goes to left, 1 goes to right
        TrieNode root = new TrieNode(0);
        TrieNode current = root;

        for (int num : nums) {
            for (int i = 31; i >= 0; --i) {
                // check xor bit by bit, from right to left
                // check from left to right does not work, since when we retain number that differs on lower digit,
                // we could lost number that differ on higher digits, so we won't find the max XOR
                int bit = num & (1 << i);

                // the node has child, either left or right
                if (bit == 0) {
                    if (current.leftChild == null) {
                        current.leftChild = new TrieNode(0);
                    }

                    current = current.leftChild;
                } else {
                    if (current.rightChild == null) {
                        current.rightChild = new TrieNode(1);
                    }

                    current = current.rightChild;
                }
            }

            current = root;
        }

        // find max XOR
        int maxXOR = 0;

        for (int num : nums) {
            int xor = 0;

            for (int i = 31; i >= 0; --i) {
                int bit = num & (1 << i);

                // if the bit is 0, best is to go to rightChild(1)
                // leftChild and rightChild will not be null at the same time
                // since trie consists of same-hight branch in this case
                if (bit == 0) {
                    if (current.rightChild != null) {
                        xor = xor | (1 << i);
                        current = current.rightChild;
                    } else {
                        xor = xor | (0 << i);
                        current = current.leftChild;
                    }
                } else {
                    if (current.leftChild != null) {
                        xor = xor | (1 << i);
                        current = current.leftChild;
                    } else {
                        xor = xor | (0 << i);
                        current = current.rightChild;
                    }
                }
            }

            maxXOR = maxXOR > xor ? maxXOR : xor;
            current = root;
        }

        return maxXOR;
    }

    // Bit manipulation
    // https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/91049/Java-O(n)-solution-using-bit-manipulation-and-HashMap/95535
    public static int findMaximumXOR1(int[] nums) {
        // maxResult is a record of the largest XOR we got so far
        int maxXOR = 0;
        int mask = 0;

        // Since we need largest XOR, we start from the very beginning, greedy approach
        // iterate from 0 to 31 does not work, since when we retain number that differs on lower digit,
        // we could lost number that differ on higher digits, so we won't find the max XOR
        for (int i = 31; i >= 0; --i) {
            mask = mask | (1 << i);  // each time, mask cover 1 bit, from left to right

            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                int bits = num & mask;
                set.add(bits);
            }

            // for current iteration, the possible maxXOR is greedyBet
            // given the previous maxXOR, the largest possible number is to append 1
            int greedyBet = maxXOR | (1 << i);

            // for each inspectingDigits, greedyBet would be the largest xor
            // then we are looking for a target, which suffice inspectingDigits ^ target = greedyBet
            // since a xor b = c   ⇒   a xor c = b   and    b xor c = a
            // so inspectingDigits ^ greedy = target
            // if target exists in set, then greedyBet is possible to get
            for (int bits : set) {
                int target = bits ^ greedyBet;

                if (set.contains(target)) {
                    maxXOR = greedyBet;
                    break;
                }

            }
        }

        return maxXOR;
    }
}
