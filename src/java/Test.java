import basic.ObjectEqualityTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Test {

    public static void main(String[] args) {
        List<String> l = new ArrayList<>();

        l.add("AAA");
        l.add("BBB");
        l.add("CCC");

        System.out.println(l.contains("AAA"));

    }
}