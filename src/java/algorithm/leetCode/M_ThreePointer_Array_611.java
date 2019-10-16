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
2,2,3
2,3,4 (using the second 2)

*/


import java.util.Arrays;

public class M_ThreePointer_Array_611 {

    public static void main(String... args) {
        System.out.println(triangleNumber3(new int[]{3, 4, 5}));    // 1
        System.out.println(triangleNumber3(new int[]{2, 2, 3, 4})); // 3
        System.out.println(triangleNumber3(new int[]{0, 1, 1, 1})); // 1
        System.out.println(triangleNumber3(new int[]{48, 66, 61, 46, 94, 75})); // 19
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
    // initial position: left = 0, middle = L-2, right = L-1
    // in this way, when nums[left] + nums[middle] < nums[right], the only option is to move left 1 position left
    public static int triangleNumber1(int[] nums) {
        int tripletCount = 0;

        if (nums == null || nums.length < 3) {
            return tripletCount;
        }

        Arrays.sort(nums);

        for (int right = nums.length - 1; right > 1; --right) { // leave room for at least 1 left and 1 middle
            int left = 0;
            int middle = right - 1;

            while (left < middle) {
                if (nums[left] + nums[middle] > nums[right]) {
                    // all value that is larger than left til middle is applicable
                    tripletCount += (middle - left);
                    middle--;
                } else {
                    left++;
                }
            }
        }

        return tripletCount;
    }

    // WRONG!!!  pay attention to the initial position of pointers
    public static int triangleNumber2(int[] nums) {
        int tripletCount = 0;

        if (nums == null || nums.length < 3) {
            return tripletCount;
        }

        Arrays.sort(nums);

        for (int left = 0; left < nums.length - 2; ++left) {
            int middle = left + 1;
            int right = nums.length - 1;

            while (middle < right) {
                // when nums[left] + nums[middle] <= nums[right], we actually have 2 options:
                // moving middle 1 position left   OR   moving right 1 position right
                // so this approach is wrong
                if (nums[left] + nums[middle] > nums[right]) {
                    tripletCount += right - middle;
                    right--;
                } else {
                    middle++;
                }
            }
        }

        return tripletCount;
    }

    // to fix triangleNumber2
    // Binary Search
    // Time: O(N^2LogN)
    public static int triangleNumber3(int[] nums) {
        int tripletCount = 0;

        if (nums == null || nums.length < 3) {
            return tripletCount;
        }

        Arrays.sort(nums);

        for (int left = 0; left < nums.length - 2; ++left) {
            for (int middle = left + 1; middle < nums.length - 1; ++middle) {
                int sum = nums[left] + nums[middle];

                // seek the 1st number that < sum
                int right = binarySearch(nums, middle + 1, nums.length - 1, sum);

                if (right != -1) {
                    tripletCount += right - middle;
                }
            }
        }

        return tripletCount;
    }

    private static int binarySearch(int[] nums, int left, int right, int target) {
        while (left + 1 < right) {  // !!! after loop, left + 1 = right
            int middle = left + (right - left) / 2;

            if (nums[middle] >= target) {
                right = middle - 1;  // we can exclude middle, since we are looking for num < target
            } else {
                left = middle;
            }
        }

        // check right first
        if (nums[right] < target) {
            return right;
        } else if (nums[left] < target) {
            return left;
        } else {
            return -1;
        }
    }

}