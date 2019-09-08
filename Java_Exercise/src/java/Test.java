import java.util.*;

public class Test {

    public static void main(String... args) {

        List<List<Integer>> c = new ArrayList<>();

        c.add(Arrays.asList(1, 2));
        c.add(Arrays.asList(3, 4));

        for(List<Integer> list: c){
            list.add(0, 0);
        }

        System.out.println(c.get(0));
    }

}