package pl.edu.wat.wcy.cop.app.shared.domain.sar.searchplan;

import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.Qualification;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.SearchUnitType;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.HumanResourceDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.TechnicalResourceDto;

import java.io.Serializable;
import java.util.*;
// Carries search unit domain data.

public class SearchUnitDomainDto implements Serializable {
    private int id;
    private String name;

    private List<HumanResourceDto> humanResources = new LinkedList<>();
    private List<TechnicalResourceDto> technicalResources= new LinkedList<>();
    private SearchUnitType type;

    // dodane by zgadzalo sie w tabelce
    private String formationSquad;
    private String communicationResources;
    // TODO w przyszlosci zapis GPX (o ile w tym miejscu potrzebny)
    private String garmin;
    private Long leaveTime;
    private Long backTime;

    public int getNumberWithHighQualification() {
        return getNumberWithQualification(Qualification.HIGH);
    }

    public int getNumberWithLowQualification() {
        return getNumberWithQualification(Qualification.LOW);
    }

    public int getNumberWithQualification(Qualification qualification) {
        Map<Qualification, Integer> assignedForceAmount = new HashMap<>();
        getHumanResources().forEach(hr -> {
            int currentVal = assignedForceAmount.getOrDefault(hr.getQualification(), 0);
            currentVal += hr.getNumber();
            assignedForceAmount.put(hr.getQualification(), currentVal);
        });
        return assignedForceAmount.getOrDefault(qualification, 0);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<HumanResourceDto> getHumanResources() {
        return humanResources;
    }

    public void setHumanResources(List<HumanResourceDto> humanResources) {
        this.humanResources = humanResources;
    }

    public List<TechnicalResourceDto> getTechnicalResources() {
        return technicalResources;
    }

    public void setTechnicalResources(List<TechnicalResourceDto> technicalResources) {
        this.technicalResources = technicalResources;
    }

    public SearchUnitType getType() {
        return type;
    }

    public void setType(SearchUnitType type) {
        this.type = type;
    }

    public String getFormationSquad() {
        return formationSquad;
    }

    public void setFormationSquad(String formationSquad) {
        this.formationSquad = formationSquad;
    }

    public String getCommunicationResources() {
        return communicationResources;
    }

    public void setCommunicationResources(String communicationResources) {
        this.communicationResources = communicationResources;
    }

    public String getGarmin() {
        return garmin;
    }

    public void setGarmin(String garmin) {
        this.garmin = garmin;
    }

    public Long getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Long leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Long getBackTime() {
        return backTime;
    }

    public void setBackTime(Long backTime) {
        this.backTime = backTime;
    }
}
