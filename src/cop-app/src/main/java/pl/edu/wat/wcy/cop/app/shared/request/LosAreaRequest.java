/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.request;
// Represents a los area request.


public class LosAreaRequest {
    private double lat;
    private double lon;
    private double radius;
    private int observerHeight;
    private double accuracy;

    /**
     *
     */
    public LosAreaRequest() {
        super();
    }

    /**
     * @param lat in decimal degrees
     * @param lon in decimal degrees
     * @param radius in decimal degrees
     * @param observerHeight in meters
     * @param accuracy
     */
    public LosAreaRequest(double lat, double lon, double radius, int observerHeight, double accuracy) {
        super();
        this.lat = lat;
        this.lon = lon;
        this.radius = radius;
        this.observerHeight = observerHeight;
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
     * @return the observerHeight
     */
    public int getObserverHeight() {
        return observerHeight;
    }

    /**
     * @param observerHeight
     *            the observerHeight to set
     */
    public void setObserverHeight(int observerHeight) {
        this.observerHeight = observerHeight;
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
