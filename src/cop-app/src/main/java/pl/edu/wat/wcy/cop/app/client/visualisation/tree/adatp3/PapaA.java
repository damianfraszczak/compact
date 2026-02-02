package pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3;
// Represents papa a.

public class PapaA implements Papa {
    private Float hazardAreaDistance;
    private Float releaseAresRadius;

    public Float getHazardAreaDistance() {
        return hazardAreaDistance;
    }

    public void setHazardAreaDistance(Float hazardAreaDistance) {
        this.hazardAreaDistance = hazardAreaDistance;
    }

    public Float getReleaseAresRadius() {
        return releaseAresRadius;
    }

    public void setReleaseAresRadius(Float releaseAresRadius) {
        this.releaseAresRadius = releaseAresRadius;
    }

    public String toString() {
        return "haz: " + hazardAreaDistance + "   rel: " + releaseAresRadius;
    }
}
