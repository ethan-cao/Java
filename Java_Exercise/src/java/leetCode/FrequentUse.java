package leetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FrequentUse {
    public static void main(String[] arg){

    }

    void array(){
        String[] strings = new String[] {"1", "2", "3"};
        int[] ints = {1,2,3,4,4};

		int l = strings.length;

		// the easiest way to print content
        System.out.println(Arrays.toString(strings));

        // ints to Set<Integer>
        HashSet<Integer> set1 = Arrays.stream(ints)
                .boxed()
                .collect(Collectors.toCollection(HashSet::new));



        // list to strings

//        System.arraycopy();

    }

    void set(){
        Set<String> set = new HashSet<>();

        String[] strings = set.toArray(new String[]{}); //
    }
}


