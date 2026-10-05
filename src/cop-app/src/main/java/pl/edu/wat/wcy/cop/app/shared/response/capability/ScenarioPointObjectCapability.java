/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.response.capability;
// Represents scenario point object capability.

public class ScenarioPointObjectCapability {
    private GeoPointCapability point = new GeoPointCapability();
    private MapSymbolCapability mapSymbol = new MapSymbolCapability();

    /**
     * @return the point
     */
    public GeoPointCapability getPoint() {
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
     * @return the mapSymbol
     */
    public MapSymbolCapability getMapSymbol() {
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
