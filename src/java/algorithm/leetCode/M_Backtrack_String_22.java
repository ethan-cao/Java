package algorithm.leetCode;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

### Example
3 ->
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

*/

import java.util.*;

public class M_Backtrack_String_22 {

    public static void main(String... args) {
        System.out.println(generateParenthesis1(0)); // [""]
        System.out.println(generateParenthesis1(1)); // ["()"]
        System.out.println(generateParenthesis1(2)); // ["(())","()()"]
        System.out.println(generateParenthesis1(3)); // ["((()))","(()())","(())()","()(())","()()()"]
    }

    public static List<String> generateParenthesis(int n) {
        List<String> parentheses = new LinkedList<>();

        if (n < 0) {
            return parentheses;
        }

        generateParenthesis(0, n, n, 0, new StringBuilder(), parentheses);

        return parentheses;
    }

    private static void generateParenthesis(int position, int totalParentheses, int availableParenthesis, int openParenthesis, StringBuilder sb, List<String> parentheses) {
        // we know the final result length is 2 * totalParentheses, fill one by one
        if (position >= 2 * totalParentheses - 1) {
            parentheses.add(sb.toString());
            return;
        }

        if (sb.length() == 0) {
            sb.append("(");
        }

        if (sb.charAt(sb.length() - 1) == '(') {
            availableParenthesis--;
            openParenthesis++;
        } else {
            openParenthesis--;
        }

        Set<String> candidates = new HashSet<>();
        if (availableParenthesis > 0) {
            candidates.add("(");
        }
        if (openParenthesis > 0) {
            candidates.add(")");
        }

        for (String candidate : candidates) {
            generateParenthesis(position + 1, totalParentheses, availableParenthesis, openParenthesis, sb.append(candidate), parentheses);
            sb.deleteCharAt(sb.length() - 1); // IMPORTANT: keep the original value!!!
        }
    }


    // backtrack
    public static List<String> generateParenthesis1(int n) {
        List<String> result = new LinkedList<>();

        if (n < 0) {
            return result;
        }

        generateParenthesis1(n, 0, 0, new StringBuilder(), result);

        return result;
    }

    private static void generateParenthesis1(int totalParentheses, int leftParentheses, int rightParentheses, StringBuilder sb, List<String> parentheses) {
        if (sb.length() == 2 * totalParentheses) {
            parentheses.add(sb.toString());
            return;
        }

        if (leftParentheses < totalParentheses) {
            generateParenthesis1(totalParentheses, leftParentheses + 1, rightParentheses, sb.append("("), parentheses);
            sb.deleteCharAt(sb.length() - 1); // NECESSARY ! need to keep its original value
        }

        if (rightParentheses < leftParentheses) {
            generateParenthesis1(totalParentheses, leftParentheses, rightParentheses + 1, sb.append(")"), parentheses);
            sb.deleteCharAt(sb.length() - 1); // NECESSARY ! need to keep its original value
        }
    }
}