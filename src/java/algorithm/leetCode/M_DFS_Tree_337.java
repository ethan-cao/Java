package algorithm.leetCode;

/*
The thief has found himself a new place for his thievery again.
There is only one entrance to this area, called the "root."
Besides the root, each house has one and only one parent house.
After a tour, the smart thief realized that "all houses in this place forms a binary tree".
It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

### Example
[3,2,3,null,3,null,1] -> 7
     3
    / \
   2   3
    \   \
     3   1
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

[3,4,5,1,3,null,1] -> 9
     3
    / \
   4   5
  / \   \
 1   3   1
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.

*/

public class M_DFS_Tree_337 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // DFS, 0ms
    public int rob(TreeNode root) {
        int[] value = visit(root);

        return Math.max(value[0], value[1]);
    }

    private int[] visit(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] valueLeft = visit(node.left);
        int[] valueRight = visit(node.right);

        // rob this node, then don't rob its children
        int robCurrent = node.val + valueLeft[1] + valueRight[1];
        // skip this node, then either rob or dont rob its children !!!
        int skipCurrent = Math.max(valueLeft[0], valueLeft[1]) + Math.max(valueRight[0], valueRight[1]);

        return new int[]{robCurrent, skipCurrent};
    }

}

