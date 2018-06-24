package algorithm.sorting;

/**
 * Given an array of N items, find a k-th smallest item
 */
public class Selection {
    public static void main(String[] args) {
        Integer[] data = {2, 4, 5, 3, 1};

        int k = 2;
        Comparable k_th_smallest = select(data, k);

        System.out.println(k_th_smallest);
    }

    // based on quickSort, linear time selection
    public static Comparable select(Comparable[] array, int k) {
        Shuffle.knuthShuffle(array);
        final int L = array.length;

        int low = 0;
        int high = L - 1;

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
