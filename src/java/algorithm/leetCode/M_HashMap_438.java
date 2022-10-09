package algorithm.leetCode;

import java.util.*;

/*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
Strings consists of lowercase English letters only
and the length of both strings s and p will not be larger than 20,100.
The order of output does not matter.

### Example
s: "cbaebabacd" p: "abc"  -> [0, 6]
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

s: "abab" p: "ab" -> [0, 1, 2]
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

*/

public class M_HashMap_438 {

    // Sliding window, Time O(n), 7ms
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();

        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return list;
        }

        int requiredCharsCount = p.length();
        int[] requiredChars = new int[256];
        for (char c : p.toCharArray()) {
            requiredChars[c]++;
        }

        int left = 0;
        int right = 0;

        while (right < s.length()) {
            //move right everytime, if the character exists in p's requiredChars, decrease the requiredCharsCount
            //current requiredChars value >= 1 means the character is existing in p
            if (requiredChars[s.charAt(right)] >= 1) {
                requiredCharsCount--;
            }

            requiredChars[s.charAt(right)]--;

            //when the requiredCharsCount is down to 0, means we found the right anagram
            if (requiredCharsCount == 0) {
                list.add(left);
            }

            // once window size > required size, narrow the window
            if (right - left + 2 > p.length()) {
                //only increase the requiredCharsCount if the character is in p
                //the requiredCharsCount >= 0 indicate it was original in the requiredChars, cuz it won't go below 0
                if (requiredChars[s.charAt(left)] >= 0) {
                    //++ to reset the requiredChars because we kicked out the left
                    requiredCharsCount++;
                }

                requiredChars[s.charAt(left)]++;
                left++;
            }

            right++;
        }

        return list;
    }

}
