package algorithm.course.assignment.week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// http://coursera.cs.princeton.edu/algs4/assignments/collinear.html
public class FastCollinearPoints {
    private List<Point[]> segmentPoints = new ArrayList<>();
    private Point[] points;
    private LineSegment[] segments;


    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
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

    // the number of line segments
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
                    this.addSegment(points.get(0), points.get(points.size() - 1));
                }

                slope = currentSlope;
                points.clear();
                points.add(point);
            }

            // check the last loop
            if (points.size() > 2) {
                points.add(origin);
                Collections.sort(points);
                this.addSegment(points.get(0), points.get(points.size() - 1));
            }
        }

        this.segments = new LineSegment[this.segmentPoints.size()];

        for (int i = 0; i < this.segments.length; ++i) {
            Point[] segmentPoint = this.segmentPoints.get(i);
            this.segments[i] = new LineSegment(segmentPoint[0], segmentPoint[1]);
        }

        return this.segments.clone();
    }

    private void addSegment(Point first, Point last) {
        double slope1 = first.slopeTo(last);
        boolean isDuplicate = false;

        for (Point[] points : this.segmentPoints) {
            double slope2 = points[0].slopeTo(points[1]);

            if (slope1 == slope2) {
                if (points[0].slopeTo(first) == slope1 ||
                        points[1].slopeTo(first) == slope1 ||
                        points[0].slopeTo(last) == slope1 ||
                        points[1].slopeTo(last) == slope1) {

                    isDuplicate = true;

                    Point[] newPoints = new Point[]{first, last, points[0], points[1]};
                    Arrays.sort(newPoints);
                    points[0] = newPoints[0];
                    points[1] = newPoints[3];

                    break;
                }
            }
        }

        if (!isDuplicate) {
            this.segmentPoints.add(new Point[]{first, last});
        }
    }

    public static void main(String[] args) {
//        Point[] points = {
//                new Point(19000, 10000),
//                new Point(18000, 10000),
//                new Point(32000, 10000),
//                new Point(21000, 10000),
//                new Point(1234, 5678),
//                new Point(14000, 10000),
//        };


//        Point[] points = {
//                new Point(1, 1),
//                new Point(2, 2),
//                new Point(3, 3),
//                new Point(4, 4),
//                new Point(5, 5),
//                new Point(0, 5),
//                new Point(1, 5),
//                new Point(2, 5),
//                new Point(3, 5),
//                new Point(4, 5),
//                new Point(5, 0),
//                new Point(5, 1),
//                new Point(5, 2),
//                new Point(5, 3),
//                new Point(5, 4)
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

        // print and draw the line segments
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }

        StdDraw.show();
    }
}
