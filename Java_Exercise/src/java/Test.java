import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String p = "p";

        m(p);

        System.out.println(p);

        System.out.println(0 < Double.POSITIVE_INFINITY);


        List<String> l = new ArrayList<>();
        l.add("a");
        l.add("b");
        l.add("c");
        l.add("d");

        String s = (String) l.stream()
                .filter(s1 -> s1.equals("a"))
                .findFirst()
                .orElse("h");

        System.out.println("@@@");
        System.out.println(s);

    }

    static void m(String s) {
        s = "b";
    }
}

