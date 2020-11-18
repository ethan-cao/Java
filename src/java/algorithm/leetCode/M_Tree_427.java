package algorithm.leetCode;

/*
We want to use quad trees to store an N x N boolean grid. Each cell in the grid can only be true or false.
The root node represents the whole grid. For each node,
it will be subdivided into four children nodes until the values in the region it represents are all the same.

Each node has another two boolean attributes : isLeaf and val.
isLeaf is true if and only if the node is a leaf node.
The val attribute for a leaf node contains the value of the region it represents.

Your task is to use a quad tree to represent a given grid.
N is less than 1000 and guaranteened to be a power of 2.

### Example
https://leetcode.com/problems/construct-quad-tree/

*/

public class M_Tree_427 {

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }

    // Time: O(N^2), 0ms
    public Node construct(int[][] grid) {
        return constructSubTree(0, 0, grid.length, grid);
    }

    private Node constructSubTree(int x, int y, int N, int[][] grid) {
        boolean value = false;
        boolean isLeft = false;
        Node topLeft = null;
        Node topRight = null;
        Node bottomLeft = null;
        Node bottomRight = null;

        int identicalValue = getIdenticalValue(x, y, N, grid);

        if (identicalValue == -1) {
            topLeft = constructSubTree(x, y, N / 2, grid);
            topRight = constructSubTree(x, y + N / 2, N / 2, grid);
            bottomLeft = constructSubTree(x + N / 2, y, N / 2, grid);
            bottomRight = constructSubTree(x + N / 2, y + N / 2, N / 2, grid);
        } else {
            isLeft = true;
            value = identicalValue == 1;
        }

        return new Node(value, isLeft, topLeft, topRight, bottomLeft, bottomRight);
    }

    private int getIdenticalValue(int x, int y, int N, int[][] grid) {
        int identicalValue = grid[x][y];

        for (int i = x; i < x + N; ++i) {
            for (int j = y; j < y + N; ++j) {
                if (grid[i][j] != identicalValue) {
                    return -1; // since grid contains only 1 and 0, return -1 if there is different value
                }
            }
        }

        return identicalValue;
    }

}

