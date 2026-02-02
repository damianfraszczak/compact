/**
 * 
 */
package pl.edu.wat.wcy.cop.domain.scenario;

import pl.edu.wat.wcy.cop.domain.BaseEntity;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;


@MappedSuperclass
// Defines a generic geographic zone.
public class Zone extends BaseEntity {


	private String name;
	private String description;
	private Double area;
	private Boolean visible = true;
	private FeatureStyle style;


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
	public boolean getVisible() {
		return visible == null ? false : visible;
	}

	/**
	 * @param visible
	 *            the visible to set
	 */
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public FeatureStyle getStyle() {
		return style;
	}

	public void setStyle(FeatureStyle style) {
		this.style = style;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}
}
