package pl.edu.wat.wcy.cop.decisionsupport.merger;

import java.awt.*;
// Creates point instances.

public class PointFactory {
    public static Point createPoint(int x, int y) {
        return new Point(x, y) {
            private static final long serialVersionUID = 1L;

            @Override
            public String toString() {
                return "(" + this.x + ", " + this.y + ")";
            }
        };
    }
}
