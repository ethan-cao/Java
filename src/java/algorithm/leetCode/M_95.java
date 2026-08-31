package algorithm.leetCode;

/*
https://leetcode.com/problems/unique-binary-search-roots-ii/

Given an integer n, return all the structurally unique BST's (binary search roots), 
which has exactly n nodes of unique values from 1 to n. Return the answer in any order.

### Example
3 -> 5

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

*/

import java.util.*;

public class M_95 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String... args) {
        System.out.println(generateTrees(3));
    }



    //----------------------------------------------------------------------------------------------
    // DP, recursive
    // 1ms
    public static List<TreeNode> generateTrees(int n) {
        return collect(1, n);
    }

    // build structurally unique BST, with values [start, end]
    private static List<TreeNode> collect(int start, int end) {
        List<TreeNode> roots = new ArrayList<>();

        // !!! when start > end, it is empty sub-tree, we count it as 1
        // this is when root is the first number
        // for instance [1,2]
        // possible BST:
        //     1        2
        //    / \      / \
        // null  2    1  null
        // 
        // when root is 1
        // left = buildTree(1, 0)   → empty subtree: null, → base case
        // right = buildTree(2, 2)  → single node subtree: 2 → base case


        if (start > end) {
            roots.add(null);
            return roots;
        }

        if (start == end) {
            TreeNode node = new TreeNode(start);
            roots.add(node);
            return roots;
        }
        
        for (int val = start; val <= end; ++val) {
             List<TreeNode> leftNodes = collect(start, val - 1);
             List<TreeNode> rightNodes = collect(val + 1, end );

            for (TreeNode leftNode : leftNodes) {
                for (TreeNode rightNode : rightNodes) {
                    TreeNode root = new TreeNode(val, leftNode, rightNode);
                    roots.add(root);
                }
            }
        }

        return roots;
    }


    //----------------------------------------------------------------------------------------------
    // DP, iterative, 2ms
    // https://bit.ly/36EKgvy
    public List<TreeNode> collect1(int n) {
        ArrayList<TreeNode>[] roots = new ArrayList[n + 1];
        roots[0] = new ArrayList<TreeNode>();
        roots[0].add(null);

        for (int i = 1; i <= n; i++) {
            roots[i] = new ArrayList<TreeNode>();

            for (int rootValue = 1; rootValue <= i; ++rootValue) {
                int leftRange = rootValue - 1;
                int rightRange = i - rootValue;

                for (TreeNode leftTree : roots[leftRange]) {
                    for (TreeNode rightTree : roots[rightRange]) {

                        TreeNode root = new TreeNode(rootValue);
                        root.left = leftTree;
                        root.right = clone(rightTree, rootValue);

                        roots[i].add(root);
                    }
                }
            }
        }

        return roots[n];
    }

    private TreeNode clone(TreeNode node, int offset) {
        if (node == null) {
            return null;
        }

        TreeNode root = new TreeNode(node.val + offset);
        root.left = clone(node.left, offset);
        root.right = clone(node.right, offset);

        return root;
    }
}