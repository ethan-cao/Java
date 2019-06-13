package algorithm.sorting;

import java.util.Arrays;

/**
 * Radix sort handles sorting by implementing counting sort (or bucket sort) on one digit at a time.
 * It generally works best for a range of smallish numbers,
 * It is the very first sorting algorithm to be created (1887)
 * It has 2 flavors:
 * 1.most significant digit (MSD) radix sort :  from the greatest digit, and moving towards the least significant digit
 * 2.least significant digit (LSD) radix sort : the smallest digit first, moving towards the greater
 * <p>
 * Rarely use since time complexity, LSD is more often than MSD
 * <p>
 * Time complexity : O(wn), n number of elements, w number of digit of the largest element
 */

public class RadixSort {
    public static void main(String[] args) {
        int[] data = {2, 4, 5, 3, 1,2,3,2,3,5,3,1,2,2,2};

        LSDSort(data);

        System.out.println(Arrays.toString(data));
    }

    public static void LSDSort(int[] data) {
        int max = Arrays.stream(data).max().getAsInt();

        for (int radix = 1; max / radix > 0; radix *= 10) {
            sortBasedOnRadix(data, radix);
        }
    }

    // using counting sort
    private static void sortBasedOnRadix(int[] data, int radix) {
        int[] result = new int[data.length];
        int[] tally = new int[10]; // since we check each digit, the range is 0-9

        for (int i = 0; i < data.length; i++) {
            int examiningDigit = (data[i] / radix) % (radix * 10);
            tally[examiningDigit] += 1;
        }

        for (int i = 1; i < tally.length; i++) {
            tally[i] += tally[i - 1];
        }

        for (int i = 0; i < data.length; ++i) {
            int numberOfSmallerElements = tally[data[i]];
            result[numberOfSmallerElements-1] = data[i];
            tally[data[i]]--;
        }

        System.arraycopy(result, 0, data, 0, data.length);
    }

    public static void MSDSort(int[] data) {

    }
}
