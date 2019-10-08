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
        System.out.println(minWindow("ADOBECODEBANC", "ADB")); // ADB
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));  // BANC
    }

    // Two pointer, sliding window
    public static String minWindow(String s, String t) {
        if (s == null || s.length() < t.length() || s.length() == 0) {
            return "";
        }

        Map<Character, Integer> counter = new HashMap<>();
        for (char c : t.toCharArray()) {
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }

        boolean hasMinWindow = false;
        int minWindowLength = s.length();
        int minWindowLeft = 0;
        int minWindowRight = 0;
        int targetCharLength = t.length();

        for (int left = 0, right = 0; right < s.length(); ++right) {
            char rightChar = s.charAt(right);

            if (counter.containsKey(rightChar)) {
                counter.put(rightChar, counter.get(rightChar) - 1);

                if (counter.get(rightChar) >= 0) {
                    targetCharLength--;
                }
            }

            // found substring from s containing all chars in t
            while (targetCharLength == 0 && left <= right) {
                hasMinWindow = true;

                // must use <=
                if (right - left + 1 <= minWindowLength) {
                    minWindowLeft = left;
                    minWindowRight = right;
                    minWindowLength = right - left + 1;
                }

                char leftChar = s.charAt(left);
                if (counter.containsKey(leftChar)) {
                    counter.put(leftChar, counter.get(leftChar) + 1);

                    if (counter.get(leftChar) >= 1) {
                        targetCharLength++;
                    }
                }

                left++;
            }
        }

        return hasMinWindow ? s.substring(minWindowLeft, minWindowRight + 1) : "" ;
    }
}