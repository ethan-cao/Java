package algorithm.unionFind;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.ArrayList;
import java.util.List;

public class Percolation {
    public static void main(String[] args) {
        Percolation p = new Percolation(4);
        p.open(1,1);
        p.open(2,1);
        p.open(2,2);
        p.open(3,2);
//        p.open(3,3);
        p.open(4,3);

        System.out.println(p.percolates());
        System.out.println(p.numberOfOpenSites());
    }

    private int size;
    private List<Integer> openSites = new ArrayList<>();
    private WeightedQuickUnionUF wquf;

    public Percolation(int n) {
        if( !validate(new int[]{n})) {
            return;
        }

        size = n ;
        wquf = new WeightedQuickUnionUF(n * n + 2);

        int virtualTop = 0;
        int vitualBottom = n * n + 1;

        for (int i = 1; i <= n; ++i){
            wquf.union(virtualTop, i);
        }

        for (int i = n * n; i >= n * n - n + 1; --i){
            wquf.union(vitualBottom, i);
        }
    }

    private boolean validate(int[] index) {
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

        return validity;
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

        if (this.isOpen(row, col)){
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
