package algorithm.unionFind;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.*;

public class PercolationSimulation {
    public static void main(String[] args){
        int n  = 3;

        int size = n * n + 2;     //  2 is the virtual spot on top and bottom
        WeightedQuickUnion wqu = new WeightedQuickUnion(size);

        for (int i = 0; i < size; i++) {
            wqu.data[i] = i;
        }

        // Connecting to virtual top
        for(int i = 1; i <= n; ++i){
            wqu.union(0, i);
        }

        // Connecting to virtual bottom
        for(int i = size - 2 ; i >= size - 1 - n ; --i){
            wqu.union(wqu.data[size-1], i);
        }

        int numberOfOpensite = 0;
        Queue<Integer> q = getRandomGenerator(size);
        while (!wqu.isConnected(0, size-1)) {
            int j = q.poll();
            List<Integer> surroundings = getSurrendings(j, size);
            numberOfOpensite++;
        }


        double p = numberOfOpensite / (n*n);
        System.out.println(p);

    }

    /**
     *  get the next random index, deduplication
     * @param  size
     * @return queue
     */
    static Queue<Integer> getRandomGenerator(int size){
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < size - 1; ++i){
            list.add(i);
        }

        Collections.shuffle(list);

        Queue<Integer> queue = new ArrayDeque<>();
        for(Integer i: list){
            queue.add(i);
        }

        return queue;
    }

    static List<Integer> getSurrendings(int index, int size){
        List list = new ArrayList();
        int n = (int) Math.sqrt(size - 2);

        int mod = index % n;

        int left = index + 1;
        if ( left> 0 && left< size - 1) list.add(left);

        int right = index + 1;
        if ( right > 0 && right < size - 1) list.add(right);

        int top = index - n;
        if ( top > 0 && top < size - 1) list.add(top);

        int bottom = index + n;
        if (bottom > 0 && bottom < size -1) list.add(bottom);

        if ( 1 == mod){
            // left most column
            list.remove(left);
        } else if ( 0 == mod){
            // right most column
            list.remove(right);
        }

        return list;
    }

}

class PercolationStats {
    public PercolationStats(int n, int trials)  {

    }
    // sample mean of percolation threshold

    public double mean(){
        return 1;
    }
    // sample standard deviation of percolation threshold

    public double stddev() {
        return 1;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo()               {
        return 1;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()              {
        return 1;
    }

    public static void main(String[] args)       {

    }
}

class Percolation {
    public static void main(String[] args)  {
    }

    private int size;
    private WeightedQuickUnionUF wquf;
    private int numberOfOpenSites = 0;

    private boolean validate(int[] index){
        boolean validity = true;

        if (index.length == 1) {
            validity = index[0] > 0 ;
        } else if (index.length == 2) {
            for (int i : index){
               validity = (i >= 1 && i <= size);
               if (!validity){
                   break;
               }
            }
        }

        return validity;
    }

    public Percolation(int n){
        validate(new int[]{n});

        size = n;
        wquf = new WeightedQuickUnionUF(n*n);
    }

    // open site (row, col) if it is not open already
    public    void open(int row, int col)   {
        validate(new int[]{row, col});

//        wquf.
        this.numberOfOpenSites++;
    }

    public boolean isOpen(int row, int col) {
        validate(new int[]{row, col});

        return true;
    }

    public boolean isFull(int row, int col)  {
        validate(new int[]{row, col});
        return true;
    }

    public     int numberOfOpenSites()       {
        return this.numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates()            {
        return true;
    }

}

