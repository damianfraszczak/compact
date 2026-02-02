/**
 * 
 */
package pl.edu.wat.wcy.cop.domain.scenario;

import pl.edu.wat.wcy.cop.domain.GeoPoint;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
// Defines a circular geographic zone.
public class CircleZone extends Zone {

	private GeoPoint point;
	private double radius;

	/**
	 * @return the point
	 */
	public GeoPoint getPoint() {
		return point;
	}

	/**
	 * @param point
	 *            the point to set
	 */
	public void setPoint(GeoPoint point) {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EventCircleZone [point=" + point + ", radius=" + radius + "]";
	}

}
