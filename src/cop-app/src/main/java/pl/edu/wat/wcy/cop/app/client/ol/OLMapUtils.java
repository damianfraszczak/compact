/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol;

import ol.*;
import ol.control.*;
import ol.events.condition.Condition;
import ol.interaction.*;
import ol.layer.Base;
import ol.layer.Layer;
import ol.proj.Projection;
import ol.tilegrid.TileGrid;
import ol.tilegrid.WmtsTileGrid;
import ol.tilegrid.WmtsTileGridOptions;
import pl.edu.wat.wcy.cop.app.client.domain.MapSymbolClientModel;
import pl.edu.wat.wcy.cop.app.client.domain.spec.SearchAndRescueClientModel;
import pl.edu.wat.wcy.cop.app.client.ol.extra.FeatureInfoProvider;
import pl.edu.wat.wcy.cop.app.client.ol.extra.Geocoder;
import pl.edu.wat.wcy.cop.app.client.utils.GwtScheduler;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.ol.*;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayer;
import pl.edu.wat.wcy.cop.app.shared.ol.layers.OLLayerType;

import java.lang.Object;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
// Provides ol map utilities.


public class OLMapUtils {
    public static final OLCoordsFormatter COORDS_FORMATTER = new OLCoordsFormatter();
    private static java.util.Map<OLInteractionType, Interaction> InteractionsMap = new HashMap<>();
    private static java.util.Map<OLControlType, Control> ControlsMap = new HashMap<>();
    private static java.util.Map<OLControlType, Runnable> SpecialControlsMap = new HashMap<>();
    private static Map map;

    static {
        InteractionsMap.put(OLInteractionType.DRAG_PAN, new DragPan());
        InteractionsMap.put(OLInteractionType.DRAG_ROTATE_AND_ZOOM, new DragRotateAndZoom());
        InteractionsMap.put(OLInteractionType.KEYBOARD_PAN, new KeyboardPan());
        InteractionsMap.put(OLInteractionType.KEYBOARD_ZOOM, new KeyboardZoom());
        InteractionsMap.put(OLInteractionType.MOUSE_WHEEL_ZOOM, new MouseWheelZoom());

        ControlsMap.put(OLControlType.FULL_SCREEN, OLFactory.createFullScreen());
        ControlsMap.put(OLControlType.MOUSE_POSITION, createMousePositionControl());
        ControlsMap.put(OLControlType.OVERVIEV_MAP, OLFactory.createOverviewMap());
        ControlsMap.put(OLControlType.SCALE_LINE, OLFactory.createScaleLine());
        ControlsMap.put(OLControlType.ZOOM_SILDER, OLFactory.createZoomSlider());
        ControlsMap.put(OLControlType.ZOOM_TO_EXTENT, OLFactory.createZoomToExtent());
        ControlsMap.put(OLControlType.MAP_LEGEND, OLNativeMethods.createMapLegend());
        ControlsMap.put(OLControlType.MAP_CROSSHAIRS, OLNativeMethods.createMapCrosshairs(COORDS_FORMATTER));

        SpecialControlsMap.put(OLControlType.GRATICULE, new Runnable() {

            private Graticule graticule = OLNativeMethods.createGraticule();
            private Map lastMap = null;

            @Override
            public void run() {
                if (lastMap == null) {
                    lastMap = map;
                } else {
                    lastMap = null;
                }
                graticule.setMap(lastMap);
            }
        });

    }

    /**
     * @return the map
     */
    public static Map getMap() {
        return map;
    }

    public static void centerMap(double lat, double lon) {
        map.getView().setCenter(GeoUtils.transformFromLatLon(lat, lon));
    }

    public static Map createMap(String mapDivId, OLMapOptions olMapOptions) {
        ViewOptions viewOptions = OLFactory.createOptions();
        // viewOptions.setProjection(Projection.get(olMapOptions.getProjection().getCode()));
        viewOptions.setMaxZoom(olMapOptions.getMaxZoom());
        viewOptions.setMinZoom(olMapOptions.getMinZoom());
        viewOptions.setZoomFactor(olMapOptions.getZoomFactor());
        viewOptions.setCenter(GeoUtils.createCoordinatesFromLatLonInMapProjection(olMapOptions.getCenterLat(),
                olMapOptions.getCenterLon()));
        View view = new View(viewOptions);
        view.setZoom(olMapOptions.getZoom());
        MapOptions mapOptions = OLFactory.createOptions();
        mapOptions.setTarget(mapDivId);
        mapOptions.setView(view);
        mapOptions.setLayers(getLayers(olMapOptions.getLayers()));
        mapOptions.setControls(getControls(olMapOptions.getControls()));
        mapOptions.setInteractions(getInteractions(olMapOptions.getInteractions()));
        updateControlsSpecificParams(olMapOptions);
        map = new Map(mapOptions);
        initSpecialInteractions(map);
        Geocoder.initGeocoder(getMap());

        return map;
    }

    /**
     * @param map
     * @param olMapOptions
     */
    public static void reloadMap(Map map, OLMapOptions olMapOptions) {


        View view = map.getView();
        view.setZoom(olMapOptions.getZoom());
        // view.set("projection",
        // Projection.get(olMapOptions.getProjection().getCode()));
        view.set("maxZoom", olMapOptions.getMaxZoom());
        view.set("minZoom", olMapOptions.getMinZoom());

        view.set("zoomFactor", olMapOptions.getZoomFactor());
        view.set("center", GeoUtils.createCoordinatesFromLatLonInMapProjection(olMapOptions.getCenterLat(),
                olMapOptions.getCenterLon()));

        map.getLayers().clear();
        map.getControls().clear();
        map.getInteractions().clear();
        initLayers(map, olMapOptions.getLayers());
        initControls(map, olMapOptions.getControls());
        initInteractions(map, olMapOptions.getInteractions());
        initSpecialInteractions(map);
        updateControlsSpecificParams(olMapOptions);
    }

    /**
     * @param olMapOptions
     */
    public static void updateControlsSpecificParams(OLMapOptions olMapOptions) {
        setScaleLineParams(olMapOptions.getScaleUnit());
        setCoordsFormattingParams(olMapOptions.getCordsFormattingType());
    }

    /**
     * @param olCordsFormattingType
     */
    public static void setCoordsFormattingParams(OLCordsFormattingType olCordsFormattingType) {
        COORDS_FORMATTER.setType(olCordsFormattingType);
    }

    /**
     * @param scaleUnit
     */
    public static void setScaleLineParams(OLScaleLineUnit scaleUnit) {
        if (scaleUnit != null) {
            ScaleLine scaleLine = (ScaleLine) ControlsMap.get(OLControlType.SCALE_LINE);
            scaleLine.set("units", scaleUnit.getCode());
        }
    }

    /**
     * @param interactions
     * @return
     */
    public static Collection<Interaction> getInteractions(List<OLInteractionType> interactions) {
        Collection<Interaction> olInteractions = new Collection<Interaction>();
        interactions.stream().map(x -> InteractionsMap.get(x)).forEach(x -> olInteractions.push(x));
        return olInteractions;
    }

    /**
     * @param controls
     * @return
     */
    public static Collection<Control> getControls(List<OLControlType> controls) {
        Collection<Control> olControls = new Collection<Control>();
        controls.stream().filter(x -> !SpecialControlsMap.containsKey(x)).map(x -> ControlsMap.get(x))
                .forEach(x -> olControls.push(x));
        return olControls;
    }

    /**
     * @param layers
     * @return
     */
    public static Collection<Base> getLayers(List<OLLayer> layers) {
        Collection<Base> olLayers = new Collection<Base>();
        layers.stream().map(x -> OLLayerUtils.toLayer(x)).forEach(x -> olLayers.push(x));
        return olLayers;
    }

    public static Map createMap(String mapDivId) {
        ViewOptions viewOptions = OLFactory.createOptions();
        viewOptions.setProjection(Projection.get(OLProjection.EPSG_4326.getCode()));
        View view = new View();
        view.setZoom(10);
        MapOptions mapOptions = OLFactory.createOptions();
        mapOptions.setTarget(mapDivId);
        mapOptions.setView(view);
        return new Map(mapOptions);
    }

    public static void initMap(Map map) {
        initInteractions(map, Arrays.asList(OLInteractionType.values()));
        initControls(map, Arrays.asList(OLControlType.values()));
        initLayers(map, Arrays.asList(new OLLayer(OLLayerType.TILE)));
    }

    public static void setMapCenter(Map map, double lat, double lon) {
        Coordinate centerCoordinate = OLFactory.createCoordinate(lat, lon);
        Coordinate transformedCenterCoordinate = Projection.transform(centerCoordinate,
                OLProjection.EPSG_4326.getCode(), OLProjection.MERCARTOR.getCode());
        map.getView().setCenter(transformedCenterCoordinate);
    }

    public static TileGrid createWmtsTileGrid(Projection projection) {
        WmtsTileGridOptions wmtsTileGridOptions = OLFactory.createOptions();
        double[] resolutions = new double[14];
        String[] matrixIds = new String[14];
        double width = OLUtil.getWidth(projection.getExtent());
        double matrixWidth = width / 256;

        for (int i = 0; i < 14; i++) {
            resolutions[i] = matrixWidth / Math.pow(2, i);
            matrixIds[i] = String.valueOf(i);
        }
        Coordinate tileGridOrigin = OLUtil.getTopLeft(projection.getExtent());
        wmtsTileGridOptions.setOrigin(tileGridOrigin);
        wmtsTileGridOptions.setResolutions(resolutions);
        wmtsTileGridOptions.setMatrixIds(matrixIds);
        return new WmtsTileGrid(wmtsTileGridOptions);

    }

    /**
     * @param interactions
     * @return
     */
    public static void initInteractions(Map map, List<OLInteractionType> interactions) {
        for (OLInteractionType interactionType : interactions) {
            map.addInteraction(InteractionsMap.get(interactionType));
        }
    }

    /**
     * @param controls
     * @return
     */
    public static void initControls(Map map, List<OLControlType> controls) {
        for (OLControlType controlType : controls) {
            Control c = ControlsMap.get(controlType);
            if (c != null)
                map.addControl(c);
            else
                initSpecialControl(controlType);
        }
        GwtScheduler.delay(5000, () -> {

            Geocoder.initGeocoder(getMap());
        });

    }

    /**
     * @param mapWidget
     * @param controlType
     * @return
     */
    public static boolean isControlActive(MapWidget mapWidget, OLControlType controlType) {
        return mapWidget.getMapOptions().getControls().stream().filter(x -> x.equals(controlType)).findAny()
                .orElse(null) != null;
    }

    /**
     * @param mapWidget
     * @param interactionType
     * @return
     */
    public static boolean isInteractionActive(MapWidget mapWidget, OLInteractionType interactionType) {
        return mapWidget.getMapOptions().getInteractions().stream().filter(x -> x.equals(interactionType)).findAny()
                .orElse(null) != null;
    }

    /**
     * @param mapWidget
     * @param controlType
     */
    public static void changeControlActivity(MapWidget mapWidget, OLControlType controlType) {
        OLMapOptions mapOptions = mapWidget.getMapOptions();
        Control control = ControlsMap.get(controlType);
        boolean isActive = isControlActive(mapWidget, controlType);
        if (control == null) {
            // TODO add isActive
            SpecialControlsMap.get(controlType).run();
        } else {
            if (!isActive) {
                mapOptions.getControls().add(controlType);
                mapWidget.getMap().addControl(control);
            } else {
                mapOptions.getControls().remove(controlType);
                mapWidget.getMap().removeControl(control);
            }
        }

    }

    /**
     * @param mapWidget
     * @param interactionType
     */
    public static void changeInteractionActivity(MapWidget mapWidget, OLInteractionType interactionType) {
        OLMapOptions mapOptions = mapWidget.getMapOptions();
        Interaction interaction = InteractionsMap.get(interactionType);
        if (!isInteractionActive(mapWidget, interactionType)) {
            mapOptions.getInteractions().add(interactionType);
            mapWidget.getMap().addInteraction(interaction);
        } else {
            mapOptions.getInteractions().remove(interactionType);
            mapWidget.getMap().removeInteraction(interaction);
        }

    }

    /**
     * @param layers
     * @return
     */
    public static void initLayers(Map map, List<OLLayer> layers) {
        for (OLLayer layer : layers) {
            map.addLayer(OLLayerUtils.toLayer(layer));
        }

    }

    /**
     * @return
     */
    private static Control createMousePositionControl() {
        MousePosition mousePosition = new MousePosition();
        mousePosition.set("coordinateFormat", createCoordinateFormat());
        // mousePosition.setCoordinateFormat();
        return mousePosition;
    }

    private static GenericFunction<Coordinate, String> createCoordinateFormat() {
        return COORDS_FORMATTER.createCoordsFormatter();
    }

    /**
     * @param controlType
     */
    private static void initSpecialControl(OLControlType controlType) {
        Runnable specialControl = SpecialControlsMap.get(controlType);
        if (specialControl != null) {
            specialControl.run();
        }
        OverviewMap oMap = (OverviewMap) ControlsMap.get(OLControlType.OVERVIEV_MAP);
        oMap.setCollapsed(false);
    }

    public static void setOverview(Map map, Base layer) {
        OverviewMap oMap = (OverviewMap) ControlsMap.get(OLControlType.OVERVIEV_MAP);
        map.removeControl(oMap);
        OverviewMapOptions opts = OLFactory.createOptions();
        Collection<Layer> layers = OLFactory.createCollection();
        layers.push((Layer) layer);
        opts.setLayers(layers);
        oMap = OLFactory.createOverviewMap(opts);
        ControlsMap.put(OLControlType.OVERVIEV_MAP, oMap);
        map.addControl(oMap);
    }

    private static Feature[] selectedFeatures;

    private static void clearSelectedFeatures() {
        if (selectedFeatures != null) {
            for (Feature f : selectedFeatures) {
                OLFeatureBuilder.setDefaultFeatureStyle(f);
            }
            selectedFeatures = null;
        }
    }

    private static void setSelectedFeatures(Feature[] features) {
        clearSelectedFeatures();
        selectedFeatures = features;
        if (selectedFeatures != null) {
            for (Feature f : selectedFeatures) {
                OLFeatureBuilder.setFeatureSelectedStyle(f);
            }
        }
    }

    private static void initSpecialInteractions(Map map) {

        GwtScheduler.delay(2000, () -> {
            SelectOptions selectOptions = new SelectOptions();
            selectOptions.setCondition(Condition.getClick());
            selectOptions.setMulti(false);

            selectOptions.setStyle(OLNativeMethods.getSelectInteractionStyle());

            // create a select interaction
            final Select selectFeature = new Select(selectOptions);
            map.addInteraction(selectFeature);

            selectFeature.on("select", (Select.Event event) -> {
                clearSelectedFeatures();
                Feature[] selectedFeatures = event.getSelected();

                for(Feature f: selectedFeatures) {
                    Object obj = f.get(AppConstants.FEATURE_OBJECT_OWNER);
                    if (obj instanceof MapSymbolClientModel) {
                        MapSymbolClientModel sarcm = (MapSymbolClientModel) obj;
                        selectedFeatures = sarcm.getFeatures();
                    }
                }

                if (selectedFeatures.length > 0) {
                    setSelectedFeatures(selectedFeatures);
                    FeatureInfoProvider provider = selectedFeatures[0].get(FeatureInfoProvider.PROVIDER_KEY);
                    if (provider != null) {

                        OLNativeMethods.setMapLegentText(provider.getTooltipText(), "");
                    }
                } else {
                    OLNativeMethods.setMapLegentText("", "");
                }


            });

            map.addInteraction(selectFeature);
        });
    }


}
