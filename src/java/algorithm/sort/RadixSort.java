package algorithm.sort;

import java.util.Arrays;

/**
 * Radix sort handles sorting by implementing counting sort (or bucket sort) on one digit at a time.
 * It generally works best for a range of smallish numbers,
 *
 * the first sorting algorithm in 1887
 *
 * There has 2 flavors:
 * 1.most significant digit (MSD) radix sort:  from the greatest digit, and moving towards the least significant digit
 * 2.least significant digit (LSD) radix sort: from the smallest digit first, moving towards the greater
 * Both based on counting sort
 *
 * Can also be used to sort String, since each char has int value
 *
 * Time complexity : O(wn), n number of elements, w number of digit of the largest element
 */

public class RadixSort {
    private static final int RADIX = 10;

    public static void main(String[] args) {
        int[] data = {2, 4, 111, 2222, 23, 543 , 3, 11, 5, 13, 1};

        LSDSort(data);
        System.out.println(Arrays.toString(data));

//        MSDSort(data);
//        System.out.println(Arrays.toString(data));
    }


    public static void LSDSort(int[] data) {
        int max = Arrays.stream(data).max().getAsInt();
        int maxDigitLength = (max + "").length();

        for (int i = 0; i < maxDigitLength; ++i) {
            int digitIndicator = (int) Math.pow(10, i);
            sortOnDigit(data, digitIndicator);
        }
    }

    private static void sortOnDigit(int[] data, int digitIndicator) {
        // there are RADIX buckets, each bucket can contain at most length elements
        int[][] bucket = new int[RADIX][data.length];
        // tally counts actual number of elements in each bucket
        // tally[x] is number of elements in bucket[x]
        int[] tally = new int[RADIX];

        for (int i = 0; i < data.length; ++i) {
            int examiningDigit = (data[i] / digitIndicator) % RADIX;
            bucket[examiningDigit][tally[examiningDigit]] = data[i];
            tally[examiningDigit]++;
        }

        int idx = 0;
        for (int i = 0; i < RADIX; ++i) {
            for (int j = 0; j < tally[i]; ++j) {
                data[idx] = bucket[i][j];
                idx++;
            }
        }
    }


    public static void MSDSort(int[] data) {
        int max = Arrays.stream(data).max().getAsInt();
        int maxDigitLength = (max + "").length();
        int digitIndicator = (int) Math.pow(10, maxDigitLength - 1);

        sortOnDigit(data, data.length, digitIndicator);
    }

    private static void sortOnDigit(int[] data, int elementNumber, int digitIndicator) {
        // there are RADIX buckets, each bucket can contain at most length elements
        int[][] bucket = new int[RADIX][elementNumber];
        // tally counts actual number of elements in each bucket
        // tally[x] is number of elements in bucket[x]
        int[] tally = new int[RADIX];

        // count occurrence of the examiningDigit
        for (int i = 0; i < elementNumber; ++i) {
            int examiningDigit = (data[i] / digitIndicator) % RADIX;
            bucket[examiningDigit][tally[examiningDigit]] = data[i];
            tally[examiningDigit]++;
        }

        for (int i = 0; i < RADIX; ++i) {
            // when it's not the right-most digit and there are more than 1 element in bucket
            // sort on the next right digit
            if (digitIndicator > 1 && bucket[i].length > 1) {
                sortOnDigit(bucket[i], tally[i], digitIndicator / 10);
            }
        }

        // position element in order when each digit is sorted, counting sort
        int idx = 0;
        for (int i = 0; i < RADIX; ++i) {
            for (int j = 0; j < tally[i]; ++j) {
                data[idx] = bucket[i][j];
                idx++;
            }
        }
    }
}
