package leetCode;

import algorithm.search.Trie;
/*
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.


### Example
"abc" ->  3, Explanation: Three palindromic strings: "a", "b", "c".
"aaa" ->  6, Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

### Condition
The input string length won't exceed 1000.

### Essential problem

### Corner case

*/

public class M_DP_String_647 {

    public static void main(String[] args) {
        System.out.println(countSubstrings(""));     // 0
        System.out.println(countSubstrings("a"));    // 1
        System.out.println(countSubstrings("aa"));   // 3
        System.out.println(countSubstrings("aaa"));  // 6
        System.out.println(countSubstrings("abc"));  // 3
    }

    public static int countSubstrings(String s) {
        int result = 0;

        // i is the middle point
        for (int i = 0; i < s.length(); ++i) {
            result += countPalindrome(s, i, i); // handle odd length palindrome
            result += countPalindrome(s, i, i + 1); // handle even length palindrome
        }

        return result;
    }

    private static int countPalindrome(String s, int left, int right) {
        int result = 0;

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            result++;

            left--;
            right++;
        }

        return result;
    }

}

