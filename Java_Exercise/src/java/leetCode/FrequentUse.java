package leetCode;

import java.util.*;
import java.util.stream.Collectors;

public class FrequentUse {
    public static void main(String[] arg){
        list();
    }

    void array(){
        String[] strings = new String[] {"1", "2", "3"};
        int[] ints = {1,2,3,4,4};

		int l = strings.length;

		// the easiest way to print content
        System.out.println(Arrays.toString(strings));

        // int[] to Set<Integer>
        HashSet<Integer> set1 = Arrays.stream(ints)
                .boxed()
                .collect(Collectors.toCollection(HashSet::new));



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

        /**
         * List<Integer> -> int[]
         */

        l2.stream().mapToInt(Integer::intValue).toArray();

    }

    void set(){
        Set<String> set = new HashSet<>();

        String[] strings = set.toArray(new String[]{}); //
    }

    static void map(){

        Map<String, Integer> m =  new HashMap<>();

        int a = 1;
        m.put("s", a);

        System.out.println(Arrays.asList(m)); // print map
    }
}


