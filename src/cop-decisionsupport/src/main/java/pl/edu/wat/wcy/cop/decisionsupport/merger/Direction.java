package pl.edu.wat.wcy.cop.decisionsupport.merger;

import java.awt.*;
// Enumerates direction.

public enum Direction {
    TOP(-1, 0), BOTTOM(1, 0), RIGHT(0, 1), LEFT(0, -1);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point move(Point point) {
        return PointFactory.createPoint(point.x + this.x, point.y + this.y);
    }
}
