package algorithm.leetCode;

/*
Given an integer n, return all the structurally unique BST's (binary search trees), 
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

public class M_DP_Tree_95 {

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
        System.out.println(generateTrees0(3));
    }

    // DP, recursive
    // 1ms
    public static List<TreeNode> generateTrees0(int n) {
        return buildTrees(1, n);
    }

    // build structurally unique BST, with values [start, end]
    private static List<TreeNode> buildTrees(int start, int end) {
        List<TreeNode> rootNodes = new ArrayList<>();

        // !!! use start > end
        // when start == end, the node with value start can be added
        if (start > end) {
            // !!! use null as child, because in the example, null is used for empty child node
            rootNodes.add(null);
            return rootNodes;
        }

        for (int rootValue = start; rootValue <= end; ++rootValue) {
            List<TreeNode> leftNodes = buildTrees(start, rootValue - 1);
            List<TreeNode> rightNodes = buildTrees(rootValue + 1, end);

            for (TreeNode leftNode : leftNodes) {
                for (TreeNode rightNode : rightNodes) {
                    TreeNode root = new TreeNode(rootValue, leftNode, rightNode);
                    rootNodes.add(root);
                }
            }
        }

        return rootNodes;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DP, iterative, 2ms
    // https://bit.ly/36EKgvy
    public List<TreeNode> generateTrees(int n) {
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