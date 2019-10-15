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


import java.util.Arrays;

public class M_ThreePointe_Array_611 {

    public static void main(String... args) {
        System.out.println(triangleNumber1(new int[]{3, 4, 5}));    // 1
        System.out.println(triangleNumber1(new int[]{2, 2, 3, 4})); // 3
        System.out.println(triangleNumber1(new int[]{0, 1, 1, 1})); // 1
        System.out.println(triangleNumber1(new int[]{48, 66, 61, 46, 94, 75})); // 19
    }

    // Brute Force, Time: O(N^3)
    public static int triangleNumber(int[] nums) {
        int tripletCount = 0;

        if (nums == null || nums.length < 3) {
            return tripletCount;
        }

        // optimization
        Arrays.sort(nums);

        for (int side1Idx = 0; side1Idx < nums.length; ++side1Idx) {
            for (int side2Idx = side1Idx + 1; side2Idx < nums.length; ++side2Idx) {
                for (int side3Idx = side2Idx + 1; side3Idx < nums.length; ++side3Idx) {

                    if (!complyWithTriangleInequality(nums[side1Idx], nums[side2Idx], nums[side3Idx])) {
                        break;
                    }

                    tripletCount++;
                }
            }
        }

        return tripletCount;
    }

    // Triangle inequality:   sideLenght1 + sideLength2 > sideLength3
    //      alternatively : sideLength1 - sideLenght2 < sideLength3
    private static boolean complyWithTriangleInequality(int side1, int side2, int side3) {
        return side1 + side2 > side3 && side2 + side3 > side1 && side1 + side3 > side2;
    }

    // Three Pointer, Time: O(N^2)
    public static int triangleNumber1(int[] nums) {
        int tripletCount = 0;

        if (nums == null || nums.length < 3) {
            return tripletCount;
        }

        Arrays.sort(nums);

        int left = 0;
        int middle = nums.length - 2;
        int right = nums.length - 1;

        while (right > 1) {  // leave room for at least 1 left and 1 middle
            // !!! initialize for each loop
            left = 0;
            middle = right - 1;

            while (left < middle) {
                if (nums[left] + nums[middle] > nums[right]) {
                    // all value that is larger than left til middle is applicable
                    tripletCount += (middle - left);
                    middle--;
                } else {
                    left++;
                }
            }

            right--;
        }

        return tripletCount;
    }

}