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

### Review:
 */


import java.util.*;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class M_Tree_427 {

    public Node construct(int[][] grid) {
        return constructSubTree(0, 0, grid.length, grid);
    }

    // Time: O(N^2)
    private Node constructSubTree(int originX, int originY, int N, int[][] grid) {
        Boolean value = FALSE;
        Boolean isLeft = FALSE;
        Node topLeft = null;
        Node topRight = null;
        Node bottomLeft = null;
        Node bottomRight = null;

        Boolean identicalValue = getIdenticalValue(originX, originY, N, grid);

        if (identicalValue == null) {
            topLeft = constructSubTree(originX, originY, N / 2, grid);
            topRight = constructSubTree(originX, originY + N / 2, N / 2, grid);
            bottomLeft = constructSubTree(originX + N / 2, originY, N / 2, grid);
            bottomRight = constructSubTree(originX + N / 2, originY + N / 2, N / 2, grid);
        } else {
            isLeft = TRUE;
            value = identicalValue;
        }

        return new Node(value, isLeft, topLeft, topRight, bottomLeft, bottomRight);
    }

    // null :  no identical
    // T or F : the identical value
    private Boolean getIdenticalValue(int originX, int originY, int N, int[][] grid) {
        int identicalValue = grid[originX][originY];

        for (int x = originX; x < originX + N; ++x) {
            for (int y = originY; y < originY + N; ++y) {
                if (grid[x][y] != identicalValue) {
                    return null;
                }
            }
        }

        return identicalValue == 1;
    }

}

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
