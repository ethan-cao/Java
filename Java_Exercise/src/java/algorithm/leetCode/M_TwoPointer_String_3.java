package algorithm.leetCode;

import java.util.Arrays;
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


public class M_TwoPointer_String_3 {

    public static void main(String... args) {
        System.out.println(lengthOfLongestSubstring1(" ")); // 1
        System.out.println(lengthOfLongestSubstring1("bbbbb")); // 1
        System.out.println(lengthOfLongestSubstring1("abcabcbb")); // 3
        System.out.println(lengthOfLongestSubstring1("aab")); // 2
        System.out.println(lengthOfLongestSubstring1("pwwkew")); // 3
        System.out.println(lengthOfLongestSubstring1("q12423rrr4r3j")); // 4
        System.out.println(lengthOfLongestSubstring1("fiu3hubvoibbbfdvbivblb43gb")); // 7
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

    // Two pointer
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

        // counter all ASCII chars, ASCII encodes 128 characters
        // could also use HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int[] tally = new int[128];
        Arrays.fill(tally, -1);

        int start = 0;
        for (int end = start; end < s.length(); ++end) {
            if (tally[s.charAt(end)] >= start) {
                start = tally[s.charAt(end)] + 1;
            }

            tally[s.charAt(end)] = end;

            longestLength = Math.max(longestLength, end - start + 1);
        }

        return longestLength;
    }
}