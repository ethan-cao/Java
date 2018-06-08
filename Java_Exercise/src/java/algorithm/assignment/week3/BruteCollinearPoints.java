package algorithm.assignment.week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

public class BruteCollinearPoints {
    Point[] points;

    public static void main(String[] args) {
        // read the n points from a file
//        In in = new In(args[0]);
//        int n = in.readInt();
//        Point[] points = new Point[n];
//        for (int i = 0; i < n; i++) {
//            int x = in.readInt();
//            int y = in.readInt();
//            points[i] = new Point(x, y);
//        }
//
//        System.out.println("@@@ number of points in" + args[0] + "  : " + points.length);

        Point[] points = {
                new Point(19000, 10000),
                new Point(18000, 10000),
                new Point(32000, 10000),
                new Point(21000, 10000),
                new Point(1234, 5678),
                new Point(14000, 10000),
        };

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);

        System.out.println("@@@ find : " + collinear.segments().length);

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    private static void test() {
        Point[] points = {new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4)};

        BruteCollinearPoints bcp = new BruteCollinearPoints(points);

        System.out.println(bcp.numberOfSegments());

        LineSegment[] lineSegments = bcp.segments();

        System.out.println(lineSegments);
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
        this.points = points;
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
            return null;
        }

        HashMap<HashSet<Point>, Double> slope = new HashMap<>();
        HashSet<Point> segment = new HashSet<>();
        for (int i = 0; i < L; ++i) {
            Point p1 = this.points[i];
            segment.clear();
            segment.add(p1);
            for (int j = 0; j < L; ++j) {
                if (i != j) {
                    Point p2 = this.points[j];
                    segment.add(p2);

                    slope.put(segment, p1.slopeTo(p2));
                }
            }
        }

        HashMap<Double, HashSet<Point>> reversedSlop = new HashMap<>();
        for (Map.Entry<HashSet<Point>, Double> entry : slope.entrySet()) {
            Double key = entry.getValue();

            if (reversedSlop.containsKey(key)) {
                HashSet<Point> points = reversedSlop.get(key);
                points.addAll(entry.getKey());
            } else {
                reversedSlop.put(key, (HashSet<Point>) entry.getKey().clone());
            }
        }


        ArrayList<LineSegment> lineSegments = new ArrayList<>();
        for (Map.Entry<Double, HashSet<Point>> entry : reversedSlop.entrySet()) {
            HashSet<Point> value = entry.getValue();

            if (value.size() == 4) {
                TreeSet<Point> points = new TreeSet<>(value);
                lineSegments.add(new LineSegment(points.first(), points.last()));
            }
        }

        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }
}
