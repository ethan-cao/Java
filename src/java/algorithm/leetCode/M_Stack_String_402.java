package algorithm.leetCode;

/*
Given a non-negative integer num represented as a string,
remove k digits from the number so that the new number is the smallest possible.
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.

### Example
num = "1432219", k = 3 -> "1219"
num = "10200", k = 1 -> "200"
num = "10", k = 2 -> "0"

*/

import java.util.*;

public class M_Stack_String_402 {

    public static void main(String... args) {
//        System.out.println(removeKdigits("1432219", 3));    // 1219
//        System.out.println(removeKdigits("10200", 1));      // 200
//        System.out.println(removeKdigits("10", 2));         // 0
//        System.out.println(removeKdigits("52660469", 2));   // 260469
        System.out.println(removeKdigits("112", 1));        // 11
    }

    // Stack, ms
    // Time: O(N), Space: O(N)
    /*
     in a general case, digit1 digit2 digit3
     if we remove a digit, the best case it to remove digit1 while digit1 > digit2 and digit1 > digit3
     this implies removing a digit if it is larger than its right digit, the largest left most one
     */
    public static String removeKdigits(String num, int k) {
        Deque<Character> stack = new ArrayDeque<>();  // monotonic decreasing

        for (int i = 0; i < num.length(); ++i) {
            char c = num.charAt(i);

            while (k > 0 && !stack.isEmpty() && stack.peek() > c) {
                stack.pop();  // remove a digit
                k--;          // k is the count for to-be-removed items
            }

            stack.push(c);
        }

        // now stack contains remaining digits

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            char c = stack.pop();

            // we migth still have digits to remove
            if (k > 0) {
                k--;
            } else {
                sb.append(c);
            }
        }

        sb.reverse();

        // !!! remove leading 0
        while (sb.indexOf("0") == 0) {
            sb.deleteCharAt(0);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

}