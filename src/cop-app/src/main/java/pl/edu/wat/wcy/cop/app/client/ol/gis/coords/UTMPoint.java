/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol.gis.coords;

import ol.Coordinate;
import pl.edu.wat.wcy.cop.app.client.CopGinInjector;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;
import pl.edu.wat.wcy.cop.app.shared.ol.OLEllipsoid;
// Represents utm point.


public class UTMPoint implements CoordsFormatter {

    public double northing;

    public double easting;

    public int zone_number;

    public char zone_letter;

    public UTMPoint() {
    }

    public UTMPoint(double northing, double easting, int zone_number, char zone_letter) {
        this.northing = northing;
        this.easting = easting;
        this.zone_number = zone_number;
        this.zone_letter = checkZone(zone_letter);
    }

    public UTMPoint(UTMPoint point) {
        this(point.northing, point.easting, point.zone_number, point.zone_letter);
    }

    public UTMPoint(Coordinate llpoint) {
        this(llpoint, OLEllipsoid.WGS_84);
    }

    public UTMPoint(Coordinate llpoint, OLEllipsoid ellip) {
        this();
        LLtoUTM(llpoint, ellip, this);
    }

    public static UTMPoint LLtoUTM(Coordinate llpoint) {
        return LLtoUTM(llpoint, OLEllipsoid.WGS_84, new UTMPoint());
    }

    public static UTMPoint LLtoUTM(Coordinate llpoint, UTMPoint utmpoint) {
        return LLtoUTM(llpoint, OLEllipsoid.WGS_84, utmpoint);
    }

    public static UTMPoint LLtoUTM(Coordinate llpoint, OLEllipsoid ellip, UTMPoint utmpoint) {
        int zoneNumber = getZoneNumber(llpoint.getY(), llpoint.getX());
        boolean isnorthern = (llpoint.lat() >= 0f);

        return LLtoUTM(llpoint, ellip, utmpoint, zoneNumber, isnorthern);
    }

    public static UTMPoint LLtoUTM(Coordinate llpoint, OLEllipsoid ellip, UTMPoint utmPoint, int zoneNumber,
                                   boolean isNorthern) {


        double a = ellip.radius;
        double k0 = 0.9996;

        double eccSquared = ellip.eccsq;
        double eccPrimeSquared = (eccSquared) / (1 - eccSquared);
        double eccSquared2 = eccSquared * eccSquared;
        double eccSquared3 = eccSquared2 * eccSquared;

        double N, T, C, A, M;

        double LatRad = ProjMath.degToRad(llpoint.lat());
        double LongRad = ProjMath.degToRad(llpoint.lon());

        // in middle of zone
        double LongOrigin = (zoneNumber - 1) * 6 - 180 + 3; // +3 puts origin
        double LongOriginRad = Math.toRadians(LongOrigin);

        double tanLatRad = Math.tan(LatRad);
        double sinLatRad = Math.sin(LatRad);
        double cosLatRad = Math.cos(LatRad);

        N = a / Math.sqrt(1 - eccSquared * sinLatRad * sinLatRad);
        T = tanLatRad * tanLatRad;
        C = eccPrimeSquared * cosLatRad * cosLatRad;
        A = cosLatRad * (LongRad - LongOriginRad);

        M = a * ((1 - eccSquared / 4 - 3 * eccSquared2 / 64 - 5 * eccSquared3 / 256) * LatRad
                - (3 * eccSquared / 8 + 3 * eccSquared2 / 32 + 45 * eccSquared3 / 1024) * Math.sin(2 * LatRad)
                + (15 * eccSquared2 / 256 + 45 * eccSquared3 / 1024) * Math.sin(4 * LatRad)
                - (35 * eccSquared3 / 3072) * Math.sin(6 * LatRad));

        double UTMEasting = (k0 * N
                * (A + (1 - T + C) * A * A * A / 6.0d
                + (5 - 18 * T + T * T + 72 * C - 58 * eccPrimeSquared) * A * A * A * A * A / 120.0d)
                + 500000.0d);

        double UTMNorthing = (k0
                * (M + N * Math.tan(LatRad) * (A * A / 2 + (5 - T + 9 * C + 4 * C * C) * A * A * A * A / 24.0d
                + (61 - 58 * T + T * T + 600 * C - 330 * eccPrimeSquared) * A * A * A * A * A * A / 720.0d)));
        if (!isNorthern) {
            UTMNorthing += 10000000.0f; // 10000000 meter offset for
            // southern hemisphere
        }

        if (utmPoint == null) {
            utmPoint = new UTMPoint();
        }

        utmPoint.northing = UTMNorthing;
        utmPoint.easting = UTMEasting;
        utmPoint.zone_number = zoneNumber;
        utmPoint.zone_letter = isNorthern ? 'N' : 'S';

        return utmPoint;
    }

    /**
     * Find zone number based on the given latitude and longitude in *degrees*.
     *
     * @param lat
     *            in decimal degrees
     * @param lon
     *            in decimal degrees
     * @return zone number for UTM zone for lat, lon
     */
    public static int getZoneNumber(double lat, double lon) {
        int zoneNumber = (int) ((lon + 180) / 6) + 1;

        // Make sure the longitude 180.00 is in Zone 60
        if (lon == 180) {
            zoneNumber = 60;
        }

        // Special zone for Norway
        if (lat >= 56.0f && lat < 64.0f && lon >= 3.0f && lon < 12.0f) {
            zoneNumber = 32;
        }

        // Special zones for Svalbard
        if (lat >= 72.0f && lat < 84.0f) {
            if (lon >= 0.0f && lon < 9.0f)
                zoneNumber = 31;
            else if (lon >= 9.0f && lon < 21.0f)
                zoneNumber = 33;
            else if (lon >= 21.0f && lon < 33.0f)
                zoneNumber = 35;
            else if (lon >= 33.0f && lon < 42.0f)
                zoneNumber = 37;
        }

        return zoneNumber;
    }

    protected char checkZone(char zone) {
        zone = Character.toUpperCase(zone);
        if (zone != 'N' && zone != 'S') {
            throw new NumberFormatException("Invalid UTMPoint zone letter: " + zone);
        }
        return zone;
    }

    protected char getLetterDesignator(double lat) {
        char letterDesignator = 'N';

        if (lat < 0) {
            letterDesignator = 'S';
        }
        return letterDesignator;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.client.ol.gis.coords.CoordsFormatter#
     * coordsToString()
     */
    @Override
    public String coordsToString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(CopGinInjector.INSTANCE.getMessages().gismenu_coords_utm_zone());
        buffer.append(" " + zone_number + " ");
        buffer.append(AppConstants.NUMBER_FORMAT.format(northing) + " N ");
        buffer.append(AppConstants.NUMBER_FORMAT.format(easting) + " E ");
        buffer.append(zone_letter);
        return buffer.toString();
    }
}
