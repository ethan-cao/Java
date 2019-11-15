package algorithm.leetCode;

/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

### Example
Given binary tree [3,9,20,null,null,15,7],

Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7

return its level order traversal as:
[ [3],
  [20, 9],
  [15,7] ]

*/

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class M_Stack_Tree_103 {

    public static void main(String... args) {
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // BFS
    // Time O(), Space: O()
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> traversal = new ArrayList<>();

        if (root == null) {
            return traversal;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        boolean toggle = true;

        while (!queue.isEmpty()) {
            List<Integer> levelTraversal = new ArrayList<>();
            int size = queue.size();

            for (int i = 0; i < size; ++i) {
                TreeNode currentNode = queue.poll();

                if (toggle) {
                    // left to right
                    levelTraversal.add(currentNode.val);
                } else {
                    // right to left
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

            toggle = !toggle;
        }

        return traversal;
    }

    // Stack
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

                if (currentNode.left != null) nextLevelStack.push(currentNode.left);
                if (currentNode.right != null) nextLevelStack.push(currentNode.right);
            }
            // it is certain that levelTraversal is not empty
            traversal.add(levelTraversal);

            levelTraversal = new ArrayList<>();
            while (!nextLevelStack.isEmpty()) {
                TreeNode currentNode = nextLevelStack.pop();
                levelTraversal.add(currentNode.val);

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