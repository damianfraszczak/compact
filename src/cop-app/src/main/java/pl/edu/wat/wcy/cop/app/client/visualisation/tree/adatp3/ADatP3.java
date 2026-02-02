package pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3;

import ol.Coordinate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
// Represents a dat 3.

public class ADatP3 {
    private String report;
    private CBRNType cbrnType;
    private String foxtrot;
    private Coordinate mapCenter;
    private Papa papa;
    private HashMap<String, List<String>> mgrsCoordinates = new HashMap<>();
    private HashMap<String, List<Coordinate>> lonLatCoordinates = new HashMap<>();
    private Date delta;
    private FeatureType featureType;

    public ADatP3() {
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public CBRNType getCbrnType() {
        return cbrnType;
    }

    public void setCbrnType(CBRNType cbrnType) {
        this.cbrnType = cbrnType;
    }

    public String getFoxtrot() {
        return foxtrot;
    }

    public void setFoxtrot(String foxtrot) {
        this.foxtrot = foxtrot;
    }

    public Papa getPapa() {
        return papa;
    }

    public void setPapa(Papa papa) {
        this.papa = papa;
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

    public Coordinate getMapCenter() {
        return mapCenter;
    }

    public void setMapCenter(Coordinate mapCenter) {
        this.mapCenter = mapCenter;
    }

    public Date getDelta() {
        return delta;
    }

//	public FeatureType getFeatureType() {
//		return featureType;
//	}

    public void setDelta(Date delta) {
        this.delta = delta;
    }

    public String getFeatureType() {
        return featureType.name();
    }

    public void setFeatureType(FeatureType featureType) {
        this.featureType = featureType;
    }

    @Override
    public String toString() {
        return "Type: " + cbrnType + ", Foxtrot: " + foxtrot + ", Papa: " + papa.toString() + ", MGRS: "
                + mgrsCoordinates.size() + ", LatLon: " + lonLatCoordinates.size();
        // return String.valueOf(mgrsCoordinates.get(String.valueOf(0)).size());
    }

    public enum FeatureType {
        POLYGON,
        CIRCLE
    }
}
