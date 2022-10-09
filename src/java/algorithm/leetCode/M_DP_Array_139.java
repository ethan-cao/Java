package algorithm.leetCode;

/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
determine if s can be segmented into a space-separated sequence of one or more dictionary words.

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.

### Example
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
*/

import java.util.*;

public class M_DP_Array_139 {

    public static void main(String... args) {
        String s = "leetcode";
        String[] wordDictArray = {"leet", "code"};
        List<String> wordDict = Arrays.asList(wordDictArray);
        System.out.println(wordBreak1(s, wordDict)); // T

        String s1 = "cars";
        String[] wordDictArray1 = {"car", "ca", "rs"};
        List<String> wordDict1 = Arrays.asList(wordDictArray1);
        System.out.println(wordBreak1(s1, wordDict1));  // T

        String s2 = "catsandog";
        String[] wordDictArray2 = {"cats", "dog", "sand", "and", "cat"};
        List<String> wordDict2 = Arrays.asList(wordDictArray2);
        System.out.println(wordBreak1(s2, wordDict2));  // F
    }

    // DP, iterative, 4ms
    public static boolean wordBreak(String s, List<String> wordDict) {
        final int L = s.length();

        // canBeSegmented[i] : if s.substring(0, i - 1) can be segmented
        boolean[] canBeSegmented = new boolean[L + 1];
        canBeSegmented[0] = true;

        for (int end = 1; end <= L; ++end) {
            for (int start = end - 1; start >= 0; --start) {
                String subString = s.substring(start, end);

                if (wordDict.contains(subString) && canBeSegmented[start]) {
                    canBeSegmented[end] = true;
                    break;
                }
            }
        }

        return canBeSegmented[L];
    }

    // DP iterative,
    public static boolean wordBreak1(String s, List<String> wordDict) {
        // canBeSegmented[i]: if s.substring(0, i-1) can be segmented
        boolean[] canBeSegmented = new boolean[s.length() + 1];
        canBeSegmented[0] = true;

        for (int end = 1; end <= s.length(); ++end) {
            for (String word : wordDict) {
                if (word.length() <= end && canBeSegmented[end - word.length()] && word.equals(s.substring(end - word.length(), end))) {
                    canBeSegmented[end] = true;
                    break;
                }
            }
        }

        return canBeSegmented[s.length()];
    }

}