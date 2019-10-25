package algorithm.leetCode;

/*
Given a string containing only three types of characters: '(', ')' and '*',
write a function to check whether this string is valid.
The string size will be in the range [1, 100].

We define the validity of a string by these rules:
1.Any left parenthesis '(' must have a corresponding right parenthesis ')'.
2.Any right parenthesis ')' must have a corresponding left parenthesis '('.
3.Left parenthesis '(' must go before the corresponding right parenthesis ')'.
4.'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
5.An empty string is also valid.

### Example
"()" -> T
"(*)" -> T
"(*))" -> T

*/

import java.util.*;

public class M_Stack_String_678 {

    public static void main(String... args) {
        System.out.println(checkValidString1(""));          // T
        System.out.println(checkValidString1("()"));        // T
        System.out.println(checkValidString1("**"));        // T
        System.out.println(checkValidString1("***"));       // T
        System.out.println(checkValidString1("(*)"));        // T
        System.out.println(checkValidString1("(*))"));        // T
        System.out.println(checkValidString1("((*)"));        // T
        System.out.println(checkValidString1("(((******))"));        // T
        System.out.println(checkValidString1("(())((())()()(*)(*()(())())())()()((()())((()))(*")); // F
    }

    // Stack
    // Time: O(N), Space: O(N)
    public static boolean checkValidString(String s) {
        // store the index
        Deque<Byte> leftParenthesis = new ArrayDeque<>();
        Deque<Byte> stars = new ArrayDeque<>();

        for (byte i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c == '*') {
                stars.push(i);
            } else if (c == '(') {
                leftParenthesis.push(i);
            } else if (c == ')') {
                if (!leftParenthesis.isEmpty()) {
                    leftParenthesis.pop();
                } else if (!stars.isEmpty()) {
                    stars.pop();
                } else {
                    return false;
                }
            }
        }

        while (!leftParenthesis.isEmpty() && !stars.isEmpty()) {
            if (stars.pop() < leftParenthesis.pop()) {
                return false;
            }
        }

        return leftParenthesis.isEmpty();
    }

    // Time: O(N), Space O(1)
    public static boolean checkValidString1(String s) {
        int maxPossibleUnmatchedLeftCount = 0;
        int maxPossibleRequiredRightCount = 0;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c == '(') {
                maxPossibleUnmatchedLeftCount++;
                maxPossibleRequiredRightCount++;
            } else if (c == ')') {
                if (maxPossibleUnmatchedLeftCount > 0) {
                    maxPossibleUnmatchedLeftCount--;
                }

                maxPossibleRequiredRightCount--;
            } else if (c == '*') {
                // take '*' as ')', if there are unmatched '('
                if (maxPossibleUnmatchedLeftCount > 0) {
                    maxPossibleUnmatchedLeftCount--;
                }

                //  take '*' as '('
                maxPossibleRequiredRightCount++;
            }

            if (maxPossibleRequiredRightCount < 0) {
                return false;
            }
        }

        return maxPossibleUnmatchedLeftCount == 0;
    }

}