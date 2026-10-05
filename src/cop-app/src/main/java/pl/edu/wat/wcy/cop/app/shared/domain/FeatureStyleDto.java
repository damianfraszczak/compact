package pl.edu.wat.wcy.cop.app.shared.domain;

import java.io.Serializable;
// Carries feature style data.

public class FeatureStyleDto implements Serializable {
    private String strokeColor = "F4A460";
    private int strokeWidth = 2;
    private double strokeAlpha = 1.0;
    private String fillColor = "F4A460";
    private double fillAlpha = 0.1;
    private String dashArray = "1";




    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public double getFillAlpha() {
        return fillAlpha;
    }

    public void setFillAlpha(double fillAlpha) {
        this.fillAlpha = fillAlpha;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }

    public double getStrokeAlpha() {
        return strokeAlpha;
    }

    public void setStrokeAlpha(double strokeAlpha) {
        this.strokeAlpha = strokeAlpha;
    }

    public String getDashArray() {
        return dashArray;
    }

    public void setDashArray(String dashArray) {
        this.dashArray = dashArray;
    }
}
