package algorithm.leetCode;

import java.util.*;
import java.util.stream.Collectors;

public class A_FrequentUse {

    public static void main(String[] arg) {
        map();
    }

    static class Number {
        static void test() {
            int i = 2;

            boolean b = (i & 1) == 0; // check if i is even
            b = (i & 1) == 1;  // check if i is odd

        }

        static int getAverage(int a, int b) {
            int average = a + (b - a) / 2;   // avoid overflow
            // overflow
//            (a + b ) >> 1;
//            (a + b )/ 2
            return average;
        }

        static void bit() {
            int maxInt = ~(1 << 31);
            int minInt = 1 << 31;
            minInt = 1 << -1;

            int x = 1;
            int y = 2;
            boolean b;

            // Check whether both have the same sign
            b = (x ^ y) >= 0;

            x = x << 1; // Multiplied by 2
            x = x >> 1; // Divided by 2
        }

        static boolean isPerfectSquare0(int i) {
            long closestRoot = (long) Math.sqrt(i);
            return i == closestRoot * closestRoot;
        }

        static boolean isPerfectSquare(long n) {
            if (n < 0)
                return false;

            // the last digit of input for Perfect squares can only end in 0, 1, 4, or 9
            // in hex by doing a bit-wise "and."
            switch ((int) (n & 0xF)) {
                case 0:
                case 1:
                case 4:
                case 9:
                    long tst = (long) Math.sqrt(n);
                    return tst * tst == n;
                default:
                    return false;
            }
        }
    }

    static void array() {
        String[] strings = new String[]{"1", "2", "3"};
        int[] ints = {1, 2, 3, 4, 4};

        Arrays.binarySearch(ints, 3);

        // iterate
        for (String s : strings) {
        }

        // print
        System.out.println(Arrays.toString(strings));

        // get max value in array
        int startIndex = 0;
        int endIndex = ints.length;
        Arrays.stream(ints, startIndex, endIndex).max().getAsInt();

        // int[] to Set
        Set<Integer> set1 = Arrays.stream(ints).boxed().collect(Collectors.toSet());

        // array to List
        int[] a = {1, 2, 3};
        List<Integer> l1 = Arrays.stream(a).boxed().collect(Collectors.toList());

        Integer[] b = new Integer[]{1, 2, 3};
        List<Integer> l2 = Arrays.asList(b); // Arrays.asList returns a fix sized list view on array
        // Array.asList() wraps an array in the list interface. The list is still backed by the array.
        // Arrays are a fixed size - they don't support adding or removing elements, so the wrapper can't either.

        // slice array
        int[] newInts = Arrays.copyOfRange(ints, 2, 3);

        // reverse array
        Arrays.stream(ints).map(i -> ints[ints.length - i]).toArray();
    }

    static void string() {
        String a = "aaabbb";

        // String -> char[]
        a.toCharArray();

        // iteration
        for (char c : a.toCharArray()) {
        }
    }

    static void list() {
        List<Integer> l = new ArrayList<>(2);
        System.out.println("size : " + l.size()); // 0

        l.addAll(Arrays.asList(1, 2, 3));

        // print
        System.out.println(Arrays.toString(l.toArray()));
        l.forEach(System.out::println);

        // sum
        int sum = l.stream().mapToInt(Integer::intValue).sum();

        // List<Integer> -> int[]
        int[] array = l.stream().mapToInt(Integer::intValue).toArray();

        // Slice list
        l.subList(0, 2);
    }

    static void set() {
        Set<String> set = new HashSet<>();

        // set to array, use set.size() to avoid create new array
        String[] strings = set.toArray(new String[set.size()]);
    }

    static void map() {
        Map<String, Integer> map = new HashMap<>();

        map.put("test1", 1);
        map.put("test2", 2);

        // print
        System.out.println(Arrays.asList(map));

        // iterate
        // forEach is usually performant
        map.forEach( (key, value) -> {
            System.out.println(key + " -> " + value);
        });

        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        map.entrySet().stream().forEach(entry ->  System.out.println(entry.getKey() + " -> " + entry.getValue()));

        System.out.println(map.containsKey("test1")); // true
        System.out.println(map.containsValue(1)); // true


        Integer newValue1 = map.computeIfPresent("test1", (k, v) -> v + 1);  // 2, if key is present, replace old value with new value, and return new value
        Integer newValue3 = map.computeIfPresent("test3", (k, v) -> v + 1);  // null, if key is not present, return null

        Integer newValue2 = map.computeIfAbsent("test2", k -> k.length());  // 2 if key is present, return the value
        Integer newValue4 = map.computeIfAbsent("test4", k -> k.length());  // 5, if key is absent,  add entry key -> value, and return the value
    }

}


