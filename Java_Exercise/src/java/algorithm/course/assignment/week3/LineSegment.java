package algorithm.course.assignment.week3;

public class LineSegment {
    private final Point p;
    private final Point q;

    public LineSegment(Point p, Point q) {
        if (p == null || q == null) {
            throw new NullPointerException("argument is null");
        }

        this.p = p;
        this.q = q;
    }

    public void draw() {
        this.p.drawTo(this.q);
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return p + " -> " + q;
    }
}
