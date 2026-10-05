package pl.edu.wat.wcy.cop.app.client.utils;

import static pl.edu.wat.wcy.cop.app.shared.AppConstants.NUMBER_FORMAT;
// Provides measures presentation utilities.

public class MeasuresPresentationUtils {

    /**
     * @param value in meters
     * @return
     */
    public static String getLengthString(double value) {
        if (Math.abs(value) < 1000) {
            return NUMBER_FORMAT.format(value) + " m";
        } else {
            return NUMBER_FORMAT.format(value / 1000.0) + " km";
        }
    }

    /**
     * @param value in meters^2
     * @return
     */
    public static String getAreaString(double value) {
        // draw always in 2 values
//        return NUMBER_FORMAT.format(value/ 10000.0) + " ha \n" +
//                NUMBER_FORMAT.format(value / 1000000.0) + " km2";

//        if (Math.abs(value) < 10000.0) {
//            return NUMBER_FORMAT.format(value) + " m2";
//        }
//        else if(Math.abs(value) < 1000000) {
//            return NUMBER_FORMAT.format(value/ 10000.0) + " ha";
//        }
//        else {
//            return NUMBER_FORMAT.format(value / 1000000.0) + " km2";
//        }

        //they need always to use ha
        return getAreaInHaString(value);
    }

    public static double getAreaInHa(double value) {
        return value / 10000.0;
    }

    public static String getAreaInHaString(double value) {
        return NUMBER_FORMAT.format(value / 10000.0) + " ha";
    }

    public static double getAreaInKm2(double value) {
        return value / 1000000.0;
    }

    /**
     * @param value in meters^3
     * @return
     */
    public static String getVolumeString(double value) {
        if (Math.abs(value) < 1000000000) {
            return NUMBER_FORMAT.format(value) + " m3";
        } else {
            return NUMBER_FORMAT.format(value / 1000000000.0) + " km3";
        }
    }
}
