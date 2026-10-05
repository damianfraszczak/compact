/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import ol.*;
import ol.interaction.Draw;
import ol.interaction.Interaction;
import pl.edu.wat.wcy.cop.app.client.events.MapLoadedEvent;
import pl.edu.wat.wcy.cop.app.client.ol.extra.FeatureInfoProvider;
import pl.edu.wat.wcy.cop.app.client.ol.extra.wrappers.FeatureHoverListener;
import pl.edu.wat.wcy.cop.app.shared.ol.OLCordsFormattingType;
import pl.edu.wat.wcy.cop.app.shared.ol.OLMapOptions;
import pl.edu.wat.wcy.cop.app.shared.ol.OLScaleLineUnit;

import java.util.logging.Logger;
// Represents map widget.

public class MapWidget extends SimpleContainer {
    public static final String MAP_DIV_ID = "cop-map";

    private static Logger logger = Logger.getLogger(MapWidget.class.getName());
    private boolean initialized = false;
    private Map map;
    private EventBus eventBus;

    private OLMapOptions mapOptions = new OLMapOptions();

    @Inject
    private OLSpecificLayersManager specificLayersManager;
    private Overlay tooltipOverlay;
    private DivElement tooltipOverlayElement;

    /**
     *
     */
    @Inject
    public MapWidget(EventBus eventBus) {
        super();
        this.eventBus = eventBus;
        setDefaultOptions();
    }

    @Override
    public void doLayout() {
        if (!initialized) {
            Scheduler.get().scheduleDeferred(() -> initMap());
            initialized = true;
        }
        super.doLayout();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sencha.gxt.widget.core.client.Component#setVisible(boolean)
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
    }

    /**
     * @return the map
     */
    public Map getMap() {
        return map;
    }

    /**
     *
     */
    private void initMap() {
        logger.fine("Inicjalizacja mapy");
        this.map = OLMapUtils.createMap(MAP_DIV_ID, getMapOptions());
        specificLayersManager.init(this.map);
        initExtraComponents();
        initMapListeners();
        logger.fine("Inicjalizacja mapy - zakonczono");
        eventBus.fireEvent(new MapLoadedEvent(this));
    }

    /**
     *
     */
    private void initExtraComponents() {

        tooltipOverlayElement = Document.get().createDivElement();
        tooltipOverlayElement.addClassName("tooltip");
        OverlayOptions overlayOptions = OLFactory.createOptions();
        overlayOptions.setElement(tooltipOverlayElement);
        overlayOptions.setPositioning("bottom-left");
        overlayOptions.setOffset(OLFactory.createPixel(10, 0));
        tooltipOverlay = new Overlay(overlayOptions);
        map.addOverlay(tooltipOverlay);
    }

    /**
     *
     */
    private void reload() {
        OLMapUtils.reloadMap(map, getMapOptions());
        specificLayersManager.init(this.map);
        eventBus.fireEvent(new MapLoadedEvent(this));
    }

    public OLMapOptions getMapOptions() {
        return mapOptions;
    }

    public void setMapOptions(OLMapOptions mapOptions) {
        this.mapOptions = mapOptions;
        if (map != null)
            reload();
    }

    /**
     *
     */
    private void setDefaultOptions() {
        // this.setSize("100%", "100%");
        this.getElement().setId(MAP_DIV_ID);
    }

    /**
     * @return
     */
    public ol.source.Vector getSituationLayerSource() {
        return specificLayersManager.getSituationLayerSource();
    }

    /**
     * @return
     */
    public OLScaleLineUnit getSelectedScaleLineUnit() {
        return mapOptions.getScaleUnit();
    }

    /**
     * @param selectedScaleLineUnit
     */
    public void setSelectedScaleLineUnit(OLScaleLineUnit selectedScaleLineUnit) {
        mapOptions.setScaleUnit(selectedScaleLineUnit);
        OLMapUtils.updateControlsSpecificParams(mapOptions);
    }

    /**
     * @return
     */
    public OLCordsFormattingType getSelectedCoordsFormat() {
        return mapOptions.getCordsFormattingType();
    }

    /**
     * @param selectedCoordsFormat
     */
    public void setSelectedCoordsFormat(OLCordsFormattingType selectedCoordsFormat) {
        mapOptions.setCordsFormattingType(selectedCoordsFormat);
        OLMapUtils.updateControlsSpecificParams(mapOptions);

    }

    /**
     *
     */
    private void initMapListeners() {
        OLNativeMethods.addPointMoveListener(this.map, new FeatureHoverListener() {

            @Override
            public void onFeatureHovered(Coordinate coords, Feature feature) {
                tooltipOverlayElement.getStyle().setDisplay(Style.Display.NONE);
                boolean isDrawing = false;
                for (Interaction i : map.getInteractions().getArray()) {
                    if (i instanceof Draw) {
                        isDrawing = true;
                        break;
                    }
                }
                if (feature != null && !isDrawing) {
                    FeatureInfoProvider provider = feature.get(FeatureInfoProvider.PROVIDER_KEY);
                    if (provider != null) {
                        tooltipOverlayElement.getStyle().setDisplay(Style.Display.INITIAL);
                        tooltipOverlay.setPosition(coords);
                        tooltipOverlayElement.setInnerHTML(provider.getTooltipText());
                    }
                }
            }
        });

    }

    public ol.source.Vector getMeasurePersistOverlaySource() {
        return specificLayersManager.getMeasurePersistOverlaySource();
    }

    /**
     *
     */
    public void updateMapSize() {
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {

            @Override
            public void execute() {
                getMap().updateSize();
            }
        });

    }
}
