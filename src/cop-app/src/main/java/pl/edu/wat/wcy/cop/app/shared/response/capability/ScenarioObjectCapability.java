/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.response.capability;
// Represents scenario object capability.


public class ScenarioObjectCapability {
    private GeoPointCapability point = new GeoPointCapability();
    private MapSymbolCapability mapSymbol = new MapSymbolCapability();
    private String code = ResponseUtils.STRING_NAME;

    /**
     * @return the point
     */
    public GeoPointCapability getPoint() {
        if (point == null) {
            point = new GeoPointCapability();
        }
        return point;
    }

    /**
     * @param point
     *            the point to set
     */
    public void setPoint(GeoPointCapability point) {
        this.point = point;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the mapSymbol
     */
    public MapSymbolCapability getMapSymbol() {
        if (mapSymbol == null) {
            mapSymbol = new MapSymbolCapability();
        }
        return mapSymbol;
    }

    /**
     * @param mapSymbol
     *            the mapSymbol to set
     */
    public void setMapSymbol(MapSymbolCapability mapSymbol) {
        this.mapSymbol = mapSymbol;
    }

}
