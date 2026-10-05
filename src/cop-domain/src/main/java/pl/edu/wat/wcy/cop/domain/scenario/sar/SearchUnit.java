package pl.edu.wat.wcy.cop.domain.scenario.sar;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.edu.wat.wcy.cop.domain.BaseEntity;
import pl.edu.wat.wcy.cop.domain.scenario.sar.resources.HumanResource;
import pl.edu.wat.wcy.cop.domain.scenario.sar.resources.TechnicalResource;

import javax.persistence.*;
import java.util.List;

@Entity
// Stores data about a search unit.
public class SearchUnit extends BaseEntity {
    private String name;
    @JoinColumn(name = "search_unit_id")
    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<HumanResource> humanResources;
    @JoinColumn(name = "search_unit_id")
    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TechnicalResource> technicalResources;
    private Double sweepWidth;
    private Double sweepSpeed;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Double getSweepWidth() {
        return sweepWidth;
    }

    public void setSweepWidth(Double sweepWidth) {
        this.sweepWidth = sweepWidth;
    }

    public Double getSweepSpeed() {
        return sweepSpeed;
    }

    public void setSweepSpeed(Double sweepSpeed) {
        this.sweepSpeed = sweepSpeed;
    }
}

