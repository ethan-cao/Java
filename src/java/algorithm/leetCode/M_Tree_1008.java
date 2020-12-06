package algorithm.leetCode;

/*
Return the root node of a binary search tree that matches the given preorder traversal.
for the given test cases there is always possible to find a binary search tree with the given requirements.

1 <= preorder.length <= 100
1 <= preorder[i] <= 10^8
The values of preorder are distinct

#Example
Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]

*/

import java.util.*;

public class M_Tree_1008 {

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

    // Binary Search, 0ms
    // Time: O(N^2)
    public TreeNode bstFromPreorder0(int[] preorder) {
        return buildBST0(preorder, 0, preorder.length - 1);
    }

    private TreeNode buildBST0(int[] preorder, int left, int right) {
        if (left > right) {
            return null;
        }

        int val = preorder[left];
        TreeNode node = new TreeNode(val);

        int i = left; // index for node.right
        while (i < preorder.length) {
            if (preorder[i] > val) {
                break;
            }
            i++;
        }

        node.left = buildBST0(preorder, left + 1, i - 1);
        node.right = buildBST0(preorder, i, right);

        return node;
    }

    // Binary Search, valueRange, 0ms
    // Time: O(N)
    private int idx;

    public TreeNode bstFromPreorder(int[] preorder) {
        idx = 0;

        return buildBST(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode buildBST(int[] preorder, int left, int right) {
        if (idx == preorder.length || preorder[idx] < left || preorder[idx] > right) {
            return null;
        }

        int val = preorder[idx];
        idx++;

        TreeNode node = new TreeNode(val);

        node.left = buildBST(preorder, left, val);
        node.right = buildBST(preorder, val, right);

        return node;
    }

    // Stack
    // Time: O(N)
    public TreeNode bstFromPreorder2(int[] preorder) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode root = new TreeNode(preorder[0]);

        stack.push(root);
        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = new TreeNode(preorder[i]);

            if (preorder[i] < stack.peek().val) {
                stack.peek().left = node;
            } else {
                TreeNode parent = stack.peek();
                while (!stack.isEmpty() && preorder[i] > stack.peek().val) {
                    parent = stack.pop();
                }
                parent.right = node;
            }
            stack.push(node);
        }

        return root;
    }

}