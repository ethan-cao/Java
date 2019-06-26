package algorithm.leetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Given a string s, find the longest palindromic substring in s.
You may assume that the maximum length of s is 1000.

### Example
"babad" -> "bab"
"aba" is also a valid answer.

"cbbd"-> "bb"
The answer is "b", with the length of 1.

*/


public class M_DP_String_5 {

    public static void main(String... args) {
        System.out.println(longestPalindrome(" ")); //
        System.out.println(longestPalindrome("")); //
        System.out.println(longestPalindrome("babad")); // bab
        System.out.println(longestPalindrome("cbbd")); // bb
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        String longestPalindrome = null;


        return longestPalindrome;
    }
}

