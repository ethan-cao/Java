package algorithm.course.assignment.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    public static void main(String[] args) {
        Percolation p = new Percolation(4);

        p.open(1, 2);
        p.open(1, 4);
        p.open(2, 1);
        p.open(2, 2);
        p.open(2, 3);
        p.open(3, 3);
        p.open(4, 1);
        p.open(4, 3);

        System.out.println(p.numberOfOpenSites());
        System.out.println(p.percolates());
    }

    private int size;
    private WeightedQuickUnionUF wquf;
    private boolean[] openSites;

    // wquf2 only connects to virtual top for fixing backwash
    private WeightedQuickUnionUF wquf2;

    public Percolation(int n) {
        validate(new int[]{n});

        this.size = n;
        this.wquf = new WeightedQuickUnionUF(n * n + 2);
        this.wquf2 = new WeightedQuickUnionUF(n * n + 2);
        this.openSites = new boolean[n * n + 2];
    }

    private void validate(int[] index) {
        boolean validity = true;

        if (index.length == 1) {
            validity = index[0] > 0;
        } else if (index.length == 2) {
            for (int i : index) {
                validity = (i > 0 && i <= size);
                if (!validity) {
                    break;
                }
            }
        }

        if (!validity) {
            throw new IllegalArgumentException();
        }
    }

    private int getIndex(int row, int col) {
        return (row - 1) * this.size + col;
    }

    public void open(int row, int col) {
        validate(new int[]{row, col});

        if (this.isOpen(row, col)) {
            return;
        }

        int index = this.getIndex(row, col);
        this.openSites[index] = true;

        if (row == 1) {
            wquf.union(0, index);
            wquf2.union(0, index);
        }

        if (row == this.size) {
            wquf.union(this.size * this.size + 1, index);
        }

        if (row > 1 && this.isOpen(row - 1, col)) {
            int topIndex = this.getIndex(row - 1, col);
            wquf.union(topIndex, index);
            wquf2.union(topIndex, index);
        }

        if (row < this.size && this.isOpen(row + 1, col)) {
            int bottomIndex = this.getIndex(row + 1, col);
            wquf.union(bottomIndex, index);
            wquf2.union(bottomIndex, index);
        }

        if (col > 1 && this.isOpen(row, col - 1)) {
            int leftIndex = this.getIndex(row, col - 1);
            wquf.union(leftIndex, index);
            wquf2.union(leftIndex, index);
        }

        if (col < this.size && this.isOpen(row, col + 1)) {
            int rightIndex = this.getIndex(row, col + 1);
            wquf.union(rightIndex, index);
            wquf2.union(rightIndex, index);
        }
    }

    public boolean isOpen(int row, int col) {
        validate(new int[]{row, col});
        return this.openSites[this.getIndex(row, col)];
    }

    public boolean isFull(int row, int col) {
        validate(new int[]{row, col});
        return wquf2.connected(0, this.getIndex(row, col));
    }

    public int numberOfOpenSites() {
        int numberOfOpenSites = 0;
        for (int i = 1; i < this.openSites.length; ++i) {
            if (openSites[i]) {
                numberOfOpenSites++;
            }
        }
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return wquf.connected(0, this.openSites.length - 1);
    }
}