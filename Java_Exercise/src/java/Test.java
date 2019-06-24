import java.util.*;

public class Test {

    public static void main(String... args) {
        int[] data = {2, 2222, 2, 23, 5, 13};

        insertionSort(data);

        System.out.println(Arrays.toString(data));
    }

    private static void insertionSort(int[] data) {
    }

    private static void selectionSort(int[] data) {

    }

    private static void exchange(int[] array, int index1, int index2) {
        int swap = array[index1];
        array[index1] = array[index2];
        array[index2] = swap;
    }
}