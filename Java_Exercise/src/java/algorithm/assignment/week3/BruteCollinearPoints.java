package algorithm.assignment.week3;

import java.util.HashMap;
import java.util.HashSet;

public class BruteCollinearPoints {
    Point[] points;

    public static void main(String[] args) {
        Point[] points = {new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4)};

        BruteCollinearPoints bcp = new BruteCollinearPoints(points);

        System.out.println(bcp.numberOfSegments());

        bcp.segments();
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

        HashMap<HashSet<Point>, Double> slopes = new HashMap<>();
        HashSet<Point> segment = new HashSet<>();

        for (int i = 0; i < L; ++i){
            Point p1 = this.points[i];
            segment.clear();
            segment.add(p1);
            for (int j = 0; j < L ; ++j){
                if (i != j){
                    Point p2 = this.points[j];
                    segment.add(p2);

                    slopes.put(segment, p1.slopeTo(p2));
                }
            }

        }


        return null;
    }
}


