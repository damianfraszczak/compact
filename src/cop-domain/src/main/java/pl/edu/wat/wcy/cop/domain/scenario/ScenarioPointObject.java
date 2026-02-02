/**
 * 
 */
package pl.edu.wat.wcy.cop.domain.scenario;

import pl.edu.wat.wcy.cop.domain.GeoPoint;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
// Represents a scenario object with a location point.
public class ScenarioPointObject extends ScenarioObject {
	private GeoPoint point;
	private MapSymbol mapSymbol;
	private String mapGroup;

	/**
	 * 
	 */
	public ScenarioPointObject() {
		this(new GeoPoint(), new MapSymbol());
	}

	/**
	 * @param point
	 * @param mapSymbol
	 */
	public ScenarioPointObject(GeoPoint point, MapSymbol mapSymbol) {
		super();
		this.point = point;
		this.mapSymbol = mapSymbol;
	}

	public GeoPoint getPoint() {
		return point;
	}

	public void setPoint(GeoPoint point) {
		this.point = point;
	}

	public MapSymbol getMapSymbol() {
		if (mapSymbol == null) {
			mapSymbol = new MapSymbol();
		}
		return mapSymbol;
	}

	public void setMapSymbol(MapSymbol mapSymbol) {
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
