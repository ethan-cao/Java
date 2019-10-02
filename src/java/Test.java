import java.util.*;

public class Test {

    public static void main(String... args) {
    // his is typically implemented by converting the internal address of the object into an intege

        Test t1 = new Test();
        Test t2 = new Test();

        System.out.println(t1.hashCode());
        System.out.println(t2.hashCode());
    }

}