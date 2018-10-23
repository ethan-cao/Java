package leetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FrequentUse {
    public static void main(String[] arg){

    }

    void array(){
        String[] array = new String[] {"1", "2", "3"};

		int l = array.length;
        System.out.println(Arrays.toString(array));  // the easiest way to print content
    }

    void set(){
        Set<String> set = new HashSet<>();

        String[] strings = set.toArray(new String[]{}); //
    }
}


