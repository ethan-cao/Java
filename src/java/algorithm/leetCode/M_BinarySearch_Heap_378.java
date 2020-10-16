package algorithm.leetCode;

/*
Given a n x n matrix where each of the rows and columns are sorted in ascending order,
find the kth smallest element in the matrix.
Note that it is the kth smallest element in the sorted order, not the kth distinct element.
You may assume k is always valid, 1 ≤ k ≤ n^2.

### Example
matrix =
[  [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15] ] ,
k = 8,
return 13.

*/

import java.util.PriorityQueue;

public class M_BinarySearch_Heap_378 {

    // binary search
    // 0ms
    public int kthSmallest(int[][] matrix, int k) {
        final int N = matrix.length;
        int left = matrix[0][0];
        int right = matrix[N-1][N-1];

        while (left < right) {
            int middle = left + (right - left) / 2;
            int smallEqualValueCount = count(matrix, middle);

            if (smallEqualValueCount < k) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return left;
    }

    private int count(int[][] matrix, int middle) {
        int count = 0;
        final int N = matrix.length;

        for(int row = 0, col = N -1; row < N; ++row){

            while(col >= 0 && matrix[row][col] > middle) {
                col--;
            }

            count += col + 1;
        }

        return count;

    }


    // min heap
    // time: O(min(K,N) + K∗logN), 13ms
    public int kthSmallest1(int[][] matrix, int k) {
        final int N = matrix.length;

        PriorityQueue<Node> minHeap = new PriorityQueue<>((n1, n2) -> n1.value - n2.value);

        // values on the 1st column(row) are smaller than others, so fill heap with values from 1st column(row)
        // no need to fill more than K values
        for (int row = 0; row < N && row < k; ++row) {
            Node node = new Node(row, 0, matrix[row][0]);
            minHeap.offer(node);
        }

        // k <= N^2, k = min(k, N^2)
        for (int i = 0; i < k - 1; ++i) {
            // poll (k - 1) value, so in the end return the kth value
            Node node = minHeap.poll();

            if (node.col == N - 1) {
                continue; // continue to the next column
            }

            // stay in the same column
            Node newNode = new Node(node.row, node.col + 1, matrix[node.row][node.col + 1]);
            minHeap.offer(newNode);
        }

        return minHeap.poll().value;
    }

    class Node {
        final int row;
        final int col;
        final int value;

        public Node(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

}