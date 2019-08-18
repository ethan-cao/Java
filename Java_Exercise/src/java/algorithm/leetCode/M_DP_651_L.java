package algorithm.leetCode;

/*
magine you have a special keyboard with the following keys:

Key 1: (A): Print one 'A' on screen.
Key 2: (Ctrl-A): Select the whole screen.
Key 3: (Ctrl-C): Copy selection to buffer.
Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.

Now, you can only press the keyboard for N times (with the above four keys),
find out the maximum numbers of 'A' you can print on screen.

1 <= N <= 50
Answers will be in the range of 32-bit signed integer.

### Example
3->3
Explanation:
We can at most get 3 A's on screen by pressing following key sequence:
A, A, A

7->9
Explanation:
We can at most get 9 A's on screen by pressing following key sequence:
A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
*/


public class M_DP_651_L {
    public static void main(String... args) {

    }

    public static int maxA(int N) {
        return 1;
    }
}
