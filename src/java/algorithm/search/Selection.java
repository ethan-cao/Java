package algorithm.search;

import algorithm.math.Shuffle;
import algorithm.sort.QuickSort;

/**
 * Given an array of N items, find a k-th smallest item
 */
public class Selection {
    public static void main(String[] args) {
        int[] data = {2, 4, 5, 3, 1};

        int k = 2;
        Comparable k_th_smallest = selectNthItem(data, k);

        System.out.println(k_th_smallest);
    }

    // based on quickSort, linear time selection
    public static Comparable selectNthItem(int[] array, int k) {
        Shuffle.knuthShuffle(array);

        int low = 0;
        int high = array.length - 1;

        while (low < high) {
            int partitionKeyIndex = QuickSort.partition(array, low, high);

            if (partitionKeyIndex > k) {
                high = partitionKeyIndex - 1;
            } else if (partitionKeyIndex < k) {
                low = partitionKeyIndex + 1;
            } else if (k == partitionKeyIndex) {
               return array[k];
            }
        }

        // now k == partitionKeyIndex
        return array[k];
    }
}
