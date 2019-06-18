package algorithm.sorting;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] data = {2, 24, 112, 2, 5, 22, 13, 1};

//        sort(data);
        sort1(data);
//        sort2(data, data.length);

        System.out.println(Arrays.toString(data));
    }

    private static void sort(int[] data) {
        for (int i = 0; i < data.length - 1; ++i) {
            for (int j = 0; j < data.length - 1 - i; ++j) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    // Optimized
    private static void sort1(int[] data){
        boolean exchanged = false;

        for (int i = 0; i < data.length - 1; ++i) {
            for (int j = 0; j < data.length - 1 -i; ++j) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;

                    exchanged = true;
                }
            }

            // if exchange is false, the array is already sorted.
            if (!exchanged) {
                break;
            }
        }
    }

    // Recursion
    private static void sort2(int[] data, int length) {
        if (length == 1) {
            return;
        }

        for (int i = 0; i < length - 1; ++i) {
            if (data[i] > data[i + 1]) {
                int temp = data[i];
                data[i] = data[i + 1];
                data[i + 1] = temp;
            }
        }

        sort2(data, length - 1);
    }

}