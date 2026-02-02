package pl.edu.wat.wcy.cop.domain.scenario.sar.searchplan.domain;

import pl.edu.wat.wcy.cop.domain.scenario.sar.searchplan.Qualification;

import java.io.Serializable;

// Stores domain data for human resources.
public class HumanResourceDomain extends SearchResourceDomain implements Serializable {
    private Qualification qualification;

    public HumanResourceDomain() {}

    public HumanResourceDomain(int number, Qualification qualification) {
        super(number);
        this.qualification = qualification;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        return "{" +
                "q=" + qualification +
                ", n=" + number +
                '}';
    }
}
