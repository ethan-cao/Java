package algorithm.leetCode;

/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
The integer division should truncate toward zero.

You may assume that the given expression is always valid.

### Example
"3+2*2" -> 7
"3/2" -> 1
"3+5/2 " -> 5

*/

public class M_String_227 {

    public static void main(String... args) {
        System.out.println(calculate("3+2*2"));    // 7
        System.out.println(calculate("3/2"));      // 1
        System.out.println(calculate("3+5/2"));    // 5
    }

    static int calculate(String s) {
        return 1;
    }

}
