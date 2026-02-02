package pl.edu.wat.wcy.cop.decisionsupport;

import com.bbn.openmap.proj.coords.LatLonPoint;
// Provides om geo utilities.

public class OMGeoUtils {
    // parametry Circle zwracane przez openlayers
    public static double getCircleRadiusInRadians(double lat, double lon, double radius) {
        LatLonPoint center = new LatLonPoint.Double(lat, lon);
        LatLonPoint toPoint = new LatLonPoint.Double(lat, lon + radius);
        return center.distance(toPoint);
    }
}
