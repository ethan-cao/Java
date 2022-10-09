package algorithm.sort;

import java.util.Arrays;

/**
 * Similar to selection sort
 *
 * When examine i-th element, it looks backward to ensure i-th is in sorted position
 *
 * Compared with selection sort,
 *  it outperforms in best case since it does 1 comparison, element with left neighbour (usually, insertion over selection)
 *  it requires more writes since it swap more elements, so preferable when writing to memory is significantly more expensive than reading
 */

public class InsertionSort {

    public static void main(String[] args) {
        int[] data = {2222, 431, 5, 4, 12, 5, 3, 1, 4, 33};

        sort(data);

        System.out.println(Arrays.toString(data));
    }

    // Time: O(N^2)
    public static void sort(int[] data) {
        for (int i = 0; i < data.length; ++i) {
            // each iteration checks only 1 element data[i], aiming to put it in sorted position
            // compare data[i] with elements before it, elements before it are sorted
            for (int j = i; j > 0; --j) {
                if (data[j - 1] > data[j]) {
                    int swap = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = swap;
                } else {
                    // this is where insertion sort might outperform selection sort
                    // since data[j-1] is the largest among data[0]...data[j-1]
                    break;
                }
            }
        }
    }

}