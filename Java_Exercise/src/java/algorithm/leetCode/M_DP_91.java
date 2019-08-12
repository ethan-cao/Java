package algorithm.leetCode;

/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:
'A' -> 1
'B' -> 2
...
'Z' -> 26

Given a non-empty string containing only digits, determine the total number of ways to decode it.

### Example
"12" -> 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).

"226" -> 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

*/


public class M_DP_91 {

    public static void main(String... args) {
        System.out.println(numDecodings("12"));     // 2
        System.out.println(numDecodings("226"));    // 3
        System.out.println(numDecodings("234"));    // 2
        System.out.println(numDecodings("232344")); // 4
    }

    public static int numDecodings(String s) {
        int result = 0;

        for (int i = 0; i < s.length() - 1; ++i) {
            for (int j = i; j < s.length() - 1; ++j) {
                int doubleDigit = Integer.parseInt(s.substring(j, j + 2));
                if (doubleDigit <= 26) {
                    j++;
                }

                if (doubleDigit <= 26 && j == i) {
                    i++;
                }

                if (j == s.length() - 1) {
                    result++;
                }
            }

            result++;
        }

        return result;
    }

}
