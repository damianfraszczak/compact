/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.ol.layers;

import pl.edu.wat.wcy.cop.app.shared.ol.OLLayerStyle;
import pl.edu.wat.wcy.cop.app.shared.ol.sources.OLBingLayerSource;
import pl.edu.wat.wcy.cop.app.shared.ol.sources.OLTileLayerSource;
import pl.edu.wat.wcy.cop.app.shared.ol.sources.OLWeatherLayerSources;
// Represents ol layer.


public class OLLayer {

    private OLLayerType layerType;
    private String name;
    private int zIndex;
    private boolean visible;
    private double opacity;
    private double minResolution;
    private double maxResolution;

    // IMAGE
    private int imageWidth;
    private int imageHeight;
    // IMAGE WMS WMTS XYZ
    private String url;

    // TILE
    private OLTileLayerSource type = OLTileLayerSource.OpenStreetMap;
    private OLBingLayerSource bingSource = OLBingLayerSource.AERIAL;
    private OLWeatherLayerSources weatherSource = OLWeatherLayerSources.CLOUDS;
    // WMS
    private String layers;
    private float ratio = 1f;

    // WMTS
    private String layer;
    private String format;
    private String matrixStyle;
    private String style;
    private String projection;
    private boolean wrapX;

    private OLLayerFormat layerFormat = null;
    private OLLayerStyle layerStyle;

    /**
     *
     */
    public OLLayer() {
        this(OLLayerType.TILE);
    }

    /**
     *
     */
    public OLLayer(OLLayerType type) {
        super();
        this.layerType = type;
        visible = true;
        opacity = 1.0;
    }

    /**
     * @param name
     */
    public OLLayer(OLLayerType type, String name) {
        this(type);
        this.name = name;
    }
    /**
     * @param name
     */
    public OLLayer(OLLayerType type, String name, String url) {
        this(type);
        this.name = name;
        this.url = url;

    }

    /**
     *
     * @param tileSource
     * @param name
     */
    public OLLayer(OLTileLayerSource tileSource, String name) {
        this(OLLayerType.TILE, name);
        this.type = tileSource;
    }

    /**
     *
     * @param bingSource
     * @param name
     */
    public OLLayer(OLBingLayerSource bingSource, String name) {
        this(OLLayerType.BING, name);
        this.bingSource = bingSource;
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
     * Return the maximum resolution of the layer.
     *
     * @return {number} The maximum resolution of the layer.
     */
    public double getMaxResolution() {
        return this.maxResolution;
    }

    /**
     * Set the maximum resolution at which the layer is visible.
     *
     * @param maxResolution
     *            The maximum resolution of the layer.
     */
    public void setMaxResolution(double maxResolution) {
        this.maxResolution = maxResolution;
    }

    /**
     * Return the minimum resolution of the layer.
     *
     * @return {number} The minimum resolution of the layer.
     */
    public double getMinResolution() {
        return this.minResolution;
    }

    /**
     * Set the minimum resolution at which the layer is visible.
     *
     * @param minResolution
     *            The minimum resolution of the layer.
     */
    public void setMinResolution(double minResolution) {
        this.minResolution = minResolution;
    }

    /**
     * Return the opacity of the layer (between 0 and 1).
     *
     * @return {number} The opacity of the layer.
     */
    public double getOpacity() {
        return this.opacity;
    }

    /**
     * Set the opacity of the layer, allowed values range from 0 to 1.
     *
     * @param opacity
     *            The opacity of the layer.
     */
    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    /**
     * Return the visibility of the layer (`true` or `false`).
     *
     * @return {boolean} The visibility of the layer.
     */
    public boolean getVisible() {
        return this.visible;
    }

    /**
     * Set the visibility of the layer (`true` or `false`).
     *
     * @param visible
     *            The visibility of the layer.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Return the Z-index of the layer, which is used to order layers before
     * rendering. The default Z-index is 0.
     *
     * @return {number} The Z-index of the layer.
     */
    public int getZIndex() {
        return this.zIndex;
    }

    /**
     * Set Z-index of the layer, which is used to order layers before rendering.
     * The default Z-index is 0.
     *
     * @param zindex
     *            The z-index of the layer.
     */
    public void setZIndex(int zindex) {
        this.zIndex = zindex;
    }

    /**
     * @return the layerType
     */
    public OLLayerType getLayerType() {
        return layerType;
    }

    /**
     * @param layerType
     *            the layerType to set
     */
    public void setLayerType(OLLayerType layerType) {
        this.layerType = layerType;
    }

    /**
     * @return the imageWidth
     */
    public int getImageWidth() {
        return imageWidth;
    }

    /**
     * @param imageWidth
     *            the imageWidth to set
     */
    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    /**
     * @return the imageHeight
     */
    public int getImageHeight() {
        return imageHeight;
    }

    /**
     * @param imageHeight
     *            the imageHeight to set
     */
    public void setImageHeight(int imageHeight) {
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
    public OLTileLayerSource getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(OLTileLayerSource type) {
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
    public float getRatio() {
        return ratio;
    }

    /**
     * @param ratio
     *            the ratio to set
     */
    public void setRatio(float ratio) {
        this.ratio = ratio;
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
    public boolean isWrapX() {
        return wrapX;
    }

    /**
     * @param wrapX
     *            the wrapX to set
     */
    public void setWrapX(boolean wrapX) {
        this.wrapX = wrapX;
    }

    /**
     * @return the bingSource
     */
    public OLBingLayerSource getBingSource() {
        return bingSource;
    }

    /**
     * @param bingSource
     *            the bingSource to set
     */
    public void setBingSource(OLBingLayerSource bingSource) {
        this.bingSource = bingSource;
    }

    /**
     * @return the weatherSource
     */
    public OLWeatherLayerSources getWeatherSource() {
        return weatherSource;
    }

    /**
     * @param weatherSource
     *            the weatherSource to set
     */
    public void setWeatherSource(OLWeatherLayerSources weatherSource) {
        this.weatherSource = weatherSource;
    }

    /**
     * @return the layerFormat
     */
    public OLLayerFormat getLayerFormat() {
        return layerFormat;
    }

    /**
     * @param layerFormat
     *            the layerFormat to set
     */
    public void setLayerFormat(OLLayerFormat layerFormat) {
        this.layerFormat = layerFormat;
    }

    public OLLayerStyle getLayerStyle() {
        return layerStyle;
    }

    public void setLayerStyle(OLLayerStyle layerStyle) {
        this.layerStyle = layerStyle;
    }
}

