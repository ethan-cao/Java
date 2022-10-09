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

import java.util.*;

public class H_Stack_String_772 {

    public static void main(String... args) {
        System.out.println(calculate1("1+1"));      // 2
        System.out.println(calculate1("(1+1)"));    // 2
        System.out.println(calculate1("2*(1+1)"));  // 4
        System.out.println(calculate1("6-4/2"));    // 4
        System.out.println(calculate1("2*(5+5*2)/3+(6/2+8)"));          // 21
        System.out.println(calculate1("(2+6* 3+5- (3*14/7+2)*5)+3"));   // -12
    }

    // Stack
    // Time: O(N), Space: O(N)
    public static int calculate1(String s) {
        Deque<Character> operatorStack = new ArrayDeque<>();
        Deque<Integer> operandStack = new ArrayDeque<>();

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
                    char previousOperator = operatorStack.peekFirst();

                    if (previousOperator == '(') {
                        operatorStack.push(c);
                    } else {
                        // previousOperator could be +, -, *, /
                        // if previous operator can be calculated, do the calculation first
                        calculateStack(operandStack, operatorStack);
                        // defer handling the current operator to next round, after calculating previous result
                        i--;
                    }
                } else if (c == '*' || c == '/') {
                    char topOperator = operatorStack.peekFirst();

                    if (topOperator == '+' || topOperator == '-' || topOperator == '(') {
                        operatorStack.push(c);
                    } else {
                        // topOperator could b * or /
                        // if previous operator can be calculated, do the calculation first
                        calculateStack(operandStack, operatorStack);
                        // defer handling the current operator to next round, after calculating previous result
                        i--;
                    }
                } else if (c == '(') {
                    operatorStack.push(c);
                } else if (c == ')') {
                    while (operatorStack.peekFirst() != '(') {
                        calculateStack(operandStack, operatorStack);
                    }
                    operatorStack.pop(); // remove (
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

    // reuse callstack
    public static int calculate2(String s) {
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

                int num = calculate2(s.substring(cur + 1, i));

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

}