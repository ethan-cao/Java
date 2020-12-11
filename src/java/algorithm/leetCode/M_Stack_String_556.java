package algorithm.leetCode;

/*
Given a positive 32-bit integer n, you need to find the smallest 32-bit integer
which has exactly the same digits existing in the integer n and is greater in value than n.
If no such positive 32-bit integer exists, you need to return -1.

### Example
12 -> 21
21 -> -1

*/

import java.util.*;

public class M_Stack_String_556 {

    public static void main(String... args) {
        System.out.println(nextGreaterElement1(12)); // 21
        System.out.println(nextGreaterElement1(21)); // -1
        System.out.println(nextGreaterElement1(123)); // 132
        System.out.println(nextGreaterElement1(235235)); // 235253
        System.out.println(nextGreaterElement1(230241)); // 230412
        System.out.println(nextGreaterElement1(1999999999)); // -1
    }

    // Stack, 1ms
    // Time: O(N)
    public static int nextGreaterElement(int n) {
        char[] digits = String.valueOf(n).toCharArray();

        // check each digit from right to left, find its right-most larger digit
        int rightMostLargerIdx = -1;
        // monotonic increasing from right to left, but decreasing from left to right
        Deque<Integer> stack = new ArrayDeque<>(); 

        for (int i = digits.length - 1; i >= 0; --i) {

            while (!stack.isEmpty() && digits[stack.peek()] > digits[i] ) {
                rightMostLargerIdx = stack.pop();
            }

            if (rightMostLargerIdx != -1) {
                swap(digits, rightMostLargerIdx, i);
                Arrays.sort(digits, i + 1, digits.length);
                break;
            }

            stack.push(i);
        }

        if (rightMostLargerIdx == -1) {
            return -1;
        }

        StringBuilder sb = new StringBuilder();
        for (char digit: digits) {
            sb.append(digit);
        }
        String s = sb.toString();

        // !!! 32 bit integer
        return Long.valueOf(s) > Integer.MAX_VALUE ? -1 : Integer.valueOf(s);
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    // same as 31 next permutation
    // more or less the same as nextGreaterElement, without using stack
    public static int nextGreaterElement1(int n) {
        char[] digits = (n + "").toCharArray();
        int turningPointIdx = digits.length - 1;

        for (int i = digits.length - 1; i > 0; --i) {
            if (digits[i - 1] < digits[i]) {
                turningPointIdx = i - 1;
                break;
            }
        }

        if (turningPointIdx == digits.length - 1) {
            return -1;
        }

        int rightMostLargerDigitIdx = digits.length - 1;

        for (int i = digits.length - 1; i > turningPointIdx; --i) {
            if (digits[i] > digits[turningPointIdx]) {
                rightMostLargerDigitIdx = i;
                break;
            }
        }

        swap(digits, turningPointIdx, rightMostLargerDigitIdx);
        Arrays.sort(digits, turningPointIdx + 1, digits.length);

        String s = "";
        for (int i = 0; i < digits.length; ++i) {
            s += digits[i];
        }

        // !!! 32 bit integer
        return Long.valueOf(s) > Integer.MAX_VALUE ? -1 : Integer.valueOf(s);
    }

}

