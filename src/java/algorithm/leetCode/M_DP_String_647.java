package algorithm.leetCode;

/*
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.


### Example
"abc" ->  3, Explanation: Three palindromic strings: "a", "b", "c".
"aaa" ->  6, Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

### Condition
The input string length won't exceed 1000.

# Related : 5

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

        int L = s.length();
        // since we are talking about substring, we need the start and end index, so came up with this array
        // isPalindrome[x][y] indicates whether substring s that starts at index x and ends at y is palindrome
        boolean[][] isPalindrome = new boolean[L][L]; // by default, all false

        for (int i = 0; i < L; ++i) {
            for (int j = i; j >= 0; --j) {

                // we cannot use for (int j = i; j < L; ++j) as 2nd iteration and i as start, j as end
                // since isPalindrome[j][i] could depends on isPalindrome[j + 1][i - 1], we need make sure isPalindrome[j + 1][i - 1] is already checked

                if (i - j < 3) {
                    isPalindrome[j][i] = s.charAt(j) == s.charAt(i);
                } else {
                    isPalindrome[j][i] = s.charAt(j) == s.charAt(i) && isPalindrome[j + 1][i - 1];
                }

                if (isPalindrome[j][i]) {
                    count++;
                }
            }
        }

        return count;
    }

    //DP iterative, Two pointer, faster
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
