
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.image.renderable.RenderableImage;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

// a set of points in the unit square (all points have x- and y-coordinates between 0 and 1)
// Brute-force implementation, using a red–black BST
public class PointSET {
    private final Set<Point2D> points = new TreeSet<>();

    public PointSET() {
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
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
        for (Point2D p : points) {
            StdDraw.point(p.x(), p.y());
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }

        if (this.isEmpty()) {
            return null;
        }

        Set<Point2D> pointsInRange = new HashSet<>();

        Point2D leftBottom = new Point2D(rect.xmin(), rect.ymin());
        Point2D rightTop = new Point2D(rect.xmax(), rect.ymax());

        for (Point2D point : this.points) {

            if (leftBottom.compareTo(point) <= 0 && rightTop.compareTo(point) >= 0) {
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

        double minDistance = 1;
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

    public static void main(String[] args) {

    }
}
