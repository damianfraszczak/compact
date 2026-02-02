package pl.edu.wat.wcy.cop.app.shared.response.capability;

import java.util.List;
// Represents flood area capability.

public class FloodAreaCapability {
    private List<PolygonWithHeight> polygonsWithHeight;
    private Integer minElevation;
    private Integer minHeight;

    private Double lat;
    private Double lon;
    private Double radius;
    private Double accuracy;
    private Integer floodHeight;

    private List<Double> minElevationPointPolygon;
    private double floodArea;
    private double floodVolume;


    public FloodAreaCapability() {
        super();
    }

    public List<PolygonWithHeight> getPolygonsWithHeight() {
        return polygonsWithHeight;
    }

    public void setPolygonsWithHeight(List<PolygonWithHeight> polygonsWithHeight) {
        this.polygonsWithHeight = polygonsWithHeight;
    }

    public Integer getMinElevation() {
        return minElevation;
    }

    public void setMinElevation(Integer minElevation) {
        this.minElevation = minElevation;
    }

    public Integer getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(Integer minHeight) {
        this.minHeight = minHeight;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getFloodHeight() {
        return floodHeight;
    }

    public void setFloodHeight(Integer floodHeight) {
        this.floodHeight = floodHeight;
    }

    public List<Double> getMinElevationPointPolygon() {
        return minElevationPointPolygon;
    }

    public void setMinElevationPointPolygon(List<Double> minElevationPointPolygon) {
        this.minElevationPointPolygon = minElevationPointPolygon;
    }

    public double getFloodArea() {
        return floodArea;
    }

    public void setFloodArea(double floodArea) {
        this.floodArea = floodArea;
    }

    public double getFloodVolume() {
        return floodVolume;
    }

    public void setFloodVolume(double floodVolume) {
        this.floodVolume = floodVolume;
    }
}
