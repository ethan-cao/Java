package algorithm.course.assignment.week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

// a set of points in the unit square (all points have x- and y-coordinates between 0 and 1)
// Brute-force implementation, using a red–black BinarySearchTree (TreeSet)
public class PointSET {
    private final Set<Point2D> points = new TreeSet<>();

    public PointSET() {
    }

    public boolean isEmpty() {
        return this.points.isEmpty();
    }

    public int size() {
        return this.points.size();
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        this.points.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        return this.points.contains(p);
    }

    public void draw() {
//        for (Point2D p : points) {
//            StdDraw.point(p.x(), p.y());
//        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }

        List<Point2D> pointsInRange = new ArrayList<>();

        if (this.isEmpty()) {
            return pointsInRange;
        }

        double leftBottomX = rect.xmin();
        double leftBottomY = rect.ymin();
        double rightTopX = rect.xmax();
        double rightTopY = rect.ymax();

        for (Point2D point : this.points) {
            if (leftBottomX <= point.x() && leftBottomY <= point.y() && rightTopX >= point.x() && rightTopY >= point.y()) {
                pointsInRange.add(point);
            }
        }

        return pointsInRange;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        if (this.isEmpty()) {
            return null;
        }

        double minDistance = Double.POSITIVE_INFINITY;
        Point2D nearestPoint = null;

        for (Point2D point : this.points) {
            double distance = p.distanceSquaredTo(point);

            if (distance < minDistance) {
                minDistance = distance;
                nearestPoint = point;
            }
        }

        return nearestPoint;
    }
}