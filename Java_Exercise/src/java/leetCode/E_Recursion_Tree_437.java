package leetCode;

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

Return 3. The paths that sum to 8 are:

path 1:   5 -> 3
path 2:   5 -> 2 -> 1
path 3:   -3 -> 11

### Condition

### Essential problem

### Corner case

*/

import java.util.HashMap;

public class E_Recursion_Tree_437 {
    public static void main(String... args) {

    }

    // O(N^2) for the worst case
    // O(NlogN) for balanced binary Tree.
    // count path from the given root
    public static int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        return getPathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    // count prefix sum,  https://en.wikipedia.org/wiki/Prefix_sum
    private static int getPathSumFrom(TreeNode node, int target) {
        int pathSum = 0;

        if (node == null) {
            return pathSum;
        }

        if (node.val == target) {
            pathSum++;
        }

        pathSum += getPathSumFrom(node.left, target - node.val) + getPathSumFrom(node.right, target - node.val);

        return pathSum;
    }

    public static int pathSum1(TreeNode root, int sum) {
        // prefixSum stores the
        // prefixSum from the root to the current node in the recursion
        //  and
        // how many times the sum occurs
        // <sum, frequency>, https://en.wikipedia.org/wiki/Prefix_sum
        HashMap<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);  // prefix sum 0 occurs once

        return count(root, 0, sum, prefixSum);
    }

    // prefix is counted from the top(root) to the bottom(leaves), the result of total count is calculated from the bottom to the top
    private static int count(TreeNode node, int currentSum, int target, HashMap<Integer, Integer> prefixSum) {
        if (node == null) {
            return 0;
        }

        currentSum += node.val;

        // The sum from any node in the path to the current node
        // equals to
        // the difference between (the sum from the root to the current node) and (the prefix sum of the node in the path)
        // assume there is a node in the path, if key currentSum - target exists, then this node does exist
        // and from that node til current node, the sum equals to target
        int pathSum = prefixSum.getOrDefault(currentSum - target, 0);

        prefixSum.put(currentSum, prefixSum.getOrDefault(currentSum, 0) + 1);

        pathSum += count(node.left, currentSum, target, prefixSum) + count(node.right, currentSum, target, prefixSum);

        // Since we count from leave in recursion, after each recursion,
        // we just need to delete the current pathsum in the preSum, and leave all other prefix sum in it.
        // Then, in higher layers, we can forget everything about this node (and its descendants).
        prefixSum.put(currentSum, prefixSum.get(currentSum) - 1);

        return pathSum;
    }

}
