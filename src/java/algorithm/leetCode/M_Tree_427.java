package algorithm.leetCode;

/*
We want to use quad trees to store an N x N boolean grid. Each cell in the grid can only be true or false.
The root node represents the whole grid. For each node,
it will be subdivided into four children nodes until the values in the region it represents are all the same.

Each node has another two boolean attributes : isLeaf and val.
isLeaf is true if and only if the node is a leaf node.
The val attribute for a leaf node contains the value of the region it represents.

Your task is to use a quad tree to represent a given grid.
N is less than 1000 and guaranteed to be a power of 2.

### Example
https://leetcode.com/problems/construct-quad-tree/

*/

public class M_Tree_427 {

    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }

    // DFS, 0ms
    // Time: O(N^2), 0ms
        public Node construct(int[][] grid) {
        final int N = grid.length;
        Node root = new Node();

        build(root, grid, 0, N-1, 0, N-1);

        return root;
    }

    private void build(Node node, int[][] matrix, int startY, int endY, int startX, int endX) {
        if (isLeaf(matrix, startY, endY, startX, endX)) {
            node.isLeaf = true;
            node.val = matrix[startY][startX] == 1;
            return;
        }

        node.isLeaf = false;
        node.val = false;
        node.topLeft = new Node();
        node.topRight = new Node();
        node.bottomLeft = new Node();
        node.bottomRight= new Node();

        int verticalMiddle = startY + (endY - startY) / 2;
        int horizontalMiddle = startX + (endX - startX) / 2;

        build(node.topLeft, matrix, startY, verticalMiddle, startX, horizontalMiddle);
        build(node.topRight, matrix, startY, verticalMiddle, horizontalMiddle + 1, endX);
        build(node.bottomLeft, matrix, verticalMiddle + 1, endY, startX, horizontalMiddle);
        build(node.bottomRight, matrix, verticalMiddle + 1, endY, horizontalMiddle + 1, endX);
    }

    private boolean isLeaf(int[][] matrix, int startY, int endY, int startX, int endX) {
        int val =  matrix[startY][startX];

        for (int y = startY; y <= endY; ++y ) {
            for (int x = startX; x <= endX; ++x ) {
                if (matrix[y][x] != val) {
                    return false;
                }
            }
        }

        return true;
    }
}

