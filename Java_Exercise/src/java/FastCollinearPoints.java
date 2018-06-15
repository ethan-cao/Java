import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private Point[] points;

    public static void main(String[] args) {
//        Point[] points = {
//                new Point(19000, 10000),
//                new Point(18000, 10000),
//                new Point(32000, 10000),
//                new Point(21000, 10000),
//                new Point(1234, 5678),
//                new Point(14000, 10000),
//        };
//
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

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        System.out.println("@@@ number of points :" + points.length);

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        FastCollinearPoints collinear = new FastCollinearPoints(points);

        System.out.println("@@@ find : " + collinear.segments().length);

        // print and draw the line segments
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }

        StdDraw.show();
    }

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
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

    // the number of line segments
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
            Point origin = this.points[i];

            Point[] auxArray = new Point[L - i];
            System.arraycopy(this.points, i, auxArray, 0, L - i);

            // Sort the points according to the slopes they makes with origin
            Arrays.sort(auxArray, origin.slopeOrder());

            List<Point> points = new ArrayList<>();
            points.add(auxArray[0]);
            double slope = auxArray[0].slopeTo(origin);

            for (int j = 1; j < L - i; ++j) {
                Point point = auxArray[j];

                double currentSlope = point.slopeTo(origin);
                if (currentSlope == slope) {
                    points.add(point);
                    continue;
                }

                if (points.size() > 2) {
                    points.add(origin);
                    Collections.sort(points);
                    lineSegments.add(new LineSegment(points.get(0), points.get(points.size() - 1)));
                }

                slope = currentSlope;
                points.clear();
                points.add(point);
            }

            // check the last loop
            if (points.size() > 2) {
                points.add(origin);
                Collections.sort(points);
                lineSegments.add(new LineSegment(points.get(0), points.get(points.size() - 1)));
            }
        }

        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }
}
