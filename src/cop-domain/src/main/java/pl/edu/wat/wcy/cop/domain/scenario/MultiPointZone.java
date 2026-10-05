/**
 * 
 */
package pl.edu.wat.wcy.cop.domain.scenario;

import pl.edu.wat.wcy.cop.domain.GeoPoint;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import java.util.LinkedList;
import java.util.List;


@MappedSuperclass
// Defines a zone using multiple points.
public class MultiPointZone extends Zone {

	@ElementCollection(fetch = FetchType.EAGER)
	private List<GeoPoint> points;

	/**
	 * @return the points
	 */
	public List<GeoPoint> getPoints() {
		if(points == null){
			points = new LinkedList<>();
		}
		return points;
	}

	/**
	 * @param points
	 *            the points to set
	 */
	public void setPoints(List<GeoPoint> points) {
		this.points = points;
	}
}
