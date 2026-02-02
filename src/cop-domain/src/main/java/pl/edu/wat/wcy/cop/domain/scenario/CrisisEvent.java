/**
 * 
 */
package pl.edu.wat.wcy.cop.domain.scenario;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.edu.wat.wcy.cop.domain.scenario.enums.crisis.CrisisEventStatus;
import pl.edu.wat.wcy.cop.domain.scenario.enums.crisis.CrisisEventType;

import javax.persistence.*;
import java.util.List;


@Entity
// Stores details of a crisis event.
public class CrisisEvent extends ScenarioPointObject {

	private String htrcId;
	@Enumerated(EnumType.STRING)
	private CrisisEventType type;
	@Enumerated(EnumType.STRING)
	private CrisisEventStatus status;
	private String substanceName;
	private String threatLevel;
	private String province;
	private String city;
	private long timestampStart;
	private long timestampEnd;

	private String source;
	private String description;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	private List<CrisisEventCricleZone> circleZones;
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<CrisisEventMultiPointZone> areaZones;

	/**
	 * @return the htrcId
	 */
	public String getHtrcId() {
		return htrcId;
	}

	/**
	 * @param htrcId
	 *            the htrcId to set
	 */
	public void setHtrcId(String htrcId) {
		this.htrcId = htrcId;
	}

	/**
	 * @return the type
	 */
	public CrisisEventType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(CrisisEventType type) {
		this.type = type;
	}

	/**
	 * @return the status
	 */
	public CrisisEventStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(CrisisEventStatus status) {
		this.status = status;
	}

	/**
	 * @return the substanceName
	 */
	public String getSubstanceName() {
		return substanceName;
	}

	/**
	 * @param substanceName
	 *            the substanceName to set
	 */
	public void setSubstanceName(String substanceName) {
		this.substanceName = substanceName;
	}

	/**
	 * @return the threatLevel
	 */
	public String getThreatLevel() {
		return threatLevel;
	}

	/**
	 * @param threatLevel
	 *            the threatLevel to set
	 */
	public void setThreatLevel(String threatLevel) {
		this.threatLevel = threatLevel;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the timestampStart
	 */
	public long getTimestampStart() {
		return timestampStart;
	}

	/**
	 * @param timestampStart
	 *            the timestampStart to set
	 */
	public void setTimestampStart(long timestampStart) {
		this.timestampStart = timestampStart;
	}

	/**
	 * @return the timestampEnd
	 */
	public long getTimestampEnd() {
		return timestampEnd;
	}

	/**
	 * @param timestampEnd
	 *            the timestampEnd to set
	 */
	public void setTimestampEnd(long timestampEnd) {
		this.timestampEnd = timestampEnd;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the circleZones
	 */
	public List<CrisisEventCricleZone> getCircleZones() {
		return circleZones;
	}

	/**
	 * @param circleZones
	 *            the circleZones to set
	 */
	public void setCircleZones(List<CrisisEventCricleZone> circleZones) {
		this.circleZones = circleZones;
	}

	/**
	 * @return the areaZones
	 */
	public List<CrisisEventMultiPointZone> getAreaZones() {
		return areaZones;
	}

	/**
	 * @param areaZones
	 *            the areaZones to set
	 */
	public void setAreaZones(List<CrisisEventMultiPointZone> areaZones) {
		this.areaZones = areaZones;
	}

}
