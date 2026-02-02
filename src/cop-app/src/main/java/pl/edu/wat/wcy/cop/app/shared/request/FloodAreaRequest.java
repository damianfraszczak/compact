/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.request;
// Represents a flood area request.


public class FloodAreaRequest {
    private double lat;
    private double lon;
    private double radius;
    private int floodHeight;
    private double accuracy;

    /**
     *
     */
    public FloodAreaRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param lat in decimal degrees
     * @param lon in decimal degrees
     * @param radius in decimal degrees
     * @param floodHeight in meters
     * @param accuracy
     */
    public FloodAreaRequest(double lat, double lon, double radius, int floodHeight, double accuracy) {
        super();
        this.lat = lat;
        this.lon = lon;
        this.radius = radius;
        this.floodHeight = floodHeight;
        this.accuracy = accuracy;
    }

    /**
     * @return the lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * @param lat
     *            the lat to set
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * @return the lon
     */
    public double getLon() {
        return lon;
    }

    /**
     * @param lon
     *            the lon to set
     */
    public void setLon(double lon) {
        this.lon = lon;
    }

    /**
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param radius
     *            the radius to set
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * @return the floodHeight
     */
    public int getFloodHeight() {
        return floodHeight;
    }

    /**
     * @param floodHeight
     *            the floodHeight to set
     */
    public void setFloodHeight(int floodHeight) {
        this.floodHeight = floodHeight;
    }

    /**
     * @return the accuracy
     */
    public double getAccuracy() {
        return accuracy;
    }

    /**
     * @param accuracy
     *            the accuracy to set
     */
    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

}
