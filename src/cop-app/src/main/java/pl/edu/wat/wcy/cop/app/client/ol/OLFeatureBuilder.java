/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol;

import ol.Coordinate;
import ol.Feature;
import ol.FeatureOptions;
import ol.OLFactory;
import ol.color.Color;
import ol.geom.Circle;
import ol.geom.*;
import ol.style.*;
import pl.edu.wat.wcy.cop.app.client.utils.CopLogger;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.domain.FeatureStyleDto;
import pl.edu.wat.wcy.cop.app.shared.domain.GUID;
import pl.edu.wat.wcy.cop.app.shared.domain.GeoPointDto;
import pl.edu.wat.wcy.cop.app.shared.ol.OLProjection;

import java.util.Arrays;
import java.util.List;
// Builds ol feature instances.


public class OLFeatureBuilder {
    public static final String SELECTED_STYLE_PROP = "SELECTED_STYLE";
    public static String INITAL_STYLE_PROP = "INITIAL_STYLE";
    static String base64String = "iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAClklEQVR42mNgGAWjYBSMAhKBAxD/ JxE7jHpk1CNDxCOcQDyBQryaDI+spoK9QsgeEQAZrGjp+F/DLWBIYDUnb1hgKGB4JGPb+f8ND/4P CVxx6f2oR0Y9MuqRUY+MemTUIwPtkX//q659RsJf/tff+4up7v6//3kHbv/PO3gHQ676xje4/urr XwfGIyDL0dtNzKys/zXdA8EOBKmpvf3zv7y5/X8WNvb/rByc/1Xs3P/X3/0DN0NSxwhFP6eA0H/n 0taB8Yias8//qLmb/0fM2vDfODIVLObdPBWsJmX9CTA/ZMqK/6kbT4LZ6VvPoXhEQEYBrD9k8vL/ 6q5+YDWBfQvp7xGz+By4GEgvSMwhvx6uhltY9L+4pv5/BWBjlE9C5n/V1c8oHhHX0IXzQTHIJST6 X87Ehv4eEVZU/a/jG/Ff2zv0v4Cs4n82bp7/2buvgtWUnX8DTFp2YHXsvPz/C48+QDED3SMgLG9m C/S8GP09IqKs8V/d2fc/IxPzf2El9f8FR+6D5evu/P4vAYwJPkmZ/6axWWC1LmXt/1XsPf6HTllJ wCOiA5e03Kp7wHzzhFxo/jgO5vt3zQXzdf2j4Jk6YcV+rB6pu/MLnLTkzewGziOgolcBWEIxMDKC HQqKGUYmpv86PuH/c/bd+O9c1gZWDxLzqJvwv/7+X7BH+CRlgZl70f+A7vn/NdwDwGpgMTZgmT3/ 0N3/bFw8wOSm/r/m1o//Xo2TgXxusDomZhZwqaZs6/Zf2sD8fy1QHrn4BXlQUE7pv1fTlMFZs4M8 BIqdyqufIDEHrCArr3wabaKMemTUI6MeGfXIqEdGPUIXj+gFRP+3SCoYEtg0JhOrR7iAeP4QxSKj s56jYBSMglEwCkYBFAAAXaCM84PpceUAAAAASUVORK5CYII=";

    /**
     * @param point
     * @return
     */
    public static Coordinate createCoordinate(GeoPointDto point) {
        return createCoordinate(point.getLat(), point.getLon());
    }

    /**
     *
     * @param lat
     * @param lon
     * @return
     */
    public static Coordinate createCoordinate(double lat, double lon) {
        return GeoUtils.createCoordinate(lat, lon);
    }

    /**
     *
     * @param lat
     * @param lon
     * @return
     */
    public static Coordinate createCoordinateInMapProjection(double lat, double lon) {
        return GeoUtils.createCoordinatesFromLatLonInMapProjection(lat, lon);
    }

    /**
     *
     * @param point
     * @param imageUrl
     * @return
     */
    public static Feature createPointFeatureFromImageSourceUrl(GeoPointDto point, String imageUrl) {
        return createPointFeatureFromImageSourceUrl(point.getLat(), point.getLon(), imageUrl);

    }

    /**
     *
     * @param point
     * @param name
     * @param imageUrl
     * @return
     */
    public static Feature createPointFeatureFromImageSourceUrl(GeoPointDto point, String name, String imageUrl) {
        if (point == null) {
            return null;
        }
        return createPointFeatureFromImageSourceUrl(point.getLat(), point.getLon(), name, imageUrl);
    }

    /**
     * @param lat
     * @param lon
     * @param imageUrl
     * @return
     */
    public static Feature createPointFeatureFromImageSourceUrl(double lat, double lon, String name, String imageUrl) {
        Point point = OLFactory.createPoint(createCoordinateInMapProjection(lat, lon));

        FeatureOptions featureOptions = OLFactory.createOptions();
        IconOptions options = OLFactory.createOptions();
        options.setSrc(imageUrl);
        // options.setImgSize(OLFactory.createSize(AppConstants.DEFAULT_ICON_WIDTH,
        // AppConstants.DEFAULT_ICON_HEIGHT));
        // options.setSize(OLFactory.createSize(AppConstants.DEFAULT_ICON_WIDTH,
        // AppConstants.DEFAULT_ICON_HEIGHT));
        Style style = createImageStyleWithBackgroundAndText(new ol.style.Icon(options), name);
        featureOptions.setGeometry(point);
        Feature feature = new Feature(featureOptions);
        feature.setId(GUID.get());
        feature.setStyle(style);
        CopLogger.getInstance().debug(OLFeatureBuilder.class, "Wybudowano grafike dla pkt " + lat + ", " + lon);
        return feature;
    }

    public static Feature createPointFeatureFromImageSourceUrl(double lat, double lon, String imageUrl) {
        Point point = OLFactory.createPoint(createCoordinateInMapProjection(lat, lon));
        FeatureOptions featureOptions = OLFactory.createOptions();
        IconOptions options = OLFactory.createOptions();
        options.setImgSize(
                OLFactory.createSize(AppConstants.DEFAULT_SYMBOL_IMAGE_SIZE, AppConstants.DEFAULT_SYMBOL_IMAGE_SIZE));
        options.setSrc(imageUrl);
        Style style = createImageStyleWithBackground(new ol.style.Icon(options));
        featureOptions.setGeometry(point);
        Feature feature = new Feature(featureOptions);
        feature.setId(GUID.get());
        feature.setStyle(style);
        CopLogger.getInstance().debug(OLFeatureBuilder.class, "Wybudowano grafike dla pkt " + lat + ", " + lon);
        return feature;
    }

    public static Feature createSimplePointFeature(double lat, double lon, String name, String color) {
        Point point = OLFactory.createPoint(createCoordinateInMapProjection(lat, lon));
        FeatureOptions featureOptions = OLFactory.createOptions();
        featureOptions.setGeometry(point);
        Feature feature = new Feature(featureOptions);
        feature.setId(GUID.get());
        //TODO
        //feature.setStyle(style);
        CopLogger.getInstance().debug(OLFeatureBuilder.class, "Wybudowano grafike dla pkt " + lat + ", " + lon);
        return feature;

    }

    public static Feature createPointFeature(GeoPointDto geoPoint, FeatureStyleDto style) {
        Point point = OLFactory.createPoint(createCoordinateInMapProjection(geoPoint.getLat(), geoPoint.getLon()));

        FeatureOptions featureOptions = OLFactory.createOptions();
        featureOptions.setGeometry(point);


        Feature feature = new Feature(featureOptions);
        feature.setId(GUID.get());
        feature.setStyle(OLStyleManager.getStyle(style));
        return feature;
    }

    public static Feature createSimplePointFeature(GeoPointDto point, String name, String color) {
        return createSimplePointFeature(point.getLat(), point.getLon(), name, color);
    }

    /**
     * @param icon
     * @return
     */
    private static Style createImageStyleWithBackground(Icon icon) {
        StyleOptions options = OLFactory.createOptions();
        options.setImage(icon);
        options.setFill(OLFactory.createFill(OLFactory.createColor(255, 0, 0, 0.5)));
        Style style = new Style(options);

        return style;
    }

    /**
     *
     * @param icon
     * @param name
     * @return
     */
    private static Style createImageStyleWithBackgroundAndText(Icon icon, String name) {
        StyleOptions options = OLFactory.createOptions();
        options.setImage(icon);
        options.setFill(OLFactory.createFill(OLFactory.createColor(255, 0, 0, 0.5)));

        // TODO change it
        // options.setText(OLNativeMethods.createTextStyle(name, TextAlign.LEFT,
        // TextBaseline.BOTTOM));
        Style style = new Style(options);
        return style;
    }

    public static Feature createTextFeature(GeoPointDto point, String name, FeatureStyleDto styleConfig) {

        return createTextFeature(GeoUtils.createCoordinate(point), name, styleConfig);
    }

    public static Feature createTextFeature(Coordinate coordinate, String name, FeatureStyleDto styleConfig) {
        if (styleConfig == null) {
            styleConfig = new FeatureStyleDto();
        }
        Point point = OLFactory.createPoint(coordinate);
        StyleOptions styleOptions = new StyleOptions();
        TextOptions to = OLFactory.createTextOptions();
        to.setText(name);
        to.setTextAlign("center");
        to.setTextBaseline("alphabetic");
        to.setStroke(OLFactory.createStroke(OLFeatureBuilder.createColorFromHex(styleConfig.getStrokeColor(), 1), 3));
        styleOptions.setText(OLFactory.createText(to));
        FeatureOptions featureOptions = OLFactory.createOptions();
        featureOptions.setGeometry(point);

        Style style = new Style(styleOptions);
        Feature feature = new Feature(featureOptions);
        feature.setId(GUID.get());
        feature.setStyle(style);
        return feature;
    }

    /**
     * @param coordinates
     * @return
     */
    public static Feature createPolygonFeature(Coordinate[][] coordinates) {
        return buildFeature(OLFactory.createPolygon(coordinates), null);
    }

    public static Style createStyleFromFillAndStroke(Fill fill, Stroke stroke) {
        StyleOptions styleOptions = new StyleOptions();
        styleOptions.setFill(fill);
        styleOptions.setStroke(stroke);
        return new Style(styleOptions);
    }

    /**
     *
     * @param center
     * @param radius
     * @param sides
     * @param style
     * @return
     */
    public static Feature createRegularPolygonFeautre(Coordinate center, Float radius, int sides, Style style) {
        return buildFeature(Polygon.circular(GeoUtils.wgs84Sphere, center, radius, sides), style);
    }


    /**
     *
     * @param center
     *            in latLon
     * @param radius
     * @param sides
     * @param style
     * @return
     */
    public static Feature createRegularPolygonFeautreInMapProjectionFromLatLon(Coordinate center, Float radius,
                                                                               int sides, Style style) {
        return buildFeature(Polygon.circular(GeoUtils.wgs84Sphere, center, radius, sides)
                .transform(OLProjection.EPSG_4326.getCode(), OLProjection.MERCARTOR.getCode()), style);
    }


    /**
     *
     * @param center
     *            in latLon
     * @param radius
     * @param sides
     * @return
     */
    public static Feature createRegularPolygonFeautreInMapProjectionFromLatLon(Coordinate center, Float radius,
                                                                               int sides) {
        return createRegularPolygonFeautreInMapProjectionFromLatLon(center, radius, sides, null);
    }

    /**
     *
     * @param center
     * @param radius
     * @param sides
     * @return
     */
    public static Feature createRegularPolygonFeautre(Coordinate center, Float radius, int sides) {
        return createRegularPolygonFeautre(center, radius, sides, null);
    }

    /**
     *
     * @param geometry
     * @param style
     * @return
     */
    public static Feature buildFeature(Geometry geometry, Style style) {
        Feature feature = new Feature();
        feature.setGeometry(geometry);
        if (style != null)
            feature.setStyle(style);
        feature.setId(GUID.get());
        feature.set(INITAL_STYLE_PROP, style);
        return feature;
    }

    public static void setFeatureSelectedStyle(Feature feature) {

        Style style = feature.get(INITAL_STYLE_PROP);
        String  geometryName = feature.getGeometryName();
        if(style == null){
            feature.set(INITAL_STYLE_PROP, feature.getStyle());
            style = feature.get(INITAL_STYLE_PROP);
        }
        Style selected = feature.get(SELECTED_STYLE_PROP);
        if(selected != null){
            feature.setStyle(selected);
        }

        else if (style != null) {
            Style newStyle = style.clone();
            if (newStyle.getStroke() != null) {
                newStyle.getStroke().setWidth(newStyle.getStroke().getWidth() + 5);
                newStyle.getStroke().setColor(getSelectedFeatureColor(newStyle.getStroke().getColor()));
            }

            if (newStyle.getFill() != null
                    && (feature.getGeometry() instanceof Point)) {
                newStyle.getFill().setColor(getSelectedFeatureColor(newStyle.getFill().getColor()));
            }
            feature.setStyle(newStyle);
        }else{

        }

    }

    private static Color getSelectedFeatureColor(Color color) {
        return OLFactory.createColor(color.getRed() * 2 % 255,
                color.getGreen() * 2 % 255,
                color.getBlue() * 2 % 255,
                1);
    }

    public static void setDefaultFeatureStyle(Feature feature) {
        Style style = feature.get(INITAL_STYLE_PROP);

        if (style != null) {
            feature.setStyle(style);
        }
    }


    /**
     *
     * @param coordinates
     * @param style
     * @return
     */
    public static Feature buildPolygonFromLinearRingCoordinates(Coordinate[][] coordinates, Style style) {
        return OLFactory.createFeature(OLFactory.createPolygon(coordinates), style);
    }

    public static Feature buildPolygonFromLinearRingCoordinates(Coordinate[] coordinates, Style style) {
        Coordinate[][] cords = new Coordinate[1][coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            cords[0][i] = coordinates[i];
        }
        return buildPolygonFromLinearRingCoordinates(cords, style);
    }

    public static Feature buildPolygonFromLinearRingCoordinates(List<Coordinate> coords, Style style) {
        return buildPolygonFromLinearRingCoordinates(coords.toArray(new Coordinate[coords.size()]), style);
    }

    /**
     *
     * @param coordinates
     * @param style
     * @return
     */
    public static Feature buildLine(Coordinate[] coordinates, Style style) {

        MultiLineString line = new MultiLineString();
        LineString ls = new LineString(new Coordinate[0]);
        for (int i = 0; i < coordinates.length; i++) {
            ls.appendCoordinate(coordinates[i]);
        }
        line.appendLineString(ls);

        Coordinate[][] cords = new Coordinate[1][coordinates.length + 1];
        for (int i = 0; i < coordinates.length; i++) {
            cords[0][i] = coordinates[i];
        }
        cords[0][coordinates.length] = coordinates[0];

        return OLFeatureBuilderAdapt3.drawBSplinePolygonSAR(Arrays.asList(coordinates), style);
    }

    public static Feature buildLine(List<Coordinate> coordinates, Style style) {
        return buildLine(coordinates.toArray(new Coordinate[coordinates.size()]), style);
    }

    /**
     *
     * @param hex
     * @param alpha
     * @return
     */
    public static Color createColorFromHex(String hex, double alpha) {
        int[] rgb = hexToRGB(hex);
        return OLFactory.createColor(rgb[0], rgb[1], rgb[2], alpha);

    }

    /**
     *
     * @param hex
     * @return
     */
    public static int[] hexToRGB(String hex) {
        if (hex == null || hex.length() != 6) {
            hex = "FF0000";
        }
        final int[] ret = new int[3];
        for (int i = 0; i < 3; i++) {
            ret[i] = Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
        }
        return ret;
    }

    /**
     * @return
     */
    public static Style createInvisibleStyle() {
        return createStyleFromFillAndStroke(OLFactory.createFill(OLFactory.createColor(0, 0, 0, 0)),
                OLFactory.createStroke(OLFactory.createColor(0, 0, 0, 0), 0));
    }

    /**
     * @param center
     * @return
     */
    public static Circle createCircle(Coordinate center, double radius) {
        return OLFactory.createCircle(center, radius);
    }


    public static Coordinate createCoordinateInMapProjection(GeoPointDto geoPoint) {
        return createCoordinateInMapProjection(geoPoint.getLat(), geoPoint.getLon());
    }

    public static Feature buildLine(FeatureStyleDto style, GeoPointDto... points) {
        Coordinate[] coords = GeoUtils.convertGeoPointsToCoordinatesArray(Arrays.asList(points));
        return buildLine(coords, OLStyleManager.getStyle(style));

    }
}
