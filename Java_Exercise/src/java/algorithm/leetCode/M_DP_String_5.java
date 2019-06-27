package algorithm.leetCode;

/*
Given a string s, find the longest palindromic substring in s.
You may assume that the maximum length of s is 1000.

### Example
"babad" -> "bab"
"aba" is also a valid answer.

"cbbd"-> "bb"
The answer is "b", with the length of 1.

# related : 647
*/


public class M_DP_String_5 {

    public static void main(String... args) {
        System.out.println(longestPalindrome1(""));   // ""
        System.out.println(longestPalindrome1(" "));  // " "
        System.out.println(longestPalindrome1("  ")); // "  "
        System.out.println(longestPalindrome1("a")); // "a"
        System.out.println(longestPalindrome1("aba")); // aba
        System.out.println(longestPalindrome1("babad")); // bab
        System.out.println(longestPalindrome1("cbbd")); // bb
        System.out.println(longestPalindrome1("eabcb")); // bcb
    }

    // Two Pointer
    public static String longestPalindrome(String s) {
        if (s == null) {
            return "";
        }

        if (s.length() < 2) {
            return s;
        }

        String longestPalindrome = "";

        // check until i - 1
        for (int i = 0; i < s.length() - 1; ++i) {
            // i is the middle point
            String oddPalindrome = getLongPalindrome(s, i, i); // handle odd length palindrome
            String evenPalindrome = getLongPalindrome(s, i, i + 1); // handle even length palindrome

            String longerPalindrome = oddPalindrome.length() > evenPalindrome.length() ? oddPalindrome : evenPalindrome;
            longestPalindrome = longestPalindrome.length() > longerPalindrome.length() ? longestPalindrome : longerPalindrome;
        }

        return longestPalindrome;
    }

    private static String getLongPalindrome(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }

        start++;
        end--;

        return s.substring(start, end + 1);
    }

    // DP
    public static String longestPalindrome1(String s) {
        final int L = s.length();
        String longestPalindrome = null;

        boolean[][] dp = new boolean[L][L]; // by default, all false
        // dp[i][j] indicates whether substring s starting at index i and ending at j is palindrome

        for (int i = L - 1; i >= 0; i--) {
            for (int j = i; j < L; j++) {
                dp[i][j] =
                        s.charAt(i) == s.charAt(j)
                                &&
                        (j - i < 3 || dp[i + 1][j - 1]);
                // if window <= 3 (element number <= 3), i match j is enough, the middle if there is does not matter
                // if window > 3, substring (i+1, j-1) should be palindrome too, substring (i+1, j-1) has been examined before

                if (dp[i][j] && (longestPalindrome == null || j - i + 1 > longestPalindrome.length())) {
                    longestPalindrome = s.substring(i, j + 1);
                }
            }
        }

        return longestPalindrome;
    }

}