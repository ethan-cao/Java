import java.util.*;

public class Test {

    public static void main(String... args) {
        int[] data = {2, 4, 5, 3, 11, 2, 10, 2, 3, 5, 13, 1};

        sort1(data);

        System.out.println(Arrays.toString(data));
    }

    private static void sort1(int[] data) {
        for (int i = 0; i < data.length; ++i) {
            for (int j = 1; j < data.length - i; ++j) {
                if (data[j-1] > data[j]) {
                    int temp = data[j-1];
                    data[j-1] = data[j];
                    data[j] = temp;
                }
            }
        }
    }

}