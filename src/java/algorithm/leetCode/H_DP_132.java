package algorithm.leetCode;

/*
Given a string s, partition s such that every substring of the partition is a palindrome.
Return the minimum cuts needed for a palindrome partitioning of s.

1 <= s.length <= 2000
s consists of lowercase English letters only.

### Example
"aab" -> 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.

*/

import java.util.Arrays;

public class H_DP_132 {

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, iterative
    // 44ms
    public int minCut(String s) {
        int L = s.length();

        // minCuts[i]: minimal cuts in string from 0 to i
        int[] minCuts = new int[L];
        boolean[][] isPalindrome = findPalindrome(s);

        for (int end = 0; end < L; ++end) {
            // at most (end) cuts !!!
            minCuts[end] = end;

            for (int start = end; start >= 0; --start) {
                if (isPalindrome[start][end])
                    if (start == 0) {
                        // BASE
                        // when start is 0, the substring is s.substring(0, end + 1), which is palindrome
                        minCuts[end] = 0;
                    } else {
                        // TRANSFORM
                        // s.substring(start, end + 1) is palindrome
                        // this means we need 1 more cut before strat, it becomes s.substring(0, start + 1) and s.substring(start, end + 1)
                        // the min cuts for s.substring(0, start + 1) is minCuts[start - 1], which is known!!!
                        // the min cuts for s.substring(startIdx, endIdx + 1) is minCuts[start - 1] + 1       
                        // since start relies on start - 1, the loop use ++start
                        minCuts[end] = Math.min(minCuts[end], minCuts[start - 1] + 1);
                    }
            }
        }

        return minCuts[L - 1];
    }

    private boolean[][] findPalindrome(String s) {
        final int L = s.length();
        boolean[][] isPalindrome = new boolean[L][L];

        for (int end = 0; end < s.length(); ++end) {
            for (int start = end; start >= 0; --start) {

                if (start == end) {
                    isPalindrome[start][end] = true;
                } else {
                    if (start + 1 <= end - 1) {
                        isPalindrome[start][end] = s.charAt(start) == s.charAt(end) && isPalindrome[start + 1][end - 1];
                    } else {
                        isPalindrome[start][end] = s.charAt(start) == s.charAt(end);
                    }
                }
            }
        }

        return isPalindrome;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, recursive
    // 3ms
    public int minCut1(String s) {
        int L = s.length();

        // minCuts[i]: minimal cuts from string from 0 to i
        int[] minCuts = new int[L];
        Arrays.fill(minCuts, L);

        for (int mid = 0; mid < L; ++mid) {
            checkPalindrome(s, mid, mid, minCuts); // Odd Length Palindrome
            checkPalindrome(s, mid, mid + 1, minCuts); // Even Length Palindrome
        }

        return minCuts[L - 1];
    }

    private void checkPalindrome(String s, int start, int end, int[] minCuts) {
        int L = s.length();

        while (start >= 0 && end < L && s.charAt(start) == s.charAt(end)) {
            minCuts[end] = start == 0 ? 0 : Math.min(minCuts[end], minCuts[start - 1] + 1);

            start--;
            end++;
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP recursive
    // 1000ms
    public int minCut2(String s) {
        final int L = s.length();

        // min cuts fro substring(i, j+1)
        Integer[][] memo = new Integer[L][L];

        return findCut(s, 0, L - 1, memo);
    }

    int findCut(String s, int start, int end, Integer[][] memo) {
        if (start >= end) {
            return 0;
        }

        if (memo[start][end] != null) {
            return memo[start][end];
        }

        if (isPalindrome(s, start, end)) {
            memo[start][end] = 0;
            return memo[start][end];
        }

        int minCut = Integer.MAX_VALUE; // or (end - start) at most

        for (int i = start; i <= end; ++i) {
            if (isPalindrome(s, start, i)) {
                int cut = 1 + findCut(s, i + 1, end, memo);
                minCut = Math.min(minCut, cut);
            }
        }

        memo[start][end] = minCut;

        return memo[start][end];
    }

    private static boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }

            start++;
            end--;
        }

        return true;
    }

}