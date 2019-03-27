package leetCode;

/*
Given a positive integer, return its corresponding column title
as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB
    ...

### Example
1 --> "A"
28 --> "AB"
701 -->  "ZY"

### Condition

### Essential problem
it is NOT convert

### Corner case

*/

public class E_Recursion_Math_168 {
    public static void main(String... args) {
        System.out.println(convertToTitle(2));
        System.out.println(convertToTitle(26));
        System.out.println(convertToTitle(28));
        System.out.println(convertToTitle(701));
    }

    public static String convertToTitle(int n) {
        if (n <= 0) {
            return "";
        }



        return "";
    }

}
