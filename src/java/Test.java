import java.util.*;

public class Test {
    public static void main(String[] args) {
        String s = "bbbab";
        int L = s.length();

        int[][] counts = new int[L][L];

        for (int end = 0; end < L; ++end) {
            // counts[end][end] = 1;

            for (int start = end; start >= 0; --start) {

                if (s.charAt(start) == s.charAt(end)) {
                    if (start + 1 <= end - 1) {
                        counts[start][end] = 2 + counts[start + 1][end - 1];
                    } else {
                        counts[start][end] = end - start + 1;
                    }
                } else {
                    counts[start][end] = Math.max(counts[start + 1][end], counts[start][end - 1]);
                }
            }
        }

        // return counts[0][L - 1];
    }
}