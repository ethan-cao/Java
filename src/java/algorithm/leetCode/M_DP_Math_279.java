package algorithm.leetCode;

/*
Given a positive integer n (n > 0)
find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n
1 <= n <= 10^4

### Example
n = 12 -> 3
Explanation: 12 = 4 + 4 + 4.

n = 13 -> 2
Explanation: 13 = 4 + 9.
*/

public class M_DP_Math_279 {

    public static void main(String... args) {
//        System.out.println(numSquares4(2)); // 2
//        System.out.println(numSquares4(4)); // 1
//        System.out.println(numSquares4(9)); // 1
//        System.out.println(numSquares4(12)); // 3
//        System.out.println(numSquares(13));    // 2
//        System.out.println(numSquares(234));   // 2
        System.out.println(numSquares2(7168));  // 4
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int numSquares00(int n) {
        int[] counts = new int[n + 1];
        Arrays.fill(counts, Integer.MAX_VALUE);
    
        // BASE
        counts[0] = 0;
        counts[1] = 1;
    
        for (int num = 2; num <= n; ++num) {
            for (int squareRoot = 1; squareRoot * squareRoot <= num; ++squareRoot) {

                int countWithSquareRoot = 1 + counts[num - squareRoot * squareRoot];
                counts[num] = Math.min(counts[num], countWithSquareRoot);
            }
        }

        return counts[n];
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, recursive, LTE
    public static int numSquares1(int n) {
        int largestPerfectSquareRoot = (int) Math.sqrt(n);
        return count(n, largestPerfectSquareRoot);
    }

    private static int count(int num, int perfectSquareRoot) {
        if (num == 1) {
            return 1;
        }

        if (perfectSquareRoot == 1) {
            return num;
        }

        if (perfectSquareRoot <= 0) {
            return Integer.MAX_VALUE;
        }

        int count = num + 1;  // USE INTEGER_MAX could cause overflow !!!!
        int perfectSquare = perfectSquareRoot * perfectSquareRoot;

        if (perfectSquare <= num) {
            int countWithoutPerfectSquare = count(num, perfectSquareRoot - 1);
            int countWithPerfectSquare = 1 + count(num - perfectSquare, perfectSquareRoot);

            count = Math.min(countWithoutPerfectSquare, countWithPerfectSquare);
        } else {
            count = count(num, perfectSquareRoot - 1);
        }

        return count;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int numSquares2(int n) {
        int largestPerfectSquareRoot = (int) Math.sqrt(n);
        int[][] memo = new int[largestPerfectSquareRoot + 1][n + 1];

        return count(largestPerfectSquareRoot, n, memo);
    }

    private static int count(int perfectSquareRoot, int num, int[][] memo) {
        if (num == 1) {
            return 1;
        }

        if (perfectSquareRoot == 1) {
            return num;
        }

        if (memo[perfectSquareRoot][num] != 0) {
            return memo[perfectSquareRoot][num];
        }

        int count = Integer.MAX_VALUE;
        int perfectSquare = perfectSquareRoot * perfectSquareRoot;

        if (perfectSquare <= num) {
            int countWithoutPerfectSquare = count(perfectSquareRoot - 1, num, memo);
            int countWithPerfectSquare = 1 + count(perfectSquareRoot, num - perfectSquare, memo);

            count = Math.min(countWithoutPerfectSquare, countWithPerfectSquare);
        } else {
            count = count(perfectSquareRoot - 1, num, memo);
        }

        memo[perfectSquareRoot][num] = count;

        return memo[perfectSquareRoot][num];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, iterative, 73ms
    public static int numSquares3(int n) {
        int largestPerfectSquareRoot = (int) Math.sqrt(n);

        int[] perfectSquares = new int[largestPerfectSquareRoot + 1];
        for (int i = 0; i <= largestPerfectSquareRoot; ++i) {
            perfectSquares[i] = i * i;
        }

        // count[i][j]: the least number of perfect square ranging from 1...i to that sum to num j
        int[][] counts = new int[largestPerfectSquareRoot + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            counts[0][i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= largestPerfectSquareRoot; ++i) {
            for (int num = 1; num <= n; ++num) {
                int perfectSquare = perfectSquares[i];

                if (perfectSquare <= num) {
                    // since it always compare with the 1st row, counts[1][j] = Math.min(counts[0][j], ...)
                    // so assign Integer.MAX to the 1st row counts[0]
                    counts[i][num] = Math.min(counts[i - 1][num], 1 + counts[i][num - perfectSquare]);
                } else {
                    counts[i][num] = counts[i - 1][num];
                }
            }
        }

        return counts[largestPerfectSquareRoot][n];
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, iterative, condensed space, 39ms
    public static int numSquares4(int n) {
        int largestPerfectSquareRoot = (int) Math.sqrt(n);

        int[] squares = new int[largestPerfectSquareRoot + 1];
        for (int i = 1; i <= largestPerfectSquareRoot; ++i) {
            squares[i] = i * i;
        }

        int[] counts = new int[n + 1];
        for (int num = 1; num <= n; ++num) {
            counts[num] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= largestPerfectSquareRoot; ++i) {
            for (int num = 1; num <= n; ++num) {
                int square = squares[i];

                if (square <= num) {
                    counts[num] = Math.min(counts[num], 1 + counts[num - square]);
                } else {
                    counts[num] = counts[num];
                }
            }
        }

        return counts[n];
    }



    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Based on Lagrange's Four Square theorem: natural number can be represented as the sum of four integer squares
    // https://en.wikipedia.org/wiki/Lagrange%27s_four-square_theorem
    // so there are only 4 possible results: 1, 2, 3, 4
    public static int numSquares5(int n) {
        // If n is a perfect square, return 1.
        int x = (int) Math.sqrt(n);
        if (x * x == n) {
            return 1;
        }

        for (int j = 1; j * j < n; ++j) {
            int y = (int) Math.sqrt(n - j * j);
            if (y * y == n - j * j) {
                return 2;
            }
        }

        // The result is 4 if and only if n can be written in the
        // form of 4^k*(8*m + 7). Please refer to
        // Legendre's three-square theorem.
        while (n % 4 == 0) {// n%4 == 0
            n /= 4;
        }
        if (n % 8 == 7) {// n%8 == 7
            return 4;
        }

        return 3;
    }

}