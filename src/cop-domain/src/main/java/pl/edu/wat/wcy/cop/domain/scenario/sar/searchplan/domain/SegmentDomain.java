package pl.edu.wat.wcy.cop.domain.scenario.sar.searchplan.domain;

import pl.edu.wat.wcy.cop.domain.scenario.sar.enums.TerrainCategory;

import java.io.Serializable;

// Stores domain data for a search segment.
public class SegmentDomain implements Serializable {
    private String name;
    private String description;
    private Double area;
    private TerrainCategory type;

    public SegmentDomain() {
    }

    public SegmentDomain(String name, String description, Double area, TerrainCategory type) {
        this.name = name;
        this.description = description;
        this.area = area;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getArea() {
        if(area == null){
            area = 0.0;
        }
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public TerrainCategory getType() {
        return type;
    }

    public void setType(TerrainCategory type) {
        this.type = type;
    }
}
