package algorithm.leetCode;

/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

### Example
    3
   / \
  9  20
    /  \
   15   7
->
[ [3],
  [20, 9],
  [15,7] ]

*/

import java.util.*;

public class M_Tree_BFS_103 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // BFS, 1ms
    // Time O(), Space: O()
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> traversal = new ArrayList<>();

        if (root == null) {
            return traversal;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        boolean isTowardsRight = true;

        while (!queue.isEmpty()) {
            List<Integer> levelTraversal = new ArrayList<>();
            int size = queue.size();

            for (int i = 0; i < size; ++i) {
                TreeNode currentNode = queue.poll();

                if (isTowardsRight) {
                    levelTraversal.add(currentNode.val);
                } else {
                    levelTraversal.add(0, currentNode.val);
                }

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            traversal.add(levelTraversal);

            isTowardsRight = !isTowardsRight;
        }

        return traversal;
    }

    // 1ms
    // BFS without boolean flag
    public static List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> traversal = new ArrayList<>();

        if (root == null) {
            return traversal;
        }

        Deque<TreeNode> currentLevelStack = new ArrayDeque<>();
        Deque<TreeNode> nextLevelStack = new ArrayDeque<>();

        currentLevelStack.push(root);

        // process 2 levels at a time
        while (!currentLevelStack.isEmpty()) {

            List<Integer> levelTraversal = new ArrayList<>();
            while (!currentLevelStack.isEmpty()) {
                TreeNode currentNode = currentLevelStack.pop();
                levelTraversal.add(currentNode.val);

                // left -> right
                if (currentNode.left != null) nextLevelStack.push(currentNode.left);
                if (currentNode.right != null) nextLevelStack.push(currentNode.right);
            }
            // it is certain that levelTraversal is not empty
            traversal.add(levelTraversal);

            levelTraversal = new ArrayList<>(); // cannot use .clear(); the list still points to the same reference
            while (!nextLevelStack.isEmpty()) {
                TreeNode currentNode = nextLevelStack.pop();
                levelTraversal.add(currentNode.val);

                // right -> left
                if (currentNode.right != null) currentLevelStack.push(currentNode.right);
                if (currentNode.left != null) currentLevelStack.push(currentNode.left);
            }
            if (!levelTraversal.isEmpty()) {
                traversal.add(levelTraversal);
            }
        }

        return traversal;
    }

}