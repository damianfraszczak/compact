package pl.edu.wat.wcy.cop.app.shared.response.capability;

import java.util.List;
// Represents los area capability.

public class LosAreaCapability {
    private Double lat;
    private Double lon;
    private Double radius;
    private Double accuracy;
    private Integer observerHeight;
    private double losArea;
    private List<List<Double>> losAreaList;

    public LosAreaCapability() {
        super();
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

    public Integer getObserverHeight() {
        return observerHeight;
    }

    public void setObserverHeight(Integer observerHeight) {
        this.observerHeight = observerHeight;
    }

    public double getLosArea() {
        return losArea;
    }

    public void setLosArea(double losArea) {
        this.losArea = losArea;
    }

    public List<List<Double>> getLosAreaList() {
        return losAreaList;
    }

    public void setLosAreaList(List<List<Double>> losAreaList) {
        this.losAreaList = losAreaList;
    }
}
