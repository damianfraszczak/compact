/**
 * 
 */
package pl.edu.wat.wcy.cop.domain.map;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.edu.wat.wcy.cop.domain.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * 
 * @author Damian Frąszczak
 * @since 16 lut 2017
 */
@Entity
// Stores map view configuration settings.
public class MapConfiguration extends BaseEntity {

	private double centerLat = 52.14;
	private double centerLon = 21;
	private double zoom = 10;
	private double maxZoom = 28;
	private double minZoom = 0;
	private double zoomFactor = 2;
	@Enumerated(EnumType.STRING)
	private MapProjection projection;
	@ElementCollection(targetClass = MapControlType.class, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<MapControlType> controls;
	@ElementCollection(targetClass = MapInteractionType.class, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<MapInteractionType> interactions;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<MapLayer> layers;
	@Enumerated(EnumType.STRING)
	private MapScaleLineUnit scaleUnit;
	@Enumerated(EnumType.STRING)
	private MapCordsFormattingType cordsFormattingType;

	/**
	 * @return the centerLat
	 */
	public double getCenterLat() {
		return centerLat;
	}

	/**
	 * @param centerLat
	 *            the centerLat to set
	 */
	public void setCenterLat(double centerLat) {
		this.centerLat = centerLat;
	}

	/**
	 * @return the centerLon
	 */
	public double getCenterLon() {
		return centerLon;
	}

	/**
	 * @param centerLon
	 *            the centerLon to set
	 */
	public void setCenterLon(double centerLon) {
		this.centerLon = centerLon;
	}

	/**
	 * @return the zoom
	 */
	public double getZoom() {
		return zoom;
	}

	/**
	 * @param zoom
	 *            the zoom to set
	 */
	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

	/**
	 * @return the maxZoom
	 */
	public double getMaxZoom() {
		return maxZoom;
	}

	/**
	 * @param maxZoom
	 *            the maxZoom to set
	 */
	public void setMaxZoom(double maxZoom) {
		this.maxZoom = maxZoom;
	}

	/**
	 * @return the minZoom
	 */
	public double getMinZoom() {
		return minZoom;
	}

	/**
	 * @param minZoom
	 *            the minZoom to set
	 */
	public void setMinZoom(double minZoom) {
		this.minZoom = minZoom;
	}

	/**
	 * @return the zoomFactor
	 */
	public double getZoomFactor() {
		return zoomFactor;
	}

	/**
	 * @param zoomFactor
	 *            the zoomFactor to set
	 */
	public void setZoomFactor(double zoomFactor) {
		this.zoomFactor = zoomFactor;
	}

	/**
	 * @return the projection
	 */
	public MapProjection getProjection() {
		return projection;
	}

	/**
	 * @param projection
	 *            the projection to set
	 */
	public void setProjection(MapProjection projection) {
		this.projection = projection;
	}

	/**
	 * @return the controls
	 */
	public List<MapControlType> getControls() {
		return controls;
	}

	/**
	 * @param controls
	 *            the controls to set
	 */
	public void setControls(List<MapControlType> controls) {
		this.controls = controls;
	}

	/**
	 * @return the interactions
	 */
	public List<MapInteractionType> getInteractions() {
		return interactions;
	}

	/**
	 * @param interactions
	 *            the interactions to set
	 */
	public void setInteractions(List<MapInteractionType> interactions) {
		this.interactions = interactions;
	}

	/**
	 * @return the layers
	 */
	public List<MapLayer> getLayers() {
		return layers;
	}

	/**
	 * @param layers
	 *            the layers to set
	 */
	public void setLayers(List<MapLayer> layers) {
		this.layers = layers;
	}

	/**
	 * @return the scaleUnit
	 */
	public MapScaleLineUnit getScaleUnit() {
		return scaleUnit;
	}

	/**
	 * @param scaleUnit
	 *            the scaleUnit to set
	 */
	public void setScaleUnit(MapScaleLineUnit scaleUnit) {
		this.scaleUnit = scaleUnit;
	}

	/**
	 * @return the cordsFormattingType
	 */
	public MapCordsFormattingType getCordsFormattingType() {
		return cordsFormattingType;
	}

	/**
	 * @param cordsFormattingType
	 *            the cordsFormattingType to set
	 */
	public void setCordsFormattingType(MapCordsFormattingType cordsFormattingType) {
		this.cordsFormattingType = cordsFormattingType;
	}

}
