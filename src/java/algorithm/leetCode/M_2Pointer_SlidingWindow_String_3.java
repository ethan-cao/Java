package algorithm.leetCode;

/*
Given a string, find the length of the longest substring without repeating characters.

### Example
"abcabcbb" -> 3,  "abc", with the length of 3.
"bbbbb"-> 1, "b", with the length of 1.
"pwwkew" -> 3, "wke", with the length of 3. answer must be a substring, "pwke" is a subsequence and not a substring.

*/

import java.util.*;

public class M_2Pointer_SlidingWindow_String_3 {

    public static void main(String... args) {
        System.out.println(lengthOfLongestSubstring2(" ")); // 1
        System.out.println(lengthOfLongestSubstring2("bbbbb")); // 1
        System.out.println(lengthOfLongestSubstring2("abcabcbb")); // 3
        System.out.println(lengthOfLongestSubstring2("aab")); // 2
        System.out.println(lengthOfLongestSubstring2("pwwkew")); // 3
        System.out.println(lengthOfLongestSubstring2("q12423rrr4r3j")); // 4
        System.out.println(lengthOfLongestSubstring2("fiu3hubvoibbbfdvbivblb43gb")); // 7
    }

    // Two pointer - Sliding window, 2ms
    public static int lengthOfLongestSubstring1(String s) {
        int longestLength = 0;

        if (s == null || s.length() == 0) {
            return longestLength;
        }

        int left = 0; // starting index for the examining substring
        int right = 0;

        // Strings are encoded as UTF-16. In UTF-16, the ASCII character set is encoded as values [0 - 127]
        // lastAppearingIdx[char]:  the last appearing index of the char
        int[] lastAppearingIdx = new int[128];
        Arrays.fill(lastAppearingIdx, -1);

        while (right < s.length()) {
            char rightChar = s.charAt(right);

            // if the char appears again, reset the left index
            if (lastAppearingIdx[rightChar] >= left) {
                left = lastAppearingIdx[rightChar] + 1;
            }

            lastAppearingIdx[rightChar] = right; // update last appearing index

            longestLength = Math.max(longestLength, right - left + 1);

            right++;
        }

        return longestLength;
    }

    // Brute force, but too slow to be accepted
    public static int lengthOfLongestSubstring0(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int longestLength = 0;

        for (int i = 0; i < s.length(); ++i) {
            String subString = s.substring(i);

            // no need to use map, since just need to know if the char occurs
            Set<Character> tally = new HashSet<>();
            for (char c : subString.toCharArray()) {
                if (tally.contains(c)) {
                    tally.clear();
                }

                tally.add(c);

                longestLength = Math.max(longestLength, tally.size());
            }
        }

        return longestLength;
    }

    // Two pointer
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int longestLength = 1;

        Set<Character> tally = new HashSet<>();

        int start = 0;
        int end = start + 1;

        while (start < end && end < s.length()) {
            char startChar = s.charAt(start);
            char endChar = s.charAt(end);

            if (tally.isEmpty()) {
                tally.add(startChar);
            }

            if (tally.contains(endChar)) {
                tally.clear();
                start++;
                end = start + 1;
            } else {
                tally.add(endChar);
                end++;
            }

            longestLength = Math.max(longestLength, tally.size());
        }

        return longestLength;
    }


    // Two pointer - Sliding window, 9ms
    public static int lengthOfLongestSubstring2(String s) {
        int longestLength = 0;

        if (s == null || s.length() == 0) {
            return longestLength;
        }

        Set<Character> appearedChars = new HashSet<>();

        int left = 0;
        int right = 0;

        while (right < s.length()) {
            char rightChar = s.charAt(right);
            char leftChar = s.charAt(left);

            if (appearedChars.contains(rightChar)) {
                appearedChars.remove(leftChar);
                left++;
            } else {
                appearedChars.add(rightChar);
                right++;
            }

            longestLength = Math.max(longestLength, right - left);
        }

        return longestLength;
    }

}