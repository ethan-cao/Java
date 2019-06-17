package  algorithm.course.assignment.week3;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    final private int x;
    final private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point p) {
        StdDraw.line(this.x, this.y, p.x, p.y);
    }

    public double slopeTo(Point p) {
        double deltaY = p.y - this.y;
        double deltaX = p.x - this.x;
        double slope;

        if (deltaX == 0 && deltaY == 0) {
            slope = Double.NEGATIVE_INFINITY;
        } else if (deltaY == 0) {
            slope = +0;
        } else if (deltaX == 0) {
            slope = Double.POSITIVE_INFINITY;
        } else {
            slope = deltaY / deltaX;
        }

        return slope;
    }

    public Comparator<Point> slopeOrder() {
        return (Point p1, Point p2) -> {
            return Double.compare(this.slopeTo(p1), this.slopeTo(p2));
        };
    }

    @Override
    public int compareTo(Point p) {
        if (this.x == p.x && this.y == p.y) {
            return 0;
        } else if (this.y < p.y || (this.y == p.y && this.x < p.x)) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}