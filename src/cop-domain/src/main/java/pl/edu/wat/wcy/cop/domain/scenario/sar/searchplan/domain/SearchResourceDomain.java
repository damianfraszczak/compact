package pl.edu.wat.wcy.cop.domain.scenario.sar.searchplan.domain;

import java.io.Serializable;
import java.util.Date;

// Stores domain data for search resources.
public abstract class SearchResourceDomain implements Serializable {
    private Date time;
    protected int number;

    public SearchResourceDomain() {
    }

    public SearchResourceDomain(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
