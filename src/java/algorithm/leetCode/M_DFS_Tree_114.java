package algorithm.leetCode;

/*
Given a binary tree, flatten it to a linked list in-place.

### Example
    1
   / \
  2   5
 / \   \
3   4   6
->
1
 \
  2
   \
    3
     \
      4  --> node
       \
        5 --> nextNode
         \
          6

*/

public class M_DFS_Tree_114 {

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

    // Time O(N), 0ms
    // https://www.youtube.com/watch?v=Vg74BsgRq8Y
    public void flatten0(TreeNode root) {
        if (root == null) {
            return;
        }

        // flatten left first
        flatten(root.left);
        // flatten right later
        flatten(root.right);

        // after flatten right and left, set root.left to empty, 
        // link root.right to flattened left child and link the end of left child to the flattened right child

        if (root.left == null) {
            return;
        }

        TreeNode rightNode = root.right;

        root.right = root.left;
        root.left = null;

        TreeNode current = root.right;
        while (current.right != null) {
            current = current.right;
        }
        current.right = rightNode;
    }

    // Morris Traversal
    // DFS, essentially flatten a tree to in-place preorder
    // Time O(N), 0msVy
    public static void flatten(TreeNode root) {
        flattenTree(root, null);
    }

    // next is the next node for node in a pre-order traversal
    private static TreeNode flattenTree(TreeNode node, TreeNode next) {
        if (node == null) {
            return next;
        }

        // flatten right tree first, since each node's right child points to next in pre-order traversal
        next = flattenTree(node.right, next);
        next = flattenTree(node.left, next);

        node.right = next;
        node.left = null;

        // 
        next = node;

        return next;
    }

    // 0ms
    public void flatten1(TreeNode root) {
        TreeNode node = root;

        while (node != null) {

            if (node.left != null) {
                TreeNode left = node.left;

                while (left.right != null) {
                    left = left.right;
                }

                left.right = node.right;

                node.right = node.left;
                node.left = null;
            }

            node = node.right;
        }
    }

}
