import java.util.*;

public class Test {

    public static void main(String... args) {
        int[] data = {2, 2222, 2, 23, 5, 13};

        sort(data);

        System.out.println(Arrays.toString(data));
    }

    private static void sort(int[] data) {
        int[] aux = new int[data.length];

        sort(data, aux, 0, data.length - 1);
    }

    static void sort(int[] data, int[] aux, int low, int high) {
        if (high - low < 1) {
            return;
        }

        int middle = low + (high - low) / 2;

        sort(data, aux, low, middle);
        sort(data, aux, middle + 1, high);

        merge(data, aux, low, middle, high);
    }

    static void merge(int[] data, int[] aux, int low, int middle, int high) {
        if (data[middle] <= data[middle + 1]) {
            return;
        }

//        int[] aux1 = new int[data.length];
//        System.arraycopy(data, low, aux1, low, high - low + 1);

        int idx1 = low; // pointer in 1st array
        int idx2 = middle + 1; // pointer for 2nd array

        for (int i = low; i <= high; ++i) {
            if (idx1 > middle) {
                data[i] = data[idx2++];
            } else if (idx2 > high) {
                data[i] = data[idx1++];
            } else {
                data[i] = data[idx1] < data[idx2] ? data[idx1++] : data[idx2++];
            }
        }
    }


}