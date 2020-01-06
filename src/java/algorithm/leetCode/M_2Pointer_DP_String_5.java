package algorithm.leetCode;

/*
Given a string s, find the longest palindromic substring in s.
You may assume that the maximum length of s is 1000.

### Example
"babad" -> "bab",  "aba" is also a valid answer.

"cbbd"-> "bb", The answer is "b", with the length of 1.

*/

public class M_2Pointer_DP_String_5 {

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

    // DP iterative, 2 pointer
    public static String longestPalindrome1(String s) {
        String longestPalindrome = "";

        // since we are talking about substring, we need the start and end index, so came up with this array
        // isPalindrome[x][y] indicates whether substring s that starts at index x and ends at y is palindrome
        boolean[][] isPalindrome = new boolean[s.length()][s.length()]; // by default, all false

        for (int end = 0; end < s.length(); ++end) {
            for (int start = end; start >= 0; --start) {

                // since isPalindrome[start][end] could depends on isPalindrome[start + 1][end - 1],
                // we need make sure isPalindrome[start + 1][end - 1] is already checked

                if (end - start + 1 <= 3) {
                    // base problem
                    // char size <= 3, end matches start is enough, the middle does not matter
                    isPalindrome[start][end] = s.charAt(start) == s.charAt(end);
                } else {
                    // char size >= 3, substring (end+1, start-1) should be palindrome too, substring (end+1, start-1) has been examined before
                    isPalindrome[start][end] = s.charAt(start) == s.charAt(end) && isPalindrome[start + 1][end - 1];
                }

                if (isPalindrome[start][end] && end - start + 1 > longestPalindrome.length()) {
                    longestPalindrome = s.substring(start, end + 1);
                }
            }
        }

        return longestPalindrome;
    }

    // 2 pointer, faster
    public static String longestPalindrome(String s) {
        String longestPalindrome = "";

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