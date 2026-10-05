package pl.edu.wat.wcy.cop.app.client.utils;

import ol.Coordinate;
import ol.Feature;
import ol.geom.Circle;
import pl.edu.wat.wcy.cop.app.client.domain.MapSymbolClientModel;
import pl.edu.wat.wcy.cop.app.client.ol.GeoUtils;
import pl.edu.wat.wcy.cop.app.client.ol.OLNativeMethods;
import pl.edu.wat.wcy.cop.app.shared.domain.ICircleObject;
import pl.edu.wat.wcy.cop.app.shared.domain.IMultiPointObject;
import pl.edu.wat.wcy.cop.app.shared.domain.ScenarioPointObjectDto;
// Provides copy coords to object utilities.

public class CopyCoordsToObjectUtils {

    public static void copyCoord(Feature feature, MapSymbolClientModel spod) {
        if (spod.getObject() instanceof ScenarioPointObjectDto) {
            Coordinate coords = OLNativeMethods.getFeatureCords(feature);
            ((ScenarioPointObjectDto) spod.getObject()).setPoint(GeoUtils.createGeoPointsFromMapCoordinates(coords));
        }
        else if(spod.getObject() instanceof ICircleObject){
            Coordinate[] coords = GeoUtils.getCoordinatesOfFeature(feature);
            ((ICircleObject) spod.getObject()).setPoint(GeoUtils.createGeoPointsFromMapCoordinates(coords[0]));
            ((ICircleObject) spod.getObject()).setRadius(GeoUtils.getRadiusInMeters((Circle) feature.getGeometry()));
        }
        else if(spod.getObject() instanceof IMultiPointObject){
            Coordinate[][] coords = OLNativeMethods.getFeatureCordsAsArray(feature);
            ((IMultiPointObject) spod.getObject()).setPoints(GeoUtils.createGeoPointsFromMapCoordinates(coords[0]));
        }

    }
}
