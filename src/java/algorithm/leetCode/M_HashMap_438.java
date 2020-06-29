package algorithm.leetCode;

import java.util.*;

/*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
Strings consists of lowercase English letters only
and the length of both strings s and p will not be larger than 20,100.
The order of output does not matter.

### Example
s: "cbaebabacd" p: "abc"  -> [0, 6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

s: "abab" p: "ab" -> [0, 1, 2]
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

*/
public class M_HashMap_438 {

    public static void main(String... args) {
    }

    // Sliding window, Time O(n)
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();

        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return list;
        }

        int[] counter = new int[256];
        for (char c : p.toCharArray()) {
            counter[c]++;
        }

        int left = 0;
        int right = 0;
        int count = p.length();

        while (right < s.length()) {
            //move right everytime, if the character exists in p's counter, decrease the count
            //current counter value >= 1 means the character is existing in p
            if (counter[s.charAt(right)] >= 1) {
                count--;
            }
            counter[s.charAt(right)]--;
            right++;

            //when the count is down to 0, means we found the right anagram
            //then add window's left to result list
            if (count == 0) {
                list.add(left);
            }

            //if window's size == p, then move left (narrow the window) to find the new match window
            //++ to reset the counter because we kicked out the left
            if (right - left == p.length()) {
                //only increase the count if the character is in p
                //the count >= 0 indicate it was original in the counter, cuz it won't go below 0
                if (counter[s.charAt(left)] >= 0) {
                    count++;
                }

                counter[s.charAt(left)]++;
                left++;
            }
        }

        return list;
    }

}
