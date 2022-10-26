package algorithm.leetCode;

/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:
'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

### Example
"12" -> 2 It could be decoded as "AB" (1 2) or "L" (12).
"226" -> 3 It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

*/

import java.util.Arrays;

public class M_DP_String_91 {

    public static void main(String... args) {
//        System.out.println(numDecodings2("0"));      // 0
//        System.out.println(numDecodings2("12"));     // 2
//        System.out.println(numDecodings2("226"));    // 3
//        System.out.println(numDecodings2("234"));    // 2
//        System.out.println(numDecodings2("232344")); // 4
//        System.out.println(numDecodings2("212221")); // 13
        System.out.println(numDecodings00("111111111111111111111111111111111111111111111")); // 1836311903
    }

    // DP, iterative
    // 1ms
    public int numDecodings0(String s) {
        final int L = s.length();

        // result[i]: when s size is i, how many ways to decode
        int[] result = new int[L + 1];

        // !!! 0 ways to decode a empty string. dp[0] set to 1 only to get the result for dp[2].
        result[0] = 1;
        result[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= L; ++i) {
            int singleDigitNum = s.charAt(i - 1) - '0';
            int doubleDigitNum = (s.charAt(i - 2) - '0') * 10 + singleDigitNum;
//            int doubleDigit = Integer.valueOf(s.substring(i, i + 2));  // alternatively

            if (singleDigitNum != 0) {
                result[i] = result[i - 1];
            }

            if (doubleDigitNum >= 10 && doubleDigitNum <= 26) {
                result[i] += result[i - 2];
            }
        }

        return result[L];
    }

    // DP, recursive, TLE
    public static int numDecodings00(String s) {
        return count(s, s.length());
    }

    private static int count(String s, int i) {
        if (i == 0) {
            return 1;
        }

        if (i == 1) {
            return s.charAt(i - 1) == '0' ? 0 : 1;
        }

        int count = 0;

        int singleDigitNum = s.charAt(i - 1) - '0';
        if (singleDigitNum != 0) {
            count += count(s, i - 1);
        }

        int doubleDigitNum = (s.charAt(i - 2) - '0') * 10 + singleDigitNum;
        if (doubleDigitNum >= 10 && doubleDigitNum <= 26) {
            count += count(s, i - 2);
        }

        return count;
    }

    // DP, recursive
    // 1ms
    public static int numDecodings000(String s) {
        Integer[] memo = new Integer[s.length()];
        
        return count(s, s.length() - 1, memo); 
    }

    private static int count(String s, int idx, Integer[] memo) {
        if (idx == -1) {
            return 1;
        }

        if (memo[idx] != null) {
            return memo[idx];
        }

        if (idx == 0) {
            memo[idx] = s.charAt(idx) == '0' ? 0 : 1;
            return memo[idx];
        }

        int count = 0;
    
        int singleDigitNum = s.charAt(idx) - '0';
        if (singleDigitNum != 0) {
            count += count(s, idx - 1, memo);
        }

        int doubleDigitNum = (s.charAt(idx - 1) - '0') * 10 + singleDigitNum;
        if (doubleDigitNum >= 10 && doubleDigitNum <= 26) {
            // !!! since idx - 2, we need check if idx == -1 so we can return 1
            count += count(s, idx - 2, memo);
        }
    
        memo[idx] = count;
        return memo[idx];
    }

    // DP, iterative
    // 1ms
    public static int numDecodings2(String s) {
        // result[i]: number of decoding for s.substring(i, s.length())
        // result[i] depends on result[i+1], so initiate result[s.length()] = 1 and iterate backwards
        final int L = s.length();

        int[] result = new int[L + 1];
        result[L] = 1;

        for (int i = L - 1; i >= 0; --i) {
            if (s.charAt(i) == '0') {
                result[i] = 0;
            } else {
                result[i] = result[i + 1];

                if (i < L - 1) {
                    int doubleDigit = Integer.valueOf(s.substring(i, i + 2));
                    if (doubleDigit <= 26) {
                        result[i] += result[i + 2];
                    }
                }
            }
        }

        return result[0];
    }

    // DP, recursive, DFS
    public static int numDecodings(String s) {
        return numDecodings(s, 0);
    }

    // get number of ways to decode s.substring(idx, s.length)
    private static int numDecodings(String s, int idx) {
        // base case
        if (idx == s.length()) {
            return 1;
        }

        // not possible to have leading 0
        if (s.charAt(idx) == '0') {
            return 0;
        }

        // case 1: result for case idx === result for idx + 1
        int result = numDecodings(s, idx + 1);

        if (idx < s.length() - 1) {
            int doubleDigit = Integer.parseInt(s.substring(idx, idx + 2));
            if (doubleDigit <= 26) {
                // case 2: result for case idx === result for idx + 2
                result += numDecodings(s, idx + 2);
            }
        }

        return result;
    }

    // DP, recursive, with memorization
    // 51 ms
    public static int numDecodings1(String s) {
        return numDecodings1(s, 0, new int[s.length() + 1]);
    }

    private static int numDecodings1(String s, int idx, int[] cache) {
        if (cache[idx] != 0) {
            return cache[idx];
        }

        // base case
        if (idx == s.length()) {
            return 1;
        }

        // not possible to have leading 0
        if (s.charAt(idx) == '0') {
            return 0;
        }

        int result = numDecodings1(s, idx + 1, cache);

        if (idx < s.length() - 1) {
            int doubleDigit = Integer.parseInt(s.substring(idx, idx + 2));
            if (doubleDigit <= 26) {
                result += numDecodings1(s, idx + 2, cache);
            }
        }

        cache[idx] = result;

        return result;
    }

}