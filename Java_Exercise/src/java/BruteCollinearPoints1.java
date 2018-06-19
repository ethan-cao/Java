import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BruteCollinearPoints1 {
    private Point[] points;
    private LineSegment[] segments;

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

        // Test subsegment
//        Point[] points = {
//                new Point(0, 0),
//                new Point(1, 1),
//                new Point(2, 2),
//                new Point(3, 3),
//                new Point(4, 4),
//
//        };

//        Point[] points = {
//                new Point(19000, 10000),
//                new Point(18000, 10000),
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

        BruteCollinearPoints1 collinear = new BruteCollinearPoints1(points);

        System.out.println("@@@ find : " + collinear.segments().length);

        // print and draw the line segments
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    public BruteCollinearPoints1(Point[] points) {
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

    @SuppressWarnings("unchecked")
    public LineSegment[] segments() {
        if (this.segments != null) {
            return this.segments;
        }

        final int L = this.points.length;

        if (L < 4) {
            return new LineSegment[]{};
        }

        List<LineSegment> lineSegments = new ArrayList<>();
        List<Object[]> segmentPoints = new ArrayList<>();

        for (int i = 0; i < L; ++i) {
            Point p1 = this.points[i];

            for (int j = i + 1; j < L; ++j) {
                Point p2 = this.points[j];

                for (int k = j + 1; k < L; ++k) {
                    Point p3 = this.points[k];

                    if (p1.slopeTo(p2) != p2.slopeTo(p3)){
                        continue;
                    }

                    for (int l = k + 1; l < L; ++l) {
                        Point p4 = this.points[l];

                        if (p1.slopeTo(p2) == p2.slopeTo(p3) && p2.slopeTo(p3) == p3.slopeTo(p4)) {
                            Object[] list = new Object[5];
                            list[0] = p1.slopeTo(p2);
                            list[1] = p1;
                            list[2] = p2;
                            list[3] = p3;
                            list[4] = p4;

                            segmentPoints.add(list);
                        }
                    }
                }
            }
        }


        int size = segmentPoints.size();
        List[] subSegmentFreePoints = new List[size];

        for (int i = 0; i < size; ++i) {
            Object[] list1 = segmentPoints.get(i);

            for (int j = i + 1; j < size; ++j) {
                Object[] list2 = segmentPoints.get(j);

                if (list1 != null && list2 != null && list1[0].equals(list2[0])) {
                    if (subSegmentFreePoints[i] == null) {
                        subSegmentFreePoints[i] = new ArrayList();
                    }

                    List<Point> points = new ArrayList<>();
                    points.add((Point) list2[1]);
                    points.add((Point) list2[2]);
                    points.add((Point) list2[3]);
                    points.add((Point) list2[4]);
                    subSegmentFreePoints[i].addAll(points);

                    segmentPoints.set(j, null);
                }
            }

            if (list1 == null) {
                continue;
            }

            List<Point> points = new ArrayList<>();
            points.add((Point) list1[1]);
            points.add((Point) list1[2]);
            points.add((Point) list1[3]);
            points.add((Point) list1[4]);

            if (subSegmentFreePoints[i] != null) {
                subSegmentFreePoints[i].addAll(points);
            } else {
                subSegmentFreePoints[i] = points;
            }
        }


        for (List<Point> points : subSegmentFreePoints) {
            if (points == null) {
                continue;
            }

            Collections.sort(points);

            LineSegment segment = new LineSegment(points.get(0), points.get(points.size() - 1));
            lineSegments.add(segment);
        }

        return this.segments = lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }
}
