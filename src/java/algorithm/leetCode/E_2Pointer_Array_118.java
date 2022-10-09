package algorithm.leetCode;

/*
Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.

### Example
Input: 5
Output:
0 --> []
1 --> [[1]]
2 --> [[1], [1,1]]
3 --> [[1], [1,1], [1,2,1]]
4 --> [[1], [1,1], [1,2,1], [1,3,3,1]]
5 --> [[1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1]]

*/


import java.util.*;

public class E_2Pointer_Array_118 {

    public static void main(String... args) {
        List<List<Integer>> result = generate(5);

        System.out.println(Arrays.toString(result.toArray()));
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();

        if (0 >= numRows) {
            return result;
        }

        for (int i = 0; i < numRows; ++i) {
            List<Integer> rowI = new ArrayList<>();

            for (int j = 0; j <= i; ++j) {
                int numAtColumnJ;

                if (0 == j || i == j) {
                    numAtColumnJ = 1;
                } else {
                    List<Integer> rowAbove = result.get(i - 1);
                    numAtColumnJ = rowAbove.get(j - 1) + rowAbove.get(j);
                }

                rowI.add(numAtColumnJ);
            }

            result.add(rowI);
        }

        return result;

    }
}
