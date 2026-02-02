/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import ol.Feature;
import ol.Map;
import ol.OLFactory;
import ol.OLUtil;
import ol.layer.Base;
import ol.layer.Vector;
import ol.layer.VectorLayerOptions;
import ol.style.Style;
import ol.style.StyleOptions;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.events.ObjectAddedEvent;

import java.util.*;
import java.util.stream.Collectors;


@Singleton
// Manages ol specific layers.
public class OLSpecificLayersManager {

    private static final int CLUSTERED_LAYER_DISTANCE = 10;
    @Inject
    private EventBus eventBus;

    private Messages messages;

    private Vector drawLayer;
    private ol.source.Vector drawLayerSource;

    private Vector situationLayer;
    private ol.source.Vector situationLayerSource;

    private Vector floodLayer;
    private ol.source.Vector floodLayerSource;

    private Vector measurePersistOverlay;
    private ol.source.Vector measurePersistOverlaySource;

    private Vector clusterSituationLayer;

    private Set<Base> anayliticalLayers = new HashSet<>();
    private Set<Base> operationalLayers = new HashSet<>();

    private Set<Base> notVisibleLayers = new HashSet<>();

    private java.util.Map<String, Vector> layerGroupToLayerSourceMap = new HashMap<>();
    private Map map;

    /**
     *
     */
    @Inject
    public OLSpecificLayersManager(Messages messages) {
        super();
        this.messages = messages;
        initLayers();
    }

    /**
     * @param map
     */
    public void init(Map map) {
        this.map = map;
        layerGroupToLayerSourceMap.values().stream().forEach(x -> map.addLayer(x));
        map.addLayer(drawLayer);
        map.addLayer(situationLayer);
        map.addLayer(clusterSituationLayer);
        map.addLayer(measurePersistOverlay);
        map.addLayer(floodLayer);

    }

    /**
     *
     */
    private void initLayers() {
        // warstwa do rysowania
        drawLayerSource = new ol.source.Vector();
        drawLayer = OLLayerUtils.createVectorLayerFromSource(drawLayerSource, "Rysowana");
        notVisibleLayers.add(drawLayer);

        // warstwa sytuacyjna
        situationLayerSource = new ol.source.Vector();
        situationLayer = OLLayerUtils.createVectorLayerFromSource(situationLayerSource, messages.layer_situation());
        operationalLayers.add(situationLayer);

        // warstwa clsuter dla sytuacyjnej
        clusterSituationLayer = OLNativeMethods.createClusterLayerFor(situationLayerSource, CLUSTERED_LAYER_DISTANCE);
        notVisibleLayers.add(clusterSituationLayer);
        //
        measurePersistOverlaySource = new ol.source.Vector();
        VectorLayerOptions voptions = OLFactory.createLayerOptionsWithSource(OLFactory.createVectorSource());
        // create a default style resembling the default editing style,
        // but adding a border to polygons
        Style sPoly = OLFactory.createStyle(OLFactory.createFill(OLFactory.createColor(255, 255, 255, 0.5)));
        Style sLine1 = OLFactory.createStyle(OLFactory.createStroke(OLFactory.createColor(255, 255, 255, 1), 5));
        Style sLine2 = OLFactory.createStyle(OLFactory.createStroke(OLFactory.createColor(0, 153, 255, 1), 3));
        // combine all styles
        Style[] s = OLUtil.addStyle(OLUtil.combineStyles(sPoly, sLine1), sLine2);
        voptions.setStyle(s);
        measurePersistOverlay = OLLayerUtils.createVectorLayerFromSource(measurePersistOverlaySource,
                messages.layer_measurePersistOverlay(), voptions);
        anayliticalLayers.add(measurePersistOverlay);

        floodLayerSource = new ol.source.Vector();
        floodLayer = OLLayerUtils.createVectorLayerFromSource(floodLayerSource, messages.layer_flood());
        StyleOptions styleOptions = new StyleOptions();
        styleOptions.setFill(OLFactory.createFill(OLFactory.createColor(0, 0, 255, 0.5)));
        styleOptions.setStroke(null);
        Style style = new Style(styleOptions);
        floodLayer.setStyle(style);
        anayliticalLayers.add(floodLayer);
    }

    /**
     * @return the situationLayer
     */
    public Vector getSituationLayer() {
        return situationLayer;
    }

    /**
     * @return the situationLayerSource
     */
    public ol.source.Vector getSituationLayerSource() {
        return situationLayerSource;
    }
    public ol.source.Vector getDrawLayerSource() {
        return drawLayerSource;
    }
    /**
     * @return the floodLayer
     */
    public Vector getFloodLayer() {
        return floodLayer;
    }

    /**
     * @return the floodLayerSource
     */
    public ol.source.Vector getFloodLayerSource() {
        return floodLayerSource;
    }

    /**
     * @param layer
     * @return
     */
    public boolean isAnalyticalLayer(Base layer) {
        return anayliticalLayers.contains(layer);
    }

    /**
     * @param layer
     * @return
     */
    public boolean isOperationalLayer(Base layer) {
        return operationalLayers.contains(layer);
    }

    /**
     * @param layer
     * @return
     */
    public boolean isVectorLayer(Base layer) {
        return layer instanceof ol.layer.Vector;
    }

    public ol.source.Vector getMeasurePersistOverlaySource() {
        return measurePersistOverlaySource;
    }

    public boolean isLayerVisible(Base layer) {
        return !notVisibleLayers.contains(layer);
    }

    /**
     * @param mapGroup
     * @return
     */
    public ol.source.Vector getLayerSourceForGroup(String mapGroup) {
        Vector layer = layerGroupToLayerSourceMap.get(mapGroup);
        if (layer == null) {
            ol.source.Vector source = new ol.source.Vector();
            layer = OLLayerUtils.createVectorLayerFromSource(source, mapGroup);
            operationalLayers.add(layer);
            layerGroupToLayerSourceMap.put(mapGroup, layer);
            if (map != null) {
                map.addLayer(layer);
                eventBus.fireEvent(new ObjectAddedEvent<Base>(this, layer));
            }

        }
        return layer.getSource();
    }

    /**
     * @return
     */
    public Feature[] getAllFeatures() {
        List<Feature> result = new LinkedList<Feature>();
        List<ol.source.Vector> vectorLayersSources = new LinkedList<>();
        vectorLayersSources.add(situationLayerSource);
        vectorLayersSources.addAll(layerGroupToLayerSourceMap.values().stream()
                .map(x -> (ol.source.Vector) x.getSource()).collect(Collectors.toList()));
        for (ol.source.Vector source : vectorLayersSources) {
            for (Feature f : source.getFeatures()) {
                result.add(f);
            }
        }
        return result.toArray(new Feature[result.size()]);
    }

    public void moveSpecificLayers() {
        OLNativeMethods.layerTop(map, drawLayer);
        OLNativeMethods.layerTop(map, situationLayer);
        OLNativeMethods.layerTop(map, clusterSituationLayer);
        OLNativeMethods.layerTop(map, measurePersistOverlay);
        OLNativeMethods.layerTop(map, floodLayer);
    }
}
