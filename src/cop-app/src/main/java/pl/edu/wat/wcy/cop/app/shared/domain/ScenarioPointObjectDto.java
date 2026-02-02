/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain;
// Carries scenario point object data.


public class ScenarioPointObjectDto extends ScenarioObjectDto {
    private GeoPointDto point;
    private MapSymbolDto mapSymbol;
    private String mapGroup;

    /**
     *
     */
    public ScenarioPointObjectDto() {
        this(new GeoPointDto(), new MapSymbolDto());
    }

    /**
     * @param point
     * @param mapSymbol
     */
    public ScenarioPointObjectDto(GeoPointDto point, MapSymbolDto mapSymbol) {
        super();
        this.point = point;
        this.mapSymbol = mapSymbol;
    }

    /**
     * @param point
     */
    public ScenarioPointObjectDto(GeoPointDto point) {
        this(point, new MapSymbolDto());
    }

    public GeoPointDto getPoint() {
        return point;
    }

    public void setPoint(GeoPointDto point) {
        this.point = point;
    }

    public MapSymbolDto getMapSymbol() {
        return mapSymbol;
    }

    public void setMapSymbol(MapSymbolDto mapSymbol) {
        this.mapSymbol = mapSymbol;
    }

    /**
     * @return the mapGroup
     */
    public String getMapGroup() {
        return mapGroup;
    }

    /**
     * @param mapGroup
     *            the mapGroup to set
     */
    public void setMapGroup(String mapGroup) {
        this.mapGroup = mapGroup;
    }
}
