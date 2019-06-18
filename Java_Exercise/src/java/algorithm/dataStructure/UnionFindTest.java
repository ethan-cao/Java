package algorithm.dataStructure;

import java.util.Arrays;

/**
 * UnionFind is a data structure that tracks a set of elements partitioned into a number of disjoint (non-overlapping) subsets.
 */

public class UnionFindTest {

    public static void main(String[] args) {
        UnionFind uf = new QuickUnion(10);
//        UnionFind uf = new WeightedQuickUnion(10);

        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 4);
//        uf.union(2,1);
//        uf.union(5,0);
//        uf.union(7,2);
//        uf.union(6,1);
//        uf.union(7,3);

        System.out.println(uf);
//        System.out.println(uf.isConnected(8,9));
    }
}

abstract class UnionFind {
    protected int[] data;

    UnionFind(int n) {
        data = new int[n];
        for (int i = 0; i < n; ++i) {
            data[i] = i;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    /**
     * connect p and q
     *
     * @param p position of the 1st number
     * @param q position of the 2nd number
     */
    abstract void union(int p, int q);

    /**
     * @return if p and q is connected
     */
    abstract boolean isConnected(int p, int q);
}

class QuickFind extends UnionFind {
    QuickFind(int n) {
        super(n);
        // data[i] is the bucket this element is in
    }

    // 2N + 2 times array access
    void union(int p, int q) {
        int bucketP = data[p];
        int bucketQ = data[q];

        // any one with bucketQ need to be moved to bucketQ
        for (int i = 0; i < data.length; i++) {
            if (data[i] == bucketQ) {
                data[i] = bucketP;
            }
        }
    }

    boolean isConnected(int p, int q) {
        // if p and q are in the same entry, they are connected
        return data[p] == data[q];
    }
}

class QuickUnion extends UnionFind {
    QuickUnion(int n) {
        super(n);
        // data[i] is the parent of i
    }

    void union(int p, int q) {
        data[getRoot(p)] = data[getRoot(q)];
    }

    boolean isConnected(int p, int q) {
        return getRoot(p) == getRoot(q);
    }

    protected int getRoot(int idx) {
        int root = data[idx];

        while (root != data[root]) {
            root = data[root];
        }

        return root;
    }
}

/**
 * optimization for QuickUnion
 *
 * Link the root of smaller tree to the root of larger tree
 * TODO : use height of tree to improve
 */
class WeightedQuickUnion extends QuickUnion {
    // number of elements in the tree rooted at i
    private int[] size;

    WeightedQuickUnion(int n) {
        super(n);
        size = new int[n];
        Arrays.fill(size, 1);
    }

    @Override
    void union(int p, int q) {
        int rootP = getRoot(p);
        int rootQ = getRoot(q);

        if (rootP == rootQ) {
            // do nothing
        } else if (size[rootP] >= size[rootQ]) {
            data[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            data[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
    }
}

/**
 * optimization for WeightedQuickUnion
 */
class PathCompressionWeightedQuickUnion extends WeightedQuickUnion {
    PathCompressionWeightedQuickUnion(int n) {
        super(n);
    }

    @Override
    protected int getRoot(int idx) {
        int root = data[idx];

        while (root != data[root]) {
            root = data[root];
            data[idx] = root; // make each one pointing to its grandparent, having the path length
        }

        return root;
    }
}