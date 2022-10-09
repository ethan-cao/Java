package algorithm.leetCode;

/*
Given a string S and a string T,
find the minimum window in S which will contain all the characters in T in complexity O(n).

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

### Example
S = "ADOBECODEBANC", T = "ABC" -> "BANC"

*/

import java.util.*;

public class H_2Pointer_SlidingWindow_String_76 {

    public static void main(String[] args) {
        System.out.println(minWindow2("aa", "aa")); // "aa"
        System.out.println(minWindow2("ADOBECODEBANC", "ADB")); // ADOB
        System.out.println(minWindow2("ADOBECODEBANC", "ABC"));  // BANC
    }

    // Two pointer, sliding window
    // Time: O(N) 2ms
    public static String minWindow2(String s, String t) {
        if (s.length() == 0 || s.length() < t.length() || t.length() == 0) {
            return "";
        }

        int left = 0;
        int right = 0;

        // Strings are encoded as UTF-16. In UTF-16, the ASCII character set is encoded as values [0 - 127]
        int requiredCharCount = t.length();
        int[] requiredCharCounter = new int[128];
        for (char c : t.toCharArray()) {
            requiredCharCounter[c]++;
        }

        int minLeft = 0;
        int minLength = Integer.MAX_VALUE;

        while (right < s.length()) {
            char rightChar = s.charAt(right);
            requiredCharCounter[rightChar]--;

            if (requiredCharCounter[rightChar] >= 0) {
                requiredCharCount--;
            }

            // trying to minimize window while requiredCharCount is 0
            while (requiredCharCount == 0 && left <= right) {
                // capture the moment when there is a minLength
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                }

                // try to move window left towards right
                char leftChar = s.charAt(left);
                requiredCharCounter[leftChar]++;

                if (requiredCharCounter[leftChar] > 0) {
                    requiredCharCount++;  // break the loop
                }

                left++;
            }

            right++;
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLength);
    }

    // use map to count, slow
    public static String minWindow1(String s, String t) {
        if (s.length() == 0 || s.length() < t.length() || t.length() == 0) {
            return "";
        }

        Map<Character, Integer> counter = new HashMap<>();
        for (char c : t.toCharArray()) {
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }

        // since we are looking for substring, naturally think about using left and right pointer
        boolean hasMinWindow = false;
        int minWindowLength = s.length();
        int minWindowLeft = 0;
        int minWindowRight = s.length() - 1;

        int requiredCharCount = t.length();

        for (int left = 0, right = 0; right < s.length(); ++right) {
            char rightChar = s.charAt(right);

            // this works, just takes more time
//            Integer newCount = counter.computeIfPresent(rightChar, (key, value)-> value - 1);
//            if (newCount != null && newCount >= 0) {
//                requiredCharCount--;
//            }

            if (counter.containsKey(rightChar)) {
                // as long as the char exist, keep subtracting,
                counter.put(rightChar, counter.get(rightChar) - 1);

                // subtract requiredCharCount iff required char appears
                // when the count is < 0, the char is not required
                if (counter.get(rightChar) >= 0) {
                    requiredCharCount--;
                }
            }

            // when found substring from s containing all chars in t, start moving left pointer
            while (requiredCharCount == 0 && left <= right) {
                hasMinWindow = true;

                // since initial value for minWindowLength is s.length()
                // if we identify substring that has s.length() length, we need to update pointers
                if (right - left + 1 <= minWindowLength) {
                    minWindowLeft = left;
                    minWindowRight = right;
                    minWindowLength = right - left + 1;
                }

                char leftChar = s.charAt(left);

                if (counter.containsKey(leftChar)) {
                    counter.put(leftChar, counter.get(leftChar) + 1);

                    if (counter.get(leftChar) >= 1) {
                        requiredCharCount++;
                    }
                }

                left++;
            }
        }

        return hasMinWindow ? s.substring(minWindowLeft, minWindowRight + 1) : "";
    }

}