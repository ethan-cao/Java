package algorithm.leetCode;

/*
Given a n x n matrix where each of the rows and columns are sorted in ascending order,
find the kth smallest element in the matrix.
Note that it is the kth smallest element in the sorted order, not the kth distinct element.
You may assume k is always valid, 1 ≤ k ≤ n^2.

### Example
k = 8
matrix =
[  [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]  ]
-> 13

*/

import java.util.*;

public class M_BinarySearch_Heap_378 {

    // Binary search, 1ms
    public int kthSmallest(int[][] matrix, int k) {
        final int N = matrix.length;

        int left = matrix[0][0];
        int right = matrix[N - 1][N - 1];

        // search in value range [left, right]

        while (left <= right) {
            // !!! middle might not exist as a valid candidate
            // we need to track [smallest number greater than the middle, largest number smaller than the middle]
            // these 2 numbers are from matrix, so we can derive result from them
            int middle = left + (right - left) / 2;
            int[] smallLargePair = {matrix[0][0], matrix[N - 1][N - 1]};

            int smallEqualCount = count(matrix, middle, smallLargePair);

            if (smallEqualCount == k) {
                return smallLargePair[0];
            }

            if (smallEqualCount > k) {
                right = smallLargePair[0];
            } else {
                left = smallLargePair[1];
            }

            // !!! normally when include == case, expect left == right == middle and middle is a possible result
            // but in this question, we are not checking the case left == right == middle to return result
            // so add the check left == right to return the right result
            // otherwise, when include == case, [[1,2],[1,3]] becomes dead loop
            if (left == right) {
                return left;
            }
        }

        return left;
    }

    private int count(int[][] matrix, int target, int[] smallerLargerPair) {
        int count = 0;

        final int N = matrix.length;

        // from left-bottom or right-top can count how much numbers are less equal than target
        // since from these 2 positions, we can move up/right or left/down to get smaller or larger element
        for (int y = 0; y < N; ++y) {
            for (int x = N - 1; x >= 0; ) {
                int num = matrix[y][x];

                if (num <= target) {
                    count += (x + 1);
                    smallerLargerPair[0] = Math.max(smallerLargerPair[0], num);
                    break;
                } else {
                    smallerLargerPair[1] = Math.min(smallerLargerPair[1], num);
                    x--;
                }
            }
        }

        return count;
    }

    // min heap
    // Time: O(min(K,N) + K∗logN), 13ms
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