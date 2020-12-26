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

    // Stack, 0ms, same as 31
    // Time: O(N)
    public static int nextGreaterElement(int n) {
        char[] nums = String.valueOf(n).toCharArray();
        int L = nums.length;
        int leftOnLocalMaxIdx = -1;
        int rightMostLargerIdx = -1;
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = L - 1; i >= 0; --i) {
            int num = nums[i];

            if (!stack.isEmpty() && nums[stack.peek()] > num) {
                leftOnLocalMaxIdx = i;
                break;
            }

            stack.push(i);
        }

        if (leftOnLocalMaxIdx == -1) {
            return -1;
        } else {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[leftOnLocalMaxIdx]) {
                rightMostLargerIdx = stack.pop();
            }

            swap(nums, leftOnLocalMaxIdx, rightMostLargerIdx);
            reverse(nums, leftOnLocalMaxIdx + 1, L - 1);

            StringBuilder sb = new StringBuilder();
            for (char num : nums) {
                sb.append(num);
            }
            String number = sb.toString();

            // !!! 32 bit integer
            return Long.parseLong(number) > Integer.MAX_VALUE ? -1 : Integer.parseInt(number);
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    private static void reverse(char[] nums, int idx1, int idx2) {
        while (idx1 < idx2) {
            swap(nums, idx1, idx2);
            idx1++;
            idx2--;
        }
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

