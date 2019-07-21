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

import java.util.Arrays;
import java.util.List;

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

    // DP iterative
    public static boolean wordBreak(String s, List<String> wordDict) {
        // results[i] : if s.substring(0, i-1) can be segmented
        boolean[] results = new boolean[s.length() + 1];
        results[0] = true;  // the most base sub problem

        for (int i = 1; i <= s.length(); ++i) {
            // iterate backward to solve dependent sub problem first
            for (int j = i - 1; j >= 0; --j) {
                // if substring(j, i) is in wordDict and substring(0, j) can be segmented
                // substring(0, i) can be segmented as well
                if (wordDict.contains(s.substring(j, i)) && results[j]) {
                    results[i] = true;
                    break;
                }
            }
        }

        return results[s.length()];
    }

    // DP iterative, alternatively, faster
    public static boolean wordBreak1(String s, List<String> wordDict) {
        // results[i] : if s.substring(0, i-1) can be segmented
        boolean[] results = new boolean[s.length() + 1];
        results[0] = true;

        for (int i = 1; i <= s.length(); ++i) {
            for (String word : wordDict) {
                if (word.length() <= i && results[i - word.length()] && word.equals(s.substring(i - word.length(), i))) {
                    results[i] = true;
                    break;
                }
            }
        }
        return results[s.length()];
    }

}