package algorithm.leetCode;

/*
Given an array with n objects colored red(0), white(1) or blue(2)
sort them in-place so that objects of the same color are adjacent,
with the colors in the order red(0), white(1) and blue(2)

### Example
[2,0,2,1,1,0] ->  [0,0,1,1,2,2]

*/

import java.util.Arrays;

public class M_Array_75 {

    public static void main(String... args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));  // [0,0,1,1,2,2]
    }

    // Quick sort: algorithm.sorting.QuickSort.sortWith3Partition
    public static void sortColors(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }

        int partitionKey = nums[low];

        int lessThanIdx = low;
        int largerThanIdx = high;

        int i = low;

        while (i <= largerThanIdx) {
            if (nums[i] > partitionKey) {
                swap(nums, i, largerThanIdx);
                largerThanIdx--;
            } else if (nums[i] < partitionKey) {
                swap(nums, i, lessThanIdx);
                lessThanIdx++;
                i++;
            } else {
                i++;
            }
        }

        sort(nums, low, lessThanIdx - 1);
        sort(nums, largerThanIdx + 1, high);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // wo pass O(m+n) space
    public static void sortColors1(int A[], int n) {
        int num0 = 0, num1 = 0, num2 = 0;

        for (int i = 0; i < n; i++) {
            if (A[i] == 0) ++num0;
            else if (A[i] == 1) ++num1;
            else if (A[i] == 2) ++num2;
        }

        for (int i = 0; i < num0; ++i) A[i] = 0;
        for (int i = 0; i < num1; ++i) A[num0 + i] = 1;
        for (int i = 0; i < num2; ++i) A[num0 + num1 + i] = 2;
    }

    // one pass in place solution
    public static void sortColors2(int A[], int n) {
        int n0 = -1, n1 = -1, n2 = -1;
        for (int i = 0; i < n; ++i) {
            if (A[i] == 0) {
                A[++n2] = 2;
                A[++n1] = 1;
                A[++n0] = 0;
            } else if (A[i] == 1) {
                A[++n2] = 2;
                A[++n1] = 1;
            } else if (A[i] == 2) {
                A[++n2] = 2;
            }
        }
    }
}