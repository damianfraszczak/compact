package pl.edu.wat.wcy.cop.app.client.ol.curve;
// Represents point for interpolation.

public class PointForInterpolation {

    private double x;
    private double y;

    public PointForInterpolation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static PointForInterpolation add(PointForInterpolation a, PointForInterpolation b) {
        return new PointForInterpolation(a.x + b.x, a.y + b.y);
    }

    public static PointForInterpolation subtract(PointForInterpolation a, PointForInterpolation b) {
        return new PointForInterpolation(a.x - b.x, a.y - b.y);
    }

    public static PointForInterpolation multiply(PointForInterpolation a, double d) {
        return new PointForInterpolation(a.x * d, a.y * d);
    }

    public static PointForInterpolation multiply(double d, PointForInterpolation a) {
        return multiply(a, d);
    }

    public static PointForInterpolation divide(PointForInterpolation a, double d) {
        return new PointForInterpolation(a.x / d, a.y / d);
    }

    public static PointForInterpolation divide(PointForInterpolation a, float f) {
        return divide(a, (double) f);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
