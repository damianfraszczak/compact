package pl.edu.wat.wcy.cop.app.shared.domain.sar.searchplan;

import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.Qualification;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.SearchUnitType;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
// Carries formation crew data.

public class FormationCrewDto {
    private SearchUnitType searchUnitType;
    private Map<Qualification, Integer> crew = new HashMap<>();
    // dodane by zgadzalo sie w tabelce
    private String formationSquad;
    private String communicationResources;
    // TODO w przyszlosci zapis GPX (o ile w tym miejscu potrzebny)
    private String garmin;
    private long leaveTime;
    private long backTime;

    private int esr;
    private int ec;
    private double pod;

    public SearchUnitType getSearchUnitType() {
        return searchUnitType;
    }

    public void setSearchUnitType(SearchUnitType searchUnitType) {
        this.searchUnitType = searchUnitType;
    }

    public Map<Qualification, Integer> getCrew() {
        if (crew == null) {
            crew = new HashMap<>();
        }
        return crew;
    }

    public void setCrew(Map<Qualification, Integer> crew) {
        this.crew = crew;
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

    public long getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(long leaveTime) {
        this.leaveTime = leaveTime;
    }

    public long getBackTime() {
        return backTime;
    }

    public void setBackTime(long backTime) {
        this.backTime = backTime;
    }

    public int getEsr() {
        return esr;
    }

    public void setEsr(int esr) {
        this.esr = esr;
    }

    public int getEc() {
        return ec;
    }

    public void setEc(int ec) {
        this.ec = ec;
    }

    public double getPod() {
        return pod;
    }

    public void setPod(double pod) {
        this.pod = pod;
    }
}
