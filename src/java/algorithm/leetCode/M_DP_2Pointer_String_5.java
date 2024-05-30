package algorithm.leetCode;

/*
Given a string s, find the longest palindromic substring in s.
You may assume that the maximum length of s is 1000.

### Example
"babad" -> "bab",  "aba" is also a valid answer.
"cbbd"-> "bb", The answer is "b", with the length of 1.

*/

public class M_DP_2Pointer_String_5 {

    public static void main(String... args) {
        System.out.println(longestPalindrome("")); // ""
        System.out.println(longestPalindrome(" ")); // " "
        System.out.println(longestPalindrome("  ")); // " "
        System.out.println(longestPalindrome("a")); // "a"
        System.out.println(longestPalindrome("aba")); // aba
        System.out.println(longestPalindrome("babad")); // bab
        System.out.println(longestPalindrome("cbbd")); // bb
        System.out.println(longestPalindrome("eabcb")); // bcb
        System.out.println(longestPalindrome("aaabaaaa")); // "aaabaaa"
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 2 pointer, 28ms
    // Time: O(N^2)
    // expand-around-center technique
    public static String longestPalindrome(String s) {
        String longestPalindrome = "";

        for (int idx = 0; idx < s.length(); ++idx) {
            String oddPalindrome = expandAroundCenter(s, idx, idx);
            if (oddPalindrome.length() > longestPalindrome.length()) {
                longestPalindrome = oddPalindrome;
            }

            String evenPalindrome = expandAroundCenter(s, idx, idx + 1);
            if (evenPalindrome.length() > longestPalindrome.length()) {
                longestPalindrome = evenPalindrome;
            }
        }

        return longestPalindrome;
    }

    private static String expandAroundCenter(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }

        // !!! reset
        start++;
        end--;

        return s.substring(start, end + 1);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, iterative, 149 ms
    // Time: O(N^2)
    public static String longestPalindrome3(String s) {
        final int L = s.length();

        // since we are talking about substring, we need the start and end index, so
        // came up with this array
        // isPalindrome[x][y] indicates whether substring s that starts at index x and
        // ends at y is palindrome
        Boolean[][] isPalindrome = new Boolean[L][L];

        int longestStart = 0;
        int longestEnd = 0;

        for (int end = 0; end < L; ++end) {
            for (int start = 0; start <= end; ++start) {

                if (start == end || start + 1 == end) {
                    // BASE
                    isPalindrome[start][end] = s.charAt(start) == s.charAt(end);
                } else {
                    // TRANSFORM
                    isPalindrome[start][end] = s.charAt(start) == s.charAt(end) && isPalindrome[start + 1][end - 1];
                }

                if (isPalindrome[start][end] && end - start > longestEnd - longestStart) {
                    longestStart = start;
                    longestEnd = end;
                }
            }
        }

        return s.substring(longestStart, longestEnd + 1);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, iterative, 110ms
    // Time: O(N^2)
    public String longestPalindrome2(String s) {
        final int L = s.length();

        // since we are talking about substring, we need the start and end index, so
        // came up with this array
        // isPalindrome[x][y] indicates whether substring s that starts at index x and
        // ends at y is palindrome
        Boolean[][] isPalindrome = new Boolean[L][L];

        int longestStart = 0;
        int longestEnd = 0;

        for (int end = 0; end < L; ++end) {
            for (int start = 0; start <= end; ++start) {

                if (start + 1 == end) {
                    // BASE
                    isPalindrome[start][end] = s.charAt(start) == s.charAt(end);
                } else {
                    // TRANSFORM
                    isPalindrome[start][end] = s.charAt(start) == s.charAt(end) && isPalindrome[start + 1][end - 1];
                }

                if (isPalindrome[start][end] && end - start > longestEnd - longestStart) {
                    longestStart = start;
                    longestEnd = end;
                }
            }
        }

        return s.substring(longestStart, longestEnd + 1);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, recursive, 550ms
    // Time: O(N^2)
    public String longestPalindrome4(String s) {
        final int L = s.length();
        Boolean[][] memo = new Boolean[L][L];

        // !!! loop based on length, cannot based on end, this guarantees the first
        // returned is the longest
        // if loop based on end and start, need to check all cases to find the longest
        // top-down
        for (int length = L; length > 0; --length) {
            for (int start = 0; start + length <= L; ++start) {
                int end = start + length - 1;

                // since we are checking length from the longest one, the first returned is the
                // longest
                if (isPalindrome(s, start, end, memo)) {
                    // this is the DP part
                    return s.substring(start, end + 1);
                }
            }
        }

        return "";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, recursive, 550ms
    // Time: O(N^2)
    public String longestPalindrome1(String s) {
        final int L = s.length();

        int longestStart = 0;
        int longestEnd = 0;

        Boolean[][] isPalindrome = new Boolean[L][L];

        for (int end = 0; end < L; ++end) {
            for (int start = 0; start <= end; ++start) {
                if (isPalindrome(s, start, end, isPalindrome)) {
                    if (end - start + 1 > longestEnd - longestStart + 1) {
                        longestStart = start;
                        longestEnd = end;
                    }
                }
            }
        }

        return s.substring(longestStart, longestEnd + 1);
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

}