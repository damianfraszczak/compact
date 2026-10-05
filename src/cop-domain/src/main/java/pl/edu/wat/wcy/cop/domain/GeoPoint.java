/**
 * 
 */
package pl.edu.wat.wcy.cop.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
// Stores latitude and longitude coordinates.
public class GeoPoint extends BaseGeoPoint{
	public GeoPoint() {
	}

	public GeoPoint(double lat, double lon) {
		super(lat, lon);
	}

	public GeoPoint(double[] generatedRandomPoint) {
		super(generatedRandomPoint);
	}
}
