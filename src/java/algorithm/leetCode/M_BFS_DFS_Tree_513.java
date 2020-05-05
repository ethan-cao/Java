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

import java.util.List;

public class M_BFS_DFS_Tree_513 {

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

    public int findBottomLeftValue(TreeNode root) {
        return 1;
    }
}