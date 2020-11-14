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

    // Time: O(N), 1ms
    public static TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        // node value -> postorder idx, since value is unique. for O(N) lookup later
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < postorder.length; ++i) {
            map.put(postorder[i], i);
        }

        return build(0, preorder.length - 1, preorder, 0, postorder.length - 1, postorder, map);
    }

    // build by preorder
    // [root][......left......][...right..]  ---pre
    // [......left......][...right..][root]  ---post
    private static TreeNode build(int preStart, int preEnd, int[] preorder, int postStart, int postEnd, int[] postorder, Map<Integer, Integer> map) {
        if (preStart > preEnd || postStart > postEnd) {
            return null;
        }

        TreeNode node = new TreeNode(preorder[preStart]);

        // need this check, since we reply on node.left, which is preorder[preStart + 1], to narrow the scope
        // once preStart == preEnd, node is the leaf, stop recursion
        if (preStart == preEnd || postStart == postEnd) {
            return node;
        }

        int leftPostIdx = map.get(preorder[preStart + 1]); // find where left (of node) is located in postorder[]
        int length = leftPostIdx - postStart; // length is the same for both preorder and inorder

        node.left = build(preStart + 1, preStart + 1 + length, preorder, postStart, postStart + length, postorder, map);
        node.right = build(preStart + 1 + length + 1, preEnd, preorder, postStart + length + 1, postEnd - 1, postorder, map);

        return node;
    }

    // 1ms
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);


        return root;
    }

}
