/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.ol;

import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayerType;
import pl.edu.wat.wcy.cop.app.shared.ol.sources.OLMapBoxLayerSource;
import pl.edu.wat.wcy.cop.app.shared.ol.sources.OLTileLayerSource;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
// Represents ol map options.


public class OLMapOptions {

    private double centerLat = 52.14;
    private double centerLon = 21;
    private double zoom = 10;
    private double maxZoom = 28;
    private double minZoom = 0;
    private double zoomFactor = 2;
    private OLProjection projection = OLProjection.MERCARTOR;
    private List<OLControlType> controls = new LinkedList<>(Arrays.asList(OLControlType.values()));
    private List<OLInteractionType> interactions = new LinkedList<>(Arrays.asList(OLInteractionType.values()));
    private List<OLLayer> layers = new LinkedList<>(Arrays.asList(new OLLayer[]{
            new OLLayer(OLLayerType.TILE, "OSM"),
            new OLLayer(OLLayerType.MAPBOX, "MAPBOX", OLMapBoxLayerSource.SATELITE.getUrl())
    }));
    private OLScaleLineUnit scaleUnit = OLScaleLineUnit.METRIC;
    private OLCordsFormattingType cordsFormattingType = OLCordsFormattingType.DMS;

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
    public OLProjection getProjection() {
        return projection;
    }

    /**
     * @param projection
     *            the projection to set
     */
    public void setProjection(OLProjection projection) {
        this.projection = projection;
    }

    /**
     * @return the controls
     */
    public List<OLControlType> getControls() {
        return controls;
    }

    /**
     * @param controls
     *            the controls to set
     */
    public void setControls(List<OLControlType> controls) {
        this.controls = controls;
    }

    /**
     * @return the interactions
     */
    public List<OLInteractionType> getInteractions() {
        return interactions;
    }

    /**
     * @param interactions
     *            the interactions to set
     */
    public void setInteractions(List<OLInteractionType> interactions) {
        this.interactions = interactions;
    }

    /**
     * @return the layers
     */
    public List<OLLayer> getLayers() {
        return layers;
    }

    /**
     * @param layers
     *            the layers to set
     */
    public void setLayers(List<OLLayer> layers) {
        this.layers = layers;
    }

    /**
     * @return the scaleUnits
     */
    public OLScaleLineUnit getScaleUnit() {
        return scaleUnit;
    }

    /**
     * @param scaleUnits
     *            the scaleUnit to set
     */
    public void setScaleUnit(OLScaleLineUnit scaleUnit) {
        this.scaleUnit = scaleUnit;
    }

    /**
     * @return the cordsFormattingType
     */
    public OLCordsFormattingType getCordsFormattingType() {
        return cordsFormattingType;
    }

    /**
     * @param cordsFormattingType
     *            the cordsFormattingType to set
     */
    public void setCordsFormattingType(OLCordsFormattingType cordsFormattingType) {
        this.cordsFormattingType = cordsFormattingType;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OLMapOptions [centerLat=" + centerLat + ", centerLon=" + centerLon + ", zoom=" + zoom + ", maxZoom="
                + maxZoom + ", minZoom=" + minZoom + ", zoomFactor=" + zoomFactor + ", projection=" + projection
                + ", controls=" + controls + ", interactions=" + interactions + ", layers=" + layers + "]";
    }

}
