/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol;

import com.google.gwt.core.client.GWT;
import ol.Coordinate;
import ol.Feature;
import ol.FeatureOptions;
import ol.OLFactory;
import ol.color.Color;
import ol.geom.Point;
import ol.style.RegularShapeOptions;
import ol.style.Style;
import ol.style.StyleOptions;
import pl.edu.wat.wcy.cop.app.client.ol.curve.*;
import pl.edu.wat.wcy.cop.app.client.utils.ParsingUtils;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.ADatP3;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.CBRNType;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.PapaA;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.PapaR;
import pl.edu.wat.wcy.cop.app.shared.domain.FeatureStyleDto;
import pl.edu.wat.wcy.cop.app.shared.domain.GUID;
import pl.edu.wat.wcy.cop.app.shared.domain.GeoPointDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.crisis.CrisisEventTypeDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueGridZoneDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
// Represents ol feature builder adapt 3.


public class OLFeatureBuilderAdapt3 {

    private static final String SAR_FILL_COLOR = "#DC143C";
    private static final String SAR_STROKE_COLOR = "#FFFFFF";
    private static final int SAR_RADIUS = 10;

    private static List<String> COLROS = Arrays.asList(new String[]{
            "#eeeed1", "#d3ffce",  "#ffffe0"
    });


    /**
     * Draw release area. Release area is always a circle.
     *
     * @return Feature that contains Release Area.
     */
    public static Feature drawReleaseArea(ADatP3 aDatP3) {
        Float radius = 0f;
        if (aDatP3.getCbrnType().equals(CBRNType.CHEM) || aDatP3.getCbrnType().equals(CBRNType.BIO)) {
            PapaA papaA = (PapaA) aDatP3.getPapa();
            radius = papaA.getReleaseAresRadius();
        } else {
            PapaR papaR = (PapaR) aDatP3.getPapa();
            radius = papaR.getReleaseAresRadius();
        }
        if (radius != null) {
            return OLFeatureBuilder.createRegularPolygonFeautreInMapProjectionFromLatLon(aDatP3.getMapCenter(), radius,
                    50, OLStyleManager.getDefaultReleaseStyle());
        }
        return null;
    }

    /**
     * Function create hazard area depends on algorithm
     *
     * @return list of hazard areas, maximum 3
     */
    public static Feature[] drawHazardArea(ADatP3 aDatP3, TypeOfBSpline typeOfBSpline) {

        if (aDatP3.getLonLatCoordinates().size() > 0) {
            if (aDatP3.getLonLatCoordinates().get(String.valueOf(0)).size() == 1) {
                aDatP3.setFeatureType(ADatP3.FeatureType.CIRCLE);
                return drawHazardAreaOneCircle(aDatP3);
            } else if (aDatP3.getLonLatCoordinates().get(String.valueOf(0)).size() == 2) {
                aDatP3.setFeatureType(ADatP3.FeatureType.CIRCLE);
                return drawHazardAreaTwoCircles(aDatP3.getLonLatCoordinates().get(String.valueOf(0)), aDatP3);
            } else {
                aDatP3.setFeatureType(ADatP3.FeatureType.POLYGON);
                switch (typeOfBSpline) {
                    case BEZIER:
                        return drawBSplineWithBezier(aDatP3);
                    case INTERPOLATED_B_SPLINE:
                        return drawBSplineWithOrWithNoInterpolation(aDatP3, typeOfBSpline);
                    case NO_B_SPLINE:
                        return drawPolygon(aDatP3);
                    case PERIODIC_B_SPLINE:
                        return drawBSplineWithOrWithNoInterpolation(aDatP3, typeOfBSpline);
                    default:
                        break;
                }
            }
        }
        return null;
    }

    private static Feature[] drawBSplineWithBezier(ADatP3 aDatP3) {
        Set<String> keys = aDatP3.getLonLatCoordinates().keySet();
        List<Feature> features = new ArrayList<>();
        int k = 0;
        for (String s : keys) {
            List<Coordinate> pointList = aDatP3.getLonLatCoordinates().get(s);
            Coordinate[] points = pointList.toArray(new Coordinate[pointList.size()]);
            double[] x = new double[points.length];
            double[] y = new double[points.length];
            for (int i = 0; i < points.length; i++) {
                x[i] = points[i].getX();
                y[i] = points[i].getY();
            }
            BSplineFromBezier bSplineFromBezier = new BSplineFromBezier(x, y);
            Double[] xDouble;
            Double[] yDouble;
            xDouble = new Double[bSplineFromBezier.getMyX().length];
            yDouble = new Double[bSplineFromBezier.getMyY().length];
            for (int i = 0; i < xDouble.length; i++) {
                xDouble[i] = bSplineFromBezier.getMyX()[i];
                yDouble[i] = bSplineFromBezier.getMyY()[i];
            }
            BezierCurve curve = new BezierCurve(xDouble, yDouble, 30);
            Coordinate[][] coordinates = new Coordinate[1][curve.getCoordinates().size()];
            for (int i = 0; i < curve.getCoordinates().size(); i++) {
                coordinates[0][i] = curve.getCoordinates().get(i);
            }
            features.add(OLFeatureBuilder.buildPolygonFromLinearRingCoordinates(
                    GeoUtils.transformFromLatLonToMapProjection(coordinates), OLStyleManager.getDefaultHazardStyle(k)));
            k++;
        }
        return features.toArray(new Feature[features.size()]);
    }

    private static Feature[] drawBSplineWithOrWithNoInterpolation(ADatP3 aDatP3, TypeOfBSpline typeOfBSpline) {
        Set<String> keys = aDatP3.getLonLatCoordinates().keySet();
        List<Feature> features = new ArrayList<>();
        int k = 0;
        for (String s : keys) {
            List<Coordinate> pointList = aDatP3.getLonLatCoordinates().get(s);
            Coordinate[] points = pointList.toArray(new Coordinate[pointList.size()]);
            PointForInterpolation[] pointForInterpolations = new PointForInterpolation[points.length];
            for (int i = 0; i < points.length; i++) {
                pointForInterpolations[i] = new PointForInterpolation(points[i].getX(), points[i].getY());
            }
            PointForInterpolation[] splines;
            if (typeOfBSpline.equals(TypeOfBSpline.INTERPOLATED_B_SPLINE)) {
                splines = Interpolation.Calculate(pointForInterpolations, 3, 20, true);
            } else {
                splines = Interpolation.Calculate(pointForInterpolations, 3, 20, false);
            }
            Coordinate[][] coordinates = new Coordinate[1][splines.length];
            for (int i = 0; i < splines.length; i++) {
                coordinates[0][i] = OLFeatureBuilder.createCoordinate(splines[i].getY(), splines[i].getX());
            }
            features.add(OLFeatureBuilder.buildPolygonFromLinearRingCoordinates(
                    GeoUtils.transformFromLatLonToMapProjection(coordinates), OLStyleManager.getDefaultHazardStyle(k)));
            k++;
        }
        return features.toArray(new Feature[features.size()]);
    }

    private static Feature[] drawHazardAreaOneCircle(ADatP3 aDatP3) {
        if (aDatP3.getCbrnType().equals(CBRNType.CHEM) || aDatP3.getCbrnType().equals(CBRNType.BIO)) {
            PapaA papaA = (PapaA) aDatP3.getPapa();
            Float radius = papaA.getHazardAreaDistance();
            Feature feature = createHazardCircle(aDatP3.getMapCenter(), radius, 0);
            if (feature != null) {
                return new Feature[]{feature};
            }
        } else {
            List<Feature> features = new ArrayList<>();
            PapaR papaR = (PapaR) aDatP3.getPapa();
            Float radius1 = papaR.getHazardAreaDistanceR1();
            addHazardCircle(features, aDatP3.getMapCenter(), radius1, 0);

            Float radius2 = papaR.getHazardAreaDistanceR2();
            addHazardCircle(features, aDatP3.getMapCenter(), radius2, 1);

            Float radius3 = papaR.getHazardAreaDistanceR3();
            addHazardCircle(features, aDatP3.getMapCenter(), radius3, 2);

            return features.toArray(new Feature[features.size()]);
        }
        return null;
    }

    private static void addHazardCircle(List<Feature> features, GeoPointDto center, Float radius, int styleIndex) {
        Feature feature = createHazardCircle(center, radius, styleIndex);
        if (feature != null) {
            features.add(feature);
        }
    }

    private static Feature createHazardCircle(GeoPointDto center, Float radius, int styleIndex) {
        if (radius == null) {
            return null;
        }
        return OLFeatureBuilder.createRegularPolygonFeautreInMapProjectionFromLatLon(
                center, radius, 50, OLStyleManager.getDefaultHazardStyle(styleIndex));
    }

    private static Feature[] drawHazardAreaTwoCircles(List<Coordinate> points, ADatP3 aDatP3) {
        Feature[] features = new Feature[2];
        Float radius;
        if (aDatP3.getCbrnType().equals(CBRNType.CHEM) || aDatP3.getCbrnType().equals(CBRNType.BIO)) {
            PapaA papaA = (PapaA) aDatP3.getPapa();
            radius = papaA.getHazardAreaDistance();
        } else {
            PapaR papaR = (PapaR) aDatP3.getPapa();
            radius = papaR.getHazardAreaDistanceR1();
        }
        if (radius != null) {
            features[0] = OLFeatureBuilder.createRegularPolygonFeautre(
                    GeoUtils.transformFromLatLonToMapProjection(points.get(0)), radius, 50,
                    OLStyleManager.getDefaultHazardStyle(0));
            features[1] = OLFeatureBuilder.createRegularPolygonFeautre(
                    GeoUtils.transformFromLatLonToMapProjection(points.get(1)), radius, 50,
                    OLStyleManager.getDefaultHazardStyle(0));
        }
        return features;
    }

    private static Feature[] drawPolygon(ADatP3 aDatP3) {
        List<Feature> features = new ArrayList<>();
        Set<String> keys = aDatP3.getLonLatCoordinates().keySet();
        int k = 0;
        for (String s : keys) {
            List<Coordinate> points = aDatP3.getLonLatCoordinates().get(String.valueOf(s));
            Coordinate[][] coordinates = new Coordinate[1][points.size()];
            for (int i = 0; i < points.size(); i++) {
                coordinates[0][i] = points.get(i);
            }
            features.add(OLFeatureBuilder.buildPolygonFromLinearRingCoordinates(coordinates,
                    OLStyleManager.getDefaultHazardStyle(k)));
            k++;
        }
        return features.toArray(new Feature[features.size()]);
    }

    /**
     * Draw BSpline polygon based only on coordinates
     *
     * @param pointList
     *            list of Coordinates
     * @return Polygon - BSpline
     */
    public static Feature drawBSplinePolygon(List<Coordinate> pointList, Style style) {

        Coordinate[] points = pointList.toArray(new Coordinate[pointList.size()]);
        PointForInterpolation[] pointForInterpolations = new PointForInterpolation[points.length];
        for (int i = 0; i < points.length; i++) {
            pointForInterpolations[i] = new PointForInterpolation(points[i].getX(), points[i].getY());
        }
        PointForInterpolation[] splines = Interpolation.Calculate(pointForInterpolations, 3, 20, false);
        Coordinate[][] coordinates = new Coordinate[1][splines.length];
        for (int i = 0; i < splines.length; i++) {
            coordinates[0][i] = OLFeatureBuilder.createCoordinate(splines[i].getY(), splines[i].getX());
        }
        return OLFeatureBuilder
                .buildPolygonFromLinearRingCoordinates(GeoUtils.transformFromLatLonToMapProjection(coordinates), style);
    }


    public static Feature drawBSplinePolygonSAR(List<Coordinate> pointList, Style style) {

        List<Feature> features = new ArrayList<>();
        Coordinate[] points = pointList.toArray(new Coordinate[pointList.size()]);
        PointForInterpolation[] pointForInterpolations = new PointForInterpolation[points.length];
        for (int i = 0; i < points.length; i++) {
            pointForInterpolations[i] = new PointForInterpolation(points[i].getX(), points[i].getY());
        }
        PointForInterpolation[] splines = Interpolation.Calculate(pointForInterpolations, 1, 1, true);
        Coordinate[][] coordinates = new Coordinate[1][splines.length];
        for (int i = 0; i < splines.length; i++) {
            coordinates[0][i] = OLFeatureBuilder.createCoordinate(splines[i].getY(), splines[i].getX());
        }
        return OLFeatureBuilder
                .buildPolygonFromLinearRingCoordinates(GeoUtils.transformFromLatLonToMapProjection(coordinates), style);
    }

    public static Feature drawBSplinePolygon(List<Coordinate> pointList) {
        return drawBSplinePolygon(pointList, OLStyleManager.getDefaultHazardStyle(0));
    }

    public static Feature drawBSplinePolygon(List<Coordinate> pointList, String color, double alpha) {
        return drawBSplinePolygon(pointList, OLStyleManager.getStrokeStyleForColor(color, alpha));
    }

    /**
     * Draw Circles based on coordinates of center and list of radiuses
     *
     * @param mapCenter
     *            Coordinates
     * @param radiuses
     *            list of radiuses
     * @return array of Circles
     */
    public static Feature[] drawCircleAreas(Coordinate mapCenter, List<Float> radiuses) {
        List<Feature> features = new ArrayList<>();
        int i = 0;
        for (Float r : radiuses) {
            features.add(OLFeatureBuilder.createRegularPolygonFeautreInMapProjectionFromLatLon(mapCenter, r, 50,
                    OLStyleManager.getDefaultHazardStyle(i)));
            i++;
            i = i % 3;
        }
        return features.toArray(new Feature[features.size()]);
    }

    /**
     * Draw Circles based on coordinates of center and list of radiuses
     *
     * @param mapCenter
     *            Coordinate
     * @param radius
     *            Radius of circle
     * @return array of Circles
     */
    public static Feature[] drawCircle(Coordinate mapCenter, Float radius, CBRNType cbrnType) {
        List<Feature> features = new ArrayList<>();
        features.add(OLFeatureBuilder.createRegularPolygonFeautreInMapProjectionFromLatLon(mapCenter, radius, 50,
                OLStyleManager.getStyleByCbrnType(cbrnType)));
        return features.toArray(new Feature[features.size()]);
    }
    public static Feature[] drawCircle(GeoPointDto point, double radius, Style style) {

        return drawCircle(OLFeatureBuilder.createCoordinate(point), (float) radius,
                style);
    }

    /**
     *
     * @param mapCenter
     * @param radius
     * @param style
     * @return
     */
    public static Feature[] drawCircle(Coordinate mapCenter, Float radius, Style style) {
        List<Feature> features = new ArrayList<>();
        features.add(
                OLFeatureBuilder.createRegularPolygonFeautreInMapProjectionFromLatLon(mapCenter, radius, 50, style));
        return features.toArray(new Feature[features.size()]);
    }

    /**
     *
     * @param mapCenter
     * @param radius
     * @param color
     * @return
     */
    public static Feature[] drawCircle(Coordinate mapCenter, Float radius, Color color) {
        return drawCircle(mapCenter, radius, OLStyleManager.getStrokeStyleForColor(color));
    }

    /**
     * @param point
     * @param radius
     * @param type
     * @return
     */
    public static Feature[] drawCircle(GeoPointDto point, double radius, CrisisEventTypeDto type) {
        return drawCircle(OLFeatureBuilder.createCoordinate(point), (float) radius,
                CBRNType.findByValue(type.getName()));
    }

    /**
     *
     * @param point
     * @param radius
     * @param color
     * @return
     */
    public static Feature[] drawCircle(GeoPointDto point, double radius, Color color) {
        return drawCircle(OLFeatureBuilder.createCoordinate(point), (float) radius, color);
    }

    /**
     *
     * @param point
     * @param radius
     * @param color
     * @param alpha
     * @return
     */
    public static Feature[] drawCircle(GeoPointDto point, double radius, String color, double alpha) {
        return drawCircle(OLFeatureBuilder.createCoordinate(point), (float) radius,
                OLStyleManager.getStrokeStyleForColor(color, alpha));
    }

    public static Feature[] drawCircle(GeoPointDto point, double radius, String color, String fill, double alpha) {
        if (!StringUtils.isNullOrEmpty(color) && !StringUtils.isNullOrEmpty(fill)) {
            return drawCircle(OLFeatureBuilder.createCoordinate(point), (float) radius,
                    OLStyleManager.getStrokeStyleForColorAndFill(color, fill, alpha));
        } else if (!StringUtils.isNullOrEmpty(color)) {
            return drawCircle(point, radius, color, alpha);
        } else if (!StringUtils.isNullOrEmpty(fill)) {
            return drawCircle(OLFeatureBuilder.createCoordinate(point), (float) radius,
                    OLStyleManager.getDefaultStrokeStyleAndFill(fill, alpha));
        } else {
            return drawCircle(OLFeatureBuilder.createCoordinate(point), (float) radius,
                    OLStyleManager.getDefaultStroke(alpha));
        }
    }


    public static Feature[] drawCircle(GeoPointDto point, double radius, FeatureStyleDto style) {

        return drawCircle(OLFeatureBuilder.createCoordinate(point), (float) radius,
                OLStyleManager.getStyle(style));
    }

    public static Feature[] drawCircle(GeoPointDto point, double radius, String color, String fill, double alpha, String name) {
        return drawCircle(OLFeatureBuilder.createCoordinate(point), (float) radius,
                OLStyleManager.getStrokeStyleForColorAndFill(color, fill, alpha, name));
    }

    public static Feature drawSARCircle(GeoPointDto geoPoint) {
        Point point = OLFactory.createPoint(OLFeatureBuilder.createCoordinateInMapProjection(geoPoint));
        FeatureOptions featureOptions = OLFactory.createOptions();
        featureOptions.setGeometry(point);
        Feature feature = new Feature(featureOptions);
        feature.setId(GUID.get());
        feature.setStyle(OLFactory.createStyle(OLFactory.createCircleStyle(
                OLFactory.createFill(OLFactory.createColor(SAR_FILL_COLOR)),
                OLFactory.createStroke(OLFactory.createColor(SAR_STROKE_COLOR), 3),
                SAR_RADIUS
        )));
        feature.set(OLFeatureBuilder.INITAL_STYLE_PROP, feature.getStyle());
        feature.set(OLFeatureBuilder.SELECTED_STYLE_PROP,OLFactory.createStyle(OLFactory.createCircleStyle(
                OLFactory.createFill(OLFactory.createColor(SAR_STROKE_COLOR)),
                OLFactory.createStroke(OLFactory.createColor(SAR_FILL_COLOR), 6),
                SAR_RADIUS
        )));
        return feature;

    }

    public static Feature drawSARDirectionCircle(GeoPointDto geoPoint, FeatureStyleDto style) {
        if (style == null) {
            style = new FeatureStyleDto();
        }
        Point point = OLFactory.createPoint(OLFeatureBuilder.createCoordinateInMapProjection(geoPoint));
        FeatureOptions featureOptions = OLFactory.createOptions();
        featureOptions.setGeometry(point);
        Feature feature = new Feature(featureOptions);
        feature.setId(GUID.get());
        feature.setStyle(OLFactory.createStyle(OLFactory.createCircleStyle(
                OLFactory.createFill(OLFactory.createColor(SAR_FILL_COLOR)),
                OLFactory.createStroke(OLFactory.createColor(SAR_STROKE_COLOR), 3),
                SAR_RADIUS / 2
        )));
        feature.set(OLFeatureBuilder.SELECTED_STYLE_PROP,OLFactory.createStyle(OLFactory.createCircleStyle(
                OLFactory.createFill(OLFactory.createColor(SAR_STROKE_COLOR)),
                OLFactory.createStroke(OLFactory.createColor(SAR_FILL_COLOR), 6),
                SAR_RADIUS / 2
        )));
        return feature;
    }

    public static Feature drawSARGrid(GeoPointDto gPoint) {
        Point point = OLFactory.createPoint(OLFeatureBuilder.createCoordinateInMapProjection(gPoint));
        FeatureOptions featureOptions = OLFactory.createOptions();
        featureOptions.setGeometry(point);
        Feature feature = new Feature(featureOptions);

        String color = getColorForSegmentNr(gPoint.getName());
        RegularShapeOptions opts = new RegularShapeOptions();
        // fill: fill,
        //      stroke: stroke,
        //      points: 4,
        //      radius: 10,
        //      angle: Math.PI / 4
        opts.setPoints(4);
        opts.setRadius(5);
        opts.setAngle(Math.PI / 4);
        opts.setStroke(OLFactory.createStroke(OLFactory.createColor(color), 1));
        opts.setFill(OLFactory.createFill(OLFactory.createColor(color)));
        feature.setId(GUID.get());
        feature.setStyle(OLFactory.createStyle(OLFactory.createRegularShape(opts)));
        return feature;
    }

    public static Style getStyleForSegmentNr(SearchAndRescueGridZoneDto zone){
        String strColor = getColorForSegmentNr(zone.getName());
        zone.getStyle().setStrokeColor(strColor);
        zone.getStyle().setFillAlpha(0);
        return OLStyleManager.getStyle(zone.getStyle());
    }

    private static String getColorForSegmentNr(String name) {
        int color = ParsingUtils.parseInt(name) % COLROS.size();
        String strColor = COLROS.get(color);
        GWT.log("Emtpy color " + name + " " + strColor);
        if (StringUtils.isNullOrEmpty(strColor)) {

            strColor = SAR_FILL_COLOR;
        }
        strColor = SAR_FILL_COLOR;

        return strColor;
    }

}
