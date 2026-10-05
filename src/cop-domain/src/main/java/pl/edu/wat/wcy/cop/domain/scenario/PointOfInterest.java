/**
 * 
 */
package pl.edu.wat.wcy.cop.domain.scenario;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.edu.wat.wcy.cop.domain.GeoPoint;

import javax.persistence.*;
import java.util.List;


@Entity
// Represents a point of interest in a scenario.
public class PointOfInterest extends ScenarioPointObject {

	@Column(length = 1024)
	private String description;
	@Column(length = 1024)
	private String name;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	private List<PoiCircleZone> circleZones;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<PoiMultiPointZone> areaZones;

	@Enumerated(EnumType.STRING)
	private PointOfInterestType type = PointOfInterestType.MARKER;

	public PointOfInterest() {
		super();
	}

	/**
	 * @param point
	 * @param description
	 * @param name
	 * @param type
	 */
	public PointOfInterest(String name, String description, GeoPoint point, PointOfInterestType type) {
		super(point, new MapSymbol());
		this.description = description;
		this.name = name;
		this.type = type;
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
	public List<PoiCircleZone> getCircleZones() {
		return circleZones;
	}

	/**
	 * @param circleZones
	 *            the circleZones to set
	 */
	public void setCircleZones(List<PoiCircleZone> circleZones) {
		this.circleZones = circleZones;
	}

	/**
	 * @return the areaZones
	 */
	public List<PoiMultiPointZone> getAreaZones() {
		return areaZones;
	}

	/**
	 * @param areaZones
	 *            the areaZones to set
	 */
	public void setAreaZones(List<PoiMultiPointZone> areaZones) {
		this.areaZones = areaZones;
	}

	/**
	 * @return the type
	 */
	public PointOfInterestType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(PointOfInterestType type) {
		this.type = type;
	}

}
