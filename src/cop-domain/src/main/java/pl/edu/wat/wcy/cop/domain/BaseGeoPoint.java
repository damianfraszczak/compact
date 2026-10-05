package pl.edu.wat.wcy.cop.domain;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
// Provides shared fields for geographic coordinates.
public class BaseGeoPoint implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5911457985611048128L;

    private Double lat;
    private Double lon;
    private String text;
    public BaseGeoPoint() {
        super();
    }

    public BaseGeoPoint(double lat, double lon) {
        super();
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * @param generatedRandomPoint
     */
    public BaseGeoPoint(double[] generatedRandomPoint) {
        this(generatedRandomPoint[0], generatedRandomPoint[1]);
    }

    public double getLat() {
        if (lat == null) {
            lat = new Double(0);
        }
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        if (lon == null) {
            lon = new Double(0);
        }
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Punkt : " + lat + " , " + lon;
    }
}
