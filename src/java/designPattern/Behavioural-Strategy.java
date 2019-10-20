package designPattern;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Strategy Pattern
 *
 * It defines a family of algorithms and execute the particular algorithm based upon the situation.
 */

class StrategyPattern {
    public static void main(String[] args) {
        // Collections.sort sorts list with different strategies based on different implementations of Comparator

        List<Integer> list1 = Arrays.asList(5, 1, 3, 2, 4);
        Collections.sort(list1);
        list1.forEach(item -> System.out.printf(item + " "));
        System.out.println("");

        List<String> list2 = Arrays.asList("12", "asd", "cd", "54");
        Collections.sort(list2);
        list2.forEach(item -> System.out.printf(item + " "));
        System.out.println("");

        List<Integer> list3 = Arrays.asList(5, 1, 3, 2, 4);
        Collections.sort(list3, (a, b) -> b - a);
        list3.forEach(item -> System.out.printf(item + " "));
        System.out.println("");
    }
}