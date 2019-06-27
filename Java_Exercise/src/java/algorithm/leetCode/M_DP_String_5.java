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
//        System.out.println(longestPalindrome(""));   // ""
//        System.out.println(longestPalindrome(" "));  // " "
//        System.out.println(longestPalindrome("  ")); // "  "
        System.out.println(longestPalindrome("a")); // "a"
//        System.out.println(longestPalindrome("aba")); // aba
//        System.out.println(longestPalindrome("babad")); // bab
//        System.out.println(longestPalindrome("cbbd")); // bb
//        System.out.println(longestPalindrome("eabcb")); // bcb
    }

    // Two Pointer
    public static String longestPalindrome(String s) {
        if (s == null ){
            return "";
        }

        if (s.length() < 2){
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

}