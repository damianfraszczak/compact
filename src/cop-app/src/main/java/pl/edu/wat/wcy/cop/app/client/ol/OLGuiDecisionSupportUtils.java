/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.menu.CheckMenuItem;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import ol.Coordinate;
import ol.Feature;
import ol.OLFactory;
import ol.event.MeasureEvent;
import ol.geom.Circle;
import ol.geom.LineString;
import ol.geom.Polygon;
import ol.interaction.Draw.Event;
import ol.source.Vector;
import ol.style.Style;
import ol.style.StyleOptions;
import org.fusesource.restygwt.client.Method;
import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.ol.OLMeasureManager.OLMeasureMode;
import pl.edu.wat.wcy.cop.app.client.ol.extra.FeatureInfoProvider;
import pl.edu.wat.wcy.cop.app.client.ol.extra.OLMeasure;
import pl.edu.wat.wcy.cop.app.client.services.server.DecisionSupportProvider;
import pl.edu.wat.wcy.cop.app.client.ui.forms.FloodAreaForm;
import pl.edu.wat.wcy.cop.app.client.ui.forms.LosAreaForm;
import pl.edu.wat.wcy.cop.app.client.ui.forms.TerrainProfileForm;
import pl.edu.wat.wcy.cop.app.client.utils.LoggingMethodCallback;
import pl.edu.wat.wcy.cop.app.client.utils.gui.GxtComponentsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.images.GisImages;
import pl.edu.wat.wcy.cop.app.shared.request.FloodAreaRequest;
import pl.edu.wat.wcy.cop.app.shared.request.LosAreaRequest;
import pl.edu.wat.wcy.cop.app.shared.request.TerrainProfileRequest;
import pl.edu.wat.wcy.cop.app.shared.response.OkResponse;
import pl.edu.wat.wcy.cop.app.shared.response.capability.FloodAreaCapability;
import pl.edu.wat.wcy.cop.app.shared.response.capability.LosAreaCapability;
import pl.edu.wat.wcy.cop.app.shared.response.capability.PolygonWithHeight;
import pl.edu.wat.wcy.cop.app.shared.response.capability.TerrainProfileCapability;

import java.util.ArrayList;
import java.util.List;
// Provides ol gui decision support utilities.

public class OLGuiDecisionSupportUtils {
    private static final int DEFAULT_ACCURACY = 50;
    private static final int DEFAULT_FLOOD_HEIGHT = 2;
    private static final int DEFAULT_OBSERVER_HEIGHT = 2;

    public static SelectionHandler<Item> getSelectionHandlerForTerrainProfile(OLMapManager olMapManager,
                                                                              DecisionSupportProvider decisionSupportProvider) {
        return new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
                olMapManager.startMeasure(OLMeasureManager.OLMeasureType.DISTANCE,
                        new OLMeasure.DrawAndMeasureListener() {
                            private TerrainProfileForm lastForm;

                            @Override
                            public void onMeasure(MeasureEvent evt) {
                                if (evt.getGeometry() instanceof LineString) {
                                    LineString lineString = (LineString) evt.getGeometry();
                                    Coordinate[] coordinatesArray = lineString.getCoordinates();
                                    double accuracy = DEFAULT_ACCURACY / 100.0;
                                    List<Double> coordinates = toLatLonList(coordinatesArray);
                                    getTerrainProfile(decisionSupportProvider, coordinates, accuracy);
                                }
                            }

                            private void getTerrainProfile(DecisionSupportProvider decisionSupportProvider,
                                                           List<Double> coordinates, double accuracy) {
                                decisionSupportProvider.getTerrainProfile(
                                        new TerrainProfileRequest(coordinates, accuracy),
                                        new LoggingMethodCallback<OkResponse<TerrainProfileCapability>>() {

                                            @Override
                                            protected void success(Method method,
                                                                   OkResponse<TerrainProfileCapability> response) {
                                                TerrainProfileCapability content = response.getContent();
                                                lastForm = new TerrainProfileForm(content) {

                                                    @Override
                                                    protected void recalculate() {
                                                        hide();
                                                        Integer accuracySliderValue = getAccuracySliderValue();
                                                        getTerrainProfile(decisionSupportProvider, coordinates,
                                                                accuracySliderValue / 100.0);
                                                    }
                                                };
                                            }

                                        });
                            }

                            @Override
                            public void onDrawStart(Event event) {
                                if (lastForm != null) {
                                    lastForm = hideForm(lastForm);
                                }
                            }

                        }, null, OLMeasureMode.PERSIST);

            }
        };

    }

    public static SelectionHandler<Item> getSelectionHandlerForFloodArea(OLMapManager olMapManager,
                                                                         DecisionSupportProvider decisionSupportProvider) {
        return new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
                Vector floodLayerSource = olMapManager.getFloodLayerSource();

                olMapManager.startMeasure(OLMeasureManager.OLMeasureType.CIRCLE,
                        new OLMeasure.DrawAndMeasureListener() {
                            private FloodAreaForm lastForm;

                            @Override
                            public void onMeasure(MeasureEvent evt) {
                                if (evt.getGeometry() instanceof Circle) {
                                    Circle circle = (Circle) evt.getGeometry();
                                    Coordinate center = circle.getCenter();
                                    double lat = center.lat();
                                    double lon = center.lon();
                                    double radius = circle.getRadius();
                                    int floodHeight = DEFAULT_FLOOD_HEIGHT;
                                    double accuracy = DEFAULT_ACCURACY / 100.0;
                                    getFloodArea(decisionSupportProvider, floodLayerSource, lat, lon, radius,
                                            floodHeight, accuracy);
                                }
                            }

                            private void getFloodArea(DecisionSupportProvider decisionSupportProvider,
                                                      Vector floodLayerSource, double lat, double lon, double radius, int floodHeight,
                                                      double accuracy) {
                                decisionSupportProvider.getFloodAreaPoints(
                                        new FloodAreaRequest(lat, lon, radius, floodHeight, accuracy),
                                        new LoggingMethodCallback<OkResponse<FloodAreaCapability>>() {

                                            @Override
                                            protected void success(Method method,
                                                                   OkResponse<FloodAreaCapability> response) {
                                                FloodAreaCapability content = response.getContent();

                                                drawFloodArea(floodLayerSource, content);
                                                lastForm = new FloodAreaForm(content) {
                                                    protected void recalculate() {
                                                        floodLayerSource.clear(false);
                                                        hide();
                                                        Integer floodHeightSliderValue = getFloodHeightSliderValue();
                                                        Integer accuracySliderValue = getAccuracySliderValue();
                                                        getFloodArea(decisionSupportProvider, floodLayerSource, lat,
                                                                lon, radius, floodHeightSliderValue,
                                                                accuracySliderValue / 100.0);
                                                    }

                                                };
                                            }

                                        });
                            }

                            @Override
                            public void onDrawStart(Event event) {
                                if (lastForm != null) {
                                    lastForm = hideForm(lastForm);
                                }
                                floodLayerSource.clear(false);
                            }

                        }, null, OLMeasureMode.PERSIST);

            }
        };
    }


    public static SelectionHandler<Item> getSelectionHandlerForLosArea(OLMapManager olMapManager,
                                                                       DecisionSupportProvider decisionSupportProvider) {
        return new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
                //TODO floodLayer -> DSLayer
                Vector floodLayerSource = olMapManager.getFloodLayerSource();

                olMapManager.startMeasure(OLMeasureManager.OLMeasureType.CIRCLE,
                        new OLMeasure.DrawAndMeasureListener() {
                            private LosAreaForm lastForm;

                            @Override
                            public void onMeasure(MeasureEvent evt) {
                                if (evt.getGeometry() instanceof Circle) {
                                    Circle circle = (Circle) evt.getGeometry();
                                    Coordinate center = circle.getCenter();
                                    double lat = center.lat();
                                    double lon = center.lon();
                                    double radius = circle.getRadius();
                                    int observerHeight = DEFAULT_OBSERVER_HEIGHT;
                                    double accuracy = DEFAULT_ACCURACY / 100.0;
                                    getLosArea(decisionSupportProvider, floodLayerSource, lat, lon, radius,
                                            observerHeight, accuracy);
                                }
                            }

                            private void getLosArea(DecisionSupportProvider decisionSupportProvider,
                                                    Vector floodLayerSource, double lat, double lon, double radius, int observerHeight,
                                                    double accuracy) {
                                decisionSupportProvider.getLosAreaPoints(
                                        new LosAreaRequest(lat, lon, radius, observerHeight, accuracy),
                                        new LoggingMethodCallback<OkResponse<LosAreaCapability>>() {

                                            @Override
                                            protected void success(Method method,
                                                                   OkResponse<LosAreaCapability> response) {
                                                LosAreaCapability content = response.getContent();

                                                drawLosArea(floodLayerSource, content);
                                                lastForm = new LosAreaForm(content) {
                                                    protected void recalculate() {
                                                        floodLayerSource.clear(false);
                                                        hide();
                                                        Integer observerHeightSliderValue = getObserverHeightSliderValue();
                                                        Integer accuracySliderValue = getAccuracySliderValue();
                                                        getLosArea(decisionSupportProvider, floodLayerSource, lat,
                                                                lon, radius, observerHeightSliderValue,
                                                                accuracySliderValue / 100.0);
                                                    }

                                                };
                                            }

                                        });
                            }

                            @Override
                            public void onDrawStart(Event event) {
                                if (lastForm != null) {
                                    lastForm = hideForm(lastForm);
                                }
                                floodLayerSource.clear(false);
                            }

                        }, null, OLMeasureMode.PERSIST);

            }
        };
    }

    public static Menu createDecisionSupportMenu(OLMapManager olMapManager,
                                                 DecisionSupportProvider decisionSupportProvider, Messages messages) {
        String groupName = "decisionsupport";
        Menu menu = new Menu();
        CheckMenuItem disabled = GxtComponentsUtils.createCheckMenuItemWithGroup(messages.menu_disabled(), groupName,
                GisImages.INSTANCE.disabled());
        disabled.setChecked(true);
        menu.add(disabled);

        CheckMenuItem floodArea = GxtComponentsUtils
                .createCheckMenuItemWithGroup(messages.gismenu_decisionsupport_floodarea(), groupName);
        menu.add(floodArea);

        CheckMenuItem losArea = GxtComponentsUtils
                .createCheckMenuItemWithGroup(messages.gismenu_decisionsupport_losarea(), groupName);
        menu.add(losArea);

        CheckMenuItem terrainProfile = GxtComponentsUtils
                .createCheckMenuItemWithGroup(messages.gismenu_decisionsupport_terrainProfile(), groupName);
        menu.add(terrainProfile);

        CheckMenuItem spyModel = GxtComponentsUtils
                .createCheckMenuItemWithGroup(messages.layer_selectForSpyMouseMode(), groupName);
        menu.add(spyModel);

        menu.addSelectionHandler(new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
                olMapManager.stopMeasure();
                olMapManager.stopSpyMouseMode();
                Item selectedItem = event.getSelectedItem();
                if (selectedItem.equals(floodArea)) {
                    getSelectionHandlerForFloodArea(olMapManager, decisionSupportProvider).onSelection(event);
                } else if (selectedItem.equals(losArea)) {
                    getSelectionHandlerForLosArea(olMapManager, decisionSupportProvider).onSelection(event);
                } else if (selectedItem.equals(terrainProfile)) {
                    getSelectionHandlerForTerrainProfile(olMapManager, decisionSupportProvider).onSelection(event);
                } else if (selectedItem.equals(spyModel)) {
                    olMapManager.startSpyMouseMode();
                }
            }

        });
        return menu;
    }

    private static void drawFloodArea(Vector floodLayerSource, FloodAreaCapability floodAreaCapability) {
        Integer minHeight = floodAreaCapability.getMinHeight();
        Integer floodHeight = floodAreaCapability.getFloodHeight();
        List<PolygonWithHeight> polygonsWithHeight = floodAreaCapability.getPolygonsWithHeight();
        for (PolygonWithHeight polygonWithHeight : polygonsWithHeight) {
            List<List<Double>> latLonCoordinates = polygonWithHeight.getCoordinates();
            Coordinate[][] coordinates = new Coordinate[latLonCoordinates.size()][];
            for (int i = 0; i < latLonCoordinates.size(); i++) {
                coordinates[i] = toLatLonCoordinates(latLonCoordinates.get(i));
            }
            Integer height = polygonWithHeight.getHeight();
            Polygon polygon = OLFactory.createPolygon(coordinates);
            FeatureInfoProvider featureInfoProvider = new FeatureInfoProvider() {

                @Override
                public String getTooltipText() {
                    return height + "m";
                }
            };
            Feature feature = new Feature();
            feature.set(FeatureInfoProvider.PROVIDER_KEY, featureInfoProvider);
            feature.setGeometry(polygon);
            StyleOptions styleOptions = new StyleOptions();
            double alpha = calculateAlpha(floodHeight, minHeight, height);
            styleOptions.setFill(OLFactory.createFill(OLFactory.createColor(0, 0, 255, alpha)));
            styleOptions.setStroke(null);
            Style style = new Style(styleOptions);
            feature.setStyle(style);
            floodLayerSource.addFeature(feature);
        }
        List<Double> minElevationPointPolygon = floodAreaCapability.getMinElevationPointPolygon();
        Coordinate[][] coordinates = new Coordinate[1][];
        coordinates[0] = toLatLonCoordinates(minElevationPointPolygon);
        Polygon polygon = OLFactory.createPolygon(coordinates);
        FeatureInfoProvider featureInfoProvider = new FeatureInfoProvider() {

            @Override
            public String getTooltipText() {
                return floodAreaCapability.getMinElevation() + " m.n.p.m.";
            }
        };
        Feature feature = new Feature();
        feature.set(FeatureInfoProvider.PROVIDER_KEY, featureInfoProvider);
        feature.setGeometry(polygon);
        StyleOptions styleOptions = new StyleOptions();
        styleOptions.setFill(OLFactory.createFill(OLFactory.createColor(255, 0, 0, 1)));
        styleOptions.setStroke(null);
        Style style = new Style(styleOptions);
        feature.setStyle(style);
        floodLayerSource.addFeature(feature);
    }

    private static void drawLosArea(Vector floodLayerSource, LosAreaCapability losAreaCapability) {
        List<List<Double>> latLonCoordinates = losAreaCapability.getLosAreaList();
        Coordinate[][] coordinates = new Coordinate[latLonCoordinates.size()][];
        for (int i = 0; i < latLonCoordinates.size(); i++) {
            coordinates[i] = toLatLonCoordinates(latLonCoordinates.get(i));
        }
        Polygon polygon = OLFactory.createPolygon(coordinates);
        Feature feature = new Feature();
        feature.setGeometry(polygon);
        StyleOptions styleOptions = new StyleOptions();
        styleOptions.setFill(OLFactory.createFill(OLFactory.createColor(255, 0, 0, 0.5)));
        styleOptions.setStroke(null);
        Style style = new Style(styleOptions);
        feature.setStyle(style);
        floodLayerSource.addFeature(feature);
    }

    private static List<Double> toLatLonList(Coordinate[] coordinatesArray) {
        List<Double> coordinates = new ArrayList<>();
        for (Coordinate coordinate : coordinatesArray) {
            coordinates.add(coordinate.lat());
            coordinates.add(coordinate.lon());
        }
        return coordinates;
    }

    private static <T extends com.google.gwt.user.client.ui.UIObject> T hideForm(T form) {
        form.hide();
        return null;
    }

    private static Coordinate[] toLatLonCoordinates(List<Double> latLonCoordinates) {
        int pointCount = latLonCoordinates.size() / 2;
        Coordinate[] coordinates = new Coordinate[pointCount];
        for (int i = 0; i < pointCount; i++) {
            coordinates[i] = GeoUtils.createCoordinatesFromLatLonInMapProjection(latLonCoordinates.get(i * 2),
                    latLonCoordinates.get(i * 2 + 1));
        }
        return coordinates;
    }

    private static double calculateAlpha(Integer floodHeight, Integer minHeight, Integer height) {
        if (floodHeight == minHeight) {
            return 0.5;
        }
        return 0.1 + 0.8 * (height - minHeight) / (floodHeight - minHeight);
    }

}
