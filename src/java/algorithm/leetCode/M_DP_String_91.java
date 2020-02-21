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

public class M_DP_String_91 {

    public static void main(String... args) {
        System.out.println(numDecodings2("0"));      // 0
        System.out.println(numDecodings2("12"));     // 2
        System.out.println(numDecodings2("226"));    // 3
        System.out.println(numDecodings2("234"));    // 2
        System.out.println(numDecodings2("232344")); // 4
        System.out.println(numDecodings2("212221")); // 13
    }

    // recursive, DFS
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


    // recursive, DFS, with cache
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

    // iterative
    // 1ms
    public static int numDecodings2(String s) {
        // numDecodings[i]: number of decoding for s.substring(i, s.length())
        // numDecodings[i] depends on numDecodings[i+1], so initiate numDecodings[s.length()] = 1 and iterate backwards
        int[] numDecodings = new int[s.length() + 1];
        numDecodings[s.length()] = 1;

        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) == '0') {
                numDecodings[i] = 0;
            } else {
                numDecodings[i] = numDecodings[i + 1];

                if (i < s.length() - 1) {
                    int doubleDigit = Integer.parseInt(s.substring(i, i + 2));
                    if (doubleDigit <= 26) {
                        numDecodings[i] += numDecodings[i + 2];
                    }
                }
            }
        }

        return numDecodings[0];
    }

}