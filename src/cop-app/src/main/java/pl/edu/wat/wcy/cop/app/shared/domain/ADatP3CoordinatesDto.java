package pl.edu.wat.wcy.cop.app.shared.domain;

import ol.Coordinate;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.ADatP3;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.CBRNType;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.PapaA;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.PapaR;

import java.util.HashMap;
import java.util.List;
// Carries a dat 3 coordinates data.

public class ADatP3CoordinatesDto {

    public String featureType;

    private String cbrnType;
    private String lat;
    private String lon;
    private String dateOfIncydent;
    private String releaseAresRadius;
    private String hazardAreaDistance;
    private String hazardAreaDistanceR1;
    private String hazardAreaDistanceR2;
    private String hazardAreaDistanceR3;

    private HashMap<String, List<String>> mgrsCoordinates = new HashMap<>();
    private HashMap<String, List<Coordinate>> lonLatCoordinates = new HashMap<>();

    public ADatP3CoordinatesDto(ADatP3 aDatP3) {
        this.featureType = aDatP3.getFeatureType();
        this.cbrnType = aDatP3.getCbrnType().getName();
        this.lat = String.valueOf(aDatP3.getMapCenter().getY());
        this.lon = String.valueOf(aDatP3.getMapCenter().getX());
        if (aDatP3.getDelta() != null)
            this.dateOfIncydent = aDatP3.getDelta().toString();
        if (this.cbrnType.equals(CBRNType.BIO.getName()) || this.cbrnType.equals(CBRNType.CHEM.getName())) {
            this.releaseAresRadius = ((PapaA) aDatP3.getPapa()).getReleaseAresRadius().toString();
            this.hazardAreaDistance = ((PapaA) aDatP3.getPapa()).getHazardAreaDistance().toString();
        } else {
            if (((PapaR) aDatP3.getPapa()).getReleaseAresRadius() != null)
                this.releaseAresRadius = ((PapaR) aDatP3.getPapa()).getReleaseAresRadius().toString();
            this.hazardAreaDistanceR1 = ((PapaR) aDatP3.getPapa()).getHazardAreaDistanceR1().toString();
            this.hazardAreaDistanceR2 = ((PapaR) aDatP3.getPapa()).getHazardAreaDistanceR2().toString();
            this.hazardAreaDistanceR3 = ((PapaR) aDatP3.getPapa()).getHazardAreaDistanceR3().toString();
        }
        this.mgrsCoordinates = aDatP3.getMgrsCoordinates();
        this.lonLatCoordinates = aDatP3.getLonLatCoordinates();
    }

    public String getFeatureType() {
        return featureType;
    }

    public void setFeatureType(String featureType) {
        this.featureType = featureType;
    }

    public String getCbrnType() {
        return cbrnType;
    }

    public void setCbrnType(String cbrnType) {
        this.cbrnType = cbrnType;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getDateOfIncydent() {
        return dateOfIncydent;
    }

    public void setDateOfIncydent(String dateOfIncydent) {
        this.dateOfIncydent = dateOfIncydent;
    }

    public String getReleaseAresRadius() {
        return releaseAresRadius;
    }

    public void setReleaseAresRadius(String releaseAresRadius) {
        this.releaseAresRadius = releaseAresRadius;
    }

    public String getHazardAreaDistance() {
        return hazardAreaDistance;
    }

    public void setHazardAreaDistance(String hazardAreaDistance) {
        this.hazardAreaDistance = hazardAreaDistance;
    }

    public String getHazardAreaDistanceR1() {
        return hazardAreaDistanceR1;
    }

    public void setHazardAreaDistanceR1(String hazardAreaDistanceR1) {
        this.hazardAreaDistanceR1 = hazardAreaDistanceR1;
    }

    public String getHazardAreaDistanceR2() {
        return hazardAreaDistanceR2;
    }

    public void setHazardAreaDistanceR2(String hazardAreaDistanceR2) {
        this.hazardAreaDistanceR2 = hazardAreaDistanceR2;
    }

    public String getHazardAreaDistanceR3() {
        return hazardAreaDistanceR3;
    }

    public void setHazardAreaDistanceR3(String hazardAreaDistanceR3) {
        this.hazardAreaDistanceR3 = hazardAreaDistanceR3;
    }

    public HashMap<String, List<String>> getMgrsCoordinates() {
        return mgrsCoordinates;
    }

    public void setMgrsCoordinates(HashMap<String, List<String>> mgrsCoordinates) {
        this.mgrsCoordinates = mgrsCoordinates;
    }

    public HashMap<String, List<Coordinate>> getLonLatCoordinates() {
        return lonLatCoordinates;
    }

    public void setLonLatCoordinates(HashMap<String, List<Coordinate>> lonLatCoordinates) {
        this.lonLatCoordinates = lonLatCoordinates;
    }
}
