package pl.edu.wat.wcy.cop.app.client.ol.curve;

import ol.Coordinate;
import pl.edu.wat.wcy.cop.app.client.ol.OLFeatureBuilder;

import java.util.ArrayList;
import java.util.List;
// Represents bezier curve.

public class BezierCurve {
    private Double[] x;
    private Double[] y;
    private List<Coordinate> Coordinates = new ArrayList<>();
    private int segmentsPerCurve;

    public BezierCurve(Double[] x, Double[] y, int segmentsPerCurve) {
        this.segmentsPerCurve = segmentsPerCurve;
        this.x = x;
        this.y = y;
        GetDrawingCoordinates();
    }

    private void GetDrawingCoordinates() {
        List<Double> drawingCoordinatesX = new ArrayList<>();
        drawCoordinates(drawingCoordinatesX, this.x);
        List<Double> drawingCoordinatesY = new ArrayList<>();
        drawCoordinates(drawingCoordinatesY, this.y);
        for (int i = 0; i < drawingCoordinatesX.size(); i++) {
            Coordinate Coordinate = OLFeatureBuilder.createCoordinate(drawingCoordinatesX.get(i),
                    drawingCoordinatesY.get(i));
            Coordinates.add(Coordinate);
        }
    }

    private void drawCoordinates(List<Double> drawingCoordinates, Double[] data) {
        for (int i = 0; i < data.length - 3; i += 3) {
            Double p0 = data[i];
            Double p1 = data[i + 1];
            Double p2 = data[i + 2];
            Double p3 = data[i + 3];

            if (i == 0) {
                drawingCoordinates.add(calculateCubicBezierCoordinate(0, p0, p1, p2, p3));
            }

            for (int j = 1; j <= segmentsPerCurve; j++) {
                float t = j / (float) segmentsPerCurve;
                drawingCoordinates.add(calculateCubicBezierCoordinate(t, p0, p1, p2, p3));
            }
        }

        int rest = data.length % 3;
        if (rest == 0) {
            Double p0 = data[data.length - 3];
            Double p1 = data[data.length - 2];
            Double p2 = data[data.length - 1];

            for (int j = 1; j <= segmentsPerCurve; j++) {
                float t = j / (float) segmentsPerCurve;
                Double Coordinate = calculateQuadraticBezierCoordinate(t, p0, p1, p2);
                drawingCoordinates.add(calculateQuadraticBezierCoordinate(t, p0, p1, p2));
            }
        } else if (rest == 2) {
            Double p0 = data[data.length - 2];
            Double p1 = data[data.length - 1];

            for (int j = 1; j <= segmentsPerCurve; j++) {
                float t = j / (float) segmentsPerCurve;
                Double Coordinate = calculateLinearBezierCoordinate(t, p0, p1);
                drawingCoordinates.add(calculateLinearBezierCoordinate(t, p0, p1));
            }
        }

        drawLastCoordinate(drawingCoordinates, data);
    }

    private void drawLastCoordinate(List<Double> drawCoordinates, Double[] data) {
        Double firstFromStart = drawCoordinates.get(2);
        Double firstFromEnd = drawCoordinates.get(drawCoordinates.size() - 3);
        Double second = drawCoordinates.get(7);
        Double lastWithoutOne = drawCoordinates.get(drawCoordinates.size() - 8);

        for (int i = 0; i < 7; i++) {
            drawCoordinates.remove(drawCoordinates.size() - 1);
        }

        for (int i = 0; i < 7; i++) {
            drawCoordinates.remove(0);
        }

        for (int j = 1; j <= segmentsPerCurve; j++) {
            float t = j / (float) segmentsPerCurve;
            Double calculated = calculateCubicBezierCoordinate(t, lastWithoutOne, firstFromEnd, firstFromStart, second);
            drawCoordinates
                    .add(calculateCubicBezierCoordinate(t, lastWithoutOne, firstFromEnd, firstFromStart, second));
        }

    }

    private Double calculateCubicBezierCoordinate(float t, Double p0, Double p1, Double p2, Double p3) {
        float u = 1 - t;
        float tt = t * t;
        float uu = u * u;
        float uuu = uu * u;
        float ttt = tt * t;

        Double p = uuu * p0;
        p += 3 * uu * t * p1;
        p += 3 * u * tt * p2;
        p += ttt * p3;

        return p;
    }

    private Double calculateQuadraticBezierCoordinate(float t, Double p0, Double p1, Double p2) {
        float u = 1 - t;
        float tt = t * t;
        float uu = u * u;

        Double p = uu * p0;
        p += 2 * u * t * p1;
        p += tt * p2;

        return p;
    }

    private Double calculateLinearBezierCoordinate(float t, Double p0, Double p1) {
        float u = 1 - t;

        Double p = u * p0;
        p += t * p1;

        return p;
    }

    public List<Coordinate> getCoordinates() {
        return Coordinates;
    }
}
