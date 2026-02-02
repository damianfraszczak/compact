/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain;

import pl.edu.wat.wcy.cop.app.shared.domain.sar.GpxTraceDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueSegmentDto;
import pl.edu.wat.wcy.cop.app.shared.ol.OLMapOptions;

import java.util.LinkedList;
import java.util.List;
// Carries scenario data.


public class ScenarioDto extends DefaultObject {
    private OLMapOptions mapConfiguration;

    private List<ADatP3ReportDto> reportsADatP3;
    private List<App6AMilitaryUnitDto> app6aMilitaryUnits;
    private List<CrisisEventDto> crisisEvents;
    private List<PointOfInterestDto> poi;
    private List<MSWiAUnitDto> mswiaUnits;
    private List<SearchAndRescueDto> searchAndRescues;
    private List<GpxTraceDto> gpxTraces;
    private List<SearchAndRescueSegmentDto> searchAndRescuesSegments;

    public ScenarioDto() {
        super();
    }

    /**
     * @param mapConfiguration
     */
    public ScenarioDto(OLMapOptions mapConfiguration) {
        this(mapConfiguration, new LinkedList<>(), new LinkedList<>());
    }

    public ScenarioDto(OLMapOptions mapConfiguration, List<App6AMilitaryUnitDto> app6aMilitaryUnits,
                       LinkedList<ADatP3ReportDto> reportsADatP3) {
        this.mapConfiguration = mapConfiguration;
        this.app6aMilitaryUnits = app6aMilitaryUnits;
        this.reportsADatP3 = reportsADatP3;
    }

    public OLMapOptions getMapConfiguration() {
        return mapConfiguration;
    }

    public void setMapConfiguration(OLMapOptions mapConfiguration) {
        this.mapConfiguration = mapConfiguration;
    }

    public List<ADatP3ReportDto> getReportsADatP3() {
        if (reportsADatP3 == null) {
            reportsADatP3 = new LinkedList<>();
        }
        return reportsADatP3;
    }

    public void setReportsADatP3(List<ADatP3ReportDto> reportsADatP3) {
        this.reportsADatP3 = reportsADatP3;
    }

    public List<App6AMilitaryUnitDto> getApp6aMilitaryUnits() {
        if (app6aMilitaryUnits == null) {
            app6aMilitaryUnits = new LinkedList<>();
        }
        return app6aMilitaryUnits;
    }

    public void setApp6aMilitaryUnits(List<App6AMilitaryUnitDto> app6aMilitaryUnits) {
        this.app6aMilitaryUnits = app6aMilitaryUnits;
    }

    public List<CrisisEventDto> getCrisisEvents() {
        if (crisisEvents == null) {
            crisisEvents = new LinkedList<>();
        }
        return crisisEvents;
    }

    public void setCrisisEvents(List<CrisisEventDto> crisisEvents) {
        this.crisisEvents = crisisEvents;
    }

    public List<PointOfInterestDto> getPoi() {
        if (poi == null) {
            poi = new LinkedList<>();
        }
        return poi;
    }

    public void setPoi(List<PointOfInterestDto> poi) {
        this.poi = poi;
    }

    public List<SearchAndRescueDto> getSearchAndRescues() {
        if (searchAndRescues == null) {
            this.searchAndRescues = new LinkedList<>();
        }
        return searchAndRescues;
    }

    public void setSearchAndRescues(List<SearchAndRescueDto> searchAndRescues) {
        this.searchAndRescues = searchAndRescues;
    }

    public List<MSWiAUnitDto> getMswiaUnits() {
        if (mswiaUnits == null) {
            mswiaUnits = new LinkedList<MSWiAUnitDto>();
        }
        return mswiaUnits;
    }

    public void setMswiaUnits(List<MSWiAUnitDto> mswiaUnits) {
        this.mswiaUnits = mswiaUnits;
    }

    public List<GpxTraceDto> getGpxTraces() {
        if (gpxTraces == null) {
            gpxTraces = new LinkedList<>();
        }
        return gpxTraces;
    }

    public void setGpxTraces(List<GpxTraceDto> gpxTraces) {
        this.gpxTraces = gpxTraces;
    }

    public List<SearchAndRescueSegmentDto> getSearchAndRescuesSegments() {
        return searchAndRescuesSegments;
    }

    public void setSearchAndRescuesSegments(List<SearchAndRescueSegmentDto> searchAndRescuesSegments) {
        this.searchAndRescuesSegments = searchAndRescuesSegments;
    }

    public void addObject(UniqueObject object) {
        // TODO add other objects
        if(object instanceof SearchAndRescueDto){
            getSearchAndRescues().add((SearchAndRescueDto) object);
        }else if(object instanceof PointOfInterestDto){
            getPoi().add((PointOfInterestDto) object);
        }
    }
}
