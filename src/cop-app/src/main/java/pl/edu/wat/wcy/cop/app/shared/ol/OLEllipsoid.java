/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.ol;
// Enumerates ol ellipsoid.


public enum OLEllipsoid {
    WGS_84("WGS 84", 6378137.0d, 0.081819191d, 0.00669438d, 6356752.3142d);

    public final String name;

    public final double radius;

    public final double polarRadius;

    public final double ecc;

    public final double eccsq;

    OLEllipsoid(String name, double radius, double eccsq) {
        this(name, radius, eccsq, Double.NaN, Double.NaN);
    }

    OLEllipsoid(String name, double equitorialRadius, double ecc, double eccsq, double polarRadius) {
        this.name = name;
        this.radius = equitorialRadius;
        this.ecc = ecc;
        this.eccsq = eccsq;
        this.polarRadius = polarRadius;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @return the polarRadius
     */
    public double getPolarRadius() {
        return polarRadius;
    }

    /**
     * @return the ecc
     */
    public double getEcc() {
        return ecc;
    }

    /**
     * @return the eccsq
     */
    public double getEccsq() {
        return eccsq;
    }

}
