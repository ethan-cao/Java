package algorithm.unionFind;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.util.ArrayList;
import java.util.List;

class PercolationStats {
    private List<Double> thresholdList = new ArrayList<>();
    private double[] thresholds;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        int lastIndex = n * n;

        for (int i = 0; i < trials; ++i) {
            Percolation p = new Percolation(n);

            while(!p.percolates()){
                int row = StdRandom.uniform(1, lastIndex + 1);
                int column = StdRandom.uniform(1, lastIndex + 1);
                p.open(row, column);
            }

            double numberOfSite = p.numberOfOpenSites();
            double threshold = numberOfSite / (n * n);
            thresholdList.add(threshold);

            if (p.numberOfOpenSites() > lastIndex){
                System.out.println("open sites : " + p.numberOfOpenSites());
            }

            System.out.println("open sites : " + p.numberOfOpenSites());
            System.out.println("threshold : " + threshold);
        }

        thresholds = thresholdList.stream().mapToDouble(d -> d).toArray();
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
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
        args = new String[] {"200", "100"};

        if (args.length < 2){
            throw new IllegalArgumentException();
        }

        int n = Integer.valueOf(args[0]);
        int trials = Integer.valueOf(args[1]);

        PercolationStats ps = new PercolationStats(n, trials);
        System.out.println("mean : " + ps.mean());
        System.out.println("stddev: " + ps.stddev());
    }
}
