/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol.gis.coords;

import ol.Coordinate;
import pl.edu.wat.wcy.cop.app.shared.ol.OLEllipsoid;
// Represents zoned utm point.


public class ZonedUTMPoint extends UTMPoint {

    /**
     * Constructor pass-through.
     */
    public ZonedUTMPoint() {
        super();
    }

    /**
     * @param northing
     *            The northing component.
     * @param easting
     *            The easting component.
     * @param zone_number
     *            The zone of the coordinate.
     * @param zone_letter
     *            MGRS zone letter
     */
    public ZonedUTMPoint(double northing, double easting, int zone_number, char zone_letter) {
        super(northing, easting, zone_number, MGRSPoint.MGRSZoneToUTMZone(zone_letter));
        // Need to remember the zone_letter passed in, that's the point of this
        // class.
        this.zone_letter = zone_letter;
    }

    /**
     * Constructor pass-through.
     */
    public ZonedUTMPoint(UTMPoint point) {
        super(point);
    }

    /**
     * Constructor pass-through.
     */
    public ZonedUTMPoint(Coordinate llpoint) {
        super(llpoint);
    }

    /**
     * Constructor pass-through.
     */
    public ZonedUTMPoint(Coordinate llpoint, OLEllipsoid ellip) {
        super(llpoint, ellip);
    }

    /**
     * Determines the correct MGRS letter designator for the given latitude
     * returns 'Z' if latitude is outside the MGRS limits of 84N to 80S.
     *
     * @param lat
     *            The float value of the latitude.
     *
     * @return A char value which is the MGRS zone letter.
     */
    protected char getLetterDesignator(double lat) {

        // This is here as an error flag to show that the Latitude is
        // outside MGRS limits
        char LetterDesignator = 'Z';

        if ((84 >= lat) && (lat >= 72))
            LetterDesignator = 'X';
        else if ((72 > lat) && (lat >= 64))
            LetterDesignator = 'W';
        else if ((64 > lat) && (lat >= 56))
            LetterDesignator = 'V';
        else if ((56 > lat) && (lat >= 48))
            LetterDesignator = 'U';
        else if ((48 > lat) && (lat >= 40))
            LetterDesignator = 'T';
        else if ((40 > lat) && (lat >= 32))
            LetterDesignator = 'S';
        else if ((32 > lat) && (lat >= 24))
            LetterDesignator = 'R';
        else if ((24 > lat) && (lat >= 16))
            LetterDesignator = 'Q';
        else if ((16 > lat) && (lat >= 8))
            LetterDesignator = 'P';
        else if ((8 > lat) && (lat >= 0))
            LetterDesignator = 'N';
        else if ((0 > lat) && (lat >= -8))
            LetterDesignator = 'M';
        else if ((-8 > lat) && (lat >= -16))
            LetterDesignator = 'L';
        else if ((-16 > lat) && (lat >= -24))
            LetterDesignator = 'K';
        else if ((-24 > lat) && (lat >= -32))
            LetterDesignator = 'J';
        else if ((-32 > lat) && (lat >= -40))
            LetterDesignator = 'H';
        else if ((-40 > lat) && (lat >= -48))
            LetterDesignator = 'G';
        else if ((-48 > lat) && (lat >= -56))
            LetterDesignator = 'F';
        else if ((-56 > lat) && (lat >= -64))
            LetterDesignator = 'E';
        else if ((-64 > lat) && (lat >= -72))
            LetterDesignator = 'D';
        else if ((-72 > lat) && (lat >= -80))
            LetterDesignator = 'C';
        return LetterDesignator;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * pl.edu.wat.wcy.cop.app.client.ol.gis.coords.UTMPoint#coordsToString()
     */
    @Override
    public String coordsToString() {
        // TODO Auto-generated method stub
        return super.coordsToString();
    }

}
