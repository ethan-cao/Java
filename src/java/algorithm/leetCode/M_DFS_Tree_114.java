package algorithm.leetCode;

/*
Given a binary tree, flatten it to a linked list in-place.

binary tree -> in-place preorder

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

    public static void flatten(TreeNode root) {
        flattenTree(root, null);
    }

    // 0ms
    // nextNode is the next node for node in a pre-order traversal
    // https://www.bilibili.com/video/av49260017?from=search&seid=1709576483152248579
    private static TreeNode flattenTree(TreeNode node, TreeNode nextNode) {
        if (node == null) {
            return nextNode;
        }

        // flatten right sub tree first
        // since each node's right child points to the next node in a pre-order traversal
        nextNode = flattenTree(node.right, nextNode);
        nextNode = flattenTree(node.left, nextNode);

        node.right = nextNode;
        node.left = null;

        nextNode = node;

        return nextNode;
    }

    // 0ms
    public void flatten1(TreeNode root) {
        TreeNode node = root;

        while (node != null) {

            if (node.left != null) {
                TreeNode last = node.left;

                while (last.right != null) {
                    last = last.right;
                }

                last.right = node.right;

                node.right = node.left;
                node.left = null;
            }

            node = node.right;
        }
    }

}
