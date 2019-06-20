import java.util.*;

public class Test {

    public static void main(String... args) {
        int[] data = {2, 2222, 2, 23, 5, 13};

        sort(data);

        System.out.println(Arrays.toString(data));
    }

    private static void sort(int[] data) {
        for (int i = 0; i < data.length; ++i) {
            int minIdx = i;

            for (int j = minIdx + 1; j < data.length; ++j) {
                if (data[j] < data[minIdx]) {
                    int temp = data[j];
                    data[j] = data[minIdx];
//                    data[minIdx]
                }
            }

        }

    }

}