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

import java.util.*;

public class M_Stack_String_227 {

    public static void main(String... args) {
        System.out.println(calculate3("0"));    // 121
        System.out.println(calculate3("11*11"));    // 121
        System.out.println(calculate3("1+2"));      // 3
        System.out.println(calculate3("3/2"));      // 1
        System.out.println(calculate3("3+2*2"));    // 7
        System.out.println(calculate3("3+5/2"));    // 5
        System.out.println(calculate3("42343*14+0-3-2+5323/24+2+2-1*123"));    // 592899
    }

    // Stack, 6ms
    // Time: O(N), Space: O(N)
    static int calculate2(String s) {
        int operand1 = 0;
        char operator = '+';
        int operand2 = 0;
        int result = 0;

        // stack stores all number await for summing up
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {
                operand2 = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    operand2 = operand2 * 10 + s.charAt(i + 1) - '0';
                    i++;
                }

                if (operator == '+') {
                    stack.push(operand2);
                } else if (operator == '-') {
                    stack.push(-operand2);
                } else if (operator == '*') {
                    operand1 = stack.pop();
                    stack.push(operand1 * operand2);
                } else if (operator == '/') {
                    operand1 = stack.pop();
                    stack.push(operand1 / operand2);
                }
            } else {
                operator = c;
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    // Time: O(N), Space: O(1), 6ms
    static int calculate1(String s) {
        int operand1 = 0;
        char operator = '+';
        int operand2 = 0;
        int result = 0;
        // result = sum (operand1 operator operand2)
        // Two Pointer
        //                                3         +        2          *         2
        // result  operand1  operator  operand2
        //                   result    operand1  operator  operand2
        //                                       result    operand1  operator  operand2
        //                                       result    operand1

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {

                operand2 = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    operand2 = operand2 * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }

                // result is result from the beginning til operand1 (exclusive)

                if (operator == '*') {
                    operand1 = operand1 * operand2;
                } else if (operator == '/') {
                    operand1 = operand1 / operand2;
                } else if (operator == '+') {
                    result += operand1;
                    operand1 = operand2;
                } else if (operator == '-') {
                    result += operand1;
                    operand1 = -operand2;
                }
            } else {
                operator = c;
            }
        }

        // since result is from beginning til operand1 (exclusive), add it
        return result + operand1;
    }

    // use array to simulate stack
    // fastest
    public static int calculate3(String s) {
        int operand1 = 0;
        char operator = '+';
        int operand2 = 0;
        int result = 0;

        // stack stores all number await for summing up
        int[] stack = new int[s.length()];
        int next = 0; // points to the next available position, different from ArrayDeque

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {
                operand2 = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    operand2 = operand2 * 10 + s.charAt(i + 1) - '0';
                    i++;
                }

                if (operator == '+') {
                    stack[next++] = operand2;
                } else if (operator == '-') {
                    stack[next++] = -operand2;
                } else if (operator == '*') {  // !!! --next
                    operand1 = stack[--next];
                    stack[next++] = operand1 * operand2;
                } else if (operator == '/') {   // !!! --next
                    operand1 = stack[--next];
                    stack[next++] = operand1 / operand2;
                }
            } else {
                operator = c;
            }
        }

        while (next !=  0) {
            result += stack[--next]; // !!! --next, next points to the available position
        }

        return result;
    }

    // Too slow
    static int calculate(String s) {
        final String PLUS = "+";
        final String MINUS = "-";
        final String MULTIPLY = "*";
        final String DIVIDE = "/";

        List<String> inputs = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            String string = c + "";

            if (PLUS.equals(string) || MINUS.equals(string) || MULTIPLY.equals(string) || DIVIDE.equals(string)) {
                inputs.add(sb.toString());
                sb = new StringBuilder();

                inputs.add(string);
                continue;
            }

            sb.append(c);
        }
        inputs.add(sb.toString());

        for (int i = 0; i < inputs.size(); ) {
            String input = inputs.get(i);

            if (input.equals(MULTIPLY) || input.equals(DIVIDE)) {
                int operand1 = Integer.parseInt(inputs.get(i - 1));
                int operand2 = Integer.parseInt(inputs.get(i + 1));
                int result = input.equals(MULTIPLY) ? operand1 * operand2 : operand1 / operand2;

                // !!! while removing, index is changing!!!
                inputs.remove(i - 1);
                inputs.remove(i - 1);
                inputs.remove(i - 1);

                inputs.add(i - 1, result + "");
            } else {
                i++;
            }
        }

        for (int i = 0; i < inputs.size(); ) {
            String input = inputs.get(i);

            if (input.equals(PLUS) || input.equals(MINUS)) {
                int operand1 = Integer.parseInt(inputs.get(i - 1));
                int operand2 = Integer.parseInt(inputs.get(i + 1));
                int result = input.equals(PLUS) ? operand1 + operand2 : operand1 - operand2;

                inputs.remove(i - 1);
                inputs.remove(i - 1);
                inputs.remove(i - 1);

                inputs.add(i - 1, result + "");
            } else {
                i++;
            }
        }

        return Integer.parseInt(inputs.get(0));
    }
}