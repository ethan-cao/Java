package algorithm.leetCode;

/*
Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.

### Example
"aab" -> [ ["aa","b"],  ["a","a","b"] ]

*/

import java.util.*;

public class M_Backtrack_Array_131 {
    public static void main(String[] args) {
        List<List<String>> result = partition("abcde");
        System.out.println(result);
    }

    // Backtrack + DP
    // Time: O(2^n), 7ms
    public static List<List<String>> partition(String s) {
        List<List<String>> palindrome = new ArrayList<>();
        List<String> tracker = new ArrayList<>();

        boolean[][] isPalindrome = findPalindrome(s);

        collect(palindrome, tracker, s, 0, isPalindrome);

        return palindrome;
    }

    static void collect(List<List<String>> palindrome, List<String> tracker, String s, int startIdx,
            boolean[][] isPalindrome) {
        if (startIdx == s.length()) {
            palindrome.add(new ArrayList<>(tracker));
            return;
        }

        for (int endIdx = startIdx; endIdx < s.length(); ++endIdx) {
            if (isPalindrome[startIdx][endIdx]) {
                tracker.add(s.substring(startIdx, endIdx + 1));

                collect(palindrome, tracker, s, endIdx + 1, isPalindrome);

                tracker.remove(tracker.size() - 1);
            }
        }
    }

    private static boolean[][] findPalindrome(String s) {
        final int L = s.length();
        boolean[][] isPalindrome = new boolean[L][L];

        for (int end = 0; end < s.length(); ++end) {
            for (int start = end; start >= 0; --start) {

                if (start == end) {
                    isPalindrome[start][end] = true;
                } else if (start + 1 == end) {
                    isPalindrome[start][end] = s.charAt(start) == s.charAt(end);
                } else {
                    isPalindrome[start][end] = s.charAt(start) == s.charAt(end) && isPalindrome[start + 1][end - 1];
                }
            }
        }

        // alternatively
        // for (int end = 0; end < L; ++end) {
        //     for (int start = 0; start <= end; ++start) {
        //         if (s.charAt(start) == s.charAt(end)) {
        //             if (start + 1 < end - 1) {
        //                 isPalindrome[start][end] = isPalindrome[start + 1][end - 1];
        //             } else {
        //                 isPalindrome[start][end] = true;
        //             }
        //         }
        //     }
        // }

        return isPalindrome;
    }
}
