package algorithm.leetCode;

/*
Given a string s, find the longest palindromic substring in s.
You may assume that the maximum length of s is 1000.

### Example
"babad" -> "bab"
"aba" is also a valid answer.

"cbbd"-> "bb"
The answer is "b", with the length of 1.

# related : 1, 516, 647

this asks for substring !!!

*/

public class M_DP_String_5 {

    public static void main(String... args) {
        System.out.println(longestPalindrome(""));   // ""
        System.out.println(longestPalindrome(" "));  // " "
        System.out.println(longestPalindrome("  ")); // "  "
        System.out.println(longestPalindrome("a")); // "a"
        System.out.println(longestPalindrome("aba")); // aba
        System.out.println(longestPalindrome("babad")); // bab
        System.out.println(longestPalindrome("cbbd")); // bb
        System.out.println(longestPalindrome("eabcb")); // bcb
        System.out.println(longestPalindrome("aaabaaaa")); // "aaabaaa"
    }


    // DP iterative
    public static String longestPalindrome1(String s) {
        String longestPalindrome = "";

        int L = s.length();
        // since we are talking about substring, we need the start and end index, so came up with this array
        // isPalindrome[x][y] indicates whether substring s that starts at index x and ends at y is palindrome
        boolean[][] isPalindrome = new boolean[L][L]; // by default, all false

        for (int i = 0; i < L; ++i) {
            for (int j = i; j >= 0; --j) {
                // j: start index, i: end index

                // we cannot use for (int j = i; j < L; ++j) as 2nd iteration and i as start, j as end
                // since isPalindrome[j][i] could depends on isPalindrome[j + 1][i - 1], we need make sure isPalindrome[j + 1][i - 1] is already checked

                if (i - j + 1 <= 3) {
                    // base problem
                    // if window < 3 (element number <= 3), i matches j is enough, the middle if there is does not matter
                    isPalindrome[j][i] = s.charAt(j) == s.charAt(i);
                } else {
                    // if window >= 3, substring (i+1, j-1) should be palindrome too, substring (i+1, j-1) has been examined before
                    isPalindrome[j][i] = s.charAt(j) == s.charAt(i) && isPalindrome[j + 1][i - 1];
                }

                if (isPalindrome[j][i] && i - j + 1 > longestPalindrome.length()) {
                    longestPalindrome = s.substring(j, i + 1);
                }
            }
        }

        return longestPalindrome;
    }

    // DP iterative, Two Pointer, faster
    public static String longestPalindrome(String s) {
        String longestPalindrome = "";

        // check until i - 1
        for (int i = 0; i < s.length(); ++i) {
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

}