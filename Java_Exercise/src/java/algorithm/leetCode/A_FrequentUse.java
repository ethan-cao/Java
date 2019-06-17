package algorithm.leetCode;

import java.util.*;
import java.util.stream.Collectors;

public class A_FrequentUse {
    public static void main(String[] arg){
        list();
    }

    void array(){
        String[] strings = new String[] {"1", "2", "3"};
        int[] ints = {1,2,3,4,4};

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


        // list to strings

//        System.arraycopy();

    }

    static void list(){
        /**
         array to List
         */
        int[] a = {1,2,3};
//        List<Integer> l1  = Arrays.asList(a); // this does not work
        Integer[] integerArray = Arrays.stream(a).boxed().toArray(Integer[]::new);  // int[] --> Integer[]
        List<Integer> l1  = new ArrayList<>();
        Collections.addAll(l1, integerArray);


        Integer[] b = new Integer[]{1, 2, 3};
        List<Integer> l2  = Arrays.asList(b);

        // PRINT
        System.out.println(Arrays.toString(l2.toArray()));

        /**
         * List<Integer> -> int[]
         */

        l2.stream().mapToInt(Integer::intValue).toArray();

        // print steam
        l2.forEach(System.out::println);
    }

    void set(){
        Set<String> set = new HashSet<>();

        String[] strings = set.toArray(new String[]{}); // set to array
    }

    static void map(){

        Map<String, Integer> m =  new HashMap<>();

        int a = 1;
        m.put("s", a);

        // print
        System.out.println(Arrays.asList(m));
    }

    static void string(){
        String a = "aaabbb";

        // String -> char[]
        a.toCharArray();

        // iteration
        for (char c : a.toCharArray()){
        }
    }
}


