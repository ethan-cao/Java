package algorithm.course.assignment.week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class BruteCollinearPoints {
    private Point[] points;
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        final int L = points.length;
        this.points = new Point[L];

        for (int i = 0; i < L; ++i) {
            Point point = points[i];

            if (point == null) {
                throw new IllegalArgumentException();
            }

            for (int j = 0; j < i; ++j) {
                if (point.compareTo(this.points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }

            this.points[i] = point;
        }
    }

    public int numberOfSegments() {
        if (this.segments != null) {
            return this.segments.length;
        } else {
            return this.segments().length;
        }
    }

    public LineSegment[] segments() {
        if (this.segments != null) {
            return this.segments.clone();
        }

        final int L = this.points.length;

        if (L < 4) {
            return new LineSegment[]{};
        }


        List<LineSegment> lineSegments = new ArrayList<>();

        for (int i = 0; i < L; ++i) {
            Point p1 = this.points[i];

            for (int j = i + 1; j < L; ++j) {
                Point p2 = this.points[j];

                for (int k = j + 1; k < L; ++k) {
                    Point p3 = this.points[k];

                    if (p1.slopeTo(p2) != p2.slopeTo(p3)) {
                        continue;
                    }

                    for (int l = k + 1; l < L; ++l) {
                        Point p4 = this.points[l];

                        if (p1.slopeTo(p2) == p2.slopeTo(p3) && p2.slopeTo(p3) == p3.slopeTo(p4)) {
                            Point[] points = new Point[]{p1, p2, p3, p4};
                            Arrays.sort(points);
                            lineSegments.add(new LineSegment(points[0], points[points.length - 1]));
                        }
                    }
                }
            }
        }

        this.segments = lineSegments.toArray(new LineSegment[lineSegments.size()]);

        // do not return reference to internal state
        return this.segments.clone();
    }

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
//                new Point(0, 0),
//                new Point(1, 1),
//                new Point(2, 2),
//                new Point(3, 3),
//                new Point(4, 4),
//        };

//        point[] points = {
//                new point(19000, 10000),
//                new point(18000, 10000),
//                new point(10000, 0),
//                new point(0, 10000),
//                new point(3000, 7000),
//                new point(7000, 3000),
//                new point(20000, 21000),
//                new point(3000, 4000),
//                new point(14000, 15000),
//                new point(6000, 7000),
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

}
