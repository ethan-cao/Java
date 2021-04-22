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

public class M_Stack_DFS_String_678 {

    public static void main(String... args) {
        System.out.println(checkValidString1(""));                    // T
        System.out.println(checkValidString1("()"));                  // T
        System.out.println(checkValidString1("**"));                  // T
        System.out.println(checkValidString1("***"));                 // T
        System.out.println(checkValidString1("(*)"));                 // T
        System.out.println(checkValidString1("(*))"));                // T
        System.out.println(checkValidString1("((*)"));                // T
        System.out.println(checkValidString1("(((******))"));         // T
        System.out.println(checkValidString1("(())((())()()(*)(*()(())())())()()((()())((()))(*")); // F
    }

    // Stack, 0ms
    // Time: O(N), Space: O(N)
    public static boolean checkValidString(String s) {
        // !!! store the index
        Deque<Integer> leftIndices = new ArrayDeque<>();
        Deque<Integer> starIndices = new ArrayDeque<>();

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c == '(') {
                leftIndices.push(i);
            } else if (c == ')') {
                if (!leftIndices.isEmpty()) {
                    leftIndices.pop();
                } else if (!starIndices.isEmpty()) {
                    starIndices.pop();
                } else {
                    return false;
                }
            } else {
                starIndices.push(i);
            }
        }

        // check the rest
        while (!leftIndices.isEmpty() && !starIndices.isEmpty()) {
            // only if star is on right side of leftParenthesis, pair them
            // otherwise not valid
            if (leftIndices.pop() > starIndices.pop()) {
                return false;
            }
        }

        return leftIndices.isEmpty();
    }

    // 0ms
    // Time: O(N), Space O(1)
    public static boolean checkValidString2(String s) {
        int maxOpenParenthesisCount = 0;
        int minOpenParenthesisCount = 0;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c == '(') {
                maxOpenParenthesisCount++;
                minOpenParenthesisCount++;
            } else if (c == ')') {
                maxOpenParenthesisCount--;
                minOpenParenthesisCount--;
            } else if (c == '*') {
                // case1: consider * as (
                // minOpenParenthesisCount remains, since the min possible value could still be the same
                maxOpenParenthesisCount++;

                // case2: consider * as )
                // since * can be considered as empty, it is only necessary when there are unmatched (
                // maxOpenParenthesisCount remains, since the max possible value could still be the same
                minOpenParenthesisCount--;

                // case3: consider * as empty, do nothing
            }

            // after each round, there is more ), and it is not possible to pair it any more
            if (maxOpenParenthesisCount < 0) {
                return false;
            }

            // minOpenParenthesisCount cannot be < 0, those cases should be eliminated
            if (minOpenParenthesisCount < 0) {
                minOpenParenthesisCount = 0;
            }
        }

        // if the min possible is 0, that means it could possibly pair all ( and )
        return minOpenParenthesisCount == 0;
    }

    // DFS
    // Time: O(N^3), when all char is *
    // we could use cache to improve
    public static boolean checkValidString1(String s) {
        return check(s, 0, 0);
    }

    private static boolean check(String s, int index, int unpairedLeftCount) {
        if (unpairedLeftCount < 0) {
            return false;
        }

        if (index >= s.length()) {
            return unpairedLeftCount == 0;
        }

        char c = s.charAt(index);

        if (c == '(') {
            return check(s, index + 1, unpairedLeftCount + 1);
        } else if (c == ')') {
            return check(s, index + 1, unpairedLeftCount - 1);
        } else {
            return check(s, index + 1, unpairedLeftCount + 1) ||
                    check(s, index + 1, unpairedLeftCount) ||
                    check(s, index + 1, unpairedLeftCount - 1);
        }
    }

}