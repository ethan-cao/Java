package algorithm.leetCode;

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

*/

import java.util.*;

public class E_Stack_20 {

    public static void main(String... args) {
        System.out.println(isValid("()")); // T
        System.out.println(isValid("()[]{}")); // T
        System.out.println(isValid("(]")); // F
        System.out.println(isValid("([)]")); // F
        System.out.println(isValid("{[]}")); // R
    }

    // 1ms
    public boolean isValid0(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); ++i) {
            char token = s.charAt(i);

            if (token == '(' || token == '[' || token == '{') {
                stack.push(token);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                if (stack.peek() == '(' && token != ')') {
                    return false;
                } else if (stack.peek() == '[' && token != ']') {
                    return false;
                } else if (stack.peek() == '{' && token != '}') {
                    return false;
                }

                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    // 2ms
    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        // use map for better maintainability and extensibility
        Map<Character, Character> mapping = buildMapping();

        for (char c : s.toCharArray()) {
            if (mapping.keySet().contains(c)) {
                stack.push(c);
            } else if (mapping.values().contains(c)) {
                if (stack.isEmpty()) {
                    return false;
                }

                char bracket = stack.pop();
                if (mapping.get(bracket) != c) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return stack.isEmpty();
    }

    private static Map<Character, Character> buildMapping() {
        Map<Character, Character> mapping = new HashMap<>();

        mapping.put('(', ')');
        mapping.put('[', ']');
        mapping.put('{', '}');

        return mapping;
    }
}
