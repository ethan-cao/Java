package collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StreamTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add(null);
        list.add("a");
        list.add("b");

        // get the 1st non-null value
        String firstNonNullValue = list.stream()
                .filter(Objects::nonNull)
                .findFirst() // return is Optional<String>
                .orElse("NO");  // return the 1st non-null found, if did not find return "NO"




    }
}

// perform filter/map/reduce like operations with the collection.