package algorithm.leetCode;

import java.util.*;
import java.util.stream.Collectors;

public class A_FrequentUse {
    public static void main(String[] arg) {
        list();
    }

    static class Number {
        static void test() {
            int i = 2;

            // check if i is even
            boolean b = (i & 1) == 0;
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

        int l = strings.length;

        // PRINT
        System.out.println(Arrays.toString(strings));

        // int[] to Set<Integer>
        HashSet<Integer> set1 = Arrays.stream(ints)
                .boxed()
                .collect(Collectors.toCollection(HashSet::new));

        // get max value in array
        int startIndex = 0;
        int endIndex = ints.length;
        Arrays.stream(ints, startIndex, endIndex).max().getAsInt();


        // slice array
        int[] newInts = Arrays.copyOfRange(ints, 2, 3);


        // reverse array
        Arrays.stream(ints).map(i -> ints[ints.length - i]).toArray();
    }

    static void list() {
        // array to List
        int[] a = {1, 2, 3};
//        List<Integer> l1  = Arrays.asList(a); // this does not work
        Integer[] integerArray = Arrays.stream(a).boxed().toArray(Integer[]::new);  // int[] --> Integer[]
        List<Integer> l1 = new ArrayList<>();
        Collections.addAll(l1, integerArray);


        Integer[] b = new Integer[]{1, 2, 3};
        List<Integer> l2 = Arrays.asList(b);

        // PRINT
        System.out.println(Arrays.toString(l2.toArray()));

        // List<Integer> -> int[]
        l2.stream().mapToInt(Integer::intValue).toArray();

        // print list
        l2.forEach(System.out::println);

        // Slice list
        l1.subList(0, 2);

    }

    static void set() {
        Set<String> set = new HashSet<>();

        // set to array, use set.size() to avoid create new array
        String[] strings = set.toArray(new String[set.size()]);
    }

    static void map() {

        Map<String, Integer> m = new HashMap<>();

        int a = 1;
        m.put("s", a);

        // print
        System.out.println(Arrays.asList(m));
    }

    static void string() {
        String a = "aaabbb";

        // String -> char[]
        a.toCharArray();

        // iteration
        for (char c : a.toCharArray()) {
        }
    }
}


