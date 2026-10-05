/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.response.capability;

import pl.edu.wat.wcy.cop.app.shared.ol.*;

import java.util.Arrays;
import java.util.List;
// Represents ol map options capability.


public class OLMapOptionsCapability {
    private String centerLat = ResponseUtils.DOUBLE_NAME;
    private String centerLon = ResponseUtils.DOUBLE_NAME;
    private String zoom = ResponseUtils.INTEGER_NAME;
    private String maxZoom = ResponseUtils.INTEGER_NAME;
    private String minZoom = ResponseUtils.INTEGER_NAME;
    private String zoomFactor = ResponseUtils.INTEGER_NAME;
    private String projection = ResponseUtils.oneOfOptions(OLProjection.values());
    private List<String> controls = ResponseUtils.listOfOptionsAsList(OLControlType.values());
    private List<String> interactions = ResponseUtils.listOfOptionsAsList(OLInteractionType.values());
    private List<OLLayerCapability> layers = Arrays.asList(new OLLayerCapability());
    private String scaleUnit = ResponseUtils.oneOfOptions(OLScaleLineUnit.values());
    private String cordsFormattingType = ResponseUtils.oneOfOptions(OLCordsFormattingType.values());

    /**
     * @return the centerLat
     */
    public String getCenterLat() {
        return centerLat;
    }

    /**
     * @param centerLat
     *            the centerLat to set
     */
    public void setCenterLat(String centerLat) {
        this.centerLat = centerLat;
    }

    /**
     * @return the centerLon
     */
    public String getCenterLon() {
        return centerLon;
    }

    /**
     * @param centerLon
     *            the centerLon to set
     */
    public void setCenterLon(String centerLon) {
        this.centerLon = centerLon;
    }

    /**
     * @return the zoom
     */
    public String getZoom() {
        return zoom;
    }

    /**
     * @param zoom
     *            the zoom to set
     */
    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    /**
     * @return the maxZoom
     */
    public String getMaxZoom() {
        return maxZoom;
    }

    /**
     * @param maxZoom
     *            the maxZoom to set
     */
    public void setMaxZoom(String maxZoom) {
        this.maxZoom = maxZoom;
    }

    /**
     * @return the minZoom
     */
    public String getMinZoom() {
        return minZoom;
    }

    /**
     * @param minZoom
     *            the minZoom to set
     */
    public void setMinZoom(String minZoom) {
        this.minZoom = minZoom;
    }

    /**
     * @return the zoomFactor
     */
    public String getZoomFactor() {
        return zoomFactor;
    }

    /**
     * @param zoomFactor
     *            the zoomFactor to set
     */
    public void setZoomFactor(String zoomFactor) {
        this.zoomFactor = zoomFactor;
    }

    /**
     * @return the projection
     */
    public String getProjection() {
        return projection;
    }

    /**
     * @param projection
     *            the projection to set
     */
    public void setProjection(String projection) {
        this.projection = projection;
    }

    /**
     * @return the controls
     */
    public List<String> getControls() {
        return controls;
    }

    /**
     * @param controls
     *            the controls to set
     */
    public void setControls(List<String> controls) {
        this.controls = controls;
    }

    /**
     * @return the interactions
     */
    public List<String> getInteractions() {
        return interactions;
    }

    /**
     * @param interactions
     *            the interactions to set
     */
    public void setInteractions(List<String> interactions) {
        this.interactions = interactions;
    }

    /**
     * @return the layers
     */
    public List<OLLayerCapability> getLayers() {
        return layers;
    }

    /**
     * @param layers
     *            the layers to set
     */
    public void setLayers(List<OLLayerCapability> layers) {
        this.layers = layers;
    }

    /**
     * @return the scaleUnit
     */
    public String getScaleUnit() {
        return scaleUnit;
    }

    /**
     * @param scaleUnit
     *            the scaleUnit to set
     */
    public void setScaleUnit(String scaleUnit) {
        this.scaleUnit = scaleUnit;
    }

    /**
     * @return the cordsFormattingType
     */
    public String getCordsFormattingType() {
        return cordsFormattingType;
    }

    /**
     * @param cordsFormattingType
     *            the cordsFormattingType to set
     */
    public void setCordsFormattingType(String cordsFormattingType) {
        this.cordsFormattingType = cordsFormattingType;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OLMapOptionsCapability [centerLat=" + centerLat + ", centerLon=" + centerLon + ", zoom=" + zoom
                + ", maxZoom=" + maxZoom + ", minZoom=" + minZoom + ", zoomFactor=" + zoomFactor + ", projection="
                + projection + ", controls=" + controls + ", interactions=" + interactions + ", layers=" + layers
                + ", scaleUnit=" + scaleUnit + "]";
    }

}
