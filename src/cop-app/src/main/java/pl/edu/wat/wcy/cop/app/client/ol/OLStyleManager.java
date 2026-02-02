package pl.edu.wat.wcy.cop.app.client.ol;

import ol.Feature;
import ol.OLFactory;
import ol.color.Color;
import ol.style.*;
import pl.edu.wat.wcy.cop.app.client.domain.spec.ADatP3ReportClientModel;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.client.visualisation.tree.adatp3.CBRNType;
import pl.edu.wat.wcy.cop.app.shared.domain.FeatureStyleDto;
import pl.edu.wat.wcy.cop.app.shared.ol.OLStyleOptions;
// Manages ol style.

public class OLStyleManager {
    private static final String DEFAULT_COLOR = "000000";
    private static Color releaseAreaColor = OLFeatureBuilder.createColorFromHex("FE0000", 0.7);
    private static Color[] hazardAreaColors = new Color[]{OLFeatureBuilder.createColorFromHex("FEFE00", 0.7), // yellow
            OLFeatureBuilder.createColorFromHex("FEC300", 0.7), OLFeatureBuilder.createColorFromHex("FE9000", 0.7) // orange
    };

    public static Style getDefaultReleaseStyle() {
        StyleOptions styleOptions = new StyleOptions();
        styleOptions.setStroke(OLFactory.createStroke(OLFeatureBuilder.createColorFromHex("000000", 1), 3));
        styleOptions.setFill(OLFactory.createFill(OLStyleManager.releaseAreaColor));
        return new Style(styleOptions);
    }

    public static Style getDefaultHazardStyle(int i) {
        StyleOptions styleOptions = new StyleOptions();
        styleOptions.setStroke(OLFactory.createStroke(OLFeatureBuilder.createColorFromHex("000000", 1), 3));
        styleOptions.setFill(OLFactory.createFill(OLStyleManager.hazardAreaColors[i]));

        return new Style(styleOptions);
    }

    public static Style createStyle(OLStyleOptions olStyleOptions, ADatP3ReportClientModel.AdatP3FeatureType type,
                                    Feature[] features) {
        if (type.equals(ADatP3ReportClientModel.AdatP3FeatureType.RELEASE)) {
            return getStyle(olStyleOptions.isHasFill(), olStyleOptions.getReleaseAreaColor(),
                    Double.parseDouble(olStyleOptions.getOpacity()));
        } else if (type.equals(ADatP3ReportClientModel.AdatP3FeatureType.HAZARD)) {
            String color = "000000";
            for (int i = 0; i < features.length; i++) {
                switch (i) {
                    case 0:
                        color = olStyleOptions.getHazardArea1Color();
                        break;
                    case 1:
                        color = olStyleOptions.getHazardArea2Color();
                        break;
                    case 2:
                        color = olStyleOptions.getHazardArea3Color();
                        break;
                    default:
                        color = olStyleOptions.getHazardArea3Color();
                        break;
                }
                features[i].setStyle(
                        getStyle(olStyleOptions.isHasFill(), color, Double.parseDouble(olStyleOptions.getOpacity())));
            }
            return getStyle(olStyleOptions.isHasFill(), color, Double.parseDouble(olStyleOptions.getOpacity()));
        }
        return null;
    }

    public static Style getStyle(boolean hasFill, String color, double opacity) {
        StyleOptions styleOptions = new StyleOptions();
        if (hasFill) {
            styleOptions.setFill(OLFactory.createFill(OLFeatureBuilder.createColorFromHex(color, opacity)));
            styleOptions.setStroke(OLFactory.createStroke(OLFeatureBuilder.createColorFromHex(color, 1), 3));
        } else {
            styleOptions.setStroke(OLFactory.createStroke(OLFeatureBuilder.createColorFromHex(color, opacity), 3));
        }
        return new Style(styleOptions);
    }

    public static Style getStyleByCbrnType(CBRNType cbrnType) {
        Color color;
        switch (cbrnType) {
            case BIO:
                color = OLFeatureBuilder.createColorFromHex("ff3300", 0.7);
                break;
            case CHEM:
                color = OLFeatureBuilder.createColorFromHex("33cc33", 0.7);
                break;
            case NUC:
                color = OLFeatureBuilder.createColorFromHex("00ffff", 0.7);
                break;
            case RAD:
                color = OLFeatureBuilder.createColorFromHex("ffff00", 0.7);
                break;
            default:
                color = OLFeatureBuilder.createColorFromHex("6666ff", 0.7);
                break;
        }
        return getStrokeStyleForColor(color);
    }

    public static Style getStrokeStyleForColor(Color color) {
        StyleOptions styleOptions = new StyleOptions();
        styleOptions.setFill(OLFactory.createFill(color));
        styleOptions.setStroke(OLFactory.createStroke(color, 3));
        return new Style(styleOptions);
    }

    /**
     * @param color
     * @return
     */
    public static Style getStrokeStyleForColor(String color, double alpha) {
        return getStrokeStyleForColor(OLFeatureBuilder.createColorFromHex(color, alpha));
    }

    /**
     * @param color
     * @return
     */
    public static Style creatTextStyle(String color, double alpha) {
        return getStrokeStyleForColor(OLFeatureBuilder.createColorFromHex(color, alpha));
    }

    public static Style getStrokeStyleForColorAndFill(String color, String fill, double alpha) {
        return getStrokeStyleForColorAndFill(color, fill, alpha, null);
    }

    public static Style getStrokeStyleForColorAndFill(String color, String fill, double alpha, String name) {
        StyleOptions styleOptions = new StyleOptions();
        if (!StringUtils.isNullOrEmpty(fill)) {
            styleOptions.setFill(OLFactory.createFill(OLFeatureBuilder.createColorFromHex(fill, alpha)));
        }
        styleOptions.setStroke(OLFactory.createStroke(OLFeatureBuilder.createColorFromHex(color, 1), 3));
        if (!StringUtils.isNullOrEmpty(name)) {
            TextOptions to = OLFactory.createTextOptions();
            to.setText(name);
            to.setTextAlign("center");
            to.setTextBaseline("alphabetic");
            to.setStroke(OLFactory.createStroke(OLFeatureBuilder.createColorFromHex(color, 1), 3));
            to.setFill(OLFactory.createFill(OLFeatureBuilder.createColorFromHex(fill, 1)));
            styleOptions.setText(OLFactory.createText(to));
        }
        return new Style(styleOptions);
    }

    public static Style getDefaultStrokeStyleAndFill(String fill, double alpha) {
        return getStrokeStyleForColorAndFill("000000", fill, alpha);
    }


    public static Style getDefaultStroke(double alpha) {
        return getStrokeStyleForColorAndFill(DEFAULT_COLOR, null, alpha);
    }


    public static Style getStyle(FeatureStyleDto styleConfig) {
        return getStyle(styleConfig, null);
    }

    public static Style getStyle(FeatureStyleDto styleConfig, String text) {
        StyleOptions styleOptions = new StyleOptions();
        if (styleConfig != null) {
            styleOptions.setFill(OLFactory.createFill(OLFeatureBuilder.createColorFromHex(getDefaultColorIfEmpty(styleConfig.getFillColor()), styleConfig.getFillAlpha())));
            StrokeOptions sOpts = new StrokeOptions();
            sOpts.setColor(OLFeatureBuilder.createColorFromHex(getDefaultColorIfEmpty(styleConfig.getStrokeColor()), styleConfig.getStrokeAlpha()));
            sOpts.setWidth(styleConfig.getStrokeWidth());
            try {
                String[] dashArray = styleConfig.getDashArray().split(" ");
                int[] dashArrayInt = new int[dashArray.length];
                for (int i = 0; i < dashArray.length; i++) {
                    dashArrayInt[i] = Integer.parseInt(dashArray[i]);
                }
                sOpts.setLineDash(dashArrayInt);
            } catch (Exception ex) {
            }
            styleOptions.setStroke(OLFactory.createStroke(sOpts));
            if (!StringUtils.isNullOrEmpty(text)) {
                TextOptions to = OLFactory.createTextOptions();
                to.setText(text);
                to.setTextAlign("center");
                to.setTextBaseline("alphabetic");
                to.setStroke(OLFactory.createStroke(OLFeatureBuilder.createColorFromHex(getDefaultColorIfEmpty(styleConfig.getStrokeColor()), 1), 3));
                styleOptions.setText(OLFactory.createText(to));
            }
        }


        return new Style(styleOptions);
    }

    private static String getDefaultColorIfEmpty(String color) {
        if (StringUtils.isNullOrEmpty(color)) {
            return DEFAULT_COLOR;
        } else {
            return color;
        }
    }


}
