import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class BruteCollinearPoints {
    private Point[] points;

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

//        Point[] points = {
//                new Point(19000, 10000),
//                new Point(18000, 10000),
//                new Point(32000, 10000),
//                new Point(21000, 10000),
//                new Point(1234, 5678),
//                new Point(14000, 10000),
//        };

//        Point[] points = {
//                new Point(10000, 0),
//                new Point(0, 10000),
//                new Point(3000, 7000),
//                new Point(7000, 3000),
//                new Point(20000, 21000),
//                new Point(3000, 4000),
//                new Point(14000, 15000),
//                new Point(6000, 7000),
//        };

        System.out.println("@@@ number of points in " + points.length);

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);

        System.out.println("@@@ find : " + collinear.segments().length);

        // print and draw the line segments
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    private static int getFactorial(int n) {
        if (n < 0) {
            throw new UnsupportedOperationException();
        }

        int factorial = 1;
        for (int i = 1; i <= n; ++i) {
            factorial *= i;
        }

        return factorial;
    }

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }

        this.points = new Point[points.length];
        System.arraycopy(points, 0, this.points, 0, points.length);
    }

    public int numberOfSegments() {
        final int L = this.points.length;

        if (L < 2) {
            return 0;
        }

        return getFactorial(L) / (getFactorial(L - 2) * 2);  //  C(L, 2)
    }

    public LineSegment[] segments() {
        final int L = this.points.length;

        if (L < 4) {
            return new LineSegment[]{};
        }

        final List<LineSegment> lineSegments = new ArrayList<>();

        for (int i = 0; i < L; ++i) {
            Point p1 = this.points[i];

            for (int j = i + 1; j < L; ++j) {
                Point p2 = this.points[j];

                for (int k = j + 1; k < L; ++k) {
                    Point p3 = this.points[k];

                    for (int l = k + 1; l < L; ++l) {
                        Point p4 = this.points[l];

                        double slope = p1.slopeTo(p2);
                        if (slope == p2.slopeTo(p3) && p2.slopeTo(p3) == p3.slopeTo(p4)) {

                            Point[] points = {p1, p2, p3, p4};
                            Arrays.sort(points);
                            lineSegments.add(new LineSegment(points[0], points[points.length - 1]));
                        }
                    }
                }
            }
        }

        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }
}
