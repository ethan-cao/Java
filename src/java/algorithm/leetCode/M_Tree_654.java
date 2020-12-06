package algorithm.leetCode;

/*
Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

#Example
 [3,2,1,6,0,5] ->
       6
    /   \
   3     5
    \    /
     2  0
       \
        1
*/

import java.util.*;

public class M_Tree_654 {

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

    // monotonic stack 6ms
    // Time: O(N), each node just enter and pop only one time
    // https://en.wikipedia.org/wiki/Cartesian_tree
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> stack = new ArrayDeque<>();

        for (int num: nums) {
            TreeNode current = new TreeNode(num);

            // keep pop the stack until empty or a larger number, the larger one if exists is parent of current
            // all the smaller from its left side becomes left child
            while(!stack.isEmpty() && stack.peek().val < num) {
                current.left = stack.pop();
            }

            // ATM, all left node of the current in stack is larger than current
            // if the larger one exists, it is parent of current,
            // the larger one is on current's left side, so current becomes its right child
            if (!stack.isEmpty()) {
                stack.peek().right = current;
            }

            // current is placed in desired position

            stack.push(current);
        }

        return stack.isEmpty() ? null : stack.peekLast();
    }

    // Binary Search 1ms
    public TreeNode constructMaximumBinaryTree1(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    public TreeNode buildTree(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        if (left == right) {
            return new TreeNode(nums[left]);
        }

        int maxIdx = findMax(nums, left, right);

        TreeNode node = new TreeNode(nums[maxIdx]);
        node.left = buildTree(nums, left, maxIdx - 1);
        node.right = buildTree(nums, maxIdx + 1, right);

        return node;
    }

    public int findMax(int[] nums, int left, int right) {
        int maxIdx = left;

        for (int i = left; i <= right; i++) {
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        return maxIdx;
    }
}