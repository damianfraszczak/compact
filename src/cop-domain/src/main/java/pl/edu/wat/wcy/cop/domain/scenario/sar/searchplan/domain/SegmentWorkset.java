package pl.edu.wat.wcy.cop.domain.scenario.sar.searchplan.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Stores work assignments for a segment.
public class SegmentWorkset implements Serializable {
    // Nie wyświetlać.
    private Double sweptArea = 0.0;
    // Nie wyświetlać.
    private Double effectivelySweptArea = 0.0;
    private Boolean searchEnded = false;
    private Double poa = 0.0;
    private Double esr = 0.0;
    private Double pod = 0.0;
    private Double ec = 0.0;

    private SegmentDomain segment;
    private List<SearchUnitDomain> units;

    // dodane by zgadzalo sie w tabelce
    private String formationSquad;
    private String communicationResources;
    // TODO w przyszlosci zapis GPX (o ile w tym miejscu potrzebny)
    private String garmin;
    private Date leaveTime;
    private Date backTime;

    public SegmentWorkset() {
    }

    public SegmentWorkset(Double sweptArea, Double effectivelySweptArea, Double poa, SegmentDomain segment) {
        this.sweptArea = sweptArea;
        this.effectivelySweptArea = effectivelySweptArea;
        this.poa = poa;
        this.segment = segment;
        this.esr = 0.0;
        this.pod = 0.0;
        this.ec = 0.0;
        units = new ArrayList<>();
    }

    public SegmentWorkset(Double sweptArea, Double effectivelySweptArea, Double poa, Double esr, Double pod, Double ec, SegmentDomain segment) {
        this.sweptArea = sweptArea;
        this.effectivelySweptArea = effectivelySweptArea;
        this.poa = poa;
        this.esr = esr;
        this.pod = pod;
        this.ec = ec;
        this.segment = segment;
        units = new ArrayList<>();
        this.searchEnded = false;
    }

    public Double getSweptArea() {
        if(sweptArea == null){
            sweptArea = 0.0;
        }
        return sweptArea;
    }

    public void setSweptArea(Double sweptArea) {
        this.sweptArea = sweptArea;
    }

    public Double getEffectivelySweptArea() {
        if (effectivelySweptArea == null) {
            effectivelySweptArea = 0.0;
        }
        return effectivelySweptArea;
    }

    public void setEffectivelySweptArea(Double effectivelySweptArea) {
        this.effectivelySweptArea = effectivelySweptArea;
    }

    public Double getPoa() {
        return poa;
    }

    public void setPoa(Double poa) {
        this.poa = poa;
    }

    public Double getEsr() {
        return esr;
    }

    public void setEsr(Double esr) {
        this.esr = esr;
    }

    public Double getPod() {
        return pod;
    }

    public void setPod(Double pod) {
        this.pod = pod;
    }

    public Double getEc() {
        return ec;
    }

    public void setEc(Double ec) {
        this.ec = ec;
    }

    public SegmentDomain getSegment() {
        return segment;
    }

    public void setSegment(SegmentDomain segment) {
        this.segment = segment;
    }

    public List<SearchUnitDomain> getUnits() {
        return units;
    }

    public void setUnits(List<SearchUnitDomain> units) {
        this.units = units;
    }

    @Override
    public String toString() {
        String unitsStr = "[";
        for (SearchUnitDomain u : units) {
            unitsStr += u + ", ";
        }
        unitsStr += "]";
        return "SegmentWorkset{" +
                "sa=" + sweptArea +
                ", esa=" + effectivelySweptArea +
                ", poa=" + poa +
                ", esr=" + esr +
                ", pod=" + pod +
                ", ec=" + ec +
                ", segment=" + segment +
                ", units=" + unitsStr +
                '}';
    }

    public Boolean getSearchEnded() {
        return searchEnded;
    }

    public void setSearchEnded(Boolean searchEnded) {
        this.searchEnded = searchEnded;
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

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }
}
