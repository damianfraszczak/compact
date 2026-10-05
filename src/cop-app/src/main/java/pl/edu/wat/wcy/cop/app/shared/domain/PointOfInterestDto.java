/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain;

import java.util.LinkedList;
import java.util.List;
// Carries point of interest data.


public class PointOfInterestDto extends ScenarioPointObjectDto implements ISymbolOnTheMap<String> {

    private String name;
    private String description;
    private List<PoiCircleZoneDto> circleZones;

    private List<PoiMultiPointZoneDto> areaZones;

    private PointOfInterestTypeDto type = PointOfInterestTypeDto.MARKER;

    /**
     *
     */
    public PointOfInterestDto() {
        this(new GeoPointDto(), new MapSymbolDto());
    }

    /**
     * @param point
     */
    public PointOfInterestDto(GeoPointDto point) {
        super(point);
    }

    /**
     * @param point
     * @param mapSymbol
     */
    public PointOfInterestDto(GeoPointDto point, MapSymbolDto mapSymbol) {
        super(point, mapSymbol);
    }

    /**
     *
     * @param name
     * @param description
     * @param point
     */
    public PointOfInterestDto(String name, String description, GeoPointDto point) {
        this(name, description, point, new MapSymbolDto());
    }

    /**
     * @param name
     * @param description
     * @param type
     */
    public PointOfInterestDto(String name, String description, GeoPointDto point, PointOfInterestTypeDto type) {
        this(name, description, point, new MapSymbolDto());
        this.type = type;
    }

    /**
     *
     * @param name
     * @param description
     * @param point
     * @param mapSymbol
     */
    public PointOfInterestDto(String name, String description, GeoPointDto point, MapSymbolDto mapSymbol) {
        super(point, mapSymbol);
        this.description = description;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the circleZones
     */
    public List<PoiCircleZoneDto> getCircleZones() {
        if (circleZones == null) {
            circleZones = new LinkedList<>();
        }
        return circleZones;
    }

    /**
     * @param circleZones
     *            the circleZones to set
     */
    public void setCircleZones(List<PoiCircleZoneDto> circleZones) {
        this.circleZones = circleZones;
    }

    /**
     * @return the areaZones
     */
    public List<PoiMultiPointZoneDto> getAreaZones() {
        if (areaZones == null) {
            areaZones = new LinkedList<>();
        }
        return areaZones;
    }

    /**
     * @param areaZones
     *            the areaZones to set
     */
    public void setAreaZones(List<PoiMultiPointZoneDto> areaZones) {
        this.areaZones = areaZones;
    }

    /**
     * @return the type
     */
    public PointOfInterestTypeDto getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(PointOfInterestTypeDto type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return getType() + " " + getDescription();
    }
}
