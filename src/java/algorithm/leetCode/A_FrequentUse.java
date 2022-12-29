package algorithm.leetCode;

import java.util.*;
import java.util.stream.Collectors;

public class A_FrequentUse {

    public static void main(String[] arg) {
        string();
    }

    static class Number {
        static void test() {
            int i = 2;

            boolean isEven = (i & 1) == 0;
            boolean isOdd = (i & 1) == 1;
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

    static void charTest() {
        boolean result = false;

        result = Character.isDigit('1'); // T
        result = Character.isDigit('a'); // F

        result = Character.isLetter('a'); // T
        result = Character.isUpperCase('a'); // F
        result = Character.isLowerCase('a'); // T

        // int -> char
        char c1 = (char) ('0' + 1);
    }

    static void string() {
        String s = "aAaabbAb";

        // index(char / String)
        System.out.println(s.indexOf("A"));             // 1
        System.out.println(s.indexOf('A'));             // 1
        System.out.println(s.lastIndexOf("A"));     // 6
        System.out.println(s.lastIndexOf('A'));     // 6

        // contains(String)
        System.out.println(s.contains("A"));     // T

        // iteration, String -> char[]
        for (char c : s.toCharArray()) {
        }

        // format
        System.out.printf("%s is Good\n", "format");

        // sort
        String[] strings = new String[]{"cwqe, b3r3r, ae3r, 5sf"};
        Arrays.sort(strings, (s1, s2) -> s1.compareTo(s2));
        System.out.println(Arrays.toString(strings));

    }

    static void stringBuilder() {
        StringBuilder sb = new StringBuilder();

        sb.append('a')  // char
                .append("b")  // String
                .append(1)   // number
                .append("a");

        sb.reverse(); // sb is reversed
        System.out.println(sb); // a1ba

        sb.deleteCharAt(2); //
        System.out.println(sb); // a1a

        System.out.println(sb.indexOf("a"));          // 0
        System.out.println(sb.lastIndexOf("a")); //  2

        // insert "new" to index 0 in sb.toString(), shift existing string towards right
        sb.insert(1, "new");
        System.out.println(sb);   // anew1a
    }

    static void array() {
        String[] strings = new String[]{"1", "2", "3"};
        int[] ints = {1, 2, 3, 4, 4};

        Arrays.sort(ints);
        Arrays.binarySearch(ints, 3);

        // iterate
        for (String s : strings) {
            System.out.println(s);
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
        List<Integer> l2 = Arrays.asList(b);
        // Arrays.asList(T... t) returns a fix sized list view on array
        // Arrays.asList() wraps an array in the list interface. The list is still backed by the array.
        // the returned array can be changed by is fixed size

        // slice array
        int[] newInts = Arrays.copyOfRange(ints, 2, 3);
        int[] newInts1 = Arrays.copyOf(ints, ints.length);

        // reverse array or write a short method
        Arrays.stream(ints).map(i -> ints[ints.length - i]).toArray();

        // create array as param on the fly
        System.out.println(new int[]{1, 2, 3,});
        System.out.println(new int[][]{new int[]{1, 2, 3}});

        // sort
        int[] test1 = {4, 3, 1, 2, -1};

        // !mutate the input
        // sort primitive
        Arrays.sort(test1);

        // sort Object
        int[] sortedArray1 = Arrays.stream(test1)
                .boxed()
//                .sorted((x, y) -> Integer.compare(x, y)) // ascending
//                .sorted((x, y) -> Integer.compare(y, x)) // descending

                .sorted((x, y) -> x.compareTo(y)) // ascending
//                .sorted((x, y) -> y.compareTo(x)) // descending

//                .sorted(Comparator.comparingInt(x -> (int) x)) // ascending
//                .sorted(Comparator.comparingInt(Integer::intValue)) // ascending
//                .sorted(Comparator.comparingInt(x -> (int) x).reversed()) // descending
//                .sorted(Comparator.comparingInt(Integer::intValue).reversed()) // descending

                .mapToInt(i -> i)
                .toArray();

        // !!! DO NOT use a comparator like (a,b) -> a-b for int
        // since the difference of two int may cause overflows.


        int[][] test2 = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};

        // !!! does not mutate the input
        // sort on index 0, then sort on index 1
        int[][] sortedArray2 = Arrays.stream(test2)
                .sorted((x, y) -> {
                    return x[0] == y[0] ? Integer.compare(x[1], y[1]) : Integer.compare(x[0], y[0]);
                })
//                .sorted(Comparator
//                        .comparingInt((int[] arr) -> arr[0])
//                        .thenComparing(Comparator.comparingInt((int[] arr) -> arr[1]))
//                )
                .toArray(int[][]::new);

        for (int[] arr : sortedArray2) {
            System.out.println(Arrays.toString((arr)));
        }
    }

    static void list() {
        // ArrayList is single-linked list,  add operation time complexity is O(N)+  (amortised constant time)
        // LinkedList is double-linked list, add operation time complexity is O(N)

        List<Integer> list = new ArrayList<>(2);
        System.out.println("size : " + list.size()); // 0


        list.addAll(Arrays.asList(1, 2, 3));

        list.add(4); // add to the end
        list.add(0, 0); // add to specified position

        list.remove(0); // remove the one on specified position
        list.remove(new Integer(1)); // removes the first occurrence of the specified element

        // print
        System.out.println(Arrays.toString(list.toArray())); // [1, 2, 3]
        list.forEach(System.out::println);

        // sum
        int sum = list.stream().mapToInt(Integer::intValue).sum();

        // List<Integer> -> int[]
        int[] array = list.stream().mapToInt(Integer::intValue).toArray();
        // this is slow, use iterate and convert manually
        // cannot use list.toArray(new int[])

        //List<int[]> -> int[][], since int[] is object, List<T> -> T[]
        List<int[]> newList = new ArrayList<>();
        newList.add(new int[]{1, 2});
        newList.add(new int[]{3, 4});
        newList.add(new int[]{5, 6});
        int[][] newArray = newList.toArray(new int[newList.size()][2]);

        // List<Integer> -> Integer[]
        Integer[] IArray = list.toArray(new Integer[3]);

        // slice list
        list.subList(0, 2);
    }

    static void stack() {
        Deque<Integer> stack = new ArrayDeque<>();  // backed by array

        // behind the scene, elements are added from the last position in array towards the first position
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
//        stack.push(null);  // NullPointerException

        //   [            4, 3, 2, 1]
        //             first     last

        // Stack specific method
        stack.push(5);  // insert to first, [5, 4, 3, 2, 1]
        stack.pop();       // remove the first, [4, 3, 2, 1]

        // general method, just retrieve value, null if empty
        Integer first = stack.peekFirst();  // 4
        Integer last = stack.peekLast();    // 1

        // general method, retrieve and remove, null if empty
        first = stack.pollFirst();     // 4
        last = stack.pollLast();       // 4

        System.out.print(stack.size());
    }

    static void queue() {
        Deque<Integer> queue = new ArrayDeque<>(); // backed by array

        // behind the scene, elements are added from the first position in array towards the last position
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
//        queue.offer(null); // NullPointerException

        //   [1, 2, 3, 4     ]
        //  first     last

        // Queue specific method
        queue.offer(5);  // insert to last,  [1, 2, 3, 4, 5]
        queue.poll();       // remove the first [2, 3, 4, 5]

        // general method, just retrieve value, null if empty
        Integer first = queue.peekFirst();  // 2
        Integer last = queue.peekLast();    // 5

        // general method, retrieve and remove, null if empty
        first = queue.pollFirst();     // 2
        last = queue.pollLast();       // 5

        System.out.print(queue.size());
    }

    static void heap() {
        // offer()
        // poll()
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
        map.forEach((key, value) -> {
            System.out.println(key + " -> " + value);
        });

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        map.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));

        System.out.println(map.containsKey("test1")); // true
        System.out.println(map.containsValue(1)); // true


        // access entry under condition
        // !!! the default value will not be associated with key in map
        Integer defaultValue = map.getOrDefault("test", 0); //0

        // update entry under condition
        Integer newValue1 = map.computeIfPresent("test1", (k, v) -> v + 1);  // 2, if key is present, replace old value with new value, and return new value
        Integer newValue3 = map.computeIfPresent("test3", (k, v) -> v + 1);  // null, if key is not present, return null
        // putIfPresent(k, v) is similar, just the 2nd param is value

        Integer newValue2 = map.computeIfAbsent("test2", k -> k.length());  // 2 if key is present, return the value
        Integer newValue4 = map.computeIfAbsent("test4", k -> k.length());  // 5, if key is absent,  add entry key -> value, and return the value
        // putIfAbsent(k, v) is similar, just the 2nd param is value

        // shortcut,  v = map.get(k)
        newValue1 = map.compute("test1", (k, v) -> v + 1); // if newValue is not null, replace old value
        newValue3 = map.compute("test3", (k, v) -> v + 1); // if newValue is null, remove the entry if it exists
        newValue4 = map.compute("test4", (k, v) -> v == null ? 1 : 2); // use this if need to initiate a collection as value


    }

    // Javadoc example: algorithm.dataStructure.BinarySearchTree
}