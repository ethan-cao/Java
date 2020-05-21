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

    // 3ms
    // BFS, in addition remember the first element of each level.
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

    // 0ms
    // BFS, recursive
    public int findBottomLeftValue1(TreeNode root) {
        int[] result = new int[]{0, 0};  // [leftMostValueInLevel, depth]

        findBottomLeftValue(root, 1, result);

        return result[0];
    }


    public void findBottomLeftValue(TreeNode node, int depth, int[] result) {
        if (node == null) {
            return;
        }

        // 1st time enter a new level
        if (depth > result[1]) {
            result[0] = node.val;
            result[1] = depth;
        }

        findBottomLeftValue(node.left, depth + 1, result);
        findBottomLeftValue(node.right, depth + 1, result);
    }

}
