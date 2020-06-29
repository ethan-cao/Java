package algorithm.leetCode;

/*
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

LCA on Wikipedia:
The lowest common ancestor is defined between two nodes p and q
as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself)

All of the nodes' values will be unique.
p and q are different and both values will exist in the binary tree.

### Example
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

*/

public class M_Tree_236 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // https://www.youtube.com/watch?v=13m9ZCB8gjw
    // Time: O(N), Space: O(N), 4ms
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root == p || root == q) {
            return root;
        }

        // start from root, and looks for p,
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // p and q exist on each side of the root, then root is LCA
        if (left != null && right != null) {
            return root;
        }

        // since we know p and q exist in root, either in left or right, so return the non-null one
        return left != null ? left : right;
    }

}
