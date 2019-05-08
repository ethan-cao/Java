package leetCode;

/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

### Example
"()" -> T
"()[]{}" -> T
"(]" -> F
"([)]" -> F
"{[]}" -> T

### Condition

### Essential problem

### Corner case

*/


import java.util.ArrayDeque;
import java.util.Deque;

public class E_Stack_20 {
    public static void main(String... args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("{[]}"));
    }

    public static boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }

        if ("".equals(s)){
            return true;
        }

        Deque<Character> stack = new ArrayDeque<>();
        stack.push(s.charAt(0));

        for (int i = 1; i < s.length(); ++i) {
            char c1 = s.charAt(i);
            char c2 = stack.isEmpty() ? '-' : stack.peekFirst();

            if (isMatch(c1, c2) ){
                stack.pop();
            } else{
                stack.push(c1);
            }
        }

        return stack.isEmpty();
    }

    private static boolean isMatch(char c1, char c2) {
        if (c1 == '(') {
            return c2 == ')';
        } else if (c1 == ')') {
            return c2 == '(';
        } else if (c1 == '[') {
            return c2 == ']';
        } else if (c1 == ']') {
            return c2 == '[';
        } else if (c1 == '{') {
            return c2 == '}';
        } else if (c1 == '}') {
            return c2 == '{';
        }

        return false;
    }
}

