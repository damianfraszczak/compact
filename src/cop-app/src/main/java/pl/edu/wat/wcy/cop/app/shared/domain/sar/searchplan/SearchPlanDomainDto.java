package pl.edu.wat.wcy.cop.app.shared.domain.sar.searchplan;


import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.SearchPlanState;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.HumanResourceDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.TechnicalResourceDto;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
// Carries search plan domain data.

public class SearchPlanDomainDto implements Serializable {

    private List<SegmentWorksetDto> segmentWorksets;
    private double probabilityOfSuccess;
    private double probabilityRoW;

    private List<HumanResourceDto> humanResources;
    private List<TechnicalResourceDto> technicalResources;

    private SearchPlanState state = SearchPlanState.PLANNED;
    private long processingTime;
    private Long startedDate;
    private Long finishedDate;
    private boolean regeneratePlan;

    public List<SegmentWorksetDto> getSegmentWorksets() {
        return segmentWorksets;
    }

    public void setSegmentWorksets(List<SegmentWorksetDto> segmentWorksets) {
        this.segmentWorksets = segmentWorksets;
    }

    public double getProbabilityOfSuccess() {
        return probabilityOfSuccess;
    }

    public void setProbabilityOfSuccess(double probabilityOfSuccess) {
        this.probabilityOfSuccess = probabilityOfSuccess;
    }
    public void setProbabilityRoW(double probabilityRoW) {
        this.probabilityRoW = probabilityRoW;
    }

    public double getProbabilityRoW() {
        return probabilityRoW;
    }


    public long getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(long processingTime) {
        this.processingTime = processingTime;
    }

    public List<HumanResourceDto> getHumanResources() {
        if(humanResources == null){
            humanResources = new LinkedList<>();
        }
        return humanResources;
    }

    public void setHumanResources(List<HumanResourceDto> humanResources) {
        this.humanResources = humanResources;
    }

    public List<TechnicalResourceDto> getTechnicalResources() {

        if(technicalResources == null){
            technicalResources = new LinkedList<>();
        }
        return technicalResources;
    }

    public void setTechnicalResources(List<TechnicalResourceDto> technicalResources) {
        this.technicalResources = technicalResources;
    }

    public SearchPlanState getState() {
        return state;
    }

    public void setState(SearchPlanState state) {
        this.state = state;
    }

    public Long getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Long startedDate) {
        this.startedDate = startedDate;
    }

    public Long getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Long finishedDate) {
        this.finishedDate = finishedDate;
    }

    public void setRegeneratePlan(boolean regeneratePlan) {
        this.regeneratePlan = regeneratePlan;
    }


}
