package pl.edu.wat.wcy.cop.domain.scenario.sar.resources;

import pl.edu.wat.wcy.cop.domain.scenario.sar.searchplan.Qualification;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
// Stores data about a human resource.
public class HumanResource extends SearchResource {
    @Enumerated(EnumType.STRING)
    private Qualification qualification;

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }
}
