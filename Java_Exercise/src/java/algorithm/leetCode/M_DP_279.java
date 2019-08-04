package algorithm.leetCode;

/*
Given a positive integer n,
find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n

### Example
Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
*/

public class M_DP_279 {

    public static void main(String... args) {
        System.out.println(numSquares1(0)); // 0
        System.out.println(numSquares1(2)); // 2
        System.out.println(numSquares1(4)); // 1
        System.out.println(numSquares1(9)); // 1
        System.out.println(numSquares1(12)); // 3
        System.out.println(numSquares1(13)); // 2
        System.out.println(numSquares1(234)); // 2
    }

    public static int numSquares(int n) {
        int upperLimit = (int) Math.sqrt(n);
        int[] perfectSquareNumber = new int[upperLimit];
        for (int i = 0; i < upperLimit; ++i) {
            perfectSquareNumber[i] = (i + 1) * (i + 1);
        }

        return getNumber(perfectSquareNumber, 0, n);
    }

    private static int getNumber(int[] perfectSquareNumber, int idx, int n) {
        if (n == 0) {
            return 0;
        }

        int number = n + 1;  // USE INTEGER_MAX could cause overflow !!!!

        for (int i = idx; i < perfectSquareNumber.length; ++i) {
            int perfectSquare = perfectSquareNumber[i];

            if (perfectSquare <= n) {
                int numberWithCurrentSquare = 1 + getNumber(perfectSquareNumber, i, n - perfectSquare);

                number = Math.min(number, numberWithCurrentSquare);
            }
        }

        return number;
    }

    public static int numSquares1(int n) {
        int upperLimit = (int) Math.sqrt(n);
        int[] perfectSquareNumber = new int[upperLimit];
        for (int i = 0; i < upperLimit; ++i) {
            perfectSquareNumber[i] = (i + 1) * (i + 1);
        }

        int[][] cache = new int[upperLimit + 1][n + 1];

        return getNumber(perfectSquareNumber, 0, n, cache);
    }

    private static int getNumber(int[] perfectSquareNumber, int idx, int n, int[][] cache) {
        if (n == 0) {
            return 0;
        }

        int number = n + 1;

        for (int i = idx; i < perfectSquareNumber.length; ++i) {
            int perfectSquare = perfectSquareNumber[i];

            if (perfectSquare <= n) {
                int numberWithCurrentSquare = 1 + getNumber(perfectSquareNumber, i, n - perfectSquare);

                number = Math.min(number, numberWithCurrentSquare);
            }
        }

        return number;
    }

}