/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol.gis.coords;

import ol.Coordinate;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
// Represents ups point.


public class UPSPoint implements CoordsFormatter {

    /** Easting */
    protected double easting;
    /** Northing */
    protected double northing;
    /** Hemisphere */
    private double Degree = Math.PI / 180.0;

    /** Constructor for the UPSPoint object */
    public UPSPoint() {
        this.easting = 0;
        this.northing = 0;
    }

    /**
     * Constructor for the UPSPoint object
     *
     * @param easting
     *            easting
     * @param northing
     *            northing
     */
    public UPSPoint(double easting, double northing) {
        this.easting = easting;
        this.northing = northing;
    }

    /**
     * Constructor for the UPSPoint object
     *
     * @param llpt
     *            LatLonPoint
     */
    public UPSPoint(Coordinate llpt) {
        this.toUPS(llpt.getY(), llpt.getX());
    }

    /**
     * Static method to create a UPSPoint object from lat/lon coordinates.
     * Method avoids conflict with (double, double) constructor.
     *
     * @param lat
     *            latitude in decimal degrees
     * @param lon
     *            longitude in decimal degrees
     */
    public static UPSPoint createUPSPoint(double lat, double lon) {
        UPSPoint ups = new UPSPoint();
        ups.toUPS(lat, lon);
        return ups;
    }

    /**
     * Calculate phi (latitude)
     *
     * @param e
     * @param t
     * @return phi
     */
    static double calcPhi(double e, double t) {
        double phi = 0;
        double old = Math.PI / 2.0 - 2.0 * Math.atan(t);
        short maxIterations = 20;

        while ((Math.abs((phi - old) / phi) > 1.0e-8) && (maxIterations != 0)) {
            old = phi;
            phi = Math.PI / 2.0
                    - 2.0 * Math.atan(t * Math.pow((1.0 - e * Math.sin(phi)) / ((1.0 + e * Math.sin(phi))), (e / 2.0)));
            maxIterations--;
        }
        return phi;
    }

    /**
     * Converts a lat-lon pair to UPS point
     *
     * @param lat
     *            latitude in decimal degrees
     * @param lon
     *            longitude in decimal degrees
     */
    public void toUPS(double lat, double lon) {
        double a = 0;
        double t = 0;
        double e = 0;
        double es = 0;
        double rho = 0;
        double x;
        double y;
        final double k0 = 0.994;

        double lambda = lon * Degree;
        double phi = Math.abs(lat * Degree);
        a = 6378137.0;
        es = 0.00669438d;

        e = Math.sqrt(es);
        t = Math.tan(Math.PI / 4.0 - phi / 2.0)
                / Math.pow((1.0 - e * Math.sin(phi)) / (1.0 + e * Math.sin(phi)), (e / 2.0));
        rho = 2.0 * a * k0 * t / Math.sqrt(Math.pow(1.0 + e, 1.0 + e) * Math.pow(1.0 - e, 1.0 - e));
        x = rho * Math.sin(lambda);
        y = rho * Math.cos(lambda);

        if (lat > 0.0) {
            // Northern hemisphere
            y = -(y);
            // southernHemisphere = false;
        }
        x += 2.0e6;
        // Add in false easting and northing
        y += 2.0e6;

        easting = x;
        northing = y;
    }

    /**
     * Gets the easting attribute
     *
     * @return The easting value
     */
    public double getNorthing() {
        return northing;
    }

    /**
     * Sets the northing attribute
     *
     * @param northing
     *            The new northing value
     */
    public void setNorthing(double northing) {
        this.northing = northing;
    }

    /**
     * Gets the easting attribute
     *
     * @return The easting value
     */
    public double getEasting() {
        return easting;
    }

    /**
     * Sets the easting attribute
     *
     * @param easting
     *            The new easting value
     */
    public void setEasting(double easting) {
        this.easting = easting;
    }

    /**
     * Description of the Method
     *
     * @return returns a string representation of the object
     */
    public String toString() {
        return "Easting:" + easting + " Northing:" + northing;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ol.gis.coords.CoordsFormatter#
     * coordsToString()
     */
    @Override
    public String coordsToString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(AppConstants.NUMBER_FORMAT.format(easting) + " E ");
        buffer.append(AppConstants.NUMBER_FORMAT.format(northing) + " N");
        return buffer.toString();
    }

}
