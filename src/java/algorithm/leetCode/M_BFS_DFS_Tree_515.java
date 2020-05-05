package algorithm.leetCode;

/*
You need to find the largest value in each row of a binary tree.

### Example
          1
         / \
        3   2
       / \   \
      5   3   9   -> [1, 3, 9]

*/

import java.util.*;

public class M_BFS_DFS_Tree_515 {

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

    public List<Integer> largestValues(TreeNode root) {
        return null;
    }

}