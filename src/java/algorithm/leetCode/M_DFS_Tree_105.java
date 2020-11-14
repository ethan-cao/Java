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

*/

import java.util.*;

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

    // Time: O(N), 1ms
    // https://www.youtube.com/watch?v=S1wNG5hx-30
    public static TreeNode build(int[] preorder, int[] inorder) {
        // value -> idx in inorder[], since value is unique, for O(N) lookup
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; ++i) {
            map.put(inorder[i], i);
        }

        return build(0, preorder.length - 1, preorder, 0, inorder.length - 1, inorder, map);
    }

    // build by pre-order
    // [root][......left......][...right..]  ---pre
    // [......left......][root][...right..]  ---in
    public static TreeNode build(int preStart, int preEnd, int[] preorder, int inStart, int inEnd, int[] inorder, Map<Integer, Integer> map) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        TreeNode node = new TreeNode(preorder[preStart]);

        // this check is not necessary, since we only reply on node
        if (preStart == preEnd || inStart == inEnd) {
            return node;
        }

        int inIdx = map.get(node.val); // find where node is located in inorder[]
        int length = inIdx - inStart; // length is the same for both preorder and inorder

        node.left =  build(preStart + 1, preStart + length, preorder, inStart, inIdx - 1, inorder, map);
        node.right = build(preStart + length + 1, preEnd, preorder, inIdx + 1, inEnd, inorder, map);

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
