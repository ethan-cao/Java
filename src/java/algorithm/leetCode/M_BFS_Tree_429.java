package algorithm.leetCode;

/*
Given an n-ary tree, return the level order traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal,
each group of children is separated by the null value (See examples).

The height of the n-ary tree is less than or equal to 1000
The total number of nodes is between [0, 10^4]

### Example
root = [1,null,3,2,4,null,5,6]
-> [[1],[3,2,4],[5,6]]

root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
-> [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]

*/

import java.util.*;

public class M_BFS_Tree_429 {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // 2ms
    public List<List<Integer>> BFS(Node root) {
        List<List<Integer>> traversal = new ArrayList<>();

        if (root == null) {
            return traversal;
        }

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelNodeCount = queue.size();
            List<Integer> levelTraversal = new ArrayList<>();

            for (int i = 0; i < levelNodeCount; ++i) {
                Node current = queue.poll();
                levelTraversal.add(current.val);

                for (Node child: current.children) {
                    queue.offer(child);
                }
            }

            traversal.add(levelTraversal);
        }

        return traversal;
    }

    // 0ms
    // Time O(), Space: O()
    public List<List<Integer>> BFS1(Node root) {
        List<List<Integer>> traversal = new ArrayList<>();

        visit(root, 0, traversal);

        return traversal;
    }

    private void visit(Node node, int depth, List<List<Integer>> traversal) {
        if (node == null) {
            return;
        }

        if (depth == traversal.size()) {
            traversal.add(new ArrayList<>());
        }

        traversal.get(depth).add(node.val);

        for (Node child : node.children) {
            visit(child, depth + 1, traversal);
        }
    }

}