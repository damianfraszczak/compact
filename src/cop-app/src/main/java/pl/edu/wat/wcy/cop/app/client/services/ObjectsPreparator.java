/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.services;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import ol.Coordinate;
import ol.Feature;
import ol.geom.Polygon;
import org.fusesource.restygwt.client.Method;
import pl.edu.wat.wcy.cop.app.client.domain.MapSymbolClientModel;
import pl.edu.wat.wcy.cop.app.client.domain.spec.*;
import pl.edu.wat.wcy.cop.app.client.domain.spec.ADatP3ReportClientModel.AdatP3FeatureType;
import pl.edu.wat.wcy.cop.app.client.ol.GeoUtils;
import pl.edu.wat.wcy.cop.app.client.ol.OLFeatureBuilder;
import pl.edu.wat.wcy.cop.app.client.ol.OLFeatureBuilderAdapt3;
import pl.edu.wat.wcy.cop.app.client.ol.OLStyleManager;
import pl.edu.wat.wcy.cop.app.client.ol.curve.TypeOfBSpline;
import pl.edu.wat.wcy.cop.app.client.ol.extra.FeatureInfoProvider;
import pl.edu.wat.wcy.cop.app.client.services.server.SymbolServiceProvider;
import pl.edu.wat.wcy.cop.app.client.utils.*;
import pl.edu.wat.wcy.cop.app.client.utils.images.CrisisEventTypeToImageMapper;
import pl.edu.wat.wcy.cop.app.client.utils.images.IconsUtils;
import pl.edu.wat.wcy.cop.app.client.utils.images.PoiTypeToMarkerImageMapper;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.ADatP3;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.ADatP3Parser;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.domain.*;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.*;
import pl.edu.wat.wcy.cop.app.shared.response.OkResponse;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Singleton
// Represents objects preparator.
public class ObjectsPreparator {

    private static final double SAR_POINT_RADIUS = 50;
    /**
     * @param codes
     * @param defaultAsyncCallback
     */
    int count = 0;
    private LocalCache cache = LocalCache.getInstance();
    @Inject
    private SymbolServiceProvider symbolServiceProvider;
    @Inject
    private ADatP3Parser parser;

    /**
     * @param content
     * @param clientModelObjectToWorkOn
     * @param loggingAsyncCallback
     */
    public <T> void updateObjectInfo(T content, MapSymbolClientModel clientModelObjectToWorkOn,
                                     LoggingAsyncCallback<MapSymbolClientModel> loggingAsyncCallback) {
        ClientAutomapper.copyFromLeftToRight(content, clientModelObjectToWorkOn.getObject());
        updateObjectFeatures(clientModelObjectToWorkOn, loggingAsyncCallback);

    }

    /**
     * @param object
     * @param loggingAsyncCallback
     */
    public void updateObjectFeatures(MapSymbolClientModel object,
                                     LoggingAsyncCallback<MapSymbolClientModel> loggingAsyncCallback) {
        List<String> codesToLoad = collectCodes(object);
        loadImagesForCodes(codesToLoad, new LoggingAsyncCallback<List<String>>() {

            @Override
            protected void success(List<String> response) {
                MapSymbolClientModel result = updateObjectFeature(object);
                loggingAsyncCallback.onSuccess(result);
            }
        });
    }

    @SuppressWarnings("rawtypes")
    public void loadImagesForMapSymbolsWithCodes(List<? extends ISymbolOnTheMapWithCode> objects,
                                                 AsyncCallback<List<String>> callback) {
        loadImagesForCodes(objects.stream().map(x -> x.getCode()).collect(Collectors.toList()), callback);
    }

    public void loadImagesForCodes(List<String> codes, AsyncCallback<List<String>> callback) {
        DefaultAsyncCallback<Void> successCallback = new DefaultAsyncCallback<Void>() {

            @Override
            public void onSuccess(Void arg) {
                resizeImagesForCodes(codes, new DefaultAsyncCallback<Void>() {

                    @Override
                    public void onSuccess(Void arg0) {
                        callback.onSuccess(cache.get(codes));

                    }
                });
            }
        };
        List<String> codesToLoad = filterCodesToLoad(codes);
        if (!codesToLoad.isEmpty()) {
            symbolServiceProvider.getBase64ImagesForCodes(codesToLoad,
                    new LoggingMethodCallback<OkResponse<List<String>>>() {

                        @Override
                        protected void success(Method method, OkResponse<List<String>> response) {
                            cache.put(codesToLoad, response.getContent());
                            successCallback.onSuccess(null);
                        }
                    });
        } else {
            successCallback.onSuccess(null);
        }

    }

    private List<String> collectCodes(MapSymbolClientModel object) {
        List<String> codesToLoad = new LinkedList<>();
        if (object instanceof App6AMilitaryUnitClientModel) {
            codesToLoad.add(((App6AMilitaryUnitClientModel) object).getObject().getCode());
        } else if (object instanceof MSWiAUnitClientModel) {
            codesToLoad.add(((MSWiAUnitClientModel) object).getObject().getCode());
        }
        return codesToLoad;
    }

    private List<String> filterCodesToLoad(List<String> codes) {
        return codes.stream()
                .filter(code -> code != null)
                .filter(code -> cache.get(code) == null)
                .collect(Collectors.toList());
    }


    public void loadScenario(ScenarioDto scenario, AsyncCallback<ScenarioClientModel> callback) {
        List<ISymbolOnTheMapWithCode> codeObjects = new LinkedList<>();
        codeObjects.addAll(scenario.getApp6aMilitaryUnits());
        codeObjects.addAll(scenario.getMswiaUnits());

        LoggingAsyncCallback<List<String>> call = new LoggingAsyncCallback<List<String>>() {

            @Override
            public void success(List<String> base64Images) {
                List<App6AMilitaryUnitClientModel> app6aObjects = loadAPP6AMilitaryUnits(
                        scenario.getApp6aMilitaryUnits());
                List<MSWiAUnitClientModel> mswiaObjects = loadMSWiAMilitaryUnits(scenario.getMswiaUnits());
                List<PointOfInterestClientModel> poiObjects = loadPOI(scenario.getPoi());
                List<CrisisEventClientModel> crisisObjects = loadCrisisEvents(scenario.getCrisisEvents());
                List<ADatP3ReportClientModel> reports = loadReports(scenario.getReportsADatP3());
                List<SearchAndRescueClientModel> sar = loadSAR(scenario.getSearchAndRescues());
                List<GpxTraceClientModel> gpx = loadGPX(scenario.getGpxTraces());
                List<SearchAndRescueSegmentClientModel> segments = loadSARSegments(scenario.getSearchAndRescuesSegments());
                callback.onSuccess(new ScenarioClientModel(scenario, reports, app6aObjects, crisisObjects, poiObjects,
                        mswiaObjects, sar, gpx, segments));
            }

        };

        if (codeObjects.isEmpty()) {
            call.onSuccess(new LinkedList<String>());
        } else
            loadImagesForMapSymbolsWithCodes(codeObjects, call);
    }

    /**
     *
     * @param aDatP3Reports
     * @return
     */
    private List<ADatP3ReportClientModel> loadReports(List<ADatP3ReportDto> aDatP3Reports) {
        List<ADatP3ReportClientModel> reports = new LinkedList<ADatP3ReportClientModel>();
        for (ADatP3ReportDto report : aDatP3Reports) {
            ADatP3ReportClientModel reportClient = new ADatP3ReportClientModel(report,
                    parser.parseADatP3Content(report.getContent()));

            reportClient.putFeature(AdatP3FeatureType.HAZARD,
                    OLFeatureBuilderAdapt3.drawHazardArea(reportClient.getaDatP3(), TypeOfBSpline.PERIODIC_B_SPLINE));
            Feature releaseArea = OLFeatureBuilderAdapt3.drawReleaseArea(reportClient.getaDatP3());
            if (releaseArea != null) {
                reportClient.putFeature(AdatP3FeatureType.RELEASE, releaseArea);
            }
            reports.add(reportClient);
        }
        return reports;
    }


    private MapSymbolClientModel updateADatP3Report(ADatP3ReportClientModel report) {
        ADatP3 aDatP3 = parser.parseADatP3Content(report.getObject().getContent());

        report.setaDatP3(aDatP3);
        report.setCbrnType(aDatP3.getCbrnType());
        report.setDateOfIncydent(aDatP3.getDelta());
        report.putFeature(ADatP3ReportClientModel.AdatP3FeatureType.HAZARD,
                OLFeatureBuilderAdapt3.drawHazardArea(aDatP3, TypeOfBSpline.PERIODIC_B_SPLINE));
        Feature releaseArea = OLFeatureBuilderAdapt3.drawReleaseArea(aDatP3);

        if (releaseArea != null) {
            report.putFeature(AdatP3FeatureType.RELEASE, releaseArea);
        }
        return report;
    }

    /**
     * @param mswiaUnits
     * @return
     */
    private List<MSWiAUnitClientModel> loadMSWiAMilitaryUnits(List<MSWiAUnitDto> mswiaUnits) {
        return mswiaUnits.stream().map(x -> loadMSWiaUnit(x)).collect(Collectors.toList());
    }

    /**
     * @param x
     * @return
     */
    private MSWiAUnitClientModel loadMSWiaUnit(MSWiAUnitDto x) {
        return new MSWiAUnitClientModel(x, OLFeatureBuilder.createPointFeatureFromImageSourceUrl(x.getPoint(),
                x.getName(), getObjectMapImageForCode(x.getCode())), getObjectImageForCode(x.getCode()));
    }

    /**
     * @param x
     * @return
     */
    private MSWiAUnitClientModel updateMSWiaUnit(MSWiAUnitClientModel x) {
        x.setFeatures(OLFeatureBuilder.createPointFeatureFromImageSourceUrl(x.getObject().getPoint(),
                x.getObject().getName(), getObjectMapImageForCode(x.getObject().getCode())));
        x.setBase64Image(getObjectImageForCode(x.getObject().getCode()));
        return x;
    }

    /**
     * @param poi
     * @return
     */
    private List<PointOfInterestClientModel> loadPOI(List<PointOfInterestDto> poi) {
        return poi.stream().map(x -> loadPOI(x)).collect(Collectors.toList());
    }

    private List<SearchAndRescueClientModel> loadSAR(List<SearchAndRescueDto> sar) {
        return sar.stream().map(x -> loadSAR(x)).collect(Collectors.toList());
    }


    private SearchAndRescueClientModel loadSAR(SearchAndRescueDto x) {
        List<Feature> zoneFeatures = loadSARFeatures(x);
        //Arrays.stream(OLFeatureBuilderAdapt3.drawCircle(x.getPoint(), SAR_POINT_RADIUS, x.getStyle())).forEach(y -> zoneFeatures.add(y));
        return new SearchAndRescueClientModel(x, zoneFeatures);
    }

    private List<Feature> loadSARFeatures(SearchAndRescueDto x) {
        List<Feature> zoneFeatures = new LinkedList<>();
        for (SearchAndRescueCircleZoneDto circleZone : x.getCircleZones()) {
            if (circleZone.isVisible()) {
                Feature[] circleFeatures = OLFeatureBuilderAdapt3.drawCircle(circleZone.getPoint(),
                        circleZone.getRadius(), circleZone.getStyle());
                String text = circleZone.getName();

                if (circleZone.getArea() > 0) {
                    text += "\n" + MeasuresPresentationUtils.getAreaInHaString(circleZone.getArea());
                }
                Polygon circle = (Polygon) circleFeatures[0].getGeometry();
                zoneFeatures.add(OLFeatureBuilder.createTextFeature(circle.getCoordinates()[0][0], text, circleZone.getStyle()));

                Arrays.stream(circleFeatures).forEach(y -> zoneFeatures.add(y));
            }

        }
        // draw dispersion if needed
        if (
                x.getDirectionPoint() != null &&
                        !x.getDirectionPoint().isEmpty()
                        && !x.getCircleZones().isEmpty()) {
            zoneFeatures.add(OLFeatureBuilderAdapt3.drawSARDirectionCircle(x.getDirectionPoint(), x.getDirectionStyle()));
            zoneFeatures.add(OLFeatureBuilder.buildLine(x.getDirectionStyle(), x.getPoint(), x.getDirectionPoint()));
            // lattest assume is the biggest
            SearchAndRescueCircleZoneDto biggestZone = x.getCircleZones().get(x.getCircleZones().size() - 1);
            Feature circleFeatures = OLFeatureBuilderAdapt3.drawCircle(biggestZone.getPoint(),
                    biggestZone.getRadius(), biggestZone.getStyle())[0];
            // get computed points
            Polygon p = (Polygon) circleFeatures.getGeometry();
            Coordinate closestPoint = GeoUtils.transformToLatLon(p.getClosestPoint(GeoUtils.transformFromLatLon(x.getDirectionPoint())));
            List<Integer> allValues = x.getCircleZones().stream().map(y -> y.getDispersion()).collect(Collectors.toList());

            allValues.forEach(val -> {
                // translatePoint
                Feature line = OLFeatureBuilder.buildLine(x.getDirectionStyle(), x.getPoint(), new GeoPointDto(closestPoint.lat(), closestPoint.lon()));
                // line.getGeometry().rotate(val, OLFactory.createCoordinate(0,0));
                GeoPointDto p1 = GeoUtils.rotatePoint(x.getPoint().getLat(), x.getPoint().getLon(), closestPoint, val);
                Coordinate newClosest = GeoUtils.transformToLatLon(p.getClosestPoint(GeoUtils.transformFromLatLon(p1.getLat(), p1.getLon())));
                p1.setLat(newClosest.lat());
                p1.setLon(newClosest.lon());
                zoneFeatures.add(OLFeatureBuilder.buildLine(x.getDirectionStyle(), x.getPoint(), p1));
            });
            allValues.forEach(val -> {
                // translatePoint
                GeoPointDto p1 = GeoUtils.rotatePoint(x.getPoint().getLat(), x.getPoint().getLon(), closestPoint, -val);
                Coordinate newClosest = GeoUtils.transformToLatLon(p.getClosestPoint(GeoUtils.transformFromLatLon(p1.getLat(), p1.getLon())));
                p1.setLat(newClosest.lat());
                p1.setLon(newClosest.lon());
                zoneFeatures.add(OLFeatureBuilder.buildLine(x.getDirectionStyle(), x.getPoint(), p1));
            });
        }


        for (SearchAndRescueSegmentDto segments : x.getAreaZones()) {
            if (segments.isVisible()) {
                if (segments.isVisible()) {
                    String text = segments.getName();
                    if (segments.getArea() > 0) {
                        text += "\n" + MeasuresPresentationUtils.getAreaInHaString(segments.getArea());
                    }
                    if (segments.getPoc() != null) {
                        double poc = segments.getPoc() > 1.0 ? segments.getPoc() : segments.getPoc() * 100;
                        GWT.log(segments.getPoc() + " " + poc);
                        text += "\nPOC: " + AppConstants.DEFAULT_NUMBER_FORMAT.format(poc) + " %";
                    }
                    zoneFeatures.add(OLFeatureBuilderAdapt3.drawBSplinePolygonSAR(
                            GeoUtils.convertGeoPointsToCoordinates(segments.getPoints()), OLStyleManager.getStyle(segments.getStyle(), text)));

//                    zoneFeatures.add(OLFeatureBuilder.buildPolygonFromLinearRingCoordinates(
//                            GeoUtils.convertGeoPointsToCoordinates(segments.getPoints()), OLStyleManager.getStyle(segments.getStyle())));


                }
            }

        }

        for (int i = x.getGridZones().size() - 1; i >= 0; i--) {
            SearchAndRescueGridZoneDto gridZone = x.getGridZones().get(i);
            if (gridZone.isVisible()) {
                String text = gridZone.getName();
                zoneFeatures.add(OLFeatureBuilderAdapt3.drawBSplinePolygonSAR(
                        GeoUtils.convertGeoPointsToCoordinates(gridZone.getPoints()), OLStyleManager.getStyle(gridZone.getStyle(), text)));
//                gridZone.getPoints().forEach(point -> {
//                    zoneFeatures.add(OLFeatureBuilderAdapt3.drawSARGrid(point));
//                });

            }
        }


        zoneFeatures.add(OLFeatureBuilderAdapt3.drawSARCircle(x.getPoint()));
        return zoneFeatures;
    }

    private List<SearchAndRescueSegmentClientModel> loadSARSegments(List<SearchAndRescueSegmentDto> segments) {
        return segments.stream().map(x -> loadSARSegment(x)).collect(Collectors.toList());
    }

    private SearchAndRescueSegmentClientModel loadSARSegment(SearchAndRescueSegmentDto segments) {
        List<Feature> zoneFeatures = new LinkedList<>();
        if (segments.isVisible()) {
            if (segments.isVisible()) {
                zoneFeatures.add(OLFeatureBuilderAdapt3.drawBSplinePolygon(
                        GeoUtils.convertGeoPointsToCoordinates(segments.getPoints()), OLStyleManager.getStyle(segments.getStyle())));
            }
        }
        return new SearchAndRescueSegmentClientModel(segments, zoneFeatures);
    }

    private List<GpxTraceClientModel> loadGPX(List<GpxTraceDto> gpxTraces) {
        return gpxTraces.stream().map(x -> loadGPX(x)).collect(Collectors.toList());
    }

    private GpxTraceClientModel loadGPX(GpxTraceDto trace) {
        List<Feature> zoneFeatures = new LinkedList<>();
        trace.getTracks().forEach(track -> {
            List<GeoPointDto> points = track.getPoints().stream().filter(x -> x != null).collect(Collectors.toList());
            Feature f = OLFeatureBuilderAdapt3.drawBSplinePolygonSAR(
                    GeoUtils.convertGeoPointsToCoordinates(points), OLStyleManager.getStyle(trace.getStyle(), track.getName()));
            f.set(FeatureInfoProvider.PROVIDER_KEY, new FeatureInfoProvider() {
                @Override
                public String getTooltipText() {
                    return track.getName() + "\n" + (trace.getDescription() != null ? trace.getDescription() : "");
                }
            });
            zoneFeatures.add(f);
        });
        return new GpxTraceClientModel(trace, zoneFeatures);
    }


    private PointOfInterestClientModel loadPOI(PointOfInterestDto x) {
        List<Feature> zoneFeatures = new LinkedList<>();
        for (PoiMultiPointZoneDto multiPointZone : x.getAreaZones()) {
            if (multiPointZone.isVisible()) {
                zoneFeatures.add(OLFeatureBuilderAdapt3.drawBSplinePolygon(
                        GeoUtils.convertGeoPointsToCoordinates(multiPointZone.getPoints()), OLStyleManager.getStyle(multiPointZone.getStyle())));
            }

        }
        for (PoiCircleZoneDto circleZone : x.getCircleZones()) {
            if (circleZone.isVisible()) {
                Feature[] circleFeatures = OLFeatureBuilderAdapt3.drawCircle(circleZone.getPoint(),
                        circleZone.getRadius(), circleZone.getStyle());
                Arrays.stream(circleFeatures).forEach(y -> zoneFeatures.add(y));
            }

        }
        return new PointOfInterestClientModel(x,
                OLFeatureBuilder.createPointFeatureFromImageSourceUrl(x.getPoint(), x.getName(),
                        PoiTypeToMarkerImageMapper.getImageSourceForPoiType(x.getType())),
                PoiTypeToMarkerImageMapper.getImageSourceForPoiType(x.getType()), zoneFeatures);
    }

    private PointOfInterestClientModel updatePOI(PointOfInterestClientModel x) {
        List<Feature> zoneFeatures = new LinkedList<>();
        for (PoiMultiPointZoneDto multiPointZone : x.getObject().getAreaZones()) {
            if (multiPointZone.isVisible()) {
                zoneFeatures.add(OLFeatureBuilderAdapt3.drawBSplinePolygon(
                        GeoUtils.convertGeoPointsToCoordinates(multiPointZone.getPoints()), OLStyleManager.getStyle(multiPointZone.getStyle())));
            }

        }
        for (PoiCircleZoneDto circleZone : x.getObject().getCircleZones()) {
            if (circleZone.isVisible()) {
                Feature[] circleFeatures = OLFeatureBuilderAdapt3.drawCircle(circleZone.getPoint(),
                        circleZone.getRadius(), circleZone.getStyle());
                Arrays.stream(circleFeatures).forEach(y -> zoneFeatures.add(y));
            }

        }

        zoneFeatures.add(0, OLFeatureBuilder.createPointFeatureFromImageSourceUrl(x.getObject().getPoint(),
                x.getObject().getName(), PoiTypeToMarkerImageMapper.getImageSourceForPoiType(x.getObject().getType())));
        x.setFeatures(zoneFeatures.toArray(new Feature[zoneFeatures.size()]));
        x.setImageSource(PoiTypeToMarkerImageMapper.getImageSourceForPoiType(x.getObject().getType()));
        return x;
    }

    private MapSymbolClientModel updateSegment(SearchAndRescueSegmentClientModel object) {
        List<Feature> zoneFeatures = new LinkedList<>();

        if (object.getObject().isVisible()) {
            if (object.getObject().isVisible()) {
                zoneFeatures.add(OLFeatureBuilderAdapt3.drawBSplinePolygon(
                        GeoUtils.convertGeoPointsToCoordinates(object.getObject().getPoints()), OLStyleManager.getStyle(object.getObject().getStyle())));
            }
        }
        object.setFeatures(zoneFeatures.toArray(new Feature[zoneFeatures.size()]));
        return object;
    }

    private MapSymbolClientModel updateGPX(GpxTraceClientModel traceClientModel) {

        List<Feature> zoneFeatures = new LinkedList<>();
        if (traceClientModel.getObject().isVisible()) {
            traceClientModel.getObject().getTracks().forEach(track -> {
                List<GeoPointDto> points = track.getPoints().stream().filter(x -> x != null).collect(Collectors.toList());
                Feature f = OLFeatureBuilderAdapt3.drawBSplinePolygonSAR(
                        GeoUtils.convertGeoPointsToCoordinates(points), OLStyleManager.getStyle(traceClientModel.getObject().getStyle(),track.getName()));
                f.set(FeatureInfoProvider.PROVIDER_KEY, new FeatureInfoProvider() {
                    @Override
                    public String getTooltipText() {
                        return track.getName() + "\n" + (track.getDescription() != null ? track.getDescription() : "");
                    }
                });
                zoneFeatures.add(f);
            });
        }
        traceClientModel.setFeatures(zoneFeatures.toArray(new Feature[zoneFeatures.size()]));
        return traceClientModel;
    }


    private MapSymbolClientModel updateSAR(SearchAndRescueClientModel x) {
        List<Feature> zoneFeatures = loadSARFeatures(x.getObject());
        x.setFeatures(zoneFeatures.toArray(new Feature[zoneFeatures.size()]));
        return x;
    }

    /**
     *
     * @param crisisEvent
     * @return
     */
    private List<CrisisEventClientModel> loadCrisisEvents(List<CrisisEventDto> crisisEvent) {
        return crisisEvent.stream().map(x -> loadCrisisEvent(x)).collect(Collectors.toList());
    }

    /**
     * @param x
     * @return
     */
    private CrisisEventClientModel loadCrisisEvent(CrisisEventDto x) {
        List<Feature> features = new LinkedList<>();

        for (CrisisEventMultiPointZoneDto multiPointZone : x.getAreaZones()) {
            if (multiPointZone.isVisible()) {
                features.add(OLFeatureBuilderAdapt3.drawBSplinePolygon(
                        GeoUtils.convertGeoPointsToCoordinates(multiPointZone.getPoints()), OLStyleManager.getStyle(multiPointZone.getStyle())));
            }

        }
        for (CrisisEventCricleZoneDto circleZone : x.getCircleZones()) {
            if (circleZone.isVisible()) {
                Feature[] circleFeatures = null;
                circleFeatures = OLFeatureBuilderAdapt3.drawCircle(circleZone.getPoint(), circleZone.getRadius(), circleZone.getStyle());
                Arrays.stream(circleFeatures).forEach(y -> features.add(y));
            }

        }
        return new CrisisEventClientModel(x,
                OLFeatureBuilder.createPointFeatureFromImageSourceUrl(x.getPoint(), x.getName(),
                        CrisisEventTypeToImageMapper.getImageSourceForCrisisEventType(x.getType())),
                CrisisEventTypeToImageMapper.getImageSourceForCrisisEventType(x.getType()), features);
    }

    /**
     * @param x
     * @return
     */
    private CrisisEventClientModel updateCrisisEvent(CrisisEventClientModel x) {
        List<Feature> features = new LinkedList<>();

        for (CrisisEventMultiPointZoneDto multiPointZone : x.getObject().getAreaZones()) {
            if (multiPointZone.isVisible())
                features.add(OLFeatureBuilderAdapt3
                        .drawBSplinePolygon(GeoUtils.convertGeoPointsToCoordinates(multiPointZone.getPoints())));
        }
        for (CrisisEventCricleZoneDto circleZone : x.getObject().getCircleZones()) {
            if (circleZone.isVisible()) {
                Feature[] circleFeatures = OLFeatureBuilderAdapt3.drawCircle(circleZone.getPoint(),
                        circleZone.getRadius(), x.getObject().getType());
                Arrays.stream(circleFeatures).forEach(y -> features.add(y));
            }

        }
        x.setFeatures(features.toArray(new Feature[features.size()]));
        if (x.getObject().getPoint() != null)
            features.add(0,
                    OLFeatureBuilder.createPointFeatureFromImageSourceUrl(x.getObject().getPoint(),
                            x.getObject().getName(), CrisisEventTypeToImageMapper
                                    .getImageSourceForCrisisEventType(x.getObject().getType())));
        x.setFeatures(features.toArray(new Feature[features.size()]));
        x.setImageSource(CrisisEventTypeToImageMapper.getImageSourceForCrisisEventType(x.getObject().getType()));

        return x;
    }

    /**
     *
     * @param app6aMilitaryUnits
     * @return
     */
    private List<App6AMilitaryUnitClientModel> loadAPP6AMilitaryUnits(List<App6AMilitaryUnitDto> app6aMilitaryUnits) {
        return app6aMilitaryUnits.stream().map(x -> loadAPP6AMilitaryUnit(x)).collect(Collectors.toList());
    }

    /**
     * @param x
     * @return
     */
    private App6AMilitaryUnitClientModel loadAPP6AMilitaryUnit(App6AMilitaryUnitDto x) {
        return new App6AMilitaryUnitClientModel(x, OLFeatureBuilder.createPointFeatureFromImageSourceUrl(x.getPoint(),
                x.getName(), getObjectMapImageForCode(x.getCode())), getObjectImageForCode(x.getCode()));
    }

    /**
     * @param x
     * @return
     */
    private App6AMilitaryUnitClientModel updateAPP6AMilitaryUnit(App6AMilitaryUnitClientModel x) {
        x.setFeatures(OLFeatureBuilder.createPointFeatureFromImageSourceUrl(x.getObject().getPoint(),
                x.getObject().getName(), getObjectMapImageForCode(x.getObject().getCode())));
        x.setBase64Image(getObjectImageForCode(x.getObject().getCode()));
        return x;
    }

    /**
     * @param object
     * @return
     */
    private MapSymbolClientModel updateObjectFeature(MapSymbolClientModel object) {
        MapSymbolClientModel result = null;
        if (object instanceof App6AMilitaryUnitClientModel) {
            result = updateAPP6AMilitaryUnit((App6AMilitaryUnitClientModel) object);
        } else if (object instanceof MSWiAUnitClientModel) {
            result = updateMSWiaUnit((MSWiAUnitClientModel) object);
        } else if (object instanceof PointOfInterestClientModel) {
            result = updatePOI((PointOfInterestClientModel) object);
        } else if (object instanceof CrisisEventClientModel) {
            result = updateCrisisEvent((CrisisEventClientModel) object);
        } else if (object instanceof ADatP3ReportClientModel) {
            result = updateADatP3Report((ADatP3ReportClientModel) object);
        } else if (object instanceof SearchAndRescueClientModel) {
            result = updateSAR((SearchAndRescueClientModel) object);
        } else if (object instanceof GpxTraceClientModel) {
            result = updateGPX((GpxTraceClientModel) object);
        } else if (object instanceof SearchAndRescueSegmentClientModel) {
            result = updateSegment((SearchAndRescueSegmentClientModel) object);
        }
        return result;
    }


    private String getObjectMapImageForCode(String code) {
        return cache.getResized(code);
    }

    private String getObjectImageForCode(String code) {
        return cache.get(code);
    }

    private void resizeImagesForCodes(List<String> codes, DefaultAsyncCallback<Void> defaultAsyncCallback) {
        if (codes.isEmpty()) {
            defaultAsyncCallback.onSuccess(null);
        } else {
            count = 0;
            for (String code : codes) {
                if(code != null){
                    NativeMethods.resizeImageFromImageSource(IconsUtils.getBase64ImgSrc(cache.get(code)),
                            AppConstants.DEFAULT_SYMBOL_WIDTH, AppConstants.DEFAULT_SYMBOL_HEIGHT, new DefaultCallback() {
                                @Override
                                public void onSuccess(String result) {
                                    super.onSuccess(result);
                                    cache.putResized(code, result);
                                    if (++count == codes.size())
                                        defaultAsyncCallback.onSuccess(null);
                                }
                            });
                }
            }
        }

    }

    public static <T> List<T> reverseList(List<T> list) {
        // base case: list is empty or only one element is left
        if (list == null || list.size() <= 1)
            return list;

        // remove first element
        T value = list.remove(0);

        // recur for remaining items
        reverseList(list);

        // insert the top element back after recusing for remaining items
        list.add(value);
        return list;
    }
}
