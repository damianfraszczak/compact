package pl.edu.wat.wcy.cop.app.shared.domain.sar.searchplan;


import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.TerrainCategory;

import java.io.Serializable;
// Carries segment domain data.

public class SegmentDomainDto implements Serializable {
    private String name;
    private String description;
    private Double area;
    private TerrainCategory type;

    public SegmentDomainDto() {
    }

    public SegmentDomainDto(String name, String description, Double area, TerrainCategory type) {
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
