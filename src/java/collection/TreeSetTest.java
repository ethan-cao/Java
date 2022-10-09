package collection;

import java.util.*;

public class TreeSetTest {

    public static void main(String[] args) {
        TreeSet<String> set1 = new TreeSet<>( new Comparator<String>(){
            public int compare(String s1, String s2) {
                return s1.length() - s2.length() ;
            }
        });

        set1.add("watermellon");
        set1.add("orange");
        set1.add("apple");

        for (String n: set1) {
            System.out.println(n);
        }
    }
}
