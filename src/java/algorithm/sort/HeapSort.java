package algorithm.sort;

import algorithm.dataStructure.BinaryHeap;

import java.util.Arrays;

/**
 * use a heap data structure, rather than a linear-time search to find the maximum
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] data = {16, 522, 3, 9, 11, 0, 3, 14, 7, 3};

        sort(data);

        System.out.println(Arrays.toString(data));
    }

    public static void sort(int[] data) {
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(data.length);

        // Build binary heap
        for (int i : data) {
            binaryHeap.insert(i);
        }

        // Remove the largest one repeatedly
        int idx = data.length - 1;
        while (!binaryHeap.isEmpty()) {
            int max = binaryHeap.delete();
            data[idx--] = max;
        }
    }
}