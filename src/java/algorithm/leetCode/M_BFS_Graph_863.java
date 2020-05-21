package algorithm.leetCode;

/*
We are given a binary tree (with root node root), a target node, and an integer value K.
Return a list of the values of all nodes that have a distance K from the target node.
The answer can be returned in any order.

The given tree is non-empty.
Each node in the tree has unique values 0 <= node.val <= 500.
The target node is a node in the tree.
0 <= K <= 1000.

### Example
https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
-> [7,4,1]
Explanation: nodes that are a distance 2 from the target node have values 7, 4, and 1

*/

import java.util.*;

public class M_BFS_Graph_863 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Graph BFS
    //build a undirected graph using treenodes as vertices, and the parent-child relation as edges
    //do BFS from target to find all vertices with distance K to it.
    public List<Integer> distanceK0(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();

        // https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/discuss/143752/JAVA-Graph-%2B-BFS
//        buildGraph();
//        visit();

        return result;
    }

    // Tree DFS, 10ms
    // Time: O(N), Space: O(N)
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();

        // Node -> its distance to target Node, node is on the path from root to target
        Map<TreeNode, Integer> distances = new HashMap<>();
        int distance = measureDistance(root, target, distances);

        visit(root, target, K, distance, distances, result);

        return result;
    }

    // return distance from node to targetNode
    private int measureDistance(TreeNode node, TreeNode targetNode, Map<TreeNode, Integer> distances) {
        if (node == null) {
            return -1;
        }

        if (node == targetNode) {
            distances.put(node, 0);
            return 0;
        }

        int distanceToLeft = measureDistance(node.left, targetNode, distances);
        if (distanceToLeft >= 0) {
            distances.put(node, distanceToLeft + 1);
            return distanceToLeft + 1;
        }

        int distanceToRight = measureDistance(node.right, targetNode, distances);
        if (distanceToRight >= 0) {
            distances.put(node, distanceToRight + 1);
            return distanceToRight + 1;
        }

        return -1;
    }

    private void visit(TreeNode node, TreeNode targetNode, int K, int distance, Map<TreeNode, Integer> distances, List<Integer> result) {
        if (node == null) {
            return;
        }

        // if the distance from node N to target node is k, the distance from N's child to the target node is k + 1
        // Unless the child node is closer to the target node which means the target node is in it's subtree.
        // To avoid this situation, we need distances from target to nodes in the path from N to target
        if (distances.containsKey(node)) {
            distance = distances.get(node);
        }

        if (distance == K) {
            result.add(node.val);
        }

        visit(node.left, targetNode, K, distance + 1, distances, result);
        visit(node.right, targetNode, K, distance + 1, distances, result);
    }

}
