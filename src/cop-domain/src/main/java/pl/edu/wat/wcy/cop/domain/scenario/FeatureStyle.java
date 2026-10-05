package pl.edu.wat.wcy.cop.domain.scenario;

import javax.persistence.Embeddable;

@Embeddable
// Stores styling information for map features.
public class FeatureStyle {
    private String strokeColor = "F4A460";
    private Integer strokeWidth = 2;
    private Double strokeAlpha = 1.0;
    private String fillColor = "F4A460";
    private Double fillAlpha = 0.1;
    private String dashArray = "1";

    public String getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public Integer getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(Integer strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public Double getStrokeAlpha() {
        return strokeAlpha;
    }

    public void setStrokeAlpha(Double strokeAlpha) {
        this.strokeAlpha = strokeAlpha;
    }

    public Double getFillAlpha() {
        return fillAlpha;
    }

    public void setFillAlpha(Double fillAlpha) {
        this.fillAlpha = fillAlpha;
    }

    public String getDashArray() {
        return dashArray;
    }

    public void setDashArray(String dashArray) {
        this.dashArray = dashArray;
    }
}
