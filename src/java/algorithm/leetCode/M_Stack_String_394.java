package algorithm.leetCode;

/*
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string],
where the encoded_string inside the square brackets is being repeated exactly k times.
Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid;
No extra white spaces, square brackets are well-formed, etc.

you may assume original data does not contain any digits and that digits are only for those repeat numbers, k
For example, there won't be input like 3a or 2[4].

### Example
s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

 */

import java.util.*;

public class M_Stack_String_394 {

    public static void main(String... args) {
        System.out.println(decodeString("1[a]"));     // a
        System.out.println(decodeString("2[a]"));     // aa
        System.out.println(decodeString("11[a]"));     // aaaaaaaaaaa
        System.out.println(decodeString("3[a]2[bc]"));     // aaabcbc
        System.out.println(decodeString("3[a2[c]]"));      // accaccacc
        System.out.println(decodeString("2[abc]3[cd]ef")); // abcabccdcdcdef
    }

    // Stack, 0ms
    public static String decodeString(String s) {
        StringBuilder decodedString = new StringBuilder();
        int k = 0;

        Deque<StringBuilder> stringStack = new ArrayDeque<>();
        Deque<Integer> countStack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c - '0' >= 0 && '9' - c >= 0) {
                k = k * 10 + c - '0';
            } else if (c == '[') {
                stringStack.push(decodedString);
                countStack.push(k);

                decodedString = new StringBuilder();
                k = 0;
            } else if (c == ']') {
                StringBuilder temp = decodedString;
                decodedString = stringStack.pop();

                for (k = countStack.pop(); k > 0; --k) {
                    decodedString.append(temp);
                }
            } else {
                decodedString.append(c);
            }
        }

        return decodedString.toString();
    }

    // Recursive 0ms
    public String decodeString1(String s) {
        Deque<Character> queue = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            queue.offer(c);
        }

        return constrctString(queue);
    }

    public String constrctString(Deque<Character> queue) {
        StringBuilder sb = new StringBuilder();
        int num = 0;

        while (!queue.isEmpty()) {
            char c = queue.poll();

            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                String sub = constrctString(queue);
                for (int i = 0; i < num; i++) {
                    sb.append(sub);
                }

                num = 0;
            } else if (c == ']') {
                break;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

}
