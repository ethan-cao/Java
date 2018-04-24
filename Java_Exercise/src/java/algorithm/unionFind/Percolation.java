package algorithm.unionFind;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    public static void main(String[] args) {
        Percolation p = new Percolation(4);

        p.open(1,2);
        p.open(1,4);
        p.open(2,1);
        p.open(2,2);
        p.open(2,3);
        p.open(3,3);
        p.open(4,1);
        p.open(4,3);

        p.isFull(4, 1);
        p.isFull(4, 3);

        System.out.println(p.numberOfOpenSites());
        System.out.println(p.percolates());
    }

    private int size;
    private int[] openSites;
    private WeightedQuickUnionUF wquf;
    private boolean[] lastRow;

    public Percolation(int n) {
        if( !validate(new int[]{n})) {
            return;
        }

        size = n ;
        openSites = new int[n*n];
        wquf = new WeightedQuickUnionUF(n * n + 2);
        lastRow = new boolean[n*2];

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
       return (index > this.size * (row-1)) && (index <= this.size * row);
    }

    private boolean isInSecondLastRow(int index){
       return index >= this.openSites.length - this.size * 2 + 1 && index<= this.openSites.length - this.size;
    }

    private boolean isInFirstLastRow(int index){
        return index >= this.openSites.length - this.size + 1 && index<= this.openSites.length;
    }

    public void open(int row, int col) {
        if (!validate(new int[]{row, col})){
            return;
        }

        if (this.isOpen(row, col)){
            return;
        }

        int index = this.getIndex(row, col);
        this.openSites[index -1] = index ;

        int topIndex = index - this.size;
        if ( this.isOpen(topIndex) ){
           wquf.union(index, topIndex);
        }

        int bottomIndex = index + this.size;
        if ( this.isOpen(bottomIndex)){
            wquf.union(index, bottomIndex);
        }

        int leftIndex = index - 1;
        if ( this.isOpen(leftIndex) && isInTheRow(row, leftIndex)){
            wquf.union(index, leftIndex);
        }

        int rightIndex = index + 1;
        if ( this.isOpen(rightIndex) && isInTheRow(row, rightIndex)) {
            wquf.union(index, rightIndex);
        }

        // fix backwash
        if ( this.isInSecondLastRow(index) ) {
            int idx = index % this.size;
            idx = idx == 0 ? this.size - 1 : idx - 1;

            if (this.isOpen(index + this.size)){
                this.lastRow[idx] = true;

                // check towards left
                leftIndex = index + this.size - 1;
                while (this.isInTheRow(this.size, leftIndex) && this.isOpen(leftIndex)){
                    lastRow[idx-1] = true;
                    leftIndex -= 1;
                }

                // check towards right
                rightIndex = index + this.size + 1;
                while (this.isInTheRow(this.size, rightIndex) && this.isOpen(rightIndex)){
                    lastRow[idx+1] = true;
                    rightIndex += 1;
                }
            }
        }
    }

    private boolean isOpen(int idx){
        int index = idx - 1;

        return index >=0
                && index < openSites.length
                && openSites[index] == idx;
    }

    public boolean isOpen(int row, int col) {
        int index = this.getIndex(row, col);
        return this.openSites[index-1] == index;
    }

    public boolean isFull(int row, int col) {
        int index = this.getIndex(row, col);

        if (this.isInFirstLastRow(index)){
            int idx = index % this.size;
            idx = idx == 0 ? this.size - 1 : idx - 1;
            return lastRow[idx];
        } else {
            return this.isOpen(row, col) && wquf.connected(0, index);
        }
    }

    public int numberOfOpenSites() {
        int numberOfOpenSites = 0;

        // 0 is the virtual top, n*n + 1 is the virtual bottom
        for(int i = 0; i < this.openSites.length ; ++i){
            if (openSites[i] == i+1){
                numberOfOpenSites++;
            }
        }

        return numberOfOpenSites;
    }

    public boolean percolates() {
        return wquf.connected(0, this.openSites.length +1);
    }
}
