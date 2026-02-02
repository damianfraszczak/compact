/**
 * 
 */
package pl.edu.wat.wcy.cop.domain.scenario;

import javax.persistence.Entity;


@Entity
// Defines a circular zone around a point of interest.
public class PoiCircleZone extends CircleZone {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PoiCircleZone [getPoint()=" + getPoint() + "]";
	}

}
