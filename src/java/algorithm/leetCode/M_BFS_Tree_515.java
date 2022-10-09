package algorithm.leetCode;

/*
You need to find the largest value in each row of a binary tree.

### Example
          1
         / \
        3   2
       / \   \
      5   3   9   -> [1, 3, 9]

*/

import java.util.*;

public class M_BFS_Tree_515 {

    public class TreeNode {
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

    // BFS, 2ms
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> largestValues = new ArrayList<>();

        if (root == null) {
            return largestValues;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int largestValueInLevel = Integer.MIN_VALUE;

            for (int i = 0; i < size; ++i) {
                TreeNode current = queue.poll();
                largestValueInLevel = Math.max(largestValueInLevel, current.val);

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            largestValues.add(largestValueInLevel);
        }

        return largestValues;
    }

    // 1ms
    // BFS, recursive
    public List<Integer> largestValues1(TreeNode root) {
        List<Integer> largestValues = new ArrayList<>();

        visit(root, 0, largestValues);

        return largestValues;
    }

    private void visit(TreeNode node, int depth, List<Integer> largestValues) {
        if (node == null) {
            return;
        }

        if (depth == largestValues.size()) {
            largestValues.add(node.val); // 1st enter new level
        } else {
            largestValues.set(depth, Math.max(largestValues.get(depth), node.val));
        }

        visit(node.left, depth + 1, largestValues);
        visit(node.right, depth + 1, largestValues);
    }
}
