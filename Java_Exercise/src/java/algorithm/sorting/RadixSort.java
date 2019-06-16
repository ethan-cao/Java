package algorithm.sorting;

import java.util.Arrays;

/**
 * Radix sort handles sorting by implementing counting sort (or bucket sort) on one digit at a time.
 * It generally works best for a range of smallish numbers,
 * It is the very first sorting algorithm to be created (1887)
 * <p>
 * There has 2 flavors:
 * 1.most significant digit (MSD) radix sort :  from the greatest digit, and moving towards the least significant digit
 * 2.least significant digit (LSD) radix sort : the smallest digit first, moving towards the greater
 * Both based on counting sort
 * <p>
 * <p>
 * Rarely use since time complexity, LSD is more often than MSD
 * Used to sort String
 * <p>
 * Time complexity : O(wn), n number of elements, w number of digit of the largest element
 */

public class RadixSort {
    private static final int RADIX = 10;

    public static void main(String[] args) {
        int[] data = {2, 4, 5, 3, 1, 2, 3, 2, 3, 5, 3, 1, 2, 2, 2};

//        LSDSort(data);
        MSDSort(data);

        System.out.println(Arrays.toString(data));
    }

    public static void LSDSort(int[] data) {
        int max = Arrays.stream(data).max().getAsInt();

        for (int radix = 1; max / radix > 0; radix *= RADIX) {
            sortBasedOnRadix(data, radix);
        }
    }

    private static void sortBasedOnRadix(int[] data, int radix) {
        int[] result = new int[data.length];
        int[] tally = new int[RADIX]; // since we check each digit, the range is 0-9

        for (int datum : data) {
            int examiningDigit = (datum / radix) % (radix * RADIX);
            tally[examiningDigit] += 1;
        }

        for (int i = 1; i < tally.length; i++) {
            tally[i] += tally[i - 1];
        }

        for (int i = 0; i < data.length; ++i) {
            int numberOfSmallerElements = tally[data[i]];
            result[numberOfSmallerElements - 1] = data[i];
            tally[data[i]]--;
        }

        System.arraycopy(result, 0, data, 0, data.length);
    }

    public static void MSDSort(int[] data) {
        int max = Arrays.stream(data).max().getAsInt();
        int maxLength = (max + "").length();

        sortOnDigit(data, data.length, maxLength);
    }

    private static void sortOnDigit(int[] data, int length, int maxLength) {
        // there are RADIX buckets, each bucket can contain length elements
        int[][] bucket = new int[RADIX][length];
        // tally counts actual number of elements in each bucket
        int[] tally = new int[RADIX];

        // count occurrence of the examiningDigit
        int divisor = (int) Math.pow(10, maxLength - 1);
        for (int i = 0; i < length; ++i) {
            int examiningDigit = (data[i] / divisor) % RADIX;
            bucket[examiningDigit][tally[examiningDigit]] = data[i];
            tally[examiningDigit]++;
        }

        for (int i = 0; i < RADIX; ++i) {
            // when there are more than 1 element in bucket, and it's not right-most digit
            // sort on the next right digit
            if (tally[i] > 1 && maxLength > 1) {
                sortOnDigit(bucket[i], tally[i], maxLength - 1);
            }
        }

        // position element in order when each digit is sorted
        int idx = 0;
        for (int i = 0; i < RADIX; i++) {
            for (int j = 0; j < tally[i]; j++) {
                data[idx] = bucket[i][j];
                idx++;
            }
        }
    }
}
