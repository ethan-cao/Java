package algorithm.leetCode;

/*
Given a string S and a string T,
find the minimum window in S which will contain all the characters in T in complexity O(n).

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

### Example
Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"

*/

import java.util.HashMap;
import java.util.Map;

public class H_SlidingWindow_String_76 {

    public static void main(String[] args) {
        System.out.println(minWindow("aa", "aa")); // "aa"
        System.out.println(minWindow("ADOBECODEBANC", "ADB")); // ADOB
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));  // BANC
    }

    /*    General approach to search substring
        1. Use two pointers: start and end to represent a window.
        2. Move end to find a valid window.
        3. When a valid window is found, move start to find a smaller window.
     */

    // Two pointer, sliding window
    public static String minWindow(String s, String t) {
        if (s.length() == 0 || s.length() < t.length() || t.length() == 0) {
            return "";
        }

        // Strings are encoded as UTF-16. In UTF-16, the ASCII character set is encoded as values [0 - 127]
        int[] charOccurrence = new int[128];
        for (char c : t.toCharArray()) {
            charOccurrence[c]++;
        }

        // since we are looking for substring, naturally think about using left and right pointer
        boolean hasMinWindow = false;
        int minWindowLength = s.length();
        int minWindowLeft = 0;
        int minWindowRight = s.length() - 1;

        int requiredCharCount = t.length();

        for (int left = 0, right = 0; right < s.length(); ++right) {
            char rightChar = s.charAt(right);

            charOccurrence[rightChar]--;

            // when the count is < 0, the char is not required
            if (charOccurrence[rightChar] >= 0) {
                requiredCharCount--;
            }

            // when found substring from s containing all chars in t, start moving left pointer
            while (requiredCharCount == 0 && left <= right) {
                char leftChar = s.charAt(left);
                hasMinWindow = true;

                // since initial value for minWindowLength is s.length()
                // if we identify substring that has s.length() length, we need to update pointers
                if (right - left + 1 <= minWindowLength) {
                    minWindowLeft = left;
                    minWindowRight = right;
                    minWindowLength = right - left + 1;
                }

                charOccurrence[leftChar]++;

                if (charOccurrence[leftChar] >= 1){
                    requiredCharCount++;
                }

                left++;
            }
        }

        return hasMinWindow ? s.substring(minWindowLeft, minWindowRight + 1) : "";
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