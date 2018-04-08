package algorithm.unionFind;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.*;

public class PercolationSimulation {
    public static void main(String[] args) {
        int n = 3;

        int size = n * n + 2;     //  2 is the virtual spot on top and bottom
        WeightedQuickUnion wqu = new WeightedQuickUnion(size);

        for (int i = 0; i < size; i++) {
            wqu.data[i] = i;
        }

        // Connecting to virtual top
        for (int i = 1; i <= n; ++i) {
            wqu.union(0, i);
        }

        // Connecting to virtual bottom
        for (int i = size - 2; i >= size - 1 - n; --i) {
            wqu.union(wqu.data[size - 1], i);
        }

        int numberOfOpensite = 0;
        Queue<Integer> q = getRandomGenerator(size);
        while (!wqu.isConnected(0, size - 1)) {
            int j = q.poll();
            List<Integer> surroundings = getSurrendings(j, size);
            numberOfOpensite++;
        }


        double p = numberOfOpensite / (n * n);
        System.out.println(p);

    }

    /**
     * get the next random index, deduplication
     *
     * @param size
     * @return queue
     */
    static Queue<Integer> getRandomGenerator(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < size - 1; ++i) {
            list.add(i);
        }

        Collections.shuffle(list);

        Queue<Integer> queue = new ArrayDeque<>();
        for (Integer i : list) {
            queue.add(i);
        }

        return queue;
    }

    static List<Integer> getSurrendings(int index, int size) {
        List list = new ArrayList();
        int n = (int) Math.sqrt(size - 2);

        int mod = index % n;

        int left = index + 1;
        if (left > 0 && left < size - 1) list.add(left);

        int right = index + 1;
        if (right > 0 && right < size - 1) list.add(right);

        int top = index - n;
        if (top > 0 && top < size - 1) list.add(top);

        int bottom = index + n;
        if (bottom > 0 && bottom < size - 1) list.add(bottom);

        if (1 == mod) {
            // left most column
            list.remove(left);
        } else if (0 == mod) {
            // right most column
            list.remove(right);
        }

        return list;
    }

}

class PercolationStats {
    public PercolationStats(int n, int trials) {

    }

    // sample mean of percolation threshold
    public double mean() {
        return 1;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 1;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return 1;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 1;
    }

    public static void main(String[] args) {
    }
}

class Percolation {
    public static void main(String[] args) {
        Percolation p = new Percolation(2);
        p.open(1,2);
        p.open(2,2);

        System.out.println(p.percolates());
    }

    private int size;
    private List<Integer> openSites = new ArrayList<>();
    private WeightedQuickUnionUF wquf;

    private boolean validate(int[] index) {
        boolean validity = true;

        if (index.length == 1) {
            validity = index[0] >= 1;
        } else if (index.length == 2) {
            for (int i : index) {
                validity = (i >= 1 && i <= size);
                if (!validity) {
                    break;
                }
            }
        }

        return validity;
    }

    public Percolation(int n) {
        if( !validate(new int[]{n})) {
            return;
        }

        size = n ;
        wquf = new WeightedQuickUnionUF(n * n + 2);
    }

    private int getIndex(int row, int col){
        return (row - 1) * this.size + col;
    }

    private boolean isInTheRow(int row, int index){
       return index <= this.size * row;
    }

    public void open(int row, int col) {
        if (!validate(new int[]{row, col})){
            return;
        }

        int index = this.getIndex(row, col);
        this.openSites.add(index);

        int topIndex = index - this.size;
        if (openSites.contains(topIndex)){
           wquf.union(index, topIndex);
        }

        int bottomIndex = index + this.size;
        if (openSites.contains(bottomIndex)){
            wquf.union(index, bottomIndex);
        }

        int leftIndex = index - 1;
        if (openSites.contains(leftIndex) && isInTheRow(row, leftIndex)){
            wquf.union(index, leftIndex);
        }

        int rightIndex = index + 1;
        if (openSites.contains(rightIndex) && isInTheRow(row, rightIndex)) {
            wquf.union(index, rightIndex);
        }

    }

    public boolean isOpen(int row, int col) {
        return this.openSites.contains(this.getIndex(row, col));
    }

    public boolean isFull(int row, int col) {
        return this.isOpen(row, col) && wquf.connected(0, this.getIndex(row, col));
    }

    public int numberOfOpenSites() {
        return this.openSites.size();
    }

    public boolean percolates() {
        return wquf.connected(0, this.size * this.size +1);
    }

}

