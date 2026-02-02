/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain;


import java.io.Serializable;
import java.util.Objects;
// Carries geo point data.


public class GeoPointDto implements Serializable {

    private static int LAST_ID = 1;
    private int id = LAST_ID++;
    private Double lat;
    private Double lon;
    private String name;

    public GeoPointDto() {
    }

    public GeoPointDto(GeoPointDto point) {
        this(point.getLat(), point.getLon());
    }

    public GeoPointDto(Double lat, Double lon) {
        super();
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        if (lat == null) {
            lat = new Double(0);
        }
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        if (lon == null) {
            lon = new Double(0);
        }
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "[lat=" + lat + ", lon=" + lon + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoPointDto that = (GeoPointDto) o;
        return Objects.equals(id, that.id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon);
    }

    public String getId() {
        return id + "";
    }

    public boolean isEmpty() {
        return equals(lat, 0, 0.01) || equals(lon, 0, 0.01);
    }

    public static boolean equals(double a, double b, double epsilon) {
        return a == b ? true : Math.abs(a - b) < epsilon;
    }
}
