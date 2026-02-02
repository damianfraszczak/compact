package pl.edu.wat.wcy.cop.app.shared.domain.sar;

import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.FeatureStyleDto;
import pl.edu.wat.wcy.cop.app.shared.domain.GeoPointDto;
import pl.edu.wat.wcy.cop.app.shared.domain.IMultiPointObject;
import pl.edu.wat.wcy.cop.app.shared.domain.ScenarioObjectDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.TerrainCategory;

import java.util.LinkedList;
import java.util.List;
// Carries search and rescue segment data.

public class SearchAndRescueSegmentDto extends ScenarioObjectDto implements IMultiPointObject {

    private String name;
    private String description;
    private double area;
    private Boolean visible = true;
    private FeatureStyleDto style = createDefaultFeatureStyle();

    private TerrainCategory terrainCategory = TerrainCategory.OPEN;


    private Double poc = 0.0;
    private double aes;
    private int type;

    private List<GeoPointDto> points;


    private FeatureStyleDto createDefaultFeatureStyle() {
        FeatureStyleDto dto = new FeatureStyleDto();
        dto.setFillColor("A52A2A");
        dto.setStrokeColor("A52A2A");
        dto.setStrokeWidth(5);
        dto.setFillAlpha(0.8);
        return dto;
    }


    public SearchAndRescueSegmentDto() {

        super();

    }

    public SearchAndRescueSegmentDto(String name, List<GeoPointDto> points) {
        super(name);
        this.points = points;

    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getArea() {
        return area;
    }


    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public FeatureStyleDto getStyle() {
        return style;
    }

    public void setStyle(FeatureStyleDto style) {
        this.style = style;
    }

    public TerrainCategory getTerrainCategory() {
        return terrainCategory;
    }

    public void setTerrainCategory(TerrainCategory terrainCategory) {
        this.terrainCategory = terrainCategory;
    }


    /**
     * @return the points
     */
    public List<GeoPointDto> getPoints() {
        if (points == null) {
            points = new LinkedList<GeoPointDto>();
        }
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(List<GeoPointDto> points) {
        this.points = points;
    }

    public void setArea(double area) {
        this.area = area;
    }


    public double getAes() {
        return aes;
    }

    public void setAes(double aes) {
        this.aes = aes;
    }


    public Double getPoc() {
        return poc;
    }

    public void setPoc(Double poc) {
        this.poc = poc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
