package algorithm;

public class Line {
    private Point start;
    private Point end;

    public Line(Point start, Point end, boolean isVertical) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return this.start;
    }

    public Point getEnd() {
        return this.end;
    }
}
