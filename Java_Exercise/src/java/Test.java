import java.util.*;

public class Test {

    public static void main(String... args) {
        int[] data = {2, 2222, 2, 23, 5, 13};

        sort(data, 0, data.length);

        System.out.println(Arrays.toString(data));
    }

    private static void sort(int[] data, int low, int high) {
        if (low >= high) {
            return;
        }

        int partitionKey = data[low];

        int lessThan = 1;
        int greatThan = data[high];

        int i = low + 1;
        while (i <= high && lessThan <= greatThan) {
            while (data[i] < partitionKey) {
                i++;
                lessThan++;
            }

            while (data[i] == partitionKey) {
                i++;
            }

            while (data[i] > partitionKey) {
                greatThan--;
            }

            exchange(data, lessThan, greatThan);
        }
        exchange(data, low, i);

        sort(data, low, lessThan);
        sort(data, greatThan + 1, high);
    }

    private static void exchange(int[] array, int index1, int index2) {
        if (index1 == index2 || index1 >= array.length || index2 >= array.length) {
            return;
        }

        int swap = array[index1];
        array[index1] = array[index2];
        array[index2] = swap;
    }
}