package pl.edu.wat.wcy.cop.domain.scenario.sar.searchplan.domain;

import pl.edu.wat.wcy.cop.domain.scenario.sar.searchplan.Qualification;
import pl.edu.wat.wcy.cop.domain.scenario.sar.searchplan.SearchUnitType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Stores domain data for search units.
public class SearchUnitDomain implements Serializable{

    private String name;

    private int id;

    private List<HumanResourceDomain> humanResources;

    private List<TechnicalResourceDomain> technicalResources;

    private SearchUnitType type;

    public SearchUnitDomain() {
    }

    public SearchUnitDomain(String name, SearchUnitType type, int high, int low) {
        this.name = name;
        this.type = type;
        humanResources = new ArrayList<>();
        humanResources.add(new HumanResourceDomain(high, Qualification.HIGH));
        humanResources.add(new HumanResourceDomain(low, Qualification.LOW));
        technicalResources = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<HumanResourceDomain> getHumanResources() {
        return humanResources;
    }

    public void setHumanResources(List<HumanResourceDomain> humanResources) {
        this.humanResources = humanResources;
    }

    public List<TechnicalResourceDomain> getTechnicalResources() {
        return technicalResources;
    }

    public void setTechnicalResources(List<TechnicalResourceDomain> technicalResources) {
        this.technicalResources = technicalResources;
    }

    public SearchUnitType getType() {
        return type;
    }

    public void setType(SearchUnitType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String hr = "[";
        for (HumanResourceDomain hrd : humanResources) {
            hr += hrd.number + ", ";
        }
        hr += ']';
        return "SearchUnit{" +
                "name='" + name + '\'' +
                ", humanResources=" + hr +
                ", type=" + type +
                '}';
    }
}


