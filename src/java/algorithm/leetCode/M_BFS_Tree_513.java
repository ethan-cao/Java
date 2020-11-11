package algorithm.leetCode;

/*
Given a binary tree, find the leftmost value in the last row of the tree.
You may assume the tree (i.e., the given root node) is not NULL.

### Example
    2
   / \
  1   3
-> 1

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7
-> 7

*/

import java.util.*;

public class M_BFS_Tree_513 {

    public class TreeNode {
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

    // BFS, 3ms, in addition remember the first element of each level.
    public int findBottomLeftValue(TreeNode root) {
        int leftMostValue = root.val;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; ++i) {
                TreeNode current = queue.poll();

                if (i == 0) {
                    leftMostValue = current.val;
                }

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }

        return leftMostValue;
    }

    // DFS, pre-order, 0ms
    public int findBottomLeftValue1(TreeNode root) {
        // [depth, leftMostValue]
        // use -1 as the initial depth since we start from 0
        int[] result = new int[]{-1, -1};

        findBottomLeftValue(root, 0, result);

        return result[1];
    }

    public void findBottomLeftValue(TreeNode node, int depth, int[] result) {
        if (node == null) {
            return;
        }

        // 1st time enter a new level, it is the left most node
        if (depth > result[0]) {
            result[0] = depth;
            result[1] = node.val;
        }

        findBottomLeftValue(node.left, depth + 1, result);
        findBottomLeftValue(node.right, depth + 1, result);
    }

}
