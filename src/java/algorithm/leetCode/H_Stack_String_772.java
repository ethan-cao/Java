package algorithm.leetCode;

/*
implement a basic calculator to evaluate a simple expression string.

The expression string contains non-negative integers, +, -, *, /, (, ) and empty spaces.

The integer division should truncate toward zero.
You may assume that the given expression is always valid.
All intermediate results will be in the range of [-2147483648, 2147483647].

### Example
"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12

*/

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class H_Stack_String_772 {

    public static void main(String... args) {
//        System.out.println(calculate1("1+1"));      // 2
//        System.out.println(calculate1("(1+1)"));    // 2
//        System.out.println(calculate1("2*(1+1)"));  // 4
//        System.out.println(calculate1("6-4/2"));   // 4
//        System.out.println(calculate1("2*(5+5*2)/3+(6/2+8)"));   // 21
        System.out.println(calculate1("(2+6* 3+5- (3*14/7+2)*5)+3"));   // -12
    }

    // Stack
    // Time: O(N), Space: O(N)
    public static int calculate(String s) {
        Deque<Integer> operandStack = new ArrayDeque<>();
        Deque<Character> operatorStack = new ArrayDeque<>();

        // handle all parenthesis case
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {
                int operand = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    operand = operand * 10 + s.charAt(i + 1) - '0';
                    i++;
                }

                operandStack.push(operand);
            } else {

                if (operatorStack.isEmpty()) {
                    operatorStack.push(c);
                } else if (c == '+' || c == '-') {
                    char top = operatorStack.peek();

                    if (top == '(') {
                        operatorStack.push(c);
                    } else {
                        calculate(operandStack, operatorStack);
                        i--;
                    }

                } else if (c == '*' || c == '/') {
                    char top = operatorStack.peek();

                    if (top == '(') {
                        operatorStack.push(c);
                    } else if (top == '*' || top == '/') {
                        calculate(operandStack, operatorStack);
                        i--;
                    } else if (top == '+' || top == '-') {
                        operatorStack.push(c);
                    }


                } else if (c == '(') {
                    operatorStack.push(c);
                } else if (c == ')') {
                    while (operandStack.peekFirst() != '(') {   // !!! peak
                        calculate(operandStack, operatorStack);
                    }
                    operatorStack.pop(); // remove (
                }
            }
        }

        // handle the reset without parenthesis
        while (!operatorStack.isEmpty()) {
            calculate(operandStack, operatorStack);
        }

        return operandStack.peekFirst();
    }

    private static int calculate(Deque<Integer> operandStack, Deque<Character> operatorStack) {
        if (operandStack.size() < 2 || operatorStack.size() < 1) {
            return 0;
        }

        int operand2 = operandStack.pop();
        char operator = operatorStack.pop();
        int operand1 = operandStack.pop();
        int result = 0;

        switch (operator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                result = operand1 / operand2;
                break;
        }

        return result;
    }


    public static int calculateA(String s) {
        int op1 = 1;
        int op2 = 1;
        int val1 = 0;
        int val2 = 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                int num = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }

                val2 = op2 == 1 ? val2 * num : val2 / num;

            } else if (c == '(') {
                int cur = i;
                int count = 0;

                while (i < s.length()) {
                    char ch = s.charAt(i);
                    if (ch == '(') count++;
                    if (ch == ')') count--;
                    if (count == 0) break;
                    i++;
                }

                int num = calculateA(s.substring(cur + 1, i));

                val2 = op2 == 1 ? val2 * num : val2 / num;

            } else if (c == '+' || c == '-') {
                val1 = val1 + op1 * val2;
                op1 = c == '+' ? 1 : -1;
                op2 = 1;
                val2 = 1;
            } else if (c == '*' || c == '/') {
                op2 = c == '*' ? 1 : -1;
            }
        }

        return val1 + op1 * val2;
    }


    public static int calculate1(String s) {
        Deque<Character> operatorStack = new ArrayDeque<>();
        Deque<Integer> operandStack = new ArrayDeque<>();


        for (int i = 0; i < s.length(); ) {
            char c = s.charAt(i);

            if (c == ' ') {
                i++;
                continue;
            }

            if (Character.isDigit(c)) {

                int operand = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    operand = operand * 10 + Character.getNumericValue(s.charAt(i));
                    i++;
                }
                operandStack.push(operand);

            } else {

                if (operatorStack.isEmpty()) {
                    operatorStack.push(c);
                    i++;
                } else if (c == '+' || c == '-') {
                    char top = operatorStack.peekFirst();

                    if (top == '(') {
                        operatorStack.push(c);
                        i++;
                    } else {
                        calculateStack(operandStack, operatorStack);
                    }

                } else if (c == '*' || c == '/') {
                    char top = operatorStack.peek();
                    if (top == '(') {
                        operatorStack.push(c);
                        i++;
                    } else if (top == '*' || top == '/') {
                        calculateStack(operandStack, operatorStack);
                    } else if (top == '+' || top == '-') {
                        operatorStack.push(c);
                        i++;
                    }
                } else if (c == '(') {
                    operatorStack.push(c);
                    i++;
                } else if (c == ')') {
                    while (operatorStack.peek() != '(') {
                        calculateStack(operandStack, operatorStack);
                    }
                    operatorStack.pop();
                    i++;
                }
            }
        }

        while (!operatorStack.isEmpty()) {
            calculateStack(operandStack, operatorStack);
        }

        return operandStack.peek();
    }

    private static void calculateStack(Deque<Integer> operandStack, Deque<Character> operatorStack) {
        int operand2 = operandStack.pop();
        char operator = operatorStack.pop();
        int operand1 = operandStack.pop();
        int result = 0;

        switch (operator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                result = operand1 / operand2;
                break;
        }

        operandStack.push(result);
    }

}