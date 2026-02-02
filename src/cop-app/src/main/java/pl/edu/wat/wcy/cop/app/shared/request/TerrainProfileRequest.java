
package pl.edu.wat.wcy.cop.app.shared.request;

import java.util.List;
// Represents a terrain profile request.

public class TerrainProfileRequest {
    private List<Double> coordinates;
    private double accuracy;

    public TerrainProfileRequest() {
        super();
    }

    public TerrainProfileRequest(List<Double> coordinates, double accuracy) {
        super();
        this.coordinates = coordinates;
        this.accuracy = accuracy;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

}
