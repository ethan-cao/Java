package algorithm.leetCode;

/*
Implement int sqrt(int x).
Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
Since the return type is an integer, the decimal digits are truncated, only the integer part of the result is returned.

### Example
4 -> 2
8 -> 2
*/

public class E_BinarySearch_69 {

    public static void main(String[] args) {
        System.out.println(mySqrt(0));  // 0
        System.out.println(mySqrt(1));  // 1
        System.out.println(mySqrt(4));  // 2
        System.out.println(mySqrt(8));  // 2
    }

    public static int mySqrt(int x) {
        int left = 1;
        int right = x;

        // find the right boundary that makes right > x /middle
        while (left <= right) {
            int middle = left + (right - left) / 2;

            // !!! use (x / middle) to prevent overflow
            if (middle == x / middle) {
                return middle;
            } else if (middle > x / middle) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return right;
    }

}
