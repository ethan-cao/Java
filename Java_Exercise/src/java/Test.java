
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {

        List<String> list =  new ArrayList<>();
        list.add(null);
        list.add("a");

        System.out.println(list.stream().findFirst());

        int x = 1;

        x += 2 - 1;

        System.out.println();

    }
}

