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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class M_Stack_String_856 {

    public static void main(String... args) {
        System.out.println(scoreOfParentheses("()"));        // 1
        System.out.println(scoreOfParentheses("(())"));      // 2
        System.out.println(scoreOfParentheses("()()"));      // 2
        System.out.println(scoreOfParentheses("(()(()))"));  // 6
    }

    static int scoreOfParentheses(String S) {
        int score = 0;

        return score;
    }

}