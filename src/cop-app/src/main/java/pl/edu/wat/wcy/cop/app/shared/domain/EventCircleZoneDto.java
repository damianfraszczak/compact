/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain;
// Carries event circle zone data.


public class EventCircleZoneDto extends EventZoneDto implements ICircleObject {

    private GeoPointDto point;
    private double radius;

    /**
     *
     */
    public EventCircleZoneDto() {
        super();
        point = new GeoPointDto();
    }

    /**
     * @return the point
     */
    public GeoPointDto getPoint() {
        return point;
    }

    /**
     * @param point
     *            the point to set
     */
    public void setPoint(GeoPointDto point) {
        this.point = point;
    }

    /**
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param radius
     *            the radius to set
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

}
