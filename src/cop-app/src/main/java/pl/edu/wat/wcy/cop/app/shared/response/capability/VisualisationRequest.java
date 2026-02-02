/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.response.capability;

import java.util.Arrays;
import java.util.List;
// Represents a visualisation request.


public class VisualisationRequest {
    private OLMapOptionsCapability mapConfiguration = new OLMapOptionsCapability();

    private List<App6AMilitaryUnitCapability> app6aMilitaryUnits = Arrays.asList(new App6AMilitaryUnitCapability());
    private List<CrisisEventCapability> crisisEvents = Arrays.asList(new CrisisEventCapability());
    private List<PointOfInterestCapability> poi = Arrays.asList(new PointOfInterestCapability());
    private List<MSWiAUnitCapability> mswiaUnits = Arrays.asList(new MSWiAUnitCapability());
    private List<ADatP3ReportCapability> reportsADatP3 = Arrays.asList(new ADatP3ReportCapability());

    /**
     * @return the mapConfiguration
     */
    public OLMapOptionsCapability getMapConfiguration() {
        return mapConfiguration;
    }

    /**
     * @param mapConfiguration
     *            the mapConfiguration to set
     */
    public void setMapConfiguration(OLMapOptionsCapability mapConfiguration) {
        this.mapConfiguration = mapConfiguration;
    }

    /**
     * @return the app6aMilitaryUnits
     */
    public List<App6AMilitaryUnitCapability> getApp6aMilitaryUnits() {
        return app6aMilitaryUnits;
    }

    /**
     * @param app6aMilitaryUnits
     *            the app6aMilitaryUnits to set
     */
    public void setApp6aMilitaryUnits(List<App6AMilitaryUnitCapability> app6aMilitaryUnits) {
        this.app6aMilitaryUnits = app6aMilitaryUnits;
    }

    /**
     * @return the crisisEvents
     */
    public List<CrisisEventCapability> getCrisisEvents() {
        return crisisEvents;
    }

    /**
     * @param crisisEvents
     *            the crisisEvents to set
     */
    public void setCrisisEvents(List<CrisisEventCapability> crisisEvents) {
        this.crisisEvents = crisisEvents;
    }

    /**
     * @return the poi
     */
    public List<PointOfInterestCapability> getPoi() {
        return poi;
    }

    /**
     * @param poi
     *            the poi to set
     */
    public void setPoi(List<PointOfInterestCapability> poi) {
        this.poi = poi;
    }

    /**
     * @return the mswiaUnits
     */
    public List<MSWiAUnitCapability> getMswiaUnits() {
        return mswiaUnits;
    }

    /**
     * @param mswiaUnits
     *            the mswiaUnits to set
     */
    public void setMswiaUnits(List<MSWiAUnitCapability> mswiaUnits) {
        this.mswiaUnits = mswiaUnits;
    }

    /**
     * @return the reportsADatP3
     */
    public List<ADatP3ReportCapability> getReportsADatP3() {
        return reportsADatP3;
    }

    /**
     * @param reportsADatP3
     *            the reportsADatP3 to set
     */
    public void setReportsADatP3(List<ADatP3ReportCapability> reportsADatP3) {
        this.reportsADatP3 = reportsADatP3;
    }

}
