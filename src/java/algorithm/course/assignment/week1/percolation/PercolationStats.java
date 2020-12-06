package algorithm.course.assignment.week1.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] thresholds;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        this.thresholds = new double[trials];

        for (int i = 0; i < trials; ++i) {
            Percolation p = new Percolation(n);

            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int column = StdRandom.uniform(1, n + 1);
                p.open(row, column);
            }

            double numberOfSite = p.numberOfOpenSites();
            double threshold = numberOfSite / (n * n);
            thresholds[i] = threshold;
        }
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
        return this.mean() -  1.96 * this.stddev() / Math.sqrt(this.thresholds.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() +  1.96 * this.stddev() / Math.sqrt(this.thresholds.length);
    }

    public static void main(String[] args) {
        if (args.length < 2){
            throw new IllegalArgumentException();
        }

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(n, trials);

        System.out.println("mean                     = " + ps.mean());
        System.out.println("stddev                   = " + ps.stddev());
        System.out.println("95% confidence interval  = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}
