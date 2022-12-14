package algorithm.leetCode;

/*
Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

### Example
3 -> 5

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

*/

public class M_DP_Tree_96 {

    public static void main(String... args) {
        System.out.println(numTrees(0));  // 1
        System.out.println(numTrees(1));  // 1
        System.out.println(numTrees(2));  // 2
        System.out.println(numTrees(3));  // 5
        System.out.println(numTrees(4));  // 14
        System.out.println(numTrees(5));  // 42
    }

    // DP, iterative, 0ms
    public static int numTrees(int n) {
        int[] count = new int[n + 1];
        count[0] = 1;

        for (int i = 1; i <= n; ++i) {
            for (int leftRange = 0; leftRange < i; ++leftRange) {
                int rightRange = i - (leftRange + 1);
                count[i] += count[leftRange] * count[rightRange];
            }
        }

        return count[n];
    }

    //DP, recursive, TLE
    public static int numTrees1(int n) {
        return getCount(1, n);
    }

    public static int getCount(int start, int end) {
        if (start >= end) {
            return 1;
        }

        int count = 0;

        for (int i = start; i <= end; i++) {
            count += getCount(start, i - 1) * getCount(i + 1, end);
        }

        return count;
    }

}