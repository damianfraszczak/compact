/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.domain.spec;

import ol.Feature;
import pl.edu.wat.wcy.cop.app.client.domain.MapSymbolClientModel;
import pl.edu.wat.wcy.cop.app.shared.domain.ScenarioDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueDto;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
// Represents scenario client model.

public class ScenarioClientModel {


    private ScenarioDto scenario;
    private List<ADatP3ReportClientModel> reports;

    private List<App6AMilitaryUnitClientModel> app6aMilitaryUnits;
    private List<CrisisEventClientModel> crisisEvents;
    private List<PointOfInterestClientModel> poi;
    private List<MSWiAUnitClientModel> mswiaUnits;
    private List<SearchAndRescueClientModel> sarUnits;
    private List<SearchAndRescueSegmentClientModel> segments;
    private List<GpxTraceClientModel> gpx;

    public ScenarioClientModel(ScenarioDto scenario) {
        this(scenario, new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), new LinkedList<>(),
                new LinkedList<>(), new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
    }

    public ScenarioClientModel(ScenarioDto scenario, List<ADatP3ReportClientModel> reports,
                               List<App6AMilitaryUnitClientModel> app6aMilitaryUnits, List<CrisisEventClientModel> crisisEvents,
                               List<PointOfInterestClientModel> poi, List<MSWiAUnitClientModel> mswiaUnits,
                               List<SearchAndRescueClientModel> sarUnits, List<GpxTraceClientModel> gpx,
                               List<SearchAndRescueSegmentClientModel> segments) {
        super();
        this.scenario = scenario;
        this.reports = reports;
        this.app6aMilitaryUnits = app6aMilitaryUnits;
        this.crisisEvents = crisisEvents;
        this.poi = poi;
        this.mswiaUnits = mswiaUnits;
        this.sarUnits = sarUnits;
        this.gpx = gpx;
        this.segments = segments;
    }


    public ScenarioDto getScenario() {
        return scenario;
    }

    public void setScenario(ScenarioDto scenario) {
        this.scenario = scenario;
    }

    public List<ADatP3ReportClientModel> getReports() {
        return reports;
    }

    public void setReports(List<ADatP3ReportClientModel> reports) {
        this.reports = reports;
    }

    public List<App6AMilitaryUnitClientModel> getApp6aMilitaryUnits() {
        return app6aMilitaryUnits;
    }

    public void setApp6aMilitaryUnits(List<App6AMilitaryUnitClientModel> app6aMilitaryUnits) {
        this.app6aMilitaryUnits = app6aMilitaryUnits;
    }

    public List<CrisisEventClientModel> getCrisisEvents() {
        return crisisEvents;
    }

    public void setCrisisEvents(List<CrisisEventClientModel> crisisEvents) {
        this.crisisEvents = crisisEvents;
    }

    public List<PointOfInterestClientModel> getPoi() {
        return poi;
    }

    public void setPoi(List<PointOfInterestClientModel> poi) {
        this.poi = poi;
    }

    public List<MSWiAUnitClientModel> getMswiaUnits() {
        return mswiaUnits;
    }

    public void setMswiaUnits(List<MSWiAUnitClientModel> mswiaUnits) {
        this.mswiaUnits = mswiaUnits;
    }

    public List<SearchAndRescueClientModel> getSarUnits() {
        if (sarUnits == null) {
            sarUnits = new LinkedList<>();
        }
        return sarUnits;
    }

    public void setSarUnits(List<SearchAndRescueClientModel> sarUnits) {
        this.sarUnits = sarUnits;
    }

    public List<SearchAndRescueSegmentClientModel> getSegments() {
        return segments;
    }

    public void setSegments(List<SearchAndRescueSegmentClientModel> segments) {
        this.segments = segments;
    }

    public List<GpxTraceClientModel> getGpx() {
        return gpx;
    }

    public void setGpx(List<GpxTraceClientModel> gpx) {
        this.gpx = gpx;
    }

    /**
     * @return
     */
    public Feature[] getFeatures() {
        List<Feature> features = new LinkedList<>();
        features.addAll(getAlLFeatures(getApp6aMilitaryUnits()));
        features.addAll(getAlLFeatures(getCrisisEvents()));
        features.addAll(getAlLFeatures(getMswiaUnits()));
        features.addAll(getAlLFeatures(getPoi()));
        features.addAll(getAlLFeatures(getSarUnits()));
        features.addAll(getAlLFeatures(getGpx()));
        features.addAll(getAlLFeatures(getSegments()));
        reports.stream().map(x -> x.getReportFeatures().values()).forEach(reportFeatures -> {
            reportFeatures.forEach(x -> {
                features.addAll(Arrays.asList(x));
            });
        });
        return features.toArray(new Feature[features.size()]);
    }

    private <T extends MapSymbolClientModel> List<Feature> getAlLFeatures(Collection<T> objects) {
        List<Feature> result = new LinkedList<>();
        objects.stream().forEach(x -> result.addAll(Arrays.asList(x.getFeatures())));
        return result;
    }

    public MapSymbolClientModel getModelForObject(Object object) {
        if (object instanceof SearchAndRescueDto) {
            SearchAndRescueDto d = (SearchAndRescueDto) object;
            for (SearchAndRescueClientModel unit : getSarUnits()) {
                if (unit.getObject().equals(d)) {
                    return unit;
                }
            }

        }
        return null;
    }

    public void addClientModel(MapSymbolClientModel clientModelObjectToWorkOn) {
        // TODO dodanie pozostalych obiektow
        if(clientModelObjectToWorkOn instanceof SearchAndRescueClientModel){
            getSarUnits().add((SearchAndRescueClientModel) clientModelObjectToWorkOn);
        }
    }
}
