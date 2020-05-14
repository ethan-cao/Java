package algorithm.dataStructure;

import java.util.*;

public class N_aryTree {

    class Node {
        private int value;
        private List<Node> children;

        Node(int value) {
            this.value = value;
        }
    }

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
                levelTraversal.add(current.value);

                for (Node child : current.children) {
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

        traversal.get(depth).add(node.value);

        for (Node child : node.children) {
            visit(child, depth + 1, traversal);
        }
    }

}
