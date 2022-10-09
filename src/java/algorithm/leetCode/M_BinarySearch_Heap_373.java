package algorithm.leetCode;

/*
You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
Define a pair (u,v) which consists of one element from the first array and one element from the second array.
Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

### Example
Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence:
             [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]

Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [1,1],[1,1]
Explanation: The first 2 pairs are returned from the sequence:
             [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]

Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [1,3],[2,3]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]

*/

import java.util.*;

public class M_BinarySearch_Heap_373 {

    class Node {
        final int row;
        final int col;
        final int value;

        public Node(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.value = val;
        }
    }

    // min heap, k-way merge sort, check 378
    // time: 3ms
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> pairs = new ArrayList<>();

        final int M = nums1.length;
        final int N = nums2.length;
        PriorityQueue<Node> minHeap = new PriorityQueue<>((n1, n2) -> n1.value - n2.value);

        for (int row = 0; row < M; ++row) {
            Node node = new Node(row, 0, nums1[row] + nums2[0]);
            minHeap.offer(node);
        }

        for (int i = 0; i < k - 1; ++i) {
            Node node = minHeap.poll();
            pairs.add(Arrays.asList(node.row, node.col));

            if (node.row == M - 1) {
                continue;
            }

            Node newNode = new Node(node.row, node.col + 1, nums1[node.row] + nums2[node.col + 1]);
            minHeap.offer(newNode);
        }

        return pairs;
    }
}