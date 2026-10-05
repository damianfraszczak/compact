/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.response.capability;
// Represents geo point capability.


public class GeoPointCapability {
    private String lat = ResponseUtils.DOUBLE_NAME;
    private String lon = ResponseUtils.DOUBLE_NAME;
    private String name = ResponseUtils.STRING_NAME;

    /**
     * @return the lat
     */
    public String getLat() {
        return lat;
    }

    /**
     * @param lat
     *            the lat to set
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * @return the lon
     */
    public String getLon() {
        return lon;
    }

    /**
     * @param lon
     *            the lon to set
     */
    public void setLon(String lon) {
        this.lon = lon;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
