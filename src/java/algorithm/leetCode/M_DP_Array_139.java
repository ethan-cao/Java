package algorithm.leetCode;

/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
determine if s can be segmented into a space-separated sequence of one or more dictionary words.

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.

### Example
s = "leetcode", wordDict = ["leet", "code"] -> true
s = "applepenapple", wordDict = ["apple", "pen"] -> true
s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"] -> true

*/

import java.util.*;

public class M_DP_Array_139 {

    public static void main(String... args) {
        String s = "leetcode";
        String[] wordDictArray = { "leet", "code" };
        List<String> wordDict = Arrays.asList(wordDictArray);
        System.out.println(wordBreak1(s, wordDict)); // T

        String s1 = "cars";
        String[] wordDictArray1 = { "car", "ca", "rs" };
        List<String> wordDict1 = Arrays.asList(wordDictArray1);
        System.out.println(wordBreak1(s1, wordDict1)); // T

        String s2 = "catsandog";
        String[] wordDictArray2 = { "cats", "dog", "sand", "and", "cat" };
        List<String> wordDict2 = Arrays.asList(wordDictArray2);
        System.out.println(wordBreak1(s2, wordDict2)); // F
    }


    //----------------------------------------------------------------------------------------------
    // ✅ DP, iterative, 
    // Time:: O(N^2), 6ms
    public static boolean wordBreak12(String s, List<String> wordDict) {
        int L = s.length();
        
        // optimization: convert list to set for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordDict);
        
        // results[i]: if s.substring(0, i) can be segmented
        // you really only need a 1D array, since results[i] only depends on results[j] where j < i
        boolean[] results = new boolean[L];

        for (int end = 0; end < L; ++end) {
            for (int start = end; start >= 0; --start) {
                String word = s.substring(start, end + 1);

                if (start == 0) {
                    results[end] = wordSet.contains(word);
                } else {
                    results[end] = wordSet.contains(word) && results[start - 1];
                }
            
                if (results[end]) {
                    break;    
                }
            }
        
        }

        return results[L - 1];
    }

    //----------------------------------------------------------------------------------------------
    // DP iterative
    public static boolean wordBreak1(String s, List<String> wordDict) {
        // result[i]: if s.substring(0, i-1) can be segmented
        boolean[] result = new boolean[s.length() + 1];

        // BASE
        result[0] = true;

        for (int end = 1; end <= s.length(); ++end) {
            for (String word : wordDict) {
                if (word.length() <= end
                        && result[end - word.length()]
                        && word.equals(s.substring(end - word.length(), end))) {
                    result[end] = true;
                    break;
                }
            }
        }

        return result[s.length()];
    }

    //----------------------------------------------------------------------------------------------
    // Brute force, DFS
    // Time: O(2^n)
    public boolean wordBreak111(String s, List<String> wordDict) {
        return canBreak(s, wordDict);
    }

    private boolean canBreak(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }

        for (int idx = 0; idx < s.length(); ++idx) {
            if (wordDict.contains(s.substring(0, idx + 1)) &&
                    canBreak(s.substring(idx + 1), wordDict)) {
                return true;
            }
        }

        return false;
    }
}