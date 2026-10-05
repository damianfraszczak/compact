/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.response.capability;

import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayerType;
import pl.edu.wat.wcy.cop.app.shared.ol.sources.OLBingLayerSource;
import pl.edu.wat.wcy.cop.app.shared.ol.sources.OLWeatherLayerSources;
// Represents ol layer capability.


public class OLLayerCapability {
    String layerType = ResponseUtils.oneOfOptions(OLLayerType.values());
    private String name = ResponseUtils.STRING_NAME;
    private String zIndex = ResponseUtils.INTEGER_NAME;
    private String visible = ResponseUtils.BOOLEAN_NAME;
    private String opacity = ResponseUtils.DOUBLE_NAME;
    private String minResolution = ResponseUtils.DOUBLE_NAME;
    private String maxResolution = ResponseUtils.DOUBLE_NAME;

    // IMAGE
    private String imageWidth = ResponseUtils.IF_SEPCIFIC_TYPE + "IMAGE," + ResponseUtils.DOUBLE_NAME;
    private String imageHeight = ResponseUtils.IF_SEPCIFIC_TYPE + "IMAGE," + ResponseUtils.DOUBLE_NAME;
    // IMAGE WMS WMTS XYZ
    private String url = ResponseUtils.IF_SEPCIFIC_TYPE + "IMAGE, WMS, WMTS, XYZ," + ResponseUtils.STRING_NAME;

    // TILE
    private String type = ResponseUtils.oneOfOptions(OLLayerType.values());
    private String bingSource = ResponseUtils.IF_SEPCIFIC_TYPE + "BING,"
            + ResponseUtils.oneOfOptions(OLBingLayerSource.values());
    private String weatherSource = ResponseUtils.IF_SEPCIFIC_TYPE + "WEAATHER,"
            + ResponseUtils.oneOfOptions(OLWeatherLayerSources.values());
    // WMS
    private String layers = ResponseUtils.IF_SEPCIFIC_TYPE + "WMS," + ResponseUtils.STRING_NAME;
    private String ratio = ResponseUtils.IF_SEPCIFIC_TYPE + "WMS," + ResponseUtils.DOUBLE_NAME;

    // WMTS
    private String layer = ResponseUtils.IF_SEPCIFIC_TYPE + "WMTS," + ResponseUtils.STRING_NAME;
    private String format = ResponseUtils.IF_SEPCIFIC_TYPE + "WMTS," + ResponseUtils.STRING_NAME;
    private String matrixStyle = ResponseUtils.IF_SEPCIFIC_TYPE + "WMTS," + ResponseUtils.STRING_NAME;
    private String style = ResponseUtils.IF_SEPCIFIC_TYPE + "WMTS," + ResponseUtils.STRING_NAME;
    private String projection = ResponseUtils.IF_SEPCIFIC_TYPE + "WMTS," + ResponseUtils.STRING_NAME;
    private String wrapX = ResponseUtils.IF_SEPCIFIC_TYPE + "WMTS," + ResponseUtils.BOOLEAN_NAME;

    /**
     * @return the layerType
     */
    public String getLayerType() {
        return layerType;
    }

    /**
     * @param layerType
     *            the layerType to set
     */
    public void setLayerType(String layerType) {
        this.layerType = layerType;
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
     * @return the zIndex
     */
    public String getzIndex() {
        return zIndex;
    }

    /**
     * @param zIndex
     *            the zIndex to set
     */
    public void setzIndex(String zIndex) {
        this.zIndex = zIndex;
    }

    /**
     * @return the visible
     */
    public String getVisible() {
        return visible;
    }

    /**
     * @param visible
     *            the visible to set
     */
    public void setVisible(String visible) {
        this.visible = visible;
    }

    /**
     * @return the opacity
     */
    public String getOpacity() {
        return opacity;
    }

    /**
     * @param opacity
     *            the opacity to set
     */
    public void setOpacity(String opacity) {
        this.opacity = opacity;
    }

    /**
     * @return the minResolution
     */
    public String getMinResolution() {
        return minResolution;
    }

    /**
     * @param minResolution
     *            the minResolution to set
     */
    public void setMinResolution(String minResolution) {
        this.minResolution = minResolution;
    }

    /**
     * @return the maxResolution
     */
    public String getMaxResolution() {
        return maxResolution;
    }

    /**
     * @param maxResolution
     *            the maxResolution to set
     */
    public void setMaxResolution(String maxResolution) {
        this.maxResolution = maxResolution;
    }

    /**
     * @return the imageWidth
     */
    public String getImageWidth() {
        return imageWidth;
    }

    /**
     * @param imageWidth
     *            the imageWidth to set
     */
    public void setImageWidth(String imageWidth) {
        this.imageWidth = imageWidth;
    }

    /**
     * @return the imageHeight
     */
    public String getImageHeight() {
        return imageHeight;
    }

    /**
     * @param imageHeight
     *            the imageHeight to set
     */
    public void setImageHeight(String imageHeight) {
        this.imageHeight = imageHeight;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the layers
     */
    public String getLayers() {
        return layers;
    }

    /**
     * @param layers
     *            the layers to set
     */
    public void setLayers(String layers) {
        this.layers = layers;
    }

    /**
     * @return the ratio
     */
    public String getRatio() {
        return ratio;
    }

    /**
     * @param ratio
     *            the ratio to set
     */
    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    /**
     * @return the bingSource
     */
    public String getBingSource() {
        return bingSource;
    }

    /**
     * @param bingSource
     *            the bingSource to set
     */
    public void setBingSource(String bingSource) {
        this.bingSource = bingSource;
    }

    /**
     * @return the weatherSource
     */
    public String getWeatherSource() {
        return weatherSource;
    }

    /**
     * @param weatherSource
     *            the weatherSource to set
     */
    public void setWeatherSource(String weatherSource) {
        this.weatherSource = weatherSource;
    }

    /**
     * @return the layer
     */
    public String getLayer() {
        return layer;
    }

    /**
     * @param layer
     *            the layer to set
     */
    public void setLayer(String layer) {
        this.layer = layer;
    }

    /**
     * @return the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format
     *            the format to set
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * @return the matrixStyle
     */
    public String getMatrixStyle() {
        return matrixStyle;
    }

    /**
     * @param matrixStyle
     *            the matrixStyle to set
     */
    public void setMatrixStyle(String matrixStyle) {
        this.matrixStyle = matrixStyle;
    }

    /**
     * @return the style
     */
    public String getStyle() {
        return style;
    }

    /**
     * @param style
     *            the style to set
     */
    public void setStyle(String style) {
        this.style = style;
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
     * @return the wrapX
     */
    public String getWrapX() {
        return wrapX;
    }

    /**
     * @param wrapX
     *            the wrapX to set
     */
    public void setWrapX(String wrapX) {
        this.wrapX = wrapX;
    }

}
