package algorithm.leetCode;

/*
Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.

### Example
"aab" -> [ ["aa","b"],  ["a","a","b"] ]

*/

import java.util.*;

public class M_Backtrack_Array_131 {

    // Backtrack + DP
    // Time: O(2^n)
    public List<List<String>> partition(String s) {
        List<List<String>> partitions = new ArrayList<>();
        List<String> tracker = new ArrayList<>();

        boolean[][] isPalindrome = checkPalindrome(s);

        getPartitions(s, 0, partitions, tracker, isPalindrome);

        return partitions;
    }

    private boolean[][] checkPalindrome(String s) {
        final int L = s.length();
        boolean[][] isPalindrome = new boolean[L][L];

        for (int end = 0; end < L; ++end) {
            for (int start = 0; start <= end; ++start) {
                if (s.charAt(start) == s.charAt(end)) {
                    if (start + 1 < end - 1) {
                        isPalindrome[start][end] = isPalindrome[start + 1][end - 1];
                    } else {
                        isPalindrome[start][end] = true;
                    }
                }
            }
        }

        // alternatively
        // for (int end = 0; end < L; ++end) {
        //     for (int start = end; start >= 0; --start) {

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

    private void getPartitions(
            String s,
            int start,
            List<List<String>> partitions,
            List<String> tracker,
            boolean[][] isPalindrome
    ) {
        if (start == s.length()) {
            partitions.add(new ArrayList<>(tracker));
            return;
        }

        for (int end = start; end < s.length(); ++end) {
            if (isPalindrome[start][end]) {
                tracker.add(s.substring(start, end + 1));
                
                getPartitions(s, end + 1, partitions, tracker, isPalindrome);

                tracker.remove(tracker.size() - 1);
            }
        }
    }

}