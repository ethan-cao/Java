package algorithm.leetCode;

import java.util.HashSet;
import java.util.Set;

/*
Given a string, find the length of the longest substring without repeating characters.

### Example
"abcabcbb" -> 3
The answer is "abc", with the length of 3.

"bbbbb"-> 1
The answer is "b", with the length of 1.

"pwwkew" -> 3
The answer is "wke", with the length of 3.
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

### Condition
### Essential problem
### Corner case

*/


public class M_String_3 {

    public static void main(String... args) {
        System.out.println(lengthOfLongestSubstring(" ")); // 1
        System.out.println(lengthOfLongestSubstring("bbbbb")); // 1
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(lengthOfLongestSubstring("aab")); // 2
        System.out.println(lengthOfLongestSubstring("pwwkew")); // 3
        System.out.println(lengthOfLongestSubstring("q12423rrr4r3j")); // 4
        System.out.println(lengthOfLongestSubstring("fiu3hubvoibbbfdvbivblb43gb")); // 7
    }

    // a working approach, but too slow to be accepted
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

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int longestLength = 1;

        Set<Character> tally = new HashSet<>();

        int start = 0;
        for (int end = start + 1; start < end && end < s.length(); ) {
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

    public static int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int longestLength = 0;

        return longestLength;
    }

}