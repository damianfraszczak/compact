package pl.edu.wat.wcy.cop.app.client.ol;

import ol.*;
import ol.geom.Circle;
import ol.geom.Geometry;
import ol.geom.Point;
import ol.geom.Polygon;
import ol.proj.Projection;
import pl.edu.wat.wcy.cop.app.client.ol.gis.coords.DMSLatLonPoint;
import pl.edu.wat.wcy.cop.app.client.ol.gis.coords.MGRSPoint;
import pl.edu.wat.wcy.cop.app.shared.domain.GeoPointDto;
import pl.edu.wat.wcy.cop.app.shared.ol.OLProjection;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
// Provides geo utilities.

public class GeoUtils {
    public final static double DATELINE = 180.0;
    public final static double LON_RANGE = 360.0;
    public final static double NORTH_POLE = 90.0;
    public final static double SOUTH_POLE = -NORTH_POLE;
    public static final Sphere wgs84Sphere = new Sphere(6378137);
    private static final Projection PROJECTION_LATLON = Projection.get(OLProjection.EPSG_4326.getCode());
    private static final Projection PROJECTION_MERKATOR = Projection.get(OLProjection.MERCARTOR.getCode());

    public static double getRadiusInMeters(Circle circle) {
        Coordinate center = circle.getCenter();

        Coordinate edgeCoordinate = getCircleEdgeCordinate(circle);
        double distance = wgs84Sphere.haversineDistance(OLFactory.createCoordinate(center.lon(), center.lat()),
                OLFactory.createCoordinate(edgeCoordinate.lon(), edgeCoordinate.lat()));
        return circle.getRadius();
    }

    public static Coordinate getCircleEdgeCordinate(Circle circle) {
        Coordinate center = circle.getCenter();
        double radius = circle.getRadius();
        return center.cloneObject().add(OLFactory.createCoordinate(radius, 0));
    }

    public static Coordinate getCircleEdgeCordinate(double lat, double lon, double radius) {

        return createCoordinate(lat, lon).add(OLFactory.createCoordinate(radius, 0));
    }

    /**
     * @param points
     * @return
     */
    public static <T extends GeoPointDto> List<Coordinate> convertGeoPointsToCoordinates(List<T> points) {
        return points.stream()
                .map(x -> createCoordinate(x))
                .collect(Collectors.toList());
    }


    public static <T extends GeoPointDto> Coordinate[] convertGeoPointsToCoordinatesArray(List<T> points) {
        List<Coordinate> result = convertGeoPointsToCoordinates(points);
        return result.toArray(new Coordinate[result.size()]);
    }

    /**
     * @param point
     * @return
     */
    public static Coordinate createCoordinate(GeoPointDto point) {
        if(point == null){
            return null;
        }
        return createCoordinate(point.getLat(), point.getLon());
    }


    public static GeoPointDto createGeoPointsFromMapCoordinates(Coordinate coords) {
        coords = transformToLatLon(coords);
        return new GeoPointDto(coords.lat(), coords.lon());
    }

    public static List<GeoPointDto> createGeoPointsFromMapCoordinates(Coordinate[] coords) {
        List<GeoPointDto> points = new LinkedList<>();
        for (Coordinate c : coords) {
            points.add(createGeoPointsFromMapCoordinates(c));
        }
        return points;
    }

    public static GeoPointDto createGeoPointsFromMapCoordinates(double lat, double lon) {
        Coordinate coords = transformToLatLon(lat, lon);
        return new GeoPointDto(coords.lat(), coords.lon());
    }

    /**
     * @param lat
     * @param lon
     * @return
     */
    public static Coordinate createCoordinate(double lat, double lon) {
        return OLFactory.createCoordinate(lon, lat);
    }

    public static Coordinate transformFromLatLon(double lat, double lon) {
        return Projection.transform(createCoordinate(lat, lon), OLProjection.EPSG_4326.getCode(),
                OLProjection.MERCARTOR.getCode());
    }

    public static Coordinate transformToLatLon(double x, double y) {
        return Projection.transform(createCoordinate(x, y), OLProjection.MERCARTOR.getCode(),
                OLProjection.EPSG_4326.getCode());
    }

    public static Coordinate transformToLatLon(Coordinate coords) {
        return Projection.transform(coords, OLProjection.MERCARTOR.getCode(), OLProjection.EPSG_4326.getCode());
    }

    public static Coordinate createCoordinatesFromLatLonInMapProjection(double lat, double lon) {
        Coordinate result = Projection.transform(createCoordinate(lat, lon), OLProjection.EPSG_4326.getCode(),
                OLProjection.MERCARTOR.getCode());
        return result;
    }

    /**
     * @param coordinates
     * @return
     */
    public static Coordinate[][] transformFromLatLonToMapProjection(Coordinate[][] coordinates) {
        Coordinate[][] result = new Coordinate[coordinates.length][coordinates[0].length];
        for (int i = 0; i < coordinates.length; i++) {
            for (int j = 0; j < coordinates[i].length; j++) {
                result[i][j] = transformFromLatLonToMapProjection(coordinates[i][j]);
            }
        }
        return result;
    }

    /**
     * @param coordinate
     * @return
     */
    public static Coordinate transformFromLatLonToMapProjection(Coordinate coordinate) {
        return createCoordinatesFromLatLonInMapProjection(coordinate.lat(), coordinate.lon());
    }

    public static List<Coordinate> transformFromLatLonToMapProjection(List<Coordinate> coordinates) {
        return coordinates.stream().map(coordinate -> createCoordinatesFromLatLonInMapProjection(coordinate.lat(), coordinate.lon())).collect(Collectors.toList());
    }

    public static String getDMSFormat(double lat, double lon) {
        return new DMSLatLonPoint(lat, lon).coordsToString();
    }

    public static String getDMSFormat(Coordinate coordinate) {
        return getDMSFormat(coordinate.lat(), coordinate.lon());
    }

    public static String getDMSFormatFromMGRS(String mgrsString) {
        MGRSPoint mgrsCoordinate = new MGRSPoint(mgrsString);
        double[] coord = mgrsCoordinate.toLatLon();
        return getDMSFormat(coord[0], coord[1]);
    }

    /**
     * Ensure latitude is between the poles.
     *
     * @param lat
     * @return latitude greater than or equal to -90 and less than or equal to
     * 90.
     */
    public final static float normalizeLatitude(float lat) {
        return (float) normalizeLatitude((double) lat);
    }

    /**
     * Sets latitude to something sane.
     *
     * @param lat latitude in decimal degrees
     * @return float normalized latitude in decimal degrees (&minus;90&deg; &le;
     * &phi; &le; 90&deg;)
     */
    public final static double normalizeLatitude(double lat) {
        if (lat > NORTH_POLE) {
            lat = NORTH_POLE;
        }
        if (lat < SOUTH_POLE) {
            lat = SOUTH_POLE;
        }
        return lat;
    }

    /**
     * Ensure the longitude is between the date line.
     *
     * @param lon
     * @return longitude that is smaller than or equal to 180 and greater than
     * or equal to -180
     */
    public final static float wrapLongitude(float lon) {
        return (float) wrapLongitude((double) lon);
    }

    /**
     * Sets longitude to something sane.
     *
     * @param lon longitude in decimal degrees
     * @return float wrapped longitude in decimal degrees (&minus;180&deg; &le;
     * &lambda; &le; 180&deg;)
     */
    public final static double wrapLongitude(double lon) {
        if ((lon < -DATELINE) || (lon > DATELINE)) {
            lon += DATELINE;
            lon = lon % LON_RANGE;
            lon = (lon < 0) ? DATELINE + lon : -DATELINE + lon;
        }
        return lon;
    }


    public static Coordinate[] getCoordinatesOfFeature(Feature feature) {
        if (feature.getGeometry() instanceof Circle) {
            Circle c = (Circle) feature.getGeometry();
            return new Coordinate[]{c.getCenter()};
        }
        if (feature.getGeometry() instanceof Point) {
            Point c = (Point) feature.getGeometry();
            return new Coordinate[]{c.getCoordinates()};
        }
        return OLNativeMethods.getFeatureCordsAsArray(feature)[0];
    }

    public static double getRadiusInMeters(Feature feature) {
        if (feature.getGeometry() instanceof Circle) {
            Circle c = (Circle) feature.getGeometry();
            return getRadiusInMeters(c);
        }
        if (feature.getGeometry() instanceof Polygon) {
            Polygon p = (Polygon) feature.getGeometry();
            return p.getArea();
        }
        return -1;
    }

    public static Double getArea(Feature feature) {

        Geometry geom = feature.getGeometry().clone().transform(PROJECTION_MERKATOR, PROJECTION_LATLON);

        if (geom instanceof ol.geom.Polygon) {
            return OLUtil.geodesicArea((ol.geom.Polygon) geom);
        } else if (geom instanceof ol.geom.Circle) {
            ol.geom.Circle circle = (Circle) geom;
            double radiusInMeters = GeoUtils.getRadiusInMeters(circle);
            return Math.PI * radiusInMeters * radiusInMeters;
        } else {
            return null;
        }

    }

    public static GeoPointDto rotatePoint(double cx, double cy, Coordinate point,  double angle) {
        GeoPointDto p1 = new GeoPointDto();
        boolean counterClock = angle < 0;
        angle = Math.toRadians(Math.abs(angle));
        if(counterClock){
            p1.setLat(Math.cos(angle) * (point.lat() - cx) + Math.sin(angle) * (point.lon() - cy) + cx);
            p1.setLon(-Math. sin(angle) * (point.lat() - cx) + Math.cos(angle) * (point.lon() - cy) + cy);
        }else{
            p1.setLat(Math.cos(angle) * (point.lat() - cx) - Math.sin(angle) * (point.lon() - cy) + cx);
            p1.setLon(Math. sin(angle) * (point.lat() - cx) + Math.cos(angle) * (point.lon() - cy) + cy);
        }


        return p1;
    }

    public static Coordinate transformFromLatLon(GeoPointDto pointDto) {
        return transformFromLatLon(pointDto.getLat(), pointDto.getLon());
    }
}
