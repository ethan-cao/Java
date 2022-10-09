package algorithm.sort;

import java.util.Arrays;

/**
 * works for integer (or char/String)
 *
 * need to know the range of our input integers.
 * works better with same rang integers (max-mix)
 * only suitable for direct use in situations where the variation in keys is not significantly greater than the number of items.
 * However, it is often used as a subroutine in another sorting algorithm,
 *
 * the input to counting sort consists of a collection of n items, each of which has a non-negative integer key whose maximum value is at most k.
 */

public class CountingSort {

    public static void main(String[] args) {
        int[] data = {2, 1, 3, 5, 4};

        sort(data, 5); // 13 is range, the max value

        System.out.println(Arrays.toString(data));
    }

    public static void sort(int[] data, int range) {
        // count occurrence
        int[] tally = new int[range + 1]; // need to allocate range + 1 position, if the range is large, and element is not distributed evenly, it is not efficient
        for (int a : data) {
            tally[a] += 1;
        }

        // after iteration, tally[i] embodies number of elements that are <=  (the element represented by tally[i])
        for (int i = 1; i < tally.length; ++i) {
            tally[i] += tally[i - 1];
        }

        int[] tempResult = new int[data.length];

        // position element in order
        for (int a : data) {
            int nonGreaterElementCount = tally[a]; // number of elements that are <=  (the element represented by tally[i])

            // put the current element on the right position
            tempResult[nonGreaterElementCount - 1] = a;  // -1 : since index starts from 0

            // reduce number of element to be sorted
            tally[a]--;
        }

        System.arraycopy(tempResult, 0, data, 0, data.length);
    }

}