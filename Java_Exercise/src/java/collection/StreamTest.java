package collection;

import java.util.ArrayList;
import java.util.List;

public class StreamTest {
    static List<Integer> myList = new ArrayList<>();

    public static void main(String[] args){

        for(int i=0; i<100; i++) {
            myList.add(i);
        }
    }


}

// perform filter/map/reduce like operations with the collection.