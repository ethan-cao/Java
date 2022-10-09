package algorithm.leetCode;

/*
You are given a binary tree in which each node contains an integer value.
Find the number of paths that sum to a given value.
The path does not need to start or end at the root or a leaf,
but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

### Example
root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1
3 paths: 5 -> 3, 5 -> 2 -> 1, -3 -> 11

*/

import java.util.HashMap;

public class M_Recursion_Tree_437 {

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

    // Time: O(N), 2ms
    // Recursion, prefix sum,  https://en.wikipedia.org/wiki/Prefix_sum
    public static int pathSum1(TreeNode root, int sum) {
        // map: prefix sum from the root to the current node in recursion -> how many times the sum occurs
        HashMap<Integer, Integer> prefixSums = new HashMap<>();
        prefixSums.put(0, 1);  // prefix sum 0 occurs once before the root

        return count(root, 0, sum, prefixSums);
    }

    // prefix is counted from the top(root) to the bottom(leaves), the count for path is calculated from the bottom to the top
    private static int count(TreeNode node, int sum, int target, HashMap<Integer, Integer> prefixSums) {
        if (node == null) {
            return 0;
        }

        // sum from the root to the current node
        sum += node.val;

        // The sum from any node in the path to the current node
        // equals to
        // the difference between (the sum from the root to the current node) and (the prefix sum of the node in the path)

        // assume there is a node in the path, from which (exclusive) to the current node the sum is target
        // so sum - target is the prefix sum of the node from root
        // if key for (sum - target) exists, then this node does exist and the count is the count of path

        // the number of valid paths ended by the current node
        int count = prefixSums.getOrDefault(sum - target, 0);

        prefixSums.put(sum, prefixSums.getOrDefault(sum, 0) + 1);

        // the number of valid paths in the subtree rooted at the current node's left child
        // the number of valid paths in the subtree rooted at the current node's right child
        count += count(node.left, sum, target, prefixSums) + count(node.right, sum, target, prefixSums);

        // when reach here for the first time, the node is a leaf
        // Since recursion counts from leaf, need to delete the current pathSum in the preSum
        // Then, in higher layers, we can forget everything about this node (and its descendants).
        prefixSums.put(sum, prefixSums.get(sum) - 1);

        return count;
    }

    // 20ms, dual recursion, O(N^2) for the worst case, O(NlogN) for balanced binary Tree.
    // count path from the given node, excluding the node
    public static int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        // count starting from the node + count starting from its left child + count starting from its right child
        return getPathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    // count path from the given node, including the node
    private static int getPathSumFrom(TreeNode node, int target) {
        int pathCount = 0;

        if (node == null) {
            return pathCount;
        }

        if (node.val == target) {
            pathCount++;
        }

        pathCount += getPathSumFrom(node.left, target - node.val) + getPathSumFrom(node.right, target - node.val);

        return pathCount;
    }
}
