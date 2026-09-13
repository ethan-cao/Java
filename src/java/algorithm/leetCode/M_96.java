package algorithm.leetCode;

/*
https://leetcode.com/problems/unique-binary-search-trees/

Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

### Example
3 -> 5

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

*/

public class M_96 {

    public static void main(String... args) {
        // System.out.println(numTrees(0));  // 1
        // System.out.println(numTrees(1));  // 1
        // System.out.println(numTrees(2));  // 2
        System.out.println(numTrees1(3)); // 5
        // System.out.println(numTrees(4));  // 14
        // System.out.println(numTrees(5));  // 42
    }


    //----------------------------------------------------------------------------------------------
    // DP, iterative, 0ms
    public static int numTrees(int n) {
        // count[k] is the number of structurally distinct BSTs holding any k
        int[] count = new int[n + 1];
        count[0] = 1;

        for (int treeSize = 1; treeSize <= n; ++treeSize) {

            // left tree size, start with empty left tree 
            for (int leftSize = 0; leftSize < treeSize; ++leftSize) {

                // right tree size
                int rightSize = treeSize - leftSize - 1;

                count[treeSize] += count[leftSize] * count[rightSize];
            }
        }

        return count[n];
    }


    //----------------------------------------------------------------------------------------------
    // DP, recursive, memo, 0ms
    public int numTrees11(int n) {
        Integer[][] memo = new Integer[n + 1][n + 1];
        return count(1, n, memo);
    }

    private int count(int start, int end, Integer[][] memo) {
        // !!! use start > end
        // for instance, when start = end = 1, there is still 1 branch
        if (start > end) {
            return 1;
        }
    
        if (memo[start][end] != null) {
            return memo[start][end];
        }
    
        int total = 0;
    
        for (int rootValue = start; rootValue <= end; ++rootValue) {
            int leftTrees = count(start, rootValue - 1, memo);
            int rightTrees = count(rootValue + 1, end, memo);
    
            total += leftTrees * rightTrees;
        }
    
        memo[start][end] = total;
        
        return total;
    }

    //----------------------------------------------------------------------------------------------
    //DP, recursive, 2715ms 
    public static int numTrees1(int n) {
        return count(1, n);
    }

    private static int count(int start, int end) {
        // !!! == is needed, coz start can be larger than end
        // the case is there is no node (null)
        if (start >= end) {
            return 1;
        }

        int count = 0;

        for (int rootValue = start; rootValue <= end; ++rootValue) {
            int leftTreeCount = count(start, rootValue - 1);
            int rightTreeCount = count(rootValue + 1, end);

            count += leftTreeCount * rightTreeCount;
        }

        return count;
    }




}


