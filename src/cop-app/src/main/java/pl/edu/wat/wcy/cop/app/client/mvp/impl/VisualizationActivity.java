/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.mvp.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import ol.Collection;
import ol.Coordinate;
import ol.Feature;
import ol.OLFactory;
import ol.interaction.Modify;
import ol.interaction.ModifyOptions;
import ol.source.Vector;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.client.domain.MapSymbolClientModel;
import pl.edu.wat.wcy.cop.app.client.domain.spec.*;
import pl.edu.wat.wcy.cop.app.client.events.*;
import pl.edu.wat.wcy.cop.app.client.mvp.BaseActivity;
import pl.edu.wat.wcy.cop.app.client.ol.GeoUtils;
import pl.edu.wat.wcy.cop.app.client.ol.OLLayerUtils;
import pl.edu.wat.wcy.cop.app.client.ol.OLMapManager;
import pl.edu.wat.wcy.cop.app.client.ol.OLNativeMethods;
import pl.edu.wat.wcy.cop.app.client.services.CrudAdapterService;
import pl.edu.wat.wcy.cop.app.client.services.LocalCache;
import pl.edu.wat.wcy.cop.app.client.services.ObjectsPreparator;
import pl.edu.wat.wcy.cop.app.client.services.server.VisualizationDataProvider;
import pl.edu.wat.wcy.cop.app.client.ui.forms.AbstractForm;
import pl.edu.wat.wcy.cop.app.client.utils.*;
import pl.edu.wat.wcy.cop.app.client.utils.gui.DialogUtils;
import pl.edu.wat.wcy.cop.app.client.utils.gui.NotificationUtils;
import pl.edu.wat.wcy.cop.app.client.visualisation.grid.VisualisationFormUtils;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.domain.*;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.GpxTraceDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueSegmentDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.SearchAndRescueAlgorithm;
import pl.edu.wat.wcy.cop.app.shared.response.OkResponse;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
// Represents visualization activity.

public class VisualizationActivity extends BaseActivity<VisualizationPlace> implements VisualizationView.Presenter {
    private final VisualizationActivityEventBinder eventBinder = GWT.create(VisualizationActivityEventBinder.class);
    AbstractForm form = null;
    MapSymbolClientModel model = null;
    @Inject
    private EventBus eventBus;
    @Inject
    private VisualizationView visualizationView;
    @Inject
    private VisualizationDataProvider visualizationDataProvider;
    @Inject
    private OLMapManager mapManager;
    @Inject
    private ObjectsPreparator objectsPreparator;
    @Inject
    private CrudAdapterService crudService;
    private ScenarioDto scenario;
    private ScenarioClientModel scenarioModel;
    private Modify modifyInteraction;

    private boolean initialised;
    private Feature modifyFeature;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.google.gwt.activity.shared.Activity#start(com.google.gwt.user.client.
     * ui.AcceptsOneWidget, com.google.gwt.event.shared.EventBus)
     */
    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        super.start(panel, eventBus);
        visualizationView.setPresenter(this);
        panel.setWidget(visualizationView);
        eventBinder.bindEventHandlers(this, eventBus);
        CopAppUtils.setVisualisationView(true);
    }

    public void addObjectFromContextMenu(Coordinate coords, String type) {
        // TODO do poprawy
        coords = GeoUtils.transformToLatLon(coords);
        GeoPointDto point = new GeoPointDto(coords.lat(), coords.lon());
        form = null;
        model = null;
        SelectHandler selectHandler = new SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                CopLogger.getInstance().debug(this, "Wysylam zdarzenie dodania nowego obiektu");
                eventBus.fireEvent(new ObjectToAddEvent(this, model));
            }
        };

        model = buildModelForType(type, point);
        selectHandler.onSelect(null);
    }

    private MapSymbolClientModel buildModelForType(String type, GeoPointDto point) {
        switch (type) {
            case "1":
                return new App6AMilitaryUnitClientModel(new App6AMilitaryUnitDto(point, "sfgpu-----ed---", ""), null, null);
            case "2":
                return new MSWiAUnitClientModel(new MSWiAUnitDto(point), null, null);
            case "3":
                return new PointOfInterestClientModel(new PointOfInterestDto(point), null, null);
            case "4":
                return new CrisisEventClientModel(new CrisisEventDto(point), null, null);
            case "5":
                return new ADatP3ReportClientModel(new ADatP3ReportDto(), null);
            case "6":
                return new SearchAndRescueClientModel(new SearchAndRescueDto(point, SearchAndRescueAlgorithm.SIMPLE), new Feature[0]);
            case "7":
                SearchAndRescueDto dto = new SearchAndRescueDto(point);
                GeoPointDto directionPoint = new GeoPointDto(point);
                directionPoint.setLon(directionPoint.getLon() + 0.1);
                dto.setDirectionPoint(directionPoint);
                return new SearchAndRescueClientModel(dto, new Feature[0]);
            case "8":
                return new SearchAndRescueClientModel(new SearchAndRescueDto(point, SearchAndRescueAlgorithm.GRID), new Feature[0]);
            default:
                return null;
        }
    }

    public void editObject(Feature feature) {
        Object owner = feature.get(AppConstants.FEATURE_OBJECT_OWNER);
        AbstractForm form = VisualisationFormUtils.getFormForObject(owner, new SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
                processUpdate(owner);
            }
        });
        if (form == null) {
            // DialogUtils.showAlertDialog(title, message);
        }
    }

    public void deleteObject(Feature feature) {
        Object owner = feature.get(AppConstants.FEATURE_OBJECT_OWNER);
        if (owner != null) {
            DialogUtils.showConfirmDialog(CopGinInjector.INSTANCE.getMessages().action_remove(),
                    CopGinInjector.INSTANCE.getMessages().action_remove_msg(), new SelectHandler() {
                        @Override
                        public void onSelect(SelectEvent event2) {
                            processRemove(owner);
                        }
                    });
        }

    }

    public void modifyPosition(Feature feature) {

        Object owner = modifyFeature == null ? feature.get(AppConstants.FEATURE_OBJECT_OWNER) : modifyFeature.get(AppConstants.FEATURE_OBJECT_OWNER);

        if (owner instanceof MapSymbolClientModel) {
            MapSymbolClientModel spod = (MapSymbolClientModel) owner;


            // TODO aktualizacja dla poligonow itp
            if (modifyInteraction == null) {
                ModifyOptions options = OLFactory.createOptions();

                Collection<Feature> olCollection = OLFactory.createCollection();
                if (spod instanceof SearchAndRescueClientModel) {
                    SearchAndRescueClientModel sarcm = (SearchAndRescueClientModel) spod;
                    feature = sarcm.getEditableFeature();
                }
                olCollection.push(feature);
                options.setFeatures(olCollection);
                modifyInteraction = new Modify(options);
                mapManager.getMap().addInteraction(modifyInteraction);
                modifyFeature = feature;
                NotificationUtils.showToast("Drawing mode", "Najedź nad wybrany element, żeby rozpocząc edycję");
            } else {
                CopyCoordsToObjectUtils.copyCoord(feature, spod);
                Coordinate coords = OLNativeMethods.getFeatureCords(feature);
                ((ScenarioPointObjectDto) spod.getObject()).setPoint(GeoUtils.createGeoPointsFromMapCoordinates(coords));
                processUpdate(owner);
                mapManager.getMap().removeInteraction(modifyInteraction);
                modifyInteraction = null;
                modifyFeature = null;
            }
        }

    }


    public void putCoordsToLocalCache(Coordinate coords) {
        coords = GeoUtils.transformToLatLon(coords);
        LocalCache.getInstance().put(AppConstants.COPIED_LAT, coords.lat());
        LocalCache.getInstance().put(AppConstants.COPIED_LON, coords.lon());
    }

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.activity.shared.AbstractActivity#onStop()
     */
    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void setPlace(VisualizationPlace place) {
        super.setPlace(place);
        loadScenario();
    }

    @EventHandler
    public void onMapLoadedEvent(MapLoadedEvent event) {
        if (!initialised) {
            OLNativeMethods.initStaticFunctions();
            OLNativeMethods.initContextMenu(mapManager.getMap(), VisualizationActivity.this);
        }
    }

    @EventHandler
    public <T> void onObjectToAddEvent(ObjectToAddEvent<T> event) {
        if (shouldProcessEvent(event)) {
            processAdd(event.getObject());
        }
    }

    @EventHandler
    public <T> void onObjectToUpdatevent(ObjectToUpdateEvent<T> event) {
        if (shouldProcessEvent(event)) {
            processUpdate(event.getObject());
        }
    }

    @EventHandler
    public <T> void onObjectToRemoveEvent(ObjectToRemoveEvent<T> event) {
        if (shouldProcessEvent(event)) {
            processRemove(event.getObject());
        }
    }

    @Override
    public void clearSituation() {
        visualizationDataProvider.clearScenario(getPlace().getId(),
                new LoggingMethodCallback<OkResponse<ScenarioDto>>() {
                    @Override
                    public void success(Method method, OkResponse<ScenarioDto> response) {
                        VisualizationActivity.this.scenario = response.getContent();
                        loadScenarioDetails();
                    }
                });

    }

    /**
     *
     */
    private void loadScenario() {
        visualizationDataProvider.getScenario(getPlace().getId(), new LoggingMethodCallback<OkResponse<ScenarioDto>>() {
            @Override
            public void success(Method method, OkResponse<ScenarioDto> response) {
                VisualizationActivity.this.scenario = response.getContent();
                loadScenarioDetails();

            }
        });

    }

    /**
     *
     */
    private void loadScenarioDetails() {

        objectsPreparator.loadScenario(this.scenario, new LoggingAsyncCallback<ScenarioClientModel>() {

            @Override
            protected void success(ScenarioClientModel response) {
                scenarioModel = response;
                mapManager.reloadMapConfiguration(scenario.getMapConfiguration());
                mapManager.setReports(scenarioModel.getReports());

                scenarioModel.getApp6aMilitaryUnits().forEach(
                        x -> mapManager.getSourceLayerForObject(x.getObject()).addFeatures(x.getFeatures()));
                scenarioModel.getCrisisEvents().forEach(
                        x -> mapManager.getSourceLayerForObject(x.getObject()).addFeatures(x.getFeatures()));
                scenarioModel.getMswiaUnits().forEach(
                        x -> mapManager.getSourceLayerForObject(x.getObject()).addFeatures(x.getFeatures()));
                scenarioModel.getSarUnits().forEach(
                        x -> mapManager.getSourceLayerForObject(x.getObject()).addFeatures(x.getFeatures()));
                scenarioModel.getGpx().forEach(
                        x -> mapManager.getSourceLayerForObject(x.getObject()).addFeatures(x.getFeatures()));
                for (PointOfInterestClientModel poi : scenarioModel.getPoi()) {
                    mapManager.getSourceLayerForObject(poi.getObject()).addFeatures(poi.getFeatures());
                }
                scenarioModel.getReports().forEach(
                        x -> mapManager.getSourceLayerForObject(x.getObject()).addFeatures(x.getFeatures()));

                eventBus.fireEvent(new ScenarioLoadedEvent(this, scenarioModel));
                visualizationView.setScenario(scenarioModel);
                // domyslnie pobiera z danych scenariusza
                // dlatego wyslij zdarzenie po skonczeniu petli przetwarzania
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {

                    public void execute() {

                        OLNativeMethods.fitToFeatureOnMap(mapManager.getMap(), mapManager.getAllFeatures());

                    }
                });
                initPoaVerifyForScenario();
            }
        });
    }

    public void refreshSegmentsDetails(){
        crudService.getSegmentsForScenarionAndModifiedDate(scenario.getId(), lastDate, new MethodCallback<OkResponse<List<SearchAndRescueSegmentDto>>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {

            }

            @Override
            public void onSuccess(Method method, OkResponse<List<SearchAndRescueSegmentDto>> response) {
                lastDate = new Date();
                List<SearchAndRescueSegmentDto> allSegments = new LinkedList<>();
                scenario.getSearchAndRescues().forEach(ss -> allSegments.addAll(ss.getAreaZones()));
                allSegments.addAll(scenario.getSearchAndRescuesSegments());
                boolean updated = false;
                for (SearchAndRescueSegmentDto s : response.getContent()) {
                    SearchAndRescueSegmentDto sd = allSegments.stream().filter(x -> x.getId() == s.getId()).findFirst().orElse(null);
                    if (sd != null && sd.getPoc() != s.getPoc()) {
                        sd.setPoc(s.getPoc());
                        updated = true;
                    }
                }
                if (updated) {
                    NotificationUtils.showToast("Stan sytuacji", "Dla wybranych sektorów została zmodyfikwowana wartość POC");
                }else{
                    NotificationUtils.showToast("Stan sytuacji", "Brak wykrytych zmian sektorów dla wartości POC");
                }

            }
        });
    }
    Timer lastTimer = null;
    Date lastDate = new Date(Long.MIN_VALUE);

    private void initPoaVerifyForScenario() {
        if (lastTimer == null) {
            // TODO add in furure currenlty POS refresh on click
//            lastTimer = new Timer() {
//                @Override
//                public void run() {
//                    refreshSegmentsDetails();
//                }
//            };
//            lastTimer.scheduleRepeating(1000 * 30);
        }

    }

    /**
     * @param object
     */
    private void processAdd(Object object) {


        final MapSymbolClientModel clientModelObjectToWorkOn = getObjectToWorkOn(object);
        final Object objectToWorkOn = clientModelObjectToWorkOn.getObject();
        CopLogger.getInstance().debug(this, "Przetwarzam operacje dodania nowego obiektu " + objectToWorkOn);
        if (objectToWorkOn instanceof ScenarioObject) {
            ((ScenarioObject) objectToWorkOn).setScenarioId(scenario.getId());
        }
        if (objectToWorkOn instanceof ADatP3ReportDto) {
            crudService.add(objectToWorkOn,
                    getAddCallback((ADatP3ReportDto) objectToWorkOn, clientModelObjectToWorkOn));
        } else if (objectToWorkOn instanceof App6AMilitaryUnitDto) {
            crudService.add(objectToWorkOn,
                    getAddCallback((App6AMilitaryUnitDto) objectToWorkOn, clientModelObjectToWorkOn));
        } else if (objectToWorkOn instanceof CrisisEventDto) {
            crudService.add(objectToWorkOn, getAddCallback((CrisisEventDto) objectToWorkOn, clientModelObjectToWorkOn));
        } else if (objectToWorkOn instanceof MSWiAUnitDto) {
            crudService.add(objectToWorkOn, getAddCallback((MSWiAUnitDto) objectToWorkOn, clientModelObjectToWorkOn));
        } else if (objectToWorkOn instanceof PointOfInterestDto) {
            crudService.add(objectToWorkOn,
                    getAddCallback((PointOfInterestDto) objectToWorkOn, clientModelObjectToWorkOn));
        } else if (objectToWorkOn instanceof SearchAndRescueDto) {
            crudService.add(objectToWorkOn,
                    getAddCallback((SearchAndRescueDto) objectToWorkOn, clientModelObjectToWorkOn));
        } else if (objectToWorkOn instanceof GpxTraceDto) {
            crudService.add(objectToWorkOn,
                    getAddCallback((GpxTraceDto) objectToWorkOn, clientModelObjectToWorkOn));
        }

    }

    /**
     * @param object
     */


    public <T> void processUpdate(Object object, DefaultAsyncCallback<Object>... additionalCallbacks) {
        CopLogger.getInstance().debug(this, "Przetwarzam operacje aktualizacji obiektu");

        MapSymbolClientModel clientModelObjectToWorkOn = getObjectToWorkOn(object);

        Object objectToWorkOn = clientModelObjectToWorkOn.getObject();
        if (objectToWorkOn instanceof ADatP3ReportDto) {
            crudService.update(objectToWorkOn,
                    getUpdateCallback((ADatP3ReportDto) objectToWorkOn, clientModelObjectToWorkOn, additionalCallbacks));
        } else if (objectToWorkOn instanceof App6AMilitaryUnitDto) {
            crudService.update(objectToWorkOn,
                    getUpdateCallback((App6AMilitaryUnitDto) objectToWorkOn, clientModelObjectToWorkOn, additionalCallbacks));
        } else if (objectToWorkOn instanceof CrisisEventDto) {
            crudService.update(objectToWorkOn,
                    getUpdateCallback((CrisisEventDto) objectToWorkOn, clientModelObjectToWorkOn, additionalCallbacks));
        } else if (objectToWorkOn instanceof MSWiAUnitDto) {
            crudService.update(objectToWorkOn,
                    getUpdateCallback((MSWiAUnitDto) objectToWorkOn, clientModelObjectToWorkOn, additionalCallbacks));
        } else if (objectToWorkOn instanceof PointOfInterestDto) {
            crudService.update(objectToWorkOn,
                    getUpdateCallback((PointOfInterestDto) objectToWorkOn, clientModelObjectToWorkOn, additionalCallbacks));
        } else if (objectToWorkOn instanceof SearchAndRescueDto) {
            crudService.update(objectToWorkOn,
                    getUpdateCallback((SearchAndRescueDto) objectToWorkOn, clientModelObjectToWorkOn, additionalCallbacks));
        } else if (objectToWorkOn instanceof GpxTraceDto) {
            crudService.update(objectToWorkOn,
                    getUpdateCallback((GpxTraceDto) objectToWorkOn, clientModelObjectToWorkOn, additionalCallbacks));
        }
    }


    /**
     * @param object
     */
    private void processRemove(Object object) {
        CopLogger.getInstance().debug(this, "Przetwarzam operacje usuniecia oiektu");

        final MapSymbolClientModel clientModelObjectToWorkOn = getObjectToWorkOn(object);
        final Object objectToWorkOn = clientModelObjectToWorkOn.getObject();
        crudService.delete(objectToWorkOn, new LoggingMethodCallback<OkResponse<String>>() {
            @Override
            protected void success(Method method, OkResponse<String> response) {
                OLLayerUtils.removeFeatures(mapManager.getSourceLayerForObject(objectToWorkOn),
                        clientModelObjectToWorkOn.getFeatures());
                eventBus.fireEvent(new ObjectRemovedEvent(this, clientModelObjectToWorkOn));
                CopLogger.getInstance().debug(this, "Usunieto obiekt");

            }
        });

    }

    /**
     * @param event
     * @return
     */
    private <T> boolean shouldProcessEvent(ObjectEvent<T> event) {
        T eventObject = event.getObject();
        return ActivityState.STARTED.equals(getState()) && isInterestedObject(eventObject);
    }

    /**
     * @param object
     * @return
     */
    private boolean isInterestedObject(Object object) {
        return object instanceof App6AMilitaryUnitClientModel || object instanceof CrisisEventClientModel
                || object instanceof MSWiAUnitClientModel || object instanceof PointOfInterestClientModel
                || object instanceof ADatP3ReportClientModel || object instanceof SearchAndRescueClientModel
                || object instanceof GpxTraceClientModel;
    }

    /**
     * @param object
     * @return
     */
    private MapSymbolClientModel getObjectToWorkOn(Object object) {
        if (object instanceof MapSymbolClientModel) {
            return (MapSymbolClientModel) object;
        } else
            // dziala dobrze tylko dla SAR, reszte TODO
            return scenarioModel.getModelForObject(object);
    }

    private <T> LoggingMethodCallback<OkResponse<T>> getAddCallback(T objectToWorkOn,
                                                                    MapSymbolClientModel clientModelObjectToWorkOn) {
        return new LoggingMethodCallback<OkResponse<T>>() {
            @Override
            protected void success(Method method, OkResponse<T> response) {
                objectsPreparator.updateObjectInfo(response.getContent(), clientModelObjectToWorkOn,
                        new LoggingAsyncCallback<MapSymbolClientModel>() {

                            @Override
                            protected void success(MapSymbolClientModel response) {
                                eventBus.fireEvent(new ObjectAddedEvent(this, response));
                                CopLogger.getInstance().debug(this, "Dodano nowy obiekt");
                                Vector source = mapManager.getSourceLayerForObject(objectToWorkOn);
                                Feature[] features = response.getFeatures();
                                source.addFeatures(features);
                                OLNativeMethods.fitToFeatureOnMap(mapManager.getMap(), response.getFeatures());
                                scenarioModel.addClientModel(clientModelObjectToWorkOn);
                                scenario.addObject(response.getObject());
                            }

                        });

            }
        };
    }

    private <T> LoggingMethodCallback<OkResponse<T>> getUpdateCallback(T objectToWorkOn,
                                                                       MapSymbolClientModel clientModelObjectToWorkOn,
                                                                       DefaultAsyncCallback<Object>... additionalCallbacks) {
        return new LoggingMethodCallback<OkResponse<T>>() {
            @Override
            protected void success(Method method, OkResponse<T> response) {
                OLLayerUtils.removeFeatures(mapManager.getSourceLayerForObject(objectToWorkOn),
                        clientModelObjectToWorkOn.getFeatures());
                objectsPreparator.updateObjectInfo(response.getContent(), clientModelObjectToWorkOn,
                        new LoggingAsyncCallback<MapSymbolClientModel>() {

                            @Override
                            protected void success(MapSymbolClientModel response) {
                                CopLogger.getInstance().debug(this, "Zaktualizowano obiekt");
                                mapManager.getSourceLayerForObject(objectToWorkOn).addFeatures(response.getFeatures());
                                eventBus.fireEvent(new ObjectUpdatedEvent(this, response));
                                for (DefaultAsyncCallback<Object> callback : additionalCallbacks) {
                                    callback.onSuccess(response.getObject());
                                }
                            }

                        });

            }
        };
    }

    interface VisualizationActivityEventBinder extends EventBinder<VisualizationActivity> {
    }

}
