package algorithm.leetCode;

/*
Return any binary tree that matches the given preorder and postorder traversals.
Values in the traversals pre and post are distinct positive integers.

1 <= pre.length == post.length <= 30
pre[] and post[] are both permutations of 1, 2, ..., pre.length.
It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.

### Example
Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]

*/

import java.util.*;

public class M_DFS_Tree_889 {

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

    public static TreeNode build(int[] preorder, int[] inorder) {
        return build(0, 0, inorder.length - 1, preorder, inorder);
    }

    // build in pre-order
    public static TreeNode build(int preorderIdx, int inorderStartIdx, int inorderEndIdx, int[] preorder, int[] inorder) {
        if (preorderIdx > preorder.length - 1 || inorderStartIdx > inorderEndIdx) {
            return null;
        }

        TreeNode node = new TreeNode(preorder[preorderIdx]);

        // find inorderIdx of current node in inorder
        int inorderIdx = 0;
        for (int i = inorderStartIdx; i <= inorderEndIdx; i++) {
            if (inorder[i] == node.val) { // no duplicates
                inorderIdx = i;
                break;
            }
        }

        node.left = build(preorderIdx + 1, inorderStartIdx, inorderIdx - 1, preorder, inorder);
        node.right = build(preorderIdx + inorderIdx - inorderStartIdx + 1, inorderIdx + 1, inorderEndIdx, preorder, inorder);

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
