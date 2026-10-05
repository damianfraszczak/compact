package pl.edu.wat.wcy.cop.app.client.ol.gis.coords;
// Converts georeff data.


public class GeoreffConverter {

    static double LATITUDE_LOW = -90.0;
    static double LATITUDE_HIGH = 90.0;
    static double LONGITUDE_LOW = -180.0;
    static double LONGITUDE_HIGH = 360.0;
    static double MIN_PER_DEG = 60.0;
    static double GEOREF_MINIMUM = 4;
    static double GEOREF_MAXIMUM = 14;
    static double MAX_PRECISION = 5;
    static double LETTER_I = 8;
    static double LETTER_M = 12;
    static double LETTER_O = 14;
    static double LETTER_Q = 16;
    static double LETTER_Z = 25;
    static double LETTER_A_OFFSET = 78;
    static double ZERO_OFFSET = 48;
    static double PI = 3.14159265358979323e0;
    static double DEGREE_TO_RADIAN = (PI / 180.0);
    static double RADIAN_TO_DEGREE = (180.0 / PI);
    static double QUAD = 15.0;
    static double ROUND_ERROR = 0.0000005;
    static String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String convertMinutesToString(double minutes, double precision) {
        double divisor;
        int min;
        divisor = Math.pow(10.0, (5.0 - precision));
        if (minutes == 60.0)
            minutes = 59.999;
        minutes = minutes * 1000;
        min = (int) Math.floor(minutes / divisor);
        return min + "";
    }

    public static String toGeoRef(double latitude, double longitude,
                                  double precision) {

        double long_min, lat_min, origin_long, origin_lat;
        double[] letter_number = new double[4];
        int i;
        String GEOREFString = "";
        double division1Lng, division2Lng, division1Lat, division2Lat;

        if ((latitude < LATITUDE_LOW) || (latitude > LATITUDE_HIGH)) {
            return null;
        }

        if (longitude < LONGITUDE_LOW) {
            return null;
        }

        if ((precision < 0) || (precision > MAX_PRECISION)) {
            return null;
        }

        if (longitude > 180) {
            longitude -= 360;
        }

        origin_long = LONGITUDE_LOW;
        origin_lat = LATITUDE_LOW;

        division1Lng = (longitude - origin_long) / QUAD + ROUND_ERROR;

        if (division1Lng >= 0) {
            letter_number[0] = Math.floor(division1Lng);
        } else {
            letter_number[0] = Math.ceil(division1Lng);
        }

        division2Lng = longitude - (letter_number[0] * QUAD + origin_long);

        if (division2Lng >= 0) {
            letter_number[2] = Math.floor(division2Lng);
        } else {
            letter_number[2] = Math.ceil(division2Lng) + ROUND_ERROR;
        }

        long_min = (division2Lng - letter_number[2]) * MIN_PER_DEG;

        division1Lat = (latitude - origin_lat) / QUAD + ROUND_ERROR;

        if (division1Lat >= 0) {
            letter_number[1] = Math.floor(division1Lat);
        } else {
            letter_number[1] = Math.ceil(division1Lat);
        }

        division2Lat = latitude - (letter_number[1] * QUAD + origin_lat);

        if (division2Lat >= 0) {
            letter_number[3] = Math.floor(division2Lat + ROUND_ERROR);
        } else {
            letter_number[3] = Math.ceil(division2Lat + ROUND_ERROR);
        }

        lat_min = (division2Lat - letter_number[3]) * MIN_PER_DEG;

        for (i = 0; i < 4; i++) {
            if (letter_number[i] >= LETTER_I)
                letter_number[i] += 1;
            if (letter_number[i] >= LETTER_O)
                letter_number[i] += 1;
        }

        if (letter_number[0] == 26) {
            letter_number[0] = LETTER_Z;
            letter_number[2] = LETTER_Q;
            long_min = 59.999;
        }

        if (letter_number[1] == 13) {
            letter_number[1] = LETTER_M;
            letter_number[3] = LETTER_Q;
            lat_min = 59.999;
        }

        for (i = 0; i < 4; i++) {
            GEOREFString = GEOREFString + abc.charAt((int) letter_number[i]);
        }

        GEOREFString = GEOREFString
                + convertMinutesToString(long_min, precision);
        GEOREFString = GEOREFString
                + convertMinutesToString(lat_min, precision);

        return GEOREFString;

    }

    public static void main(String[] args) {
        System.out.println(toGeoRef(-45.447778, 120.2594444, 5));
    }
}
