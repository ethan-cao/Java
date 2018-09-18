import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;


// a set of points in the unit square (all points have x- and y-coordinates between 0 and 1)
// 2d-tree implementation
public class KdTree {

    private class Node<T extends Comparable<T>> {
        private T value;
        private Node<T> left;
        private Node<T> right;

        // Each node corresponds to an axis-aligned rectangle in the unit square, which encloses all of the points in its subtree
        private RectHV rect;

        private int size = 1;
        private int depth = 0;

        private Node(T value) {
            this.value = value;
        }

        private boolean isVerticalSplit() {
            return this.depth % 2 == 0;
        }
    }

    private Node<Point2D> root;

    public KdTree() {
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int size() {
        return this.isEmpty() ? 0 : this.size(this.root);
    }

    private int size(Node<Point2D> node) {
        return node == null ? 0 : this.size(node.left) + this.size(node.right) + 1;
    }

    public void insert(Point2D p) {
        this.root = this.insert(p, this.root, null);
    }

    /**
     * Insert point to subtree which has node as root, node's parent node is parentNode
     *
     * @return the updated subtree
     */
    private Node<Point2D> insert(Point2D point, Node<Point2D> node, Node<Point2D> parentNode) {
        if (node == null) {
            Node<Point2D> newNode = new Node<>(point);

            if (parentNode == null) {
                newNode.depth = 0;
                newNode.rect = new RectHV(0, 0, 1, 1);
            } else {
                newNode.depth = parentNode.depth + 1;

                RectHV rect;
                RectHV parentRect = parentNode.rect;
                int cmp = newNode.isVerticalSplit() ? Point2D.Y_ORDER.compare(point, parentNode.value) : Point2D.X_ORDER.compare(point, parentNode.value);

                if (cmp < 0) {
                    if (parentNode.isVerticalSplit()) {
                        rect = new RectHV(parentRect.xmin(), parentRect.ymin(), parentNode.value.x(), parentRect.ymax());
                    } else {
                        rect = new RectHV(parentRect.xmin(), parentRect.ymin(), parentRect.xmax(), parentNode.value.y());
                    }
                } else {
                    if (parentNode.isVerticalSplit()) {
                        rect = new RectHV(parentNode.value.x(), parentRect.ymin(), parentRect.xmax(), parentRect.ymax());
                    } else {
                        rect = new RectHV(parentRect.xmin(), parentNode.value.y(), parentRect.xmax(), parentRect.ymax());
                    }
                }

                newNode.rect = rect;
            }

//            System.out.println("Add point : " + point + " rect : " + newNode.rect + " depth : " + newNode.depth);
            return newNode;
        }

        int cmp = node.isVerticalSplit() ? Point2D.X_ORDER.compare(point, node.value) : Point2D.Y_ORDER.compare(point, node.value);
        if (cmp < 0) {
            node.left = this.insert(point, node.left, node);
        } else {
            node.right = this.insert(point, node.right, node);
        }

        node.size = this.size(node);

        return node;
    }

    public boolean contains(Point2D p) {
        return this.contains(p, this.root);
    }

    private boolean contains(Point2D p, Node<Point2D> node) {
        if (node == null) {
            return false;
        }

        int cmp = p.compareTo(node.value);

        if (cmp > 0) {
            return this.contains(p, node.right);
        } else if (cmp < 0) {
            return this.contains(p, node.left);
        } else {
            return true;
        }
    }

    public void draw() {
        this.draw(this.root, null);
    }

    private void draw(Node<Point2D> node, Node<Point2D> parentNode) {
        if (node == null) {
            return;
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(node.value.x(), node.value.y());
        StdDraw.setPenRadius();

        if (parentNode == null) {
            // Draw root
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.value.x(), 0, node.value.x(), 1);
        } else if (node.isVerticalSplit()) {
            StdDraw.setPenColor(StdDraw.RED);

            double x = node.value.x();
            double y1 = node.rect.ymin();
            double y2 = node.rect.ymax();
            StdDraw.line(x, y1, x, y2);
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);

            double x1 = node.rect.xmin();
            double x2 = node.rect.xmax();
            double y = node.value.y();
            StdDraw.line(x1, y, x2, y);
        }

        this.draw(node.left, node);
        this.draw(node.right, node);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }

        List<Point2D> results = new ArrayList<>();

        if (this.isEmpty()) {
            return results;
        }

        this.range(this.root, rect, results);

        return results;
    }

    private void range(Node<Point2D> node, RectHV rect, List<Point2D> results) {
        if (node == null) {
            return;
        }

        if (rect.contains(node.value)) {
            results.add(node.value);
        }

        // search the node iff its corresponding rect has intersection with query rect
        if (node.left != null && node.left.rect.intersects(rect)) {
            this.range(node.left, rect, results);
        }

        if (node.right != null && node.right.rect.intersects(rect)) {
            this.range(node.right, rect, results);
        }
    }

    public Point2D nearest(Point2D query) {
        if (query == null) {
            throw new IllegalArgumentException();
        }

        if (this.isEmpty()) {
            return null;
        }

        Deque<Point2D> nearestPoints = new ArrayDeque<>();
        nearestPoints.add(this.root.value);

        this.nearest(this.root, query, nearestPoints);

        return nearestPoints.peekLast();
    }

    private void nearest(Node<Point2D> node, Point2D query, Deque<Point2D> nearestPoints) {
        if (node == null) {
            return;
        }

//        System.out.println("@@@ : " + node.value);

        double currentMinDistance = nearestPoints.peekLast().distanceSquaredTo(query);
        double distanceToNode = node.value.distanceSquaredTo(query);

        if (distanceToNode < currentMinDistance) {
            nearestPoints.push(node.value);
            currentMinDistance = distanceToNode;
        }

        double distanceToLeft = node.left == null ? Double.POSITIVE_INFINITY : node.left.rect.distanceSquaredTo(query);
        double distanceToRight = node.right == null ? Double.POSITIVE_INFINITY : node.right.rect.distanceSquaredTo(query);

        if (currentMinDistance > distanceToLeft && currentMinDistance > distanceToRight) {
            if (node.isVerticalSplit()) {
                if (node.value.x() - query.x() > 0) {
                    this.nearest(node.left, query, nearestPoints);
                    this.nearest(node.right, query, nearestPoints);
                } else {
                    this.nearest(node.right, query, nearestPoints);
                    this.nearest(node.left, query, nearestPoints);
                }
            } else {
                if (node.value.y() - query.y() > 0) {
                    this.nearest(node.left, query, nearestPoints);
                    this.nearest(node.right, query, nearestPoints);
                } else {
                    this.nearest(node.right, query, nearestPoints);
                    this.nearest(node.left, query, nearestPoints);
                }
            }
        } else if (currentMinDistance > distanceToLeft) {
            this.nearest(node.left, query, nearestPoints);
        } else if (currentMinDistance > distanceToRight) {
            this.nearest(node.right, query, nearestPoints);
        }
    }

    public static void main(String[] args) {
        List<Point2D> points = new ArrayList<>();
        points.add(new Point2D(0.206107, 0.095492)); // A
        points.add(new Point2D(0.975528, 0.654508)); // B
        points.add(new Point2D(0.024472, 0.345492)); // C
        points.add(new Point2D(0.793893, 0.095492)); // D
        points.add(new Point2D(0.793893, 0.904508)); // E
        points.add(new Point2D(0.975528, 0.345492)); // F
        points.add(new Point2D(0.206107, 0.904508)); // G
        points.add(new Point2D(0.500000, 0.000000)); // H
        points.add(new Point2D(0.024472, 0.654508)); // I
        points.add(new Point2D(0.500000, 1.000000)); // J

        KdTree kdtree = new KdTree();
        StdDraw.enableDoubleBuffering();

        for (Point2D point : points) {
            kdtree.insert(point);
            StdDraw.clear();
            kdtree.draw();
            StdDraw.show();
        }

        System.out.println(kdtree.size());
        System.out.println(kdtree.nearest(new Point2D(0.81, 0.30)));
    }
}