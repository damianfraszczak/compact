package pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3;
// Represents papa r.

public class PapaR implements Papa {
    private Float hazardAreaDistanceR1;
    private Float hazardAreaDistanceR2;
    private Float hazardAreaDistanceR3;
    private Float releaseAresRadius;

    public Float getHazardAreaDistanceR1() {
        return hazardAreaDistanceR1;
    }

    public void setHazardAreaDistanceR1(Float hazardAreaDistanceR1) {
        this.hazardAreaDistanceR1 = hazardAreaDistanceR1;
    }

    public Float getHazardAreaDistanceR2() {
        return hazardAreaDistanceR2;
    }

    public void setHazardAreaDistanceR2(Float hazardAreaDistanceR2) {
        this.hazardAreaDistanceR2 = hazardAreaDistanceR2;
    }

    public Float getHazardAreaDistanceR3() {
        return hazardAreaDistanceR3;
    }

    public void setHazardAreaDistanceR3(Float hazardAreaDistanceR3) {
        this.hazardAreaDistanceR3 = hazardAreaDistanceR3;
    }

    public Float getReleaseAresRadius() {
        return releaseAresRadius;
    }

    public void setReleaseAresRadius(Float releaseAresRadius) {
        this.releaseAresRadius = releaseAresRadius;
    }

    public String toString() {
        return " haz1: " + hazardAreaDistanceR1 + " haz2: " + hazardAreaDistanceR2 + " haz3: " + hazardAreaDistanceR3
                + " rel: " + releaseAresRadius;
    }
}
