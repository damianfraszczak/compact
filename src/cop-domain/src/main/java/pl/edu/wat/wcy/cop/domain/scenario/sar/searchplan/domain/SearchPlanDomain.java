package pl.edu.wat.wcy.cop.domain.scenario.sar.searchplan.domain;

import pl.edu.wat.wcy.cop.domain.scenario.sar.enums.SearchPlanState;
import pl.edu.wat.wcy.cop.domain.scenario.sar.resources.HumanResource;
import pl.edu.wat.wcy.cop.domain.scenario.sar.resources.TechnicalResource;

import javax.persistence.Transient;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Stores domain data for search plans.
public class SearchPlanDomain  implements Serializable {
    private List<SegmentWorkset> segmentWorksets;
    private Double probabilityOfSuccess;
    private Double probabilityRoW;
    private Date processingTime;
    // Nie wyświetlać i nie zapisywać.
    private double duration;

    private List<HumanResource> humanResources;
    private List<TechnicalResource> technicalResources;

    private SearchPlanState state = SearchPlanState.PLANNED;
    private Date startedDate;
    private Date finishedDate;


    public SearchPlanDomain() {
        segmentWorksets = new ArrayList<>();
        processingTime = new Date();
        probabilityOfSuccess = 0.0;
        probabilityRoW = 0.0;
    }

    public List<SegmentWorkset> getSegmentWorksets() {
        return segmentWorksets;
    }

    public void setSegmentWorksets(List<SegmentWorkset> segmentWorksets) {
        this.segmentWorksets = segmentWorksets;
    }

    public Double getProbabilityOfSuccess() {
        return probabilityOfSuccess;
    }

    public void setProbabilityOfSuccess(Double probabilityOfSuccess) {
        this.probabilityOfSuccess = probabilityOfSuccess;
    }

    public Double getProbabilityRoW() {
        return probabilityRoW;
    }

    public void setProbabilityRoW(Double probabilityRoW) {
        this.probabilityRoW = probabilityRoW;
    }

    public Date getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(Date processingTime) {
        this.processingTime = processingTime;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void print(PrintStream printStream) {
        printStream.println();
        printStream.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        printStream.printf("Pos=%1$7.4f, Prow=%2$7.4f, DT=%3$7.3f%n", probabilityOfSuccess, probabilityRoW, duration / 3600);
        printStream.println("Seg | TT |    Area |     Poc |     Pod |      ec |     esr |      sa | ut |  e |  u ");
        int i = 0;
        for (SegmentWorkset sw : segmentWorksets) {
            double area_ha = sw.getSegment().getArea() / 10000;
            printStream.printf("%1$3d |%2$3d |%3$8.2f |%4$8.4f |%5$8.4f |%6$8.2f |%7$8.1f |%8$8.2f |",
                    i, sw.getSegment().getType().id, area_ha, sw.getPoa(), sw.getPod(), sw.getEc(), sw.getEsr() * 0.36, sw.getSweptArea() / 10000);
            if (sw.getUnits().size() == 0) {
                printStream.println("  - |  - |  - ");
            } else {
                SearchUnitDomain su = sw.getUnits().get(0);
                printStream.printf("%1$3d |%2$3d |%3$3d%n", su.getType().id, su.getHumanResources().get(0).number,
                        su.getHumanResources().get(1).number);
                for (int m = 1; m < sw.getUnits().size(); m++) {
                    printStream.print("                                                                     |");
                    su = sw.getUnits().get(m);
                    printStream.printf("%1$3d |%2$3d |%3$3d%n", su.getType().id, su.getHumanResources().get(0).number,
                            su.getHumanResources().get(1).number);
                }
            }
            i++;
        }
        printStream.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

    //MOJE ZMIANY

    public List<HumanResource> getHumanResources() {
        return humanResources;
    }

    public void setHumanResources(List<HumanResource> humanResources) {
        this.humanResources = humanResources;
    }

    public List<TechnicalResource> getTechnicalResources() {
        return technicalResources;
    }

    public void setTechnicalResources(List<TechnicalResource> technicalResources) {
        this.technicalResources = technicalResources;
    }

    public SearchPlanState getState() {
        return state;
    }

    public void setState(SearchPlanState state) {
        this.state = state;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void fillComputedValues(SearchPlanDomain computedPlan) {
        this.segmentWorksets = computedPlan.segmentWorksets;
        this.probabilityOfSuccess = computedPlan.probabilityOfSuccess;
        this.probabilityRoW = computedPlan.probabilityRoW;
        this.processingTime = computedPlan.processingTime;

    }

    private transient boolean regeneratePlan;

    public boolean isRegeneratePlan() {
        return regeneratePlan;
    }

}
