/**
 * 
 */
package pl.edu.wat.wcy.cop.domain.scenario;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.edu.wat.wcy.cop.domain.BaseEntity;
import pl.edu.wat.wcy.cop.domain.map.MapConfiguration;
import pl.edu.wat.wcy.cop.domain.scenario.sar.GpxTrace;
import pl.edu.wat.wcy.cop.domain.scenario.sar.SearchAndRescue;
import pl.edu.wat.wcy.cop.domain.scenario.sar.SearchAndRescueSegment;
import pl.edu.wat.wcy.cop.domain.scenario.sar.SearchUnit;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
// Stores a scenario and its associated objects.
public class Scenario extends BaseEntity {

	private String name;

	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dateFrom;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dateTo;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private MapConfiguration mapConfiguration;

	@JoinColumn(name = "scenario_id")
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<ADatP3Report> reportsADatP3;
	@JoinColumn(name = "scenario_id")
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<APP6AMilitaryUnit> app6aMilitaryUnits;
	@JoinColumn(name = "scenario_id")
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<CrisisEvent> crisisEvents;
	@JoinColumn(name = "scenario_id")
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<PointOfInterest> poi;
	@JoinColumn(name = "scenario_id")
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<MSWiAUnit> mswiaUnits;
	@JoinColumn(name = "scenario_id")
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<SearchAndRescue> searchAndRescues;

	@JoinColumn(name = "scenario_id")
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<SearchAndRescueSegment> searchAndRescuesSegments;

	@JoinColumn(name = "scenario_id")
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<GpxTrace> gpxTraces;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public MapConfiguration getMapConfiguration() {
		return mapConfiguration;
	}

	public void setMapConfiguration(MapConfiguration mapConfiguration) {
		this.mapConfiguration = mapConfiguration;
	}

	/**
	 * @return the reportsADatP3
	 */
	public List<ADatP3Report> getReportsADatP3() {
		return reportsADatP3;
	}

	/**
	 * @param reportsADatP3
	 *            the reportsADatP3 to set
	 */
	public void setReportsADatP3(List<ADatP3Report> reportsADatP3) {
		this.reportsADatP3 = reportsADatP3;
	}

	public List<APP6AMilitaryUnit> getApp6aMilitaryUnits() {
		return app6aMilitaryUnits;
	}

	public void setApp6aMilitaryUnits(List<APP6AMilitaryUnit> app6aMilitaryUnits) {
		this.app6aMilitaryUnits = app6aMilitaryUnits;
	}

	public List<CrisisEvent> getCrisisEvents() {
		return crisisEvents;
	}

	public void setCrisisEvents(List<CrisisEvent> crisisEvents) {
		this.crisisEvents = crisisEvents;
	}

	public List<PointOfInterest> getPoi() {
		return poi;
	}

	public void setPoi(List<PointOfInterest> poi) {
		this.poi = poi;
	}

	public List<MSWiAUnit> getMswiaUnits() {
		return mswiaUnits;
	}

	public void setMswiaUnits(List<MSWiAUnit> mswiaUnits) {
		this.mswiaUnits = mswiaUnits;
	}

	public List<SearchAndRescue> getSearchAndRescues() {
		return searchAndRescues;
	}

	public void setSearchAndRescues(List<SearchAndRescue> searchAndRescues) {
		this.searchAndRescues = searchAndRescues;
	}

	public List<SearchAndRescueSegment> getSearchAndRescuesSegments() {
		return searchAndRescuesSegments;
	}

	public void setSearchAndRescuesSegments(List<SearchAndRescueSegment> searchAndRescuesSegments) {
		this.searchAndRescuesSegments = searchAndRescuesSegments;
	}

	public List<GpxTrace> getGpxTraces() {
		return gpxTraces;
	}

	public void setGpxTraces(List<GpxTrace> gpxTraces) {
		this.gpxTraces = gpxTraces;
	}
}
