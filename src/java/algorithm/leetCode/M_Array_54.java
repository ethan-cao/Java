package algorithm.leetCode;

/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

### Example
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_Array_54 {

    public static void main(String... args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };

        System.out.println(Arrays.toString(spiralOrder(matrix).toArray()));
        //  [1,2,3,6,9,8,7,4,5]

        int[][] matrix1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        System.out.println(Arrays.toString(spiralOrder(matrix1).toArray()));
        // [1,2,3,4,8,12,11,10,9,5,6,7]
    }

    // time O(N)
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<>();


        return order;
    }

}