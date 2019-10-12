package algorithm.search;

/**
 * Binary search
 * Prerequisite:  array is sorted
 */

public class BinarySearch {

    public static void main(String[] args) {
        int[] array = {2, 3, 5, 6, 8, 10, 14};
        int index = BinarySearch.binarySearch1(array, 14);

        System.out.println("target is at : " + index);
    }

    // Iterative
    // Time: O(logN), Space: O(1)
    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            // int mid = (left + right) / 2;
            // divided by 2 is a bug... if left + right is larger than maximal value of int, result overflows to negative
            // https://research.googleblog.com/2006/06/extra-extra-read-all-about-it-nearly.html
            int middle = left + (right - left) / 2;

            if (array[middle] == target) {
                return middle;
            }

            if (array[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return -1;
    }

    // Recursive
    // Time: O(logN), Space: O(logN)
    public static int binarySearch1(int[] array, int target) {
        return search(array, 0, array.length - 1, target);
    }

    private static int search(int[] array, int left, int right, int target) {
        if (left > right) {
            return -1;
        }

        int middle = left + (right - left) / 2;

        if (array[middle] == target) {
            return middle;
        }

        if (target > array[middle]) {
            left = middle + 1;
        } else {
            right = middle - 1;
        }

        return search(array, left, right, target);
    }

}