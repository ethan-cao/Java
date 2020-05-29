package algorithm.search;

import algorithm.math.Shuffle;
import algorithm.sort.QuickSort;

public class Selection {

    public static void main(String[] args) {
        int[] data = {32, 43, 555, 33, 1};
        int k = 2;

        Comparable k_th_smallest = QuickSelect1(data, k);

        System.out.println(k_th_smallest);
    }

    // Time: O(N)
    // Quick Select, based on quickSort,
    // Given an data of N items, find a targetIdx-th smallest item
    public static Comparable QuickSelect1(int[] data, int targetIdx) {
        Shuffle.knuthShuffle(data);

        int start = 0;
        int end = data.length - 1;

        while (start < end) {
            int partitionKeyIndex = QuickSort.partition(data, start, end);

            if (partitionKeyIndex > targetIdx) {
                end = partitionKeyIndex - 1;
            } else if (partitionKeyIndex < targetIdx) {
                start = partitionKeyIndex + 1;
            } else {
                return data[partitionKeyIndex];
            }
        }

        // now targetIdx == partitionKeyIndex
        return data[targetIdx];
    }

}
