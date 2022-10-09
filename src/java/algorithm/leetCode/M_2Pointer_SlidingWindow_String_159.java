package algorithm.leetCode;

/*
Given a string s, find the length of the longest substring t that contains at most 2 distinct characters.

### Example
"eceba" -> 3, t is "ece" which its length is 3.
"ccaabbb" -> 5, t is "aabbb" which its length is 5.

*/

import java.util.*;

public class M_2Pointer_SlidingWindow_String_159 {

    public static void main(String... args) {
        System.out.println(lengthOfLongestSubstringTwoDistinct("")); // 0
        System.out.println(lengthOfLongestSubstringTwoDistinct("eceba")); // 3
        System.out.println(lengthOfLongestSubstringTwoDistinct("ccaabbb")); // 5
        System.out.println(lengthOfLongestSubstringTwoDistinct("adeee112")); // 5
    }

    // Two pointer - Sliding window, 2ms
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int longestLength = 0;

        if (s == null || s.length() == 0) {
            return longestLength;
        }

        int left = 0;
        int right = 0;

        // Strings are encoded as UTF-16. In UTF-16, the ASCII character set is encoded as values [0 - 127]
        int[] frequencies = new int[128];
        int distinctCharCount = 0;

        while (right < s.length()) {
            char rightChar = s.charAt(right);
            frequencies[rightChar]++;

            // if appears for the 1st time
            if (frequencies[rightChar] == 1) {
                distinctCharCount++;
            }

            while (distinctCharCount > 2 && left <= right) {
                char leftChar = s.charAt(left);
                frequencies[leftChar]--;

                if (frequencies[leftChar] == 0) {
                    distinctCharCount--;
                }

                left++;
            }

            longestLength = Math.max(longestLength, right - left + 1);

            right++;
        }

        return longestLength;
    }

}
