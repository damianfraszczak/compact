package pl.edu.wat.wcy.cop.domain.scenario.sar;

import pl.edu.wat.wcy.cop.domain.GeoPoint;
import pl.edu.wat.wcy.cop.domain.scenario.FeatureStyle;
import pl.edu.wat.wcy.cop.domain.scenario.ScenarioObject;
import pl.edu.wat.wcy.cop.domain.scenario.sar.enums.TerrainCategory;

import javax.persistence.*;
import java.util.List;

@Entity
// Stores data for a search-and-rescue segment.
public class SearchAndRescueSegment extends ScenarioObject {
    private String name;
    private String description;
    private Double area;
    private Boolean visible = true;
    private FeatureStyle style;
    private Double sweptArea;

    @Enumerated(EnumType.STRING)
    private TerrainCategory terrainCategory;

    private Integer type;
    private Double poc;
    private Double aes;

    @Transient
    private boolean regeneratePoc;

    public SearchAndRescueSegment() {
    }

    public SearchAndRescueSegment(int type, double area, double poc, double aes) {
        this.type = type;
        this.area = area;
        this.poc = poc;
        this.aes = aes;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    private List<GeoPoint> points;

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

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public FeatureStyle getStyle() {
        return style;
    }

    public void setStyle(FeatureStyle style) {
        this.style = style;
    }

    /**
     * @return the points
     */
    public List<GeoPoint> getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(List<GeoPoint> points) {
        this.points = points;
    }

    public TerrainCategory getTerrainCategory() {
        return terrainCategory;
    }

    public void setTerrainCategory(TerrainCategory terrainCategory) {
        this.terrainCategory = terrainCategory;
    }


    public Integer getType() {

        if (type == null) {
            type = 0;
        }
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getPoc() {
        return poc;
    }

    public void setPoc(Double poc) {
        this.poc = poc;
    }

    public double getAes() {
        return aes;
    }

    public void setAes(double aes) {
        this.aes = aes;
    }

    public boolean isRegeneratePoc() {
        return regeneratePoc;
    }

    public void setRegeneratePoc(boolean regeneratePoc) {
        this.regeneratePoc = regeneratePoc;
    }

    public Double getSweptArea() {
        return sweptArea;
    }

    public void setSweptArea(Double sweptArea) {
        this.sweptArea = sweptArea;
    }
}
