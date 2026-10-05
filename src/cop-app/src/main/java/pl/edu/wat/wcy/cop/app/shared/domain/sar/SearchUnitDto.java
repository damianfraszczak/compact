package pl.edu.wat.wcy.cop.app.shared.domain.sar;

import pl.edu.wat.wcy.cop.app.shared.domain.DefaultObject;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.SearchUnitType;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.HumanResourceDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.TechnicalResourceDto;

import java.util.LinkedList;
import java.util.List;
// Carries search unit data.

public class SearchUnitDto extends DefaultObject {
    private String name;

    private double sweepWidth;
    private double sweepSpeed;
    private List<HumanResourceDto> humanResources;
    private List<TechnicalResourceDto> technicalResources;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HumanResourceDto> getHumanResources() {
        if (humanResources == null) {
            humanResources = new LinkedList<>();
        }
        return humanResources;
    }

    public void setHumanResources(List<HumanResourceDto> humanResources) {
        this.humanResources = humanResources;
    }

    public List<TechnicalResourceDto> getTechnicalResources() {
        if (technicalResources == null) {
            technicalResources = new LinkedList<>();
        }
        return technicalResources;
    }

    public void setTechnicalResources(List<TechnicalResourceDto> technicalResources) {
        this.technicalResources = technicalResources;
    }

    public double getSweepWidth() {
        return sweepWidth;
    }

    public void setSweepWidth(double sweepWidth) {
        this.sweepWidth = sweepWidth;
    }

    public double getSweepSpeed() {
        return sweepSpeed;
    }

    public void setSweepSpeed(double sweepSpeed) {
        this.sweepSpeed = sweepSpeed;
    }
}
