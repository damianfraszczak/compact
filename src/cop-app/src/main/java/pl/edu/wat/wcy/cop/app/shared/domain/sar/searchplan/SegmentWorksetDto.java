package pl.edu.wat.wcy.cop.app.shared.domain.sar.searchplan;

import com.google.gwt.core.client.GWT;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
// Carries segment workset data.

public class SegmentWorksetDto implements Serializable {
    private Double sweptArea;
    private double poa;
    private double esr;
    private double pod;
    private double ec;
    private SegmentDomainDto segment;
    private List<SearchUnitDomainDto> units;
    private Boolean searchEnded;
    // dodane by zgadzalo sie w tabelce
    private String formationSquad;
    private String communicationResources;
    // TODO w przyszlosci zapis GPX (o ile w tym miejscu potrzebny)
    private String garmin;
    private Long leaveTime;
    private Long backTime;

    public SegmentWorksetDto() {
    }

    public SegmentWorksetDto(SegmentDomainDto segment) {
        this.segment = segment;
    }

    public SegmentWorksetDto(double poa, SegmentDomainDto segment) {
        this.poa = poa;
        this.segment = segment;
    }

    public Double getSweptArea() {
        return sweptArea;
    }

    public void setSweptArea(Double sweptArea) {
        this.sweptArea = sweptArea;
    }

    public double getPoa() {
        return poa;
    }

    public void setPoa(double poa) {
        this.poa = poa;
    }

    public double getEsr() {
        return esr;
    }

    public void setEsr(double esr) {
        this.esr = esr;
    }

    public double getPod() {
        return pod;
    }

    public void setPod(double pod) {
        this.pod = pod;
    }

    public double getEc() {
        return ec;
    }

    public void setEc(double ec) {
        this.ec = ec;
    }

    public SegmentDomainDto getSegment() {
        return segment;
    }

    public void setSegment(SegmentDomainDto segment) {
        this.segment = segment;
    }

    public List<SearchUnitDomainDto> getUnits() {
        if(units == null){
            units = new LinkedList<>();
        }
        return units;
    }

    public void setUnits(List<SearchUnitDomainDto> units) {
        this.units = units;
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

