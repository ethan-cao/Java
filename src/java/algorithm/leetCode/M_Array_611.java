package algorithm.leetCode;

/*
Given an array consists of non-negative integers,
count the number of triplets chosen from the array that can make triangles
if we take them as side lengths of a triangle.

The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].

### Example
[2,2,3,4] -> 3
Explanation:
Valid combinations are:
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3

*/


public class M_Array_611 {

    public static void main(String... args) {
        System.out.println(triangleNumber(new int[]{2, 2, 3, 4})); // 3
    }

    public static  int triangleNumber(int[] nums) {
        int tripletCount = 0;

        return tripletCount;
    }

}