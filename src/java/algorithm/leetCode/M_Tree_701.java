package algorithm.leetCode;

/*
You are given the root node of a binary search tree (BST) and a value to insert into the tree.
Return the root of the BST after the insertion. It's guaranteed that the new value doesn't exist in the original BST
Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion.
You can return any of them.

The number of nodes in the tree will be in the range [0, 104].
-108 <= Node.val <= 108
All the values Node.val are unique.
-108 <= val <= 108
It's guaranteed that val does not exist in the original BST.

#Example
Input: root = [4,2,7,1,3], val = 5
Output: [4,2,7,1,3,5]

Input: root = [40,20,60,10,30,50,70], val = 25
Output: [40,20,60,10,30,50,70,null,null,25]

Input: root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
Output: [4,2,7,1,3,5]
*/

public class M_Tree_701 {

    class TreeNode {
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

    // 0ms
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        return sink(root, node);
    }

    private TreeNode sink(TreeNode parent, TreeNode node) {
        if (parent == null) {
            return node;
        }

        if (node.val < parent.val) {
            parent.left = sink(parent.left, node);
        } else {
            parent.right = sink(parent.right, node);
        }

        return parent;
    }

    // 0
    public TreeNode insertIntoBST1(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);

        if (root == null) {
            return node;
        }

        TreeNode previous = null;
        TreeNode current = root;

        while (current != null)  {
            previous = current;
            if (node.val < current.val) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (node.val < previous.val) {
            previous.left = node;
        } else {
            previous.right = node;
        }

        return root;
    }
}