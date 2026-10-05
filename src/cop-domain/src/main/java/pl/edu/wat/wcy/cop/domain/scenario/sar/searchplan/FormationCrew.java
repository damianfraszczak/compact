package pl.edu.wat.wcy.cop.domain.scenario.sar.searchplan;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
// Stores data about a crew formation.
public class FormationCrew implements Serializable {
    private SearchUnitType searchUnitType;
    private Map<Qualification, Integer> crew;

    private String formationSquad;
    private String communicationResources;
    private String garmin;
    private Date leaveTime;
    private Date backTime;
    private int esr;
    private int ec;
    private double pod;
}
