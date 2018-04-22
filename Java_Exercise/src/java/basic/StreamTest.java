package basic;

import java.util.ArrayList;
import java.util.List;

public class StreamTest {

    public static void main(String[] args){


        // Convert list to array
        List<Integer> list = new ArrayList<>();
        int[] array = list.stream().mapToInt(i -> i).toArray();
    }
}
