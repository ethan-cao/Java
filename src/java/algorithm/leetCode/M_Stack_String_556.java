package algorithm.leetCode;

/*
Given a positive 32-bit integer n, you need to find the smallest 32-bit integer
which has exactly the same digits existing in the integer n and is greater in value than n.
If no such positive 32-bit integer exists, you need to return -1.

### Example
12 -> 21
21 -> -1

*/

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class M_Stack_String_556 {

    public static void main(String... args) {
        System.out.println(nextGreaterElement1(12)); // 21
        System.out.println(nextGreaterElement1(21)); // -1
        System.out.println(nextGreaterElement1(123)); // 132
        System.out.println(nextGreaterElement1(235235)); // 235253
        System.out.println(nextGreaterElement1(230241)); // 230412
        System.out.println(nextGreaterElement1(1999999999)); // -1
    }

    // Stack
    public static int nextGreaterElement(int n) {
        char[] digits = String.valueOf(n).toCharArray();
        Deque<Integer> idxStack = new ArrayDeque<>();
        int rightMostLargerDigitIdx = -1;

        // look for a digit larger than  digits[i], on the right most position
        for (int i = digits.length - 1; i >= 0; --i) {

            while (!idxStack.isEmpty() && digits[i] < digits[idxStack.peekFirst()]) {
                rightMostLargerDigitIdx = idxStack.pop();
            }

            if (rightMostLargerDigitIdx != -1) {
                // once found, swap them
                swap(digits, rightMostLargerDigitIdx, i);
                // sort digits on the right part of i
                Arrays.sort(digits, i + 1, digits.length);
                break;
            }

            idxStack.push(i);
        }

        if (rightMostLargerDigitIdx == -1) {
            return -1;
        }

        String s = "";
        for (int i = 0; i < digits.length; ++i) {
            s += digits[i];
        }

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

