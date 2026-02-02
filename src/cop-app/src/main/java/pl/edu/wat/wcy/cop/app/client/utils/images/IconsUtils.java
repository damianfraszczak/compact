/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.images;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import pl.edu.wat.wcy.cop.app.shared.ol.OLControlType;
import pl.edu.wat.wcy.cop.app.shared.ol.OLCordsFormattingType;
import pl.edu.wat.wcy.cop.app.shared.ol.OLInteractionType;
import pl.edu.wat.wcy.cop.app.shared.ol.OLScaleLineUnit;
import pl.edu.wat.wcy.cop.app.shared.ol.sources.OLMapBoxLayerSource;
import pl.edu.wat.wcy.cop.app.shared.ol.sources.OLWeatherLayerSources;
// Provides icons utilities.

public class IconsUtils {

    private static final GisImages GIS_IMAGES = GisImages.INSTANCE;

    private static final String BASE64_IMAGE_SOURCE_FORMAT = "data:image/png;base64,";

    public static String getBase64ImgSrc(String base64) {
        if (base64.contains(BASE64_IMAGE_SOURCE_FORMAT)) {
            return base64;
        }
        return BASE64_IMAGE_SOURCE_FORMAT + base64;
    }

    public static ImageResource getTreeIconFromBase64(String base64) {
        // return getImageFromBase64(base64, AppConstants.DEFAULT_ICON_WIDTH,
        // AppConstants.DEFAULT_ICON_HEIGHT);
        // return new DynamicImageResource(getBase64ImgSrc(base64));
        return null;
    }

    public static Image getImageFromBase64(String base64, int width, int height) {
        DynamicImageResource imageResource = new DynamicImageResource(getBase64ImgSrc(base64));
        return new ScalableImage(imageResource, width, height);
    }

    /**
     * @param imageSource
     * @return
     */
    public static ImageResource getTreeIconFromImageSource(String imageSource) {
        return null;
    }

    /**
     * @param scaleLineUnit
     * @return
     */
    public static ImageResource scaleLineUnitToIcon(OLScaleLineUnit scaleLineUnit) {
        switch (scaleLineUnit) {

            case DEGREES:
                return GIS_IMAGES.scale_line_degrees();
            case IMPERIAL_INCH:
                return GIS_IMAGES.scale_line();
            case METRIC:
                return GIS_IMAGES.scale_line_metric();
            case NAUTICAL_MILE:
                return GIS_IMAGES.scale_line();
            case US_INCH:
                return GIS_IMAGES.scale_line();
            default:
                break;
        }
        return GIS_IMAGES.scale_line();
    }

    /**
     *
     * @param controlType
     * @return
     */
    public static ImageResource controlTypeToIcon(OLControlType controlType) {
        return GIS_IMAGES.controls();
    }

    /**
     *
     * @param interactionType
     * @return
     */
    public static ImageResource interactionTypeToIcon(OLInteractionType interactionType) {
        return GIS_IMAGES.interactions();
    }

    /**
     * @param coordsFormat
     * @return
     */
    public static ImageResource coordsFormatToIcon(OLCordsFormattingType coordsFormat) {
        return GIS_IMAGES.coords();
    }

    /**
     * @param source
     * @return
     */
    public static ImageResource weatherLayerSourceToIcon(OLWeatherLayerSources source) {
        switch (source) {
            case CLOUDS:
                return GIS_IMAGES.layer_clouds();
            case PRECIPITATIONS:
                return GIS_IMAGES.layer_precipitations();
            case SEA_LEVEL_PRESSURE:
                return GIS_IMAGES.layer_pressure();
            case TEMPERATURE:
                return GIS_IMAGES.layer_temperature();
            case WIND_SPEED:
                return GIS_IMAGES.layer_wind();
            default:
                break;
        }
        return GIS_IMAGES.layer_weather();
    }

    /**
     * @param source
     * @return
     */
    public static ImageResource mapboxLayerSourceToIcon(OLMapBoxLayerSource source) {
        return GIS_IMAGES.layer_add();
    }

}
