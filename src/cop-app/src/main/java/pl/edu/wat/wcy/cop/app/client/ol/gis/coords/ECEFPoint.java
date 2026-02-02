/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol.gis.coords;

import ol.Coordinate;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.ol.OLEllipsoid;
// Represents ecef point.


public class ECEFPoint implements CoordsFormatter {

    protected static double EQUIVALENT_TOLERANCE = 0.001;

    protected double x_ = 0.0;
    protected double y_ = 0.0;
    protected double z_ = 0.0;

    /**
     * Construct a default ECEFPoint.
     */
    public ECEFPoint() {
    }

    /**
     * Construct an ECEFPoint
     */
    public ECEFPoint(double x, double y, double z) {
        setECEF(x, y, z);
    }

    /**
     * Construct an ECEFPoint
     *
     * @param pt
     *            ECEFPoint
     */
    public ECEFPoint(ECEFPoint pt) {
        x_ = pt.x_;
        y_ = pt.y_;
        z_ = pt.z_;
    }

    /**
     * Construct an ECEFPoint
     */
    public ECEFPoint(float x, float y, float z) {
        this((double) x, (double) y, (double) z);
    }


    public static ECEFPoint LLtoECEF(Coordinate llpoint) {
        return LLtoECEF(llpoint, new ECEFPoint());
    }

    public static ECEFPoint LLtoECEF(Coordinate llpoint, ECEFPoint ecef) {
        if (ecef == null) {
            ecef = new ECEFPoint();
        }

        ecef.setLatLon(llpoint);
        return ecef;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String representation
     */
    public String toString() {
        return "ECEFPoint[x=" + x_ + ",y=" + y_ + ",z=" + z_ + "]";
    }

    /**
     * Set x.
     */
    public void setx(double x) {
        x_ = x;
    }

    /**
     * Set y.
     */
    public void sety(double y) {
        y_ = y;
    }

    /**
     * Set z.
     */
    public void setz(double z) {
        z_ = z;
    }

    /**
     * Set x y z.
     */
    public void setECEF(double x, double y, double z) {
        x_ = x;
        y_ = y;
        z_ = z;
    }

    /**
     * Set ECEFPoint.
     */
    public void setECEF(ECEFPoint pt) {
        x_ = pt.x_;
        y_ = pt.y_;
        z_ = pt.z_;
    }

    /**
     * Get x
     */
    public double getx() {
        return x_;
    }

    /**
     * Get y
     */
    public double gety() {
        return y_;
    }

    /**
     * Get z
     */
    public double getz() {
        return z_;
    }

    /**
     * Determines whether two ECEFPoints are equal.
     *
     * @param obj
     *            Object
     * @return Whether the two points are equal
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ECEFPoint pt = (ECEFPoint) obj;
        return (MoreMath.approximately_equal(x_, pt.x_, EQUIVALENT_TOLERANCE)
                && MoreMath.approximately_equal(y_, pt.y_, EQUIVALENT_TOLERANCE)
                && MoreMath.approximately_equal(z_, pt.z_, EQUIVALENT_TOLERANCE));
    }

    /**
     * Set an ECEFPoint from a Coordinate
     *
     * @param pt
     *            Coordinate
     */
    public void setLatLon(Coordinate pt) {
        setLatLon(pt.getY(), pt.getX(), OLEllipsoid.WGS_84);
    }

    /**
     * Set an ECEFPoint from a Lat, Lon
     */
    public void setLatLon(float lat, float lon) {
        setLatLon(lat, lon, OLEllipsoid.WGS_84);
    }

    /**
     * Set an ECEFPoint from a Lat, Lon
     */
    public void setLatLon(double lat, double lon, OLEllipsoid ellip) {

        final double a = ellip.radius; // semimajor (meters)
        final double b = ellip.polarRadius; // semiminor (meters)
        final double a2 = a * a;
        final double b2 = b * b;
        final double e2 = (a2 - b2) / a2;

        final double L = Math.toRadians(lon); // Longitude
        final double P = Math.toRadians(lat); // Latitude
        final double h = 0; // Height above the ellipsoid (m)

        final double cosLat = Math.cos(P);
        final double sinLat = Math.sin(P);
        final double rn = a / Math.sqrt(1 - e2 * (sinLat * sinLat));

        final double x = (rn + h) * cosLat * Math.cos(L); // X: Toward
        // prime
        // meridian
        final double y = (rn + h) * cosLat * Math.sin(L); // Y: Toward
        // East
        final double z = (rn * (1 - e2) + h) * sinLat; // Z: Toward
        // North

        this.setECEF(x, y, z);
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
        buffer.append(AppConstants.NUMBER_FORMAT.format(x_) + " X ");
        buffer.append(AppConstants.NUMBER_FORMAT.format(y_) + " Y ");
        buffer.append(AppConstants.NUMBER_FORMAT.format(z_) + " Z");
        return buffer.toString();
    }

}
