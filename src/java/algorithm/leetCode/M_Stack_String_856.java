package algorithm.leetCode;

/*
Given a balanced parentheses string S, compute the score of the string based on the following rule:
    () has score 1
    AB has score A + B, where A and B are balanced parentheses strings.
    (A) has score 2 * A, where A is a balanced parentheses string.
S is a balanced parentheses string, containing only ( and ).
2 <= S.length <= 50

### Example
"()" -> 1
"(())" -> 2
"()()" -> 2
"(()(()))" -> 6

*/

import java.util.*;

public class M_Stack_String_856 {

    public static void main(String... args) {
        System.out.println(scoreOfParentheses1("()"));          // 1
        System.out.println(scoreOfParentheses1("()()"));        // 2
        System.out.println(scoreOfParentheses1("()()()"));      // 3
        System.out.println(scoreOfParentheses1("(())"));        // 2
        System.out.println(scoreOfParentheses1("((()))"));      // 4
        System.out.println(scoreOfParentheses1("(()(()))"));    // 6
        System.out.println(scoreOfParentheses1("((()))(())"));  // 6
        System.out.println(scoreOfParentheses1("()(())()(((()(()())))(((())(()))()))(((())))"));  // 88
    }

    // Stack, 0ms
    // Time: O(N), Space: O(N)
    public static int scoreOfParentheses(String s) {
        int score = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(score);
                score = 0; // start counting score for this parenthesis '(' c
            } else {
                // since S is a balanced, there are 2 possible cases: () or (A)
                // if score is 0, then we have (), so the score is 1
                // if score is not 0, then we have (A), so the score is score * 2
                int newScore = score == 0 ? 1 : score * 2;
                int currentScore = stack.pop();

                score = newScore + currentScore; // case AB
            }
        }

        return score;
    }

    // Time: O(N), Space: O(1)
    public static int scoreOfParentheses1(String s) {
        int score = 0;
        int level = 0;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (c == '(') {
                level++;
            } else {
                level--;
            }

            // update score when () appears
            // 1 << level is score of the just appeared ()
            if (c == ')' && s.charAt(i - 1) == '(') {
                score += 1 << level;
            }
        }

        return score;
    }
}