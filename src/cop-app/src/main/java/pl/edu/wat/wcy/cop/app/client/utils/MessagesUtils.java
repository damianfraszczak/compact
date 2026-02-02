/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils;

import pl.edu.wat.wcy.cop.app.client.Messages;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.shared.ol.OLControlType;
import pl.edu.wat.wcy.cop.app.shared.ol.OLCordsFormattingType;
import pl.edu.wat.wcy.cop.app.shared.ol.OLInteractionType;
import pl.edu.wat.wcy.cop.app.shared.ol.OLScaleLineUnit;
import pl.edu.wat.wcy.cop.app.shared.ol.sources.OLBingLayerSource;
import pl.edu.wat.wcy.cop.app.shared.ol.sources.OLMapBoxLayerSource;
import pl.edu.wat.wcy.cop.app.shared.ol.sources.OLWeatherLayerSources;
// Provides messages utilities.


public class MessagesUtils {

    private static Messages MESSAGES = CopGinInjector.INSTANCE.getMessages();

    /**
     * @param item
     * @return
     */
    public static String enumToString(Enum item) {
        if (item instanceof OLScaleLineUnit) {
            return scaleLineUnitToString((OLScaleLineUnit) item);
        } else if (item instanceof OLCordsFormattingType) {
            return coordsFormatToString((OLCordsFormattingType) item);
        }
        return item.toString();
    }

    public static String scaleLineUnitToString(OLScaleLineUnit scaleUnit) {
        switch (scaleUnit) {

            case DEGREES:
                return MESSAGES.gismenu_scaleline_degrees();
            case IMPERIAL_INCH:
                return MESSAGES.gismenu_scaleline_imperial();
            case METRIC:
                return MESSAGES.gismenu_scaleline_metric();
            case NAUTICAL_MILE:
                return MESSAGES.gismenu_scaleline_nautical();
            case US_INCH:
                return MESSAGES.gismenu_scaleline_us();
            default:
                break;
        }
        return scaleUnit.toString();
    }

    /**
     * @param coordsFormat
     * @return
     */
    public static String coordsFormatToString(OLCordsFormattingType coordsFormat) {
        switch (coordsFormat) {
            case ECEF:
                return MESSAGES.gismenu_coords_ecef();
            case GEOREF:
                return MESSAGES.gismenu_coords_georeff();
            case MGRS:
                return MESSAGES.gismenu_coords_mgrs();
            case UPS:
                return MESSAGES.gismenu_coords_ups();
            case UTM:
                return MESSAGES.gismenu_coords_utm();
            case DMS:
                return MESSAGES.gismenu_coords_dms();
            default:
                break;
        }
        return coordsFormat.toString();
    }

    /**
     * @param source
     * @return
     */
    public static String weatherLayerSourceToString(OLWeatherLayerSources source) {
        switch (source) {
            case CLOUDS:
                return MESSAGES.layer_weather_clouds();
            case PRECIPITATIONS:
                return MESSAGES.layer_weather_precipitations();
            case SEA_LEVEL_PRESSURE:
                return MESSAGES.layer_weather_sea_level_pressure();
            case TEMPERATURE:
                return MESSAGES.layer_weather_temperature();
            case WIND_SPEED:
                return MESSAGES.layer_weather_wind_speed();

            default:
                break;
        }
        return MESSAGES.layer_weather();
    }

    /**
     * @param source
     * @return
     */
    public static String bingLayerSourceToString(OLBingLayerSource source) {
        switch (source) {

            case AERIAL:
                return MESSAGES.layer_bing_aerial();
            case AERIAL_WITH_LABELS:
                return MESSAGES.layer_bing_aerial_with_labels();
            case COLLINS_BART:
                return MESSAGES.layer_bing_collins_bart();
            case ORDNANCESURVEY:
                return MESSAGES.layer_bing_ordnance_survey();
            case ROAD:
                return MESSAGES.layer_bing_road();
            default:
                break;
        }
        return MESSAGES.layer_bing();
    }

    /**
     *
     * @param controlType
     * @return
     */
    public static String controlTypeToString(OLControlType controlType) {
        switch (controlType) {
            case FULL_SCREEN:
                return MESSAGES.controltype_full_screen();
            case MOUSE_POSITION:
                return MESSAGES.controltype_mouse_position();
            case OVERVIEV_MAP:
                return MESSAGES.controltype_overview_map();
            case SCALE_LINE:
                return MESSAGES.controltype_scale_line();
            case ZOOM_SILDER:
                return MESSAGES.controltype_zoom_slider();
            case ZOOM_TO_EXTENT:
                return MESSAGES.controltype_zoom_to_extent();
            case MAP_LEGEND:
                return MESSAGES.controltype_mapLegend();
            case GRATICULE:
                return MESSAGES.layer_graticule();
            case MAP_CROSSHAIRS:
                return MESSAGES.controltype_mapCrosshairs();
            default:
                break;

        }
        return controlType.toString();
    }

    /**
     * @param interactionType
     * @return
     */
    public static String interactionTypeToString(OLInteractionType interactionType) {
        switch (interactionType) {
            case DRAG_PAN:
                return MESSAGES.interactiontype_drag_pan();
            case DRAG_ROTATE_AND_ZOOM:
                return MESSAGES.interactiontype_drag_rotate_and_zoom();
            case KEYBOARD_PAN:
                return MESSAGES.interactiontype_keyboard_pan();
            case KEYBOARD_ZOOM:
                return MESSAGES.interactiontype_keyboard_zoom();
            case MOUSE_WHEEL_ZOOM:
                return MESSAGES.interactiontype_mouse_wheel_zoom();
            default:
                break;

        }
        return interactionType.toString();
    }

    /**
     * @param source
     * @return
     */
    public static String mapboxLayerSourceToString(OLMapBoxLayerSource source) {
        switch (source) {
            case DARK:
                return MESSAGES.layer_mapbox_dark();
            case LIGHT:
                return MESSAGES.layer_mapbox_Light();
            case OUTDOOR:
                return MESSAGES.layer_mapbox_outdoor();
            case SATELITE:
                return MESSAGES.layer_mapbox_satelite();
//            case SATELITE_STREETS:
//                return MESSAGES.layer_mapbox_satelite_street();
            case STREETS:
                return MESSAGES.layer_mapbox_streeet();
            default:
                return source.toString();
        }
    }
}
