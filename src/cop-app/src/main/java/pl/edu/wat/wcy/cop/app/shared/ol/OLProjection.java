/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.ol;
// Enumerates ol projection.

public enum OLProjection {

    EPSG_4326("EPSG:4326"), MERCARTOR("EPSG:3857"), EPSG_21781("EPSG:21781");
    private String code;
    private OLEllipsoid ellipsoid;

    /**
     * @param code
     */
    OLProjection(String code) {
        this(code, OLEllipsoid.WGS_84);
    }

    /**
     * @param code
     * @param ellipsoid
     */
    OLProjection(String code, OLEllipsoid ellipsoid) {
        this.code = code;
        this.ellipsoid = ellipsoid;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the ellipsoid
     */
    public OLEllipsoid getEllipsoid() {
        return ellipsoid;
    }

}
