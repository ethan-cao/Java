package algorithm.leetCode;

/*
Implement a basic calculator to evaluate a simple expression string.
The expression string may contain open ( and closing parentheses ),
the plus + or minus sign -, non-negative integers and empty spaces .

### Example
"1 + 1" -> 2
" 2-1 + 2 " -> 3
 "(1+(4+5+2)-3)+(6+8)" -> 23

*/

public class H_Stack_String_224 {

    public static void main(String... args) {
        System.out.println(calculate("11*11"));                     // 121
        System.out.println(calculate( " 2-1 + 2 "));                // 3
        System.out.println(calculate( "(1+(4+5+2)-3)+(6+8)"));      // 23
        System.out.println(calculate("42343*14+0-3-2+5323/24+2+2-1*123"));    // 592899
    }

    static int calculate(String s) {
        return 1;
    }
}
