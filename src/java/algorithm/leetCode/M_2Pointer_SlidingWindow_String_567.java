package algorithm.leetCode;

/*
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
In other words, one of the first string's permutations is the substring of the second string.

The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].

### Example
s1 = "ab" s2 = "eidbaooo" -> True
s1 = "ab" s2 = "eidboaoo" -> False

*/

import java.util.*;

public class M_2Pointer_SlidingWindow_String_567 {

    public static void main(String... args) {
        System.out.println(checkInclusion1("ab", "eidbaooo")); // T
        System.out.println(checkInclusion1("ab", "eidboaoo")); // F
        System.out.println(checkInclusion1("hello", "ooolleoooleh")); // F
    }

    // string p is a permutation of string s if each char in p is in s
    // find a sliding window of length s1.length() in s2 that contains all chars in s1
    // Time: O(N), 4ms
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] counter = new int[26];
        for (char c : s1.toCharArray()) {
            counter[c - 'a']++;
        }

        for (int i = 0; i < s2.length(); ++i) {
            char charToInclude = s2.charAt(i);
            counter[charToInclude - 'a']--;

            // create a sliding window with length == s1.length()
            // char outside the sliding window needs to added back to counter
            if (i >= s1.length()) {
                char charToExclude = s2.charAt(i - s1.length());
                counter[charToExclude - 'a']++;
            }

            if (isEmpty(counter)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isEmpty(int[] counter) {
        for (int i = 0; i < 26; i++) {
            if (counter[i] != 0) {
                return false;
            }
        }

        return true;
    }

    // SlidingWindow
    // Time: O(N), ~6ms
    public static boolean checkInclusion1(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] counter = new int[26];  // use 128, not necessary to contain only letter
        for (char c : s1.toCharArray()) {
            counter[c - 'a']++;
        }

        int left = 0;
        int right = 0;

        while (right < s2.length()) {
            char rightChar = s2.charAt(right);
            counter[rightChar - 'a']--;

            // when the char is more than we need, check if it is possible to move window left to get rid of extra char
            // this loop assures that we
            while (counter[rightChar - 'a'] < 0 && left <= right) {
                // left never surpasses right, since when left reaches right, counter[rightChar - 'a'] will be 0, loop stops
                // so we can ignore left <= right
                char leftChar = s2.charAt(left);
                counter[leftChar - 'a']++;

                left++;
            }

            // at this stage, chars in s2.substring(left, right+1) are all in s1
            // and if window size is s1.length(), it means this window contains all the chars in s1
            if (right - left + 1 == s1.length()) {
                return true;
            }

            right++;
        }

        return false;
    }

}