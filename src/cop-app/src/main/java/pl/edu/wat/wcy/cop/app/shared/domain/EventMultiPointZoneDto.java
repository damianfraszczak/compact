/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain;

import java.util.LinkedList;
import java.util.List;
// Carries event multi point zone data.


public class EventMultiPointZoneDto extends EventZoneDto implements IMultiPointObject {

    private List<GeoPointDto> points;

    public EventMultiPointZoneDto() {
        super();
    }

    public EventMultiPointZoneDto(String name, List<GeoPointDto> points) {
        super(name);
        this.points = points;
    }

    /**
     * @return the points
     */
    public List<GeoPointDto> getPoints() {
        if (points == null) {
            points = new LinkedList<GeoPointDto>();
        }
        return points;
    }

    /**
     * @param points
     *            the points to set
     */
    public void setPoints(List<GeoPointDto> points) {
        this.points = points;
    }

}
