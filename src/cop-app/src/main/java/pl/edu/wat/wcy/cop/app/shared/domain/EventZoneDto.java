/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain;
// Carries event zone data.

public class EventZoneDto extends DefaultObject {

    private boolean visible = true;
    private String name;
    private String description;
    private FeatureStyleDto style;
    private double area;

    public EventZoneDto() {
        super();
    }

    public EventZoneDto(String name) {
        super();
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible
     *            the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public FeatureStyleDto getStyle() {
        if (style == null) {
            style = new FeatureStyleDto();
        }

        return style;
    }

    public void setStyle(FeatureStyleDto style) {
        this.style = style;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
