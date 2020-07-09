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
        System.out.println(countSubstrings1(""));     // 0
        System.out.println(countSubstrings1("a"));    // 1
        System.out.println(countSubstrings1("aa"));   // 3
        System.out.println(countSubstrings1("aaa"));  // 6
        System.out.println(countSubstrings1("abc"));  // 3
    }

    //DP iterative
    public static int countSubstrings1(String s) {
        int count = 0;

        // since we are talking about substring, we need the start and end index, so came up with this array
        // isPalindrome[x][y] indicates whether substring s that starts at index x and ends at y is palindrome
        boolean[][] isPalindrome = new boolean[s.length()][s.length()]; // by default, all false

        for (int left = 0; left < s.length(); ++left) {
            for (int right = left; right >= 0; --right) {

                // we cannot use for (int right = left; right < L; ++right) as 2nd iteration and left as start, right as end
                // since isPalindrome[right][left] could depends on isPalindrome[right + 1][left - 1], we need make sure isPalindrome[right + 1][left - 1] is already checked

                if (left - right < 3) {
                    isPalindrome[right][left] = s.charAt(right) == s.charAt(left);
                } else {
                    isPalindrome[right][left] = s.charAt(right) == s.charAt(left) && isPalindrome[right + 1][left - 1];
                }

                if (isPalindrome[right][left]) {
                    count++;
                }
            }
        }

        return count;
    }

    //DP iterative, Two pointer, faster
    public static int countSubstrings(String s) {
        int count = 0;

        // i is the middle point
        for (int i = 0; i < s.length(); ++i) {
            count += countPalindrome(s, i, i); // handle odd length palindrome
            count += countPalindrome(s, i, i + 1); // handle even length palindrome
        }

        return count;
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