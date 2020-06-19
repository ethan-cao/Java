package algorithm.leetCode;

/*
Given preorder and inorder traversal of a tree, construct the binary tree.
You may assume that duplicates do not exist in the tree.

### Example
preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
->
    3
   / \
  9  20
    /  \
   15   7

Related: 889
*/

import java.util.ArrayDeque;
import java.util.Deque;

public class M_DFS_Tree_105 {

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

    // 3ms
    // https://www.youtube.com/watch?v=S1wNG5hx-30
    public static TreeNode build(int[] preorder, int[] inorder) {
        return build(0, 0, inorder.length - 1, preorder, inorder);
    }

    public static TreeNode build(int preorderStart, int inorderStart, int inorderEnd, int[] preorder, int[] inorder) {
        if (preorderStart > preorder.length - 1 || inorderStart > inorderEnd) {
            return null;
        }

        TreeNode node = new TreeNode(preorder[preorderStart]);

        int index = 0; // Index of current node in inorder
        for (int i = inorderStart; i <= inorderEnd; i++) {
            if (inorder[i] == node.val) {
                index = i;
                break;
            }
        }

        node.left = build(preorderStart + 1, inorderStart, index - 1, preorder, inorder);
        node.right = build(preorderStart + index - inorderStart + 1, index + 1, inorderEnd, preorder, inorder);

        return node;
    }

    // 1ms
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;

        for (int i = 1, j = 0; i < preorder.length; i++) {
            if (current.val != inorder[j]) {
                current.left = new TreeNode(preorder[i]);
                stack.push(current);
                current = current.left;
            } else {
                j++;
                while (!stack.isEmpty() && stack.peek().val == inorder[j]) {
                    current = stack.pop();
                    j++;
                }

                current = current.right = new TreeNode(preorder[i]);
            }
        }

        return root;
    }


}
