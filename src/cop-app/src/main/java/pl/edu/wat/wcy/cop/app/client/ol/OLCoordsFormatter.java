/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol;

import ol.Coordinate;
import ol.GenericFunction;
import ol.proj.Projection;
import pl.edu.wat.wcy.cop.app.client.ol.gis.coords.*;
import pl.edu.wat.wcy.cop.app.shared.ol.OLCordsFormattingType;
import pl.edu.wat.wcy.cop.app.shared.ol.OLProjection;
// Represents ol coords formatter.


public class OLCoordsFormatter {
    private OLCordsFormattingType type = OLCordsFormattingType.UTM;
    private CoordsChangedListener listener;

    public static String format(OLCordsFormattingType type, Coordinate coordsToFormat){
        final Coordinate coords = Projection.transform(coordsToFormat, OLProjection.MERCARTOR.getCode(),
                OLProjection.EPSG_4326.getCode());
        CoordsFormatter formatter = null;
        switch (type) {
            case MGRS:
                formatter = MGRSPoint.LLtoMGRS(coords);
                break;
            case ECEF:
                formatter = ECEFPoint.LLtoECEF(coords);
                break;
            case GEOREF:
                formatter = new CoordsFormatter() {

                    @Override
                    public String coordsToString() {
                        return GeoreffConverter.toGeoRef(coords.lat(), coords.lon(), 4);
                    }
                };
                break;
            // case MGRS:
            // formatter = new MGRSPoint(coords);
            // break;
            case UPS:
                formatter = new UPSPoint(coords);
                break;
            case UTM:
                formatter = new UTMPoint(coords);
                break;
            case DMS:
                formatter = new DMSLatLonPoint(coords);
                break;
            default:
                formatter = new UTMPoint(coords);
                break;
        }
        return formatter.coordsToString();

    }
    public String format(Coordinate coordsToFormat) {
        final Coordinate coords = Projection.transform(coordsToFormat, OLProjection.MERCARTOR.getCode(),
                OLProjection.EPSG_4326.getCode());
        CoordsFormatter formatter = null;
        switch (type) {
            case MGRS:
                formatter = MGRSPoint.LLtoMGRS(coords);
                break;
            case ECEF:
                formatter = ECEFPoint.LLtoECEF(coords);
                break;
            case GEOREF:
                formatter = new CoordsFormatter() {

                    @Override
                    public String coordsToString() {
                        return GeoreffConverter.toGeoRef(coords.lat(), coords.lon(), 4);
                    }
                };
                break;
            // case MGRS:
            // formatter = new MGRSPoint(coords);
            // break;
            case UPS:
                formatter = new UPSPoint(coords);
                break;
            case UTM:
                formatter = new UTMPoint(coords);
                break;
            case DMS:
                formatter = new DMSLatLonPoint(coords);
                break;
            default:
                formatter = new UTMPoint(coords);
                break;
        }
        if (listener != null) {
            listener.onCoordsChanged(formatter.coordsToString());
            return "";
        } else
            return formatter.coordsToString();
    }

    public GenericFunction<Coordinate, String> createCoordsFormatter() {
        return new GenericFunction<Coordinate, String>() {

            @Override
            public String call(Coordinate object) {
                return OLCoordsFormatter.this.format(object);
            }
        };
    }

    public CoordsChangedListener getListener() {
        return listener;
    }
    // zostawia jako przylad native
//	public native JavaScriptObject createCoordsFormatter()
//	/*-{
//	 	var that = this;
//	 	return $entry(function(coords) {
//	 		
//	 		return
//	 			that.@pl.edu.wat.wcy.cop.app.client.ol.OLCoordsFormatter::format(Lol/Coordinate;)(coords);
//	 	});
//	 }-*/;

    public void setListener(CoordsChangedListener listener) {
        this.listener = listener;
    }

    public OLCordsFormattingType getType() {
        return type;
    }

    public void setType(OLCordsFormattingType type) {
        this.type = type;
    }

    public interface CoordsChangedListener {
        void onCoordsChanged(String formattedCoords);
    }

}
