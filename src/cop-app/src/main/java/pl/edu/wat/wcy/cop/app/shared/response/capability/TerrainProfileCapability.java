package pl.edu.wat.wcy.cop.app.shared.response.capability;

import java.util.List;
// Represents terrain profile capability.

public class TerrainProfileCapability {

    private List<TerrainProfilePoint> terrainProfilePoints;
    private Double accuracy;

    public TerrainProfileCapability() {
        super();
    }

    public List<TerrainProfilePoint> getTerrainProfilePoints() {
        return terrainProfilePoints;
    }

    public void setTerrainProfilePoints(List<TerrainProfilePoint> terrainProfilePoints) {
        this.terrainProfilePoints = terrainProfilePoints;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

}
