/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import ol.*;
import ol.event.EventListener;
import ol.event.MeasureEvent;
import ol.interaction.Draw;
import ol.interaction.Draw.Event;
import ol.interaction.DrawOptions;
import ol.interaction.Modify;
import ol.layer.Base;
import ol.source.Vector;
import ol.style.Style;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.domain.spec.ADatP3ReportClientModel;
import pl.edu.wat.wcy.cop.app.client.events.ObjectAddedEvent;
import pl.edu.wat.wcy.cop.app.client.events.ObjectRemovedEvent;
import pl.edu.wat.wcy.cop.app.client.events.ObjectUpdatedEvent;
import pl.edu.wat.wcy.cop.app.client.ol.OLMeasureManager.OLMeasureMode;
import pl.edu.wat.wcy.cop.app.client.ol.OLMeasureManager.OLMeasureType;
import pl.edu.wat.wcy.cop.app.client.ol.extra.OLMeasure.DrawAndMeasureListener;
import pl.edu.wat.wcy.cop.app.client.ol.forms.OLStyleForm;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm;
import pl.edu.wat.wcy.cop.app.client.utils.DefaultAsyncCallback;
import pl.edu.wat.wcy.cop.app.client.utils.GwtScheduler;
import pl.edu.wat.wcy.cop.app.client.utils.MessagesUtils;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.DialogUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.ScenarioPointObjectDto;
import pl.edu.wat.wcy.cop.app.shared.ol.*;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayerFormat;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayerType;
import pl.edu.wat.wcy.cop.app.shared.ol.sources.*;

import java.lang.Object;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


@Singleton
// Manages ol map.
public class OLMapManager {

    private Logger logger = Logger.getLogger(OLMapManager.class.getName());
    @Inject
    private MapWidget mapWidget;
    @Inject
    private OLSpecificLayersManager specificLayersManager;
    @Inject
    private EventBus eventBus;
    @Inject
    private OLMeasureManager measureManager;
    @Inject
    private Messages messages;
    private List<ADatP3ReportClientModel> reports;


    // draw new
    private Draw draw;
    private EventListener<MapBrowserEvent> drawListener;
    private EventListener<MapBrowserEvent> stopDrawingListener;
    // modify current
    private Modify modifyInteraction;


    public OLMapManager() {
        this.initMap();
    }

    /**
     * @param mapConfiguration
     */
    public void reloadMapConfiguration(OLMapOptions mapConfiguration) {
        mapWidget.setMapOptions(mapConfiguration);


    }


    private void initMap() {
        GwtScheduler.delay(5000, new Command() {
            @Override
            public void execute() {
                GWT.log("Dodaj lsitener");
                OLNativeMethods.addListener(getMap(), "keydown", new EventListener<MapBrowserEvent>() {
                    @Override
                    public void onEvent(MapBrowserEvent mapBrowserEvent) {
                        GWT.log(mapBrowserEvent.toString());
                    }
                });
            }
        });
    }

    public void modifyFeature(Feature feature, DefaultAsyncCallback<Feature> callback) {
//        // TODO aktualizacja dla poligonow itp
//        if (modifyInteraction == null) {
//            ModifyOptions options = OLFactory.createOptions();
//            Collection<Feature> olCollection = OLFactory.createCollection();
//            olCollection.push(feature);
//            options.setFeatures(olCollection);
//            modifyInteraction = new Modify(options);
//            getMap().addInteraction(modifyInteraction);
//        } else {
//            Object owner = feature.get(AppConstants.FEATURE_OBJECT_OWNER);
//            if (owner instanceof MapSymbolClientModel) {
//                MapSymbolClientModel spod = (MapSymbolClientModel) owner;
//                Coordinate coords = OLNativeMethods.getFeatureCords(modifiedFeature);
//                ((ScenarioPointObjectDto) spod.getObject()).setPoint(GeoUtils.createGeoPointsFromMapCoordinates(coords));
//                processUpdate(owner);
//            }
//            mapManager.getMap().removeInteraction(modifyInteraction);
//            modifiedFeature = null;
//            modifyInteraction = null;
//        }
    }

    public void drawFeatrue(String type, DefaultAsyncCallback<Feature> callback) {
        Map map = mapWidget.getMap();
        String listenerType = type.equalsIgnoreCase("point")  ?
                "click" : "dblclick";

        if (draw != null) {
            clearDraw();
        }
        DrawOptions options = OLFactory.createOptions();
        options.setSource(specificLayersManager.getDrawLayerSource());
        options.setType(type);

        draw = OLFactory.createDraw(options);
        map.addInteraction(draw);
        drawListener = new EventListener<MapBrowserEvent>() {
            @Override
            public void onEvent(MapBrowserEvent mapBrowserEvent) {

                if (draw != null) {
                    Feature[] feat = specificLayersManager.getDrawLayerSource().getFeatures();
                    Feature newFeature = feat[feat.length - 1];
                    if (newFeature != null) {
                        callback.onSuccess(newFeature);
                    }
                    for (Feature f : feat) {
                        specificLayersManager.getDrawLayerSource().removeFeature(f);
                    }
                    specificLayersManager.getDrawLayerSource().clear(true);


                }
                clearDraw();
            }
        };


        // map.addDoubleClickListener(drawListener);
        OLNativeMethods.addListener(map, listenerType, drawListener);
        DialogUtils.showInfoDialog("Drawing", "" +
                "Drawing mode is active.\n" +
                " To confirm, double-click the left mouse button.\n" +
                "Last element will be saved");

    }

    private void clearDraw() {
        try {
            if (draw != null) {
                try {
                    draw.set("escKey", Math.random());
                    draw.setActive(false);
                    draw.finishDrawing();
                } catch (Exception ex) {

                }

                getMap().removeInteraction(draw);
            }
        } catch (Exception ex) {

        }
        if (drawListener != null) {
            OLNativeMethods.removeListener(getMap(), "dblclick", drawListener);
            OLNativeMethods.removeListener(getMap(), "click", drawListener);
        }
        draw = null;
        drawListener = null;
    }


    public Map getMap() {
        return mapWidget.getMap();
    }

    public Base[] getLayers() {
        return getLayersAsCollection().getArray();
    }

    public Collection<Base> getLayersAsCollection() {
        return getMap().getLayers();
    }

    public void addNewLayer(OLLayer newLayer) {
        if (getLayerByName(newLayer.getName()) != null) {
            newLayer.setName(newLayer.getName() + new Date().getTime());
        }
        Base layer = OLLayerUtils.toLayer(newLayer);
        getMap().addLayer(layer);
        specificLayersManager.moveSpecificLayers();
        logger.fine("Dodano nowa warstwe " + newLayer.getName());
        eventBus.fireEvent(new ObjectAddedEvent<Base>(this, layer));
    }

    public void editLayer(OLLayer olLayer) {
        Base layer = getLayer(olLayer);
        OLLayerUtils.setLayerOptions(layer, olLayer);
        eventBus.fireEvent(new ObjectUpdatedEvent<Base>(this, layer));
    }

    public void setReports(List<ADatP3ReportClientModel> reports) {
        this.reports = reports;
    }

    /**
     * @param controlType
     */
    public void changeControlActivity(OLControlType controlType) {
        OLMapUtils.changeControlActivity(mapWidget, controlType);
    }

    /**
     * @param controlType
     * @return
     */
    public boolean isControlActive(OLControlType controlType) {
        return OLMapUtils.isControlActive(mapWidget, controlType);
    }

    /**
     * @param interactionType
     * @return
     */
    public boolean isInteractionActive(OLInteractionType interactionType) {
        return OLMapUtils.isInteractionActive(mapWidget, interactionType);
    }

    /**
     * @param interactionType
     */
    public void changeInteractionActivity(OLInteractionType interactionType) {
        OLMapUtils.changeInteractionActivity(mapWidget, interactionType);

    }

    private void setNewStyleOptions(OLLayer olLayer, OLStyleOptions olStyleOptions) {
        Base layer = getLayer(olLayer);
        OLLayerUtils.setLayerOptions(layer, olLayer);
        Feature[] features = this.getSituationLayerSource().getFeatures();
        for (ADatP3ReportClientModel model : reports) {
            if (model.getFeatures(ADatP3ReportClientModel.AdatP3FeatureType.RELEASE) != null) {
                for (Feature feature : model.getFeatures(ADatP3ReportClientModel.AdatP3FeatureType.RELEASE)) {
                    for (Feature feature1 : features) {
                        if (feature.equals(feature1)) {
                            feature1.setStyle(OLStyleManager.createStyle(olStyleOptions,
                                    ADatP3ReportClientModel.AdatP3FeatureType.RELEASE,
                                    model.getFeatures(ADatP3ReportClientModel.AdatP3FeatureType.RELEASE)));
                        }
                    }
                }
            }
            for (Feature feature : model.getFeatures(ADatP3ReportClientModel.AdatP3FeatureType.HAZARD)) {
                for (Feature feature1 : features) {
                    if (feature.equals(feature1)) {
                        feature1.setStyle(OLStyleManager.createStyle(olStyleOptions,
                                ADatP3ReportClientModel.AdatP3FeatureType.HAZARD,
                                model.getFeatures(ADatP3ReportClientModel.AdatP3FeatureType.HAZARD)));
                    }
                }
            }
        }
        eventBus.fireEvent(new ObjectUpdatedEvent<Base>(this, layer));
    }

    public void removeLayer(OLLayer layer) {
        removeLayer(getLayer(layer));

    }

    public void removeLayer(Base layer) {
        if (layer != null) {
            getMap().removeLayer(layer);
            eventBus.fireEvent(new ObjectRemovedEvent<Base>(this, layer));
        }

    }

    public void changeVisibility(Base layer) {
        if (isLayerPresent(layer)) {
            layer.setVisible(!layer.getVisible());
        }
    }

    public boolean layerUp(Base layer) {
        if (isLayerPresent(layer)) {
            return OLNativeMethods.layerUp(getMap(), layer);
        }
        return false;
    }

    public boolean layerDown(Base layer) {
        if (isLayerPresent(layer)) {
            return OLNativeMethods.layerDown(getMap(), layer);
        }
        return false;

    }

    /**
     * przerzuca na top z warstw GIS nie nachodzi na warstwy specjalne
     *
     * @param layer
     * @return
     */
    public boolean layerTop(Base layer) {
        if (isLayerPresent(layer)) {
            return OLNativeMethods.layerTop(getMap(), layer);
        }
        return false;
    }

    public boolean layerBottom(Base layer) {
        if (isLayerPresent(layer)) {
            return OLNativeMethods.layerBottom(getMap(), layer);
        }
        return false;
    }

    /**
     * @param layer
     * @return
     */
    public Base getLayerInOLStack(Base layer) {
        return getLayerByName(getLayerName(layer));
    }

    private boolean isLayerPresent(Base layer) {
        return layer != null && getLayerInOLStack(layer) != null;
    }

    public Base getLayer(OLLayer layer) {
        return getLayerByName(layer.getName());
    }

    public Base getLayerByName(String name) {
        return OLUtil.getLayerByName(getMap(), name);
    }

    public String getLayerName(Base layer) {
        return OLUtil.getName(layer);
    }

    /**
     * @return
     */
    public OLScaleLineUnit getSelectedScaleLineUnit() {
        return mapWidget.getSelectedScaleLineUnit();
    }

    /**
     * @param data
     */
    public void setSelectedScaleLine(OLScaleLineUnit selectedScaleLineUnit) {
        mapWidget.setSelectedScaleLineUnit(selectedScaleLineUnit);

    }

    /**
     * @return
     */
    public OLCordsFormattingType getSelectedCoordsFormat() {
        return mapWidget.getSelectedCoordsFormat();
    }

    /**
     * @param data
     */
    public void setSelectedCoordsFormat(OLCordsFormattingType selectedCoordsFormat) {
        mapWidget.setSelectedCoordsFormat(selectedCoordsFormat);

    }

    /**
     * @return
     */
    public Vector getFloodLayerSource() {
        return specificLayersManager.getFloodLayerSource();
    }

    /**
     * @return
     */
    public Vector getSituationLayerSource() {
        return specificLayersManager.getSituationLayerSource();
    }

    /**
     * @param measureType
     * @param listener
     * @param style
     * @param measureMode
     */
    public void startMeasure(OLMeasureType measureType, DrawAndMeasureListener listener, Style style,
                             OLMeasureMode measureMode) {
        measureManager.startMeasure(measureType, new DrawAndMeasureListener() {

            @Override
            public void onMeasure(MeasureEvent evt) {
                if (listener != null)
                    listener.onMeasure(evt);
                // if (evt instanceof ExtendedMeasureEvent) {
                // eventBus.fireEvent(new ObjectAddedEvent<OLMapMeasure>(this,
                // new OLMapMeasure(measureType, ((ExtendedMeasureEvent)
                // evt).getFeature())));
                // }

            }

            @Override
            public void onDrawStart(Event event) {
                if (listener != null)
                    listener.onDrawStart(event);

            }
        }, style, measureMode);

    }

    /**
     *
     */
    public void stopMeasure() {
        measureManager.stopMeasure();
    }

    public copLayerType getLayerType(Base layer) {
        if (specificLayersManager.isOperationalLayer(layer)) {
            return copLayerType.OPERATIONAL;
        } else if (specificLayersManager.isAnalyticalLayer(layer)) {
            return copLayerType.ANALYTICAL;
        } else if (specificLayersManager.isVectorLayer(layer)) {
            return copLayerType.VECTOR;
        }
        return copLayerType.GIS;
    }

    /**
     * @param tileLayerType
     * @return
     */
    public SelectionHandler<Item> getSelectionHandlerForAddTileLayer(OLTileLayerSource tileLayerType) {

        return new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
                OLLayer newLayer = new OLLayer(OLLayerType.TILE, MessagesUtils.enumToString(tileLayerType));
                newLayer.setType(tileLayerType);
                executeAddNewLayerProcess(newLayer);

            }
        };
    }

    /**
     * @param source
     * @return
     */
    public SelectionHandler<Item> getSelectionHandlerForAddWeatherLayer(OLWeatherLayerSources source) {
        return new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
                OLLayer newLayer = new OLLayer(OLLayerType.WEATHER, MessagesUtils.enumToString(source));
                newLayer.setWeatherSource(source);
                executeAddNewLayerProcess(newLayer);

            }
        };
    }

    /**
     * @param layerSource
     * @return
     */
    public SelectionHandler<Item> getSelectionHandlerForAddPreconfiguredWmsLayerSource(
            OLWmsGeoportalLayerSources layerSource) {
        return new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
                OLLayer newLayer = new OLLayer(OLLayerType.WMS, layerSource.getLayerName());
                newLayer.setUrl(layerSource.getUrl());
                newLayer.setLayers(layerSource.getLayers());
                newLayer.setName(layerSource.getLayerName());
                executeAddNewLayerProcess(newLayer);
            }
        };
    }

    public SelectionHandler<Item> getSelectionHandlerForAddConfigLayer(OLLayerType layerType) {
        return getSelectionHandlerForAddConfigLayer(layerType, null);
    }

    /**
     * @param layerType
     * @return
     */
    public SelectionHandler<Item> getSelectionHandlerForAddConfigLayer(OLLayerType layerType, OLLayerFormat format) {
        return new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
                OLLayer newLayer = new OLLayer(layerType, MessagesUtils.enumToString(layerType));
                newLayer.setLayerFormat(format);
                executeAddNewLayerProcess(newLayer);
            }
        };
    }

    /**
     * @param source
     * @return
     */
    public SelectionHandler<Item> getSelectionHandlerForAddBingLayer(OLBingLayerSource source) {
        return new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
                OLLayer newLayer = new OLLayer(OLLayerType.BING, MessagesUtils.bingLayerSourceToString(source));
                newLayer.setBingSource(source);
                executeAddNewLayerProcess(newLayer);
            }
        };
    }

    /**
     * @param source
     * @return
     */
    public SelectionHandler<Item> getSelectionHandlerForAddMapboxLayer(OLMapBoxLayerSource source) {
        return new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
                OLLayer newLayer = new OLLayer(OLLayerType.MAPBOX,
                        MessagesUtils.mapboxLayerSourceToString(source));
                newLayer.setLayerType(OLLayerType.XYZ);
                newLayer.setUrl(source.getUrl());
                newLayer.setName(source.toString());
                executeAddNewLayerProcess(newLayer);
            }
        };
    }

    /**
     * @param layer
     * @return
     */
    public SelectionHandler<Item> getSelectionHandlerForEditLayer(Base layer) {
        return new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
                OLLayer olLayer = OLLayerUtils.toOLLayer(layer);
                AbstractForm layerForm = OLGuiUtils.getFormForLayer(olLayer, new SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        editLayer(olLayer);
                    }

                });
            }
        };
    }

    /**
     * @param layer
     * @return
     */
    public SelectionHandler<Item> getSelectionHandlerForEditLayerStyle(Base layer) {
        return new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
                OLLayer olLayer = OLLayerUtils.toOLLayer(layer);
                OLStyleOptions olStyleOptions = new OLStyleOptions();
                OLStyleForm styleForm = new OLStyleForm(olStyleOptions, new SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        setNewStyleOptions(olLayer, olStyleOptions);
                    }
                });
            }
        };
    }

    /**
     * @param layer
     * @return
     */
    public SelectionHandler<Item> getSelectionHandlerForRemoveLayer(Base layer) {
        return new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
                DialogUtils.showConfirmDialog(messages.layer_remove(), messages.layer_remove(), new SelectHandler() {

                    @Override
                    public void onSelect(SelectEvent event) {
                        removeLayer(layer);
                    }
                });

            }
        };
    }

    /**
     * @param newLayer
     */
    private void executeAddNewLayerProcess(OLLayer newLayer) {
        AbstractForm layerForm = OLGuiUtils.getFormForLayer(newLayer, new SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                addNewLayer(newLayer);
            }

        });

    }

    public void startSpyMouseMode() {
        OLNativeMethods.startSpyMouseMode(mapWidget.getMap());
    }

    public void stopSpyMouseMode() {
        OLNativeMethods.stopSpyMouseMode(mapWidget.getMap());
    }

    public void clearMouseMode() {
        stopMeasure();
        stopSpyMouseMode();
    }

    /**
     * @param layer
     * @return
     */
    public boolean isLayerVisibleInTree(Base layer) {
        return specificLayersManager.isLayerVisible(layer);
    }

    /**
     * @param objectToWorkOn
     * @return
     */
    public Vector getSourceLayerForObject(Object objectToWorkOn) {
        if (objectToWorkOn instanceof ScenarioPointObjectDto) {
            ScenarioPointObjectDto dto = (ScenarioPointObjectDto) objectToWorkOn;
            if (!StringUtils.isNullOrEmpty(dto.getMapGroup())) {
                return specificLayersManager.getLayerSourceForGroup(dto.getMapGroup());
            }
        }

        return specificLayersManager.getSituationLayerSource();
    }

    /**
     * @return
     */
    public Feature[] getAllFeatures() {
        return specificLayersManager.getAllFeatures();
    }

    public void setOverview(Base layer) {
        OLMapUtils.setOverview(getMap(), layer);
    }

    public enum copLayerType {
        OPERATIONAL, ANALYTICAL, GIS, VECTOR
    }

}
