package algorithm.leetCode;

/*
Given two binary trees, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical
and the nodes have the same value.

### Example
Input:     1         1
          / \       / \
         2   3     2   3
Output: true

Input:     1         1
          / \       / \
         2   1     1   2
Output: false

*/
public class E_Tree_100 {

    static class TreeNode {
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
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);

        n1.left = n2;
        n1.right = n3;
    }

    // 0ms
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }

        // Both nodes have values; descend iff those values are equal
        // "&&" here allows for any difference to overrule a local equality
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}

