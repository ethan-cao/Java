package algorithm.leetCode;

/*
Given a string, your task is to count how many palindromic substrings in this string.
The substrings with different start indexes or
end indexes are counted as different substrings even they consist of same characters.

The input string length won't exceed 1000.

### Example
"abc" ->  3, Explanation: Three palindromic strings: "a", "b", "c".
"aaa" ->  6, Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

*/

public class M_DP_String_647 {

    public static void main(String[] args) {
        System.out.println(countSubstrings1("")); // 0
        System.out.println(countSubstrings1("a")); // 1
        System.out.println(countSubstrings1("aa")); // 3
        System.out.println(countSubstrings1("aaa")); // 6
        System.out.println(countSubstrings1("abc")); // 3
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Two pointer
    // 8ms
    public static int countSubstrings(String s) {
        int count = 0;

        // i is the middle point
        for (int i = 0; i < s.length(); ++i) {
            count += countPalindrome(s, i, i); // handle odd length palindrome
            count += countPalindrome(s, i, i + 1); // handle even length palindrome
        }

        return count;
    }

    // TODO: use memo
    private static int countPalindrome(String s, int left, int right) {
        int result = 0;

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            result++;

            left--;
            right++;
        }

        return result;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, recursive
    // 55ms
    public int countSubstrings11(String s) {
        final int L = s.length();

        int count = 0;
        Boolean[][] isPalindrome = new Boolean[L][L];

        for (int end = 0; end < L; ++end) {
            for (int start = 0; start <= end; ++start) {
                if (isPalindrome(s, start, end, isPalindrome)) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isPalindrome(String s, int start, int end, Boolean[][] isPalindrome) {
        if (isPalindrome[start][end] != null) {
            return isPalindrome[start][end];
        }

        if (start == end) {
            // BASE
            isPalindrome[start][end] = true;
        } else {
            // TRANSFORM
            if (start + 1 <= end - 1) {
                isPalindrome[start][end] = s.charAt(start) == s.charAt(end)
                        && isPalindrome(s, start + 1, end - 1, isPalindrome);
            } else {
                isPalindrome[start][end] = s.charAt(start) == s.charAt(end);
            }
        }

        return isPalindrome[start][end];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP iterative
    // 12 ms
    public static int countSubstrings1(String s) {
        final int L = s.length();

        int count = 0;

        // since we are talking about substring, we need the start and end index, so
        // came up with this array
        // isPalindrome[x][y] indicates whether substring s that starts at index x and
        // ends at y is palindrome, both inclusive
        boolean[][] isPalindrome = new boolean[L][L];

        for (int end = 0; end < L; ++end) {
            for (int start = end; start >= 0; --start) {

                // we cannot use for (int left = right; left < L; ++left) as 2nd iteration and
                // right as start, left as end
                // since isPalindrome[left][right] could depends on isPalindrome[left + 1][right
                // - 1], we need make sure isPalindrome[left + 1][right - 1] is already checked
                if (end == start) {
                    // BASE
                    isPalindrome[start][end] = true;
                } else {
                    // TRANSFORM
                    if (start + 1 < end - 1) {
                        isPalindrome[start][end] = s.charAt(start) == s.charAt(end) && isPalindrome[start + 1][end - 1];
                    } else {
                        isPalindrome[start][end] = s.charAt(start) == s.charAt(end);
                    }
                }

                if (isPalindrome[start][end]) {
                    count++;
                }
            }
        }

        return count;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP iterative
    // 35 ms
    public int countSubstrings111(String s) {
        final int L = s.length();

        int count = 0;
        Boolean[][] isPalindrome = new Boolean[L][L];

        for (int end = 0; end < L; ++end) {
            for (int start = 0; start <= end; ++start) {

                if (start == end) {
                    isPalindrome[start][end] = true;
                } else {
                    if (start + 1 < end - 1) {
                        isPalindrome[start][end] = s.charAt(start) == s.charAt(end) && isPalindrome[start + 1][end - 1];
                    } else {
                        isPalindrome[start][end] = s.charAt(start) == s.charAt(end);
                    }
                }

                if (isPalindrome[start][end]) {
                    count++;
                }
            }
        }

        return count;
    }

}