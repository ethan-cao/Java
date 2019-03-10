package leetCode;

/*
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

### Input
int[] array, int k

### Output
int[]

### Example
"11",  "1"      --> "100"
"1010","1011"   --> "10101"

### Condition

### Essential problem

### Corner case
*/

import com.google.common.base.Strings;

public class E_String_67 {
    public static void main(String... args) {
        String a = "11";
        String b = "1";

        System.out.println(addBinary(a, b));
    }

    public static String addBinary(String a, String b) {

        if (Strings.isNullOrEmpty(a) || Strings.isNullOrEmpty(b)){
           return a + b;
        }

        return "";
    }
}
