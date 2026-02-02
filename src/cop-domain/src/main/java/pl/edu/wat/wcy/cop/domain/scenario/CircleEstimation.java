package pl.edu.wat.wcy.cop.domain.scenario;

import pl.edu.wat.wcy.cop.domain.BaseEntity;

import javax.persistence.Entity;

@Entity

// Stores estimated circular area parameters.
public class CircleEstimation extends BaseEntity {

    private String name;
    private double distance;
    private int dispersion;
    private int percentage;
    private Boolean isMountainTerrain;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getDispersion() {
        return dispersion;
    }

    public void setDispersion(int dispersion) {
        this.dispersion = dispersion;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public boolean isMountainTerrain() {
        return isMountainTerrain == null ? false : isMountainTerrain;
    }

    public void setMountainTerrain(boolean mountainTerrain) {
        isMountainTerrain = mountainTerrain;
    }
}
