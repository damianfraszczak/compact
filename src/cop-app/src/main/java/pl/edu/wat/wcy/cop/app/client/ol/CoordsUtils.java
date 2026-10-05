/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol;
// Provides coords utilities.


public class CoordsUtils {
    public static String getDMSFormat(double x) {
        double partInSeconds = (x - (int) x) * 3600.0;
        int[] tab = new int[3];
        tab[0] = (int) x;
        tab[1] = (int) ((x - tab[0]) * 60.0);
        tab[2] = (int) (partInSeconds - tab[1] * 60);
        return tab[0] + "° " + tab[1] + "' " + tab[2] + "\"";
    }

    public static String getDMSFormat(double x, CoordinateType dir) {
        String letter;
        switch (dir) {
            case LAT:
                if (x >= 0)
                    letter = "N";
                else
                    letter = "S";
                break;
            case LON:
                if (x >= 0)
                    letter = "E";
                else
                    letter = "W";
                break;
            default:
                return getDMSFormat(x);
        }
        return getDMSFormat(Math.abs(x)) + " " + letter;
    }

    public enum CoordinateType {
        LAT, LON
    }
}
