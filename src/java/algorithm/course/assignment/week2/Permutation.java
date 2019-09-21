package algorithm.course.assignment.week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// http://coursera.cs.princeton.edu/algs4/assignments/queues.html
public class Permutation {
    public static void main(String[] args) {
        if (args == null) {
            throw new IllegalArgumentException();
        }

        RandomizedQueue<String> rq = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }

        int k = Integer.parseInt(args[0]);
        for (int i = 0; i < k; ++i) {
            StdOut.println(rq.dequeue());
        }
    }
}
