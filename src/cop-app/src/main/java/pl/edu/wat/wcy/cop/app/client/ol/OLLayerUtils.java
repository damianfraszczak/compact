/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol;

import ol.*;
import ol.format.JsonFeature;
import ol.layer.Image;
import ol.layer.Tile;
import ol.layer.Vector;
import ol.layer.*;
import ol.proj.Projection;
import ol.proj.ProjectionOptions;
import ol.source.*;
import pl.edu.wat.wcy.cop.app.client.ol.extra.BingMapsOptions;
import pl.edu.wat.wcy.cop.app.client.ol.extra.BingMapsSource;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayerFormat;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
// Provides ol layer utilities.


public class OLLayerUtils {
    public static final String LAYER_TYPE = "LAYER_TYPE";

    /**
     * @param destinationLayer
     * @param features
     */
    public static void removeFeatures(ol.source.Vector destinationLayer, Feature... features) {
        for (Feature feature : features) {
            if (feature != null) {
                destinationLayer.removeFeature(feature);
            }
        }

    }

    /**
     * @param layer
     * @return
     */
    public static OLLayer toOLLayer(Base layer) {
        OLLayer olLayer = new OLLayer();
        setOLLayerOptions(olLayer, layer);
        return olLayer;
    }

    /**
     *
     * @param layer
     * @return
     */
    public static Layer toLayer(OLLayer layer) {
        switch (layer.getLayerType()) {
            // case IMAGE:
            case KML:
            case VECTOR:
                return createVectorOLLayer(layer);
            case VECTOR_TILE:
                return createVectorTileOLLayer(layer);
            case TILE:
                return createOLTileLayer(layer);
            case WMS:
                return createWmsOLLayer(layer);
            case WMTS:
                return createWmtsOLLayer(layer);
            case XYZ:
            case MAPBOX:
                return createXyzOLLayer(layer);
            case BING:
                return createBingLayer(layer);
            case WEATHER:
                return createWeatherLayer(layer);
            case IMAGE:
                return createImageOLLayer(layer);
            case HEATMAP:
                return createHeatmapOLLayer(layer);
            default:
                break;
        }

        return null;
    }

    /**
     * @param layer
     * @return
     */
    public static Layer createVectorTileOLLayer(OLLayer layer) {
        Layer ollayer = OLNativeMethods.createVectorTileLayer(layer.getUrl(), GetOLLayerFormat(layer.getLayerFormat()));
        setLayerOptions(ollayer, layer);
        return ollayer;
    }

    /**
     * @param layer
     * @return
     */
    public static Layer createWeatherLayer(OLLayer layer) {
        layer.setUrl(layer.getWeatherSource().getUrl());
        return createXyzOLLayer(layer);
    }

    /**
     * @param layer
     * @return
     */
    public static Layer createImageOLLayer(OLLayer layer) {
        ProjectionOptions projectionOptions = OLFactory.createOptions();
        Extent imageExtent = OLFactory.createExtent(0, 0, layer.getImageHeight(), layer.getImageWidth());
        projectionOptions.setCode("pixel");
        projectionOptions.setExtent(imageExtent);
        projectionOptions.setUnits("pixels");
        Projection projection = new Projection(projectionOptions);
        ImageStaticOptions imageStaticOptions = OLFactory.createOptions();
        imageStaticOptions.setUrl(layer.getUrl());
        imageStaticOptions.setImageSize(OLFactory.createSize(layer.getImageHeight(), layer.getImageWidth()));
        imageStaticOptions.setImageExtent(imageExtent);
        imageStaticOptions.setProjection(projection);
        ImageStatic imageStatic = new ImageStatic(imageStaticOptions);
        LayerOptions layerOptions = OLFactory.createOptions();
        layerOptions.setSource(imageStatic);
        Image imageLayer = new Image(layerOptions);
        setLayerOptions(imageLayer, layer);
        return imageLayer;
    }

    public static Layer createBingLayer(OLLayer layer) {
        BingMapsOptions sourceOptions = OLFactory.createOptions();
        sourceOptions.setImagerySet("Road");
        sourceOptions.setKey(AppConstants.BING_MAPS_KEY);
        BingMapsSource source = new BingMapsSource(sourceOptions);
        LayerOptions bingLayerOptions = OLFactory.createOptions();
        bingLayerOptions.setSource(source);
        Tile bingLayer = new Tile(bingLayerOptions);
        setLayerOptions(bingLayer, layer);
        return bingLayer;
    }

    /**
     * @param layer
     * @return
     */
    public static Layer createWmtsOLLayer(OLLayer layer) {
        Projection projection = Projection.get(layer.getProjection());
        WmtsOptions wmtsOptions = OLFactory.createOptions();
        wmtsOptions.setUrl(layer.getUrl());
        wmtsOptions.setLayer(layer.getLayer());
        wmtsOptions.setFormat(layer.getFormat());
        wmtsOptions.setMatrixSet(layer.getMatrixStyle());
        wmtsOptions.setStyle(layer.getStyle());
        wmtsOptions.setWrapX(layer.isWrapX());
        wmtsOptions.setProjection(projection);
        wmtsOptions.setTileGrid(OLMapUtils.createWmtsTileGrid(projection));
        Wmts wmtsSource = new Wmts(wmtsOptions);
        LayerOptions wmtsLayerOptions = OLFactory.createOptions();
        wmtsLayerOptions.setSource(wmtsSource);
        Tile wmtsLayer = new Tile(wmtsLayerOptions);
        setLayerOptions(wmtsLayer, layer);
        return wmtsLayer;
    }

    /**
     * @param layer
     * @return
     */
    public static Layer createXyzOLLayer(OLLayer layer) {
        XyzOptions xyzOptions = OLFactory.createOptions();
        xyzOptions.setUrl(layer.getUrl());
        Xyz xyzSource = new Xyz(xyzOptions);
        LayerOptions xyzLayerOptions = OLFactory.createOptions();
        xyzLayerOptions.setSource(xyzSource);
        Tile xyzLayer = new Tile(xyzLayerOptions);
        setLayerOptions(xyzLayer, layer);
        return xyzLayer;
    }

    public static Layer createVectorOLLayer(OLLayer layer) {
        if (layer.getLayerFormat() == null) {
            layer.setLayerFormat(OLLayerFormat.KML);
        }
        Layer ollayer = OLNativeMethods.createVectorLayer(layer.getUrl(), GetOLLayerFormat(layer.getLayerFormat()),
                OLLayerFormat.KML.equals(layer.getLayerFormat()));
        setLayerOptions(ollayer, layer);
        return ollayer;
    }

    /**
     * @param layerFormat
     * @return
     */
    public static JsonFeature GetOLLayerFormat(OLLayerFormat layerFormat) {
        if (layerFormat == null) {
            layerFormat = OLLayerFormat.KML;
        }
        switch (layerFormat) {
            case GEO_JSON:
                return OLNativeMethods.createGeoJsonFormat();
            case KML:
                return OLNativeMethods.createKMLFormat();
            case GPX:
                return OLNativeMethods.createGpxFormat();
            case MVT:
                OLNativeMethods.createMVTFormat();
            case TOPO_JSON:
                OLNativeMethods.createTopoJSONFormat();
            default:
                return OLNativeMethods.createKMLFormat();
        }
    }

    /**
     * @param layer
     * @return
     */
    public static Layer createHeatmapOLLayer(OLLayer layer) {
        Layer ollayer = OLNativeMethods.createHeatmapLayer(layer.getUrl());
        setLayerOptions(ollayer, layer);
        return ollayer;
    }

    /**
     * @param layer
     * @return
     */
    public static Layer createOLTileLayer(OLLayer layer) {
        XyzOptions osmSourceOptions = OLFactory.createOptions();
        osmSourceOptions.setUrl(layer.getType().getUrl());
        Osm osmSource = new Osm(osmSourceOptions);
        LayerOptions osmLayerOptions = OLFactory.createOptions();
        osmLayerOptions.setSource(osmSource);
        Tile osmLayer = new Tile(osmLayerOptions);
        setLayerOptions(osmLayer, layer);
        return osmLayer;
    }

    /**
     * @param layer
     * @return
     */
    public static Layer createWmsOLLayer(OLLayer layer) {
        ol.source.ImageWmsParams imageWMSParams = OLFactory.createOptions();
        imageWMSParams.setLayers(layer.getLayers());
        imageWMSParams.setSize(OLFactory.createSize(256, 256));
        ol.source.ImageWmsOptions imageWMSOptions = OLFactory.createOptions();
        imageWMSOptions.setUrl(layer.getUrl());
        imageWMSOptions.setParams(imageWMSParams);
        imageWMSOptions.setRatio(layer.getRatio());
        imageWMSOptions.setRatio(1);

        ol.source.ImageWms imageWMSSource = new ol.source.ImageWms(imageWMSOptions);
        LayerOptions layerOptions = OLFactory.createOptions();
        layerOptions.setSource(imageWMSSource);

        Image wmsLayer = new Image(layerOptions);
        setLayerOptions(wmsLayer, layer);
        return wmsLayer;
    }

    /**
     *
     * @param olLayer
     * @param layer
     */
    public static void setLayerOptions(Base olLayer, OLLayer layer) {
        if (layer.getMaxResolution() > 0)
            olLayer.setMaxResolution(layer.getMaxResolution());
        if (layer.getMinResolution() > 0)
            olLayer.setMinResolution(layer.getMinResolution());
        if (layer.getOpacity() > 0)
            olLayer.setOpacity(layer.getOpacity());

        olLayer.setVisible(layer.getVisible());
        if (layer.getZIndex() > 0)
            olLayer.setZIndex(layer.getZIndex());
        if (layer.getName() != null)
            OLUtil.setName(olLayer, layer.getName());

        olLayer.set(LAYER_TYPE, layer.getLayerType());
    }

    /**
     *
     * @param layer
     * @param olLayer
     */
    public static void setOLLayerOptions(OLLayer layer, Base olLayer) {
        layer.setMaxResolution(olLayer.getMaxResolution());
        layer.setMinResolution(olLayer.getMinResolution());
        layer.setOpacity(olLayer.getOpacity());
        layer.setVisible(olLayer.getVisible());
        layer.setZIndex(olLayer.getZIndex());
        layer.setName(OLUtil.getName(olLayer));

        ol.source.Source source = olLayer.get("source");
        if (source != null) {
            String url = OLNativeMethods.getLayerUrl(source);
            if (url != null) {
                layer.setUrl(url);
            }

        }
    }

    /**
     *
     * @param layerSource
     * @param name
     * @return
     */
    public static Vector createVectorLayerFromSource(ol.source.Vector layerSource, String name) {
        return createVectorLayerFromSource(layerSource, name, OLFactory.createOptions());
    }

    /**
     *
     * @param layerSource
     * @param name
     * @param vectorLayerOptions
     * @return
     */
    public static Vector createVectorLayerFromSource(ol.source.Vector layerSource, String name,
                                                     VectorLayerOptions vectorLayerOptions) {
        vectorLayerOptions.setSource(layerSource);
        Vector layer = new Vector(vectorLayerOptions);
        OLUtil.setName(layer, name);
        return layer;
    }

    /**
     *
     * @param layers
     * @param ascending
     * @return
     */
    public static List<Base> sortByLayerIndex(Collection<Base> layers, boolean ascending) {
        List<Base> list = toList(layers);
        list.sort(new Comparator<Base>() {

            @Override
            public int compare(Base o1, Base o2) {
                Map map = OLMapUtils.getMap();
                int o1Index = OLNativeMethods.getLayerIndex(map, o1);
                int o2Index = OLNativeMethods.getLayerIndex(map, o2);
                if (ascending) {
                    return Integer.compare(o1Index, o2Index);
                } else {
                    return Integer.compare(o2Index, o1Index);
                }
            }
        });
        return list;
    }

    public static <T> List<T> toList(Collection<T> col) {
        List<T> result = new LinkedList<T>();
        T[] array = col.getArray();
        for (int i = 0; i < array.length; i++) {
            result.add(array[i]);
        }
        return result;
    }

}
