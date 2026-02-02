/**
 *
 */
package pl.edu.wat.wcy.cop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.cop.domain.scenario.Scenario;
import pl.edu.wat.wcy.cop.services.repositories.impl.*;

import java.util.List;


@Service
// Provides visualization operations.
public class VisualizationService {

    @Autowired
    private ScenarioRepository scenarioRepository;
    @Autowired
    private ADatP3ReportRepository adatP3ReportRepository;
    @Autowired
    private APP6AMilitaryUnitRepository app6aMilitaryUnitRepository;
    @Autowired
    private CrisisEventRepository crisisEventRepository;
    @Autowired
    private MSWiAUnitRepository mswiaRepository;
    @Autowired
    private PointOfInterestRepository pointOfInterestRepository;
    @Autowired
    private SearchAndRescueRepository sarRepository;
    @Autowired
    private SearchAndRescueSegmentRepository sarSegmentRepository;
    @Autowired
    private GpxTraceRepository gpxTraceRepository;

    @Autowired
    private SearchAndRescueService sarService;

    public Scenario createScenario(Scenario scenario) {
        scenarioRepository.save(scenario);
        return scenario;
    }

    public Scenario getScenarioById(long id) {
        return scenarioRepository.findOne(id);
    }

    /**
     * @return
     */
    public List<Long> getScenarioIds() {
        return scenarioRepository.getScenarioIds();
    }

    /**
     * @param scenarioId
     * @return
     */
    public Scenario clearScenario(Long scenarioId) {
        Scenario scenario = scenarioRepository.findOne(scenarioId);
        adatP3ReportRepository.delete(scenario.getReportsADatP3());
        app6aMilitaryUnitRepository.delete(scenario.getApp6aMilitaryUnits());
        crisisEventRepository.delete(scenario.getCrisisEvents());
        mswiaRepository.delete(scenario.getMswiaUnits());
        pointOfInterestRepository.delete(scenario.getPoi());
        sarRepository.delete(scenario.getSearchAndRescues());
        gpxTraceRepository.delete(scenario.getGpxTraces());
        sarSegmentRepository.delete(scenario.getSearchAndRescuesSegments());
        return scenarioRepository.findOne(scenarioId);
    }
}
