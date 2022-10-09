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

    // DP, iterative
    // 30ms
    public int minCut(String s) {
        int L = s.length();

        // minCuts[i]: minimal cuts from string from 0 to i
        int[] minCuts = new int[L];

        boolean[][] isPalindrome = new boolean[L][L];

        for (int end = 0; end < L; ++end) {
            // at most (end) cuts
            minCuts[end] = end;

            for (int start = 0; start <= end; ++start) {

                if (s.charAt(start) == s.charAt(end)) {

                    if (start + 1 < end - 1) {
                        isPalindrome[start][end] = isPalindrome[start + 1][end - 1];
                    } else {
                        isPalindrome[start][end] = true;
                    }

                    if (isPalindrome[start][end]) {
                        // since s.substring(start, end) is palindrome
                        // we can cut before the start, basically, 1 more cut than mins[start -1]
                        minCuts[end] = start >= 1 ? Math.min(minCuts[end], minCuts[start - 1] + 1) : 0;
                    }
                }

            }
        }

        return minCuts[L - 1];
    }

    // DP, recursive
    // 3ms
    public int minCut1(String s) {
        int L = s.length();

        int[] minCut = new int[L];
        Arrays.fill(minCut, L);

        for (int mid = 0; mid < L; ++mid) {
            checkPalindrome(s, mid, mid, minCut); // Odd Length Palindrome
            checkPalindrome(s, mid, mid + 1, minCut); // Even Length Palindrome
        }

        return minCut[L - 1];
    }

    private void checkPalindrome(String s, int start, int end, int[] minCut) {
        int L = s.length();

        while (start >= 0 && end < L && s.charAt(start) == s.charAt(end)) {
            minCut[end] = start == 0 ? 0 : Math.min(minCut[end], minCut[start - 1] + 1);

            start--;
            end++;
        }
    }

    // DP recursive
    // 1000ms
    public int minCut2(String s) {
        final int L = s.length();
        Integer[][] memo = new Integer[L][L];

        return findCuts(s, 0, L - 1, memo);
    }

    int findCuts(String s, int start, int end, Integer[][] memo) {
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

        int min = Integer.MAX_VALUE;

        for (int i = start; i <= end; ++i) {

            if (isPalindrome(s, start, i)) {
                int partitions = 1 + findCuts(s, i + 1, end, memo);
                min = Math.min(min, partitions);
            }
        }

        memo[start][end] = min;

        return memo[start][end];
    }

    private static boolean isPalindrome(String s, int left, int right) {
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

}
