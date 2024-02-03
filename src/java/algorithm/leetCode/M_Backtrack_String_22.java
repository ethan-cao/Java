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

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Backtrack
    // Time: 0ms
    public List<String> generateParenthesis(int n) {
        List<String> parenthesis = new ArrayList<>();
        StringBuilder tracker = new StringBuilder();

        collect(parenthesis, tracker, n, n);

        return parenthesis;
    }

    private void collect(List<String> parenthesis, StringBuilder tracker, int leftCount, int rightCount) {
        if (leftCount == 0 && rightCount == 0) {
            parenthesis.add(tracker.toString());
            return;
        }

        if (leftCount > 0) {
            tracker.append("(");
            collect(parenthesis, tracker, leftCount - 1, rightCount);
            tracker.deleteCharAt(tracker.length() - 1);
        }

        if (rightCount > leftCount) {
            tracker.append(")");
            collect(parenthesis, tracker, leftCount, rightCount - 1);
            tracker.deleteCharAt(tracker.length() - 1);
        }

        // alternatively
        // if (leftCount > 0) {
        //     collect(parenthesis, new StringBuilder(sb).append("("), leftCount - 1, rightCount);
        // }

        // if (rightCount > 0 && leftCount < rightCount) {
        //     collect(parenthesis, new StringBuilder(sb).append(")"), leftCount, rightCount - 1);
        // }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static List<String> generateParenthesis1(int n) {
        List<String> parentheses = new LinkedList<>();

        generateParenthesis(0, n, n, 0, new StringBuilder(), parentheses);

        return parentheses;
    }

    private static void generateParenthesis(
            int position,
            int totalParentheses,
            int availableParenthesis,
            int openParenthesis,
            StringBuilder sb,
            List<String> parentheses) {
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
            generateParenthesis(
                    position + 1,
                    totalParentheses,
                    availableParenthesis,
                    openParenthesis,
                    sb.append(candidate),
                    parentheses);

            sb.deleteCharAt(sb.length() - 1); // IMPORTANT: keep the original value!!!
        }
    }

}