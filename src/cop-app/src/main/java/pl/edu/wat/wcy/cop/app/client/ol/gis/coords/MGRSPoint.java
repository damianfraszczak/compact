/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ol.gis.coords;

import ol.Coordinate;
import pl.edu.wat.wcy.cop.app.shared.ol.OLEllipsoid;
// Represents mgrs point.


public class MGRSPoint extends ZonedUTMPoint implements CoordsFormatter {

    /**
     * The column letters (for easting) of the lower left value, per set.
     */
    public final static int[] SET_ORIGIN_COLUMN_LETTERS = {'A', 'J', 'S', 'A', 'J', 'S'};
    /**
     * The row letters (for northing) of the lower left value, per set.
     */
    public final static int[] SET_ORIGIN_ROW_LETTERS = {'A', 'F', 'A', 'F', 'A', 'F'};
    /**
     * The column letters (for easting) of the lower left value, per set,, for
     * Bessel Ellipsoid.
     */
    public final static int[] BESSEL_SET_ORIGIN_COLUMN_LETTERS = {'A', 'J', 'S', 'A', 'J', 'S'};
    /**
     * The row letters (for northing) of the lower left value, per set, for
     * Bessel Ellipsoid.
     */
    public final static int[] BESSEL_SET_ORIGIN_ROW_LETTERS = {'L', 'R', 'L', 'R', 'L', 'R'};
    public final static int SET_NORTHING_ROLLOVER = 20000000;
    /**
     * Use 5 digits for northing and easting values, for 1 meter accuracy of
     * coordinate.
     */
    public final static int ACCURACY_1_METER = 5;
    /**
     * Use 4 digits for northing and easting values, for 10 meter accuracy of
     * coordinate.
     */
    public final static int ACCURACY_10_METER = 4;
    /**
     * Use 3 digits for northing and easting values, for 100 meter accuracy of
     * coordinate.
     */
    public final static int ACCURACY_100_METER = 3;
    /**
     * Use 2 digits for northing and easting values, for 1000 meter accuracy of
     * coordinate.
     */
    public final static int ACCURACY_1000_METER = 2;
    /**
     * Use 1 digits for northing and easting values, for 10000 meter accuracy of
     * coordinate.
     */
    public final static int ACCURACY_10000_METER = 1;
    public final static int A = 'A';
    public final static int I = 'I';
    public final static int O = 'O';
    public final static int V = 'V';
    public final static int Z = 'Z';
    /**
     * UTM zones are grouped, and assigned to one of a group of 6 sets.
     */
    protected final static int NUM_100K_SETS = 6;
    /** The set origin column letters to use. */
    protected int[] originColumnLetters = SET_ORIGIN_COLUMN_LETTERS;
    /** The set origin row letters to use. */
    protected int[] originRowLetters = SET_ORIGIN_ROW_LETTERS;
    /** The String holding the MGRS coordinate value. */
    protected String mgrs;

    /**
     * Controls the number of digits that the MGRS coordinate will have, which
     * directly affects the accuracy of the coordinate. Default is
     * ACCURACY_1_METER, which indicates that MGRS coordinates will have 10
     * digits (5 easting, 5 northing) after the 100k two letter code, indicating
     * 1 meter resolution.
     */
    protected int accuracy = ACCURACY_1_METER;

    /**
     * Point to create if you are going to use the static methods to fill the
     * values in.
     */
    public MGRSPoint() {

    }

    /**
     * Constructs a new MGRS instance from a MGRS String, validating the string
     * as a MGRS coordinate.
     */
    public MGRSPoint(String mgrsString) throws NumberFormatException {
        this();
        setMGRS(mgrsString);
    }

    /**
     * Constructs a new MGRSPoint instance from values in another MGRSPoint.
     */
    public MGRSPoint(MGRSPoint point) {
        this();
        copyFrom(point);
    }

    /**
     * Create a MGRSPoint from standard values.
     *
     * @param northing
     *            northing offset
     * @param easting
     *            easting offset
     * @param zoneNumber
     *            the MGRS zone number
     * @param zoneLetter
     *            the MGRS zone letter.
     */
    public MGRSPoint(double northing, double easting, int zoneNumber, char zoneLetter) {
        super(northing, easting, zoneNumber, zoneLetter);
    }

    /**
     * Construct a MGRSPoint from a LatLonPoint, assuming a WGS_84 ellipsoid.
     */
    public MGRSPoint(Coordinate llpoint) {
        this(llpoint, OLEllipsoid.WGS_84);
    }

    /**
     * Construct a MGRSPoint from a LatLonPoint and a particular ellipsoid.
     */
    public MGRSPoint(Coordinate llpoint, OLEllipsoid ellip) {
        this();
        LLtoMGRS(llpoint, ellip, this);
    }

    /**
     * Converts a LatLonPoint to a MGRS Point, assuming the WGS_84 ellipsoid.
     *
     * @return MGRSPoint, or null if something bad happened.
     */
    public static MGRSPoint LLtoMGRS(Coordinate llpoint) {
        return LLtoMGRS(llpoint, OLEllipsoid.WGS_84, new MGRSPoint());
    }

    /**
     * Converts a LatLonPoint to a MGRS Point.
     *
     * @param llpoint
     *            the LatLonPoint to convert.
     * @param mgrsp
     *            a MGRSPoint to put the results in. If it's null, a MGRSPoint
     *            will be allocated.
     * @return MGRSPoint, or null if something bad happened. If a MGRSPoint was
     *         passed in, it will also be returned on a successful conversion.
     */
    public static MGRSPoint LLtoMGRS(Coordinate llpoint, MGRSPoint mgrsp) {
        return LLtoMGRS(llpoint, OLEllipsoid.WGS_84, mgrsp);
    }

    /**
     * Create a MGRSPoint from a LatLonPoint.
     *
     * @param llp
     *            LatLonPoint to convert.
     * @param ellip
     *            Ellipsoid for earth model.
     * @param mgrsp
     *            a MGRSPoint to fill in values for. If null, a new MGRSPoint
     *            will be returned. If not null, the new values will be set in
     *            this object, and it will be returned.
     * @return MGRSPoint with values converted from lat/lon.
     */
    public static MGRSPoint LLtoMGRS(Coordinate llp, OLEllipsoid ellip, MGRSPoint mgrsp) {
        mgrsp = ensurePoint(mgrsp);

        // Calling LLtoUTM here results in N/S zone letters! wrong!
        mgrsp = (MGRSPoint) LLtoUTM(llp, ellip, mgrsp);
        // Need to add this to set the right letter for the latitude.
        mgrsp.zone_letter = mgrsp.getLetterDesignator(llp.lat());
        mgrsp.resolve();
        return mgrsp;
    }

    private static MGRSPoint ensurePoint(MGRSPoint mgrsp) {
        if (mgrsp == null) {
            return new MGRSPoint();
        }
        return mgrsp;
    }

    private void copyFrom(MGRSPoint point) {
        mgrs = point.mgrs;
        northing = point.northing;
        easting = point.easting;
        zone_number = point.zone_number;
        zone_letter = point.zone_letter;
        accuracy = point.accuracy;
    }

    /**
     * Convert MGRS zone letter to UTM zone letter, N or S.
     *
     * @param mgrsZone
     * @return N of given zone is equal or larger than N, S otherwise.
     */
    public static char MGRSZoneToUTMZone(char mgrsZone) {
        if (Character.toUpperCase(mgrsZone) >= 'N') {
            return 'N';
        } else {
            return 'S';
        }
    }

    /**
     * Get the MGRS string value - the honkin' coordinate value.
     */
    public String getMGRS() {
        if (mgrs == null) {
            resolve();
        }
        return mgrs;
    }

    /**
     * Set the MGRS value for this Point. Will be decoded, and the MGRS values
     * figured out. You can call toLatLonPoint() to translate it to lat/lon
     * decimal degrees.
     */
    public void setMGRS(String mgrsString) throws NumberFormatException {
        try {
            mgrs = mgrsString.toUpperCase(); // Just to make sure.
            decode(mgrs);
        } catch (StringIndexOutOfBoundsException sioobe) {
            throw new NumberFormatException("MGRSPoint has bad string: " + mgrsString);
        } catch (NullPointerException npe) {
            // Blow off
        }
    }

    /**
     * Method that provides a check for MGRS zone letters. Returns an uppercase
     * version of any valid letter passed in.
     */
    protected char checkZone(char zone) {
        zone = Character.toUpperCase(zone);

        if (zone <= 'A' || zone == 'B' || zone == 'Y' || zone >= 'Z' || zone == 'I' || zone == 'O') {
            throw new NumberFormatException("Invalid MGRSPoint zone letter: " + zone);
        }

        return zone;
    }

    public int getAccuracy() {
        return accuracy;
    }

    /**
     * Set the number of digits to use for easting and northing numbers in the
     * mgrs string, which reflects the accuracy of the coordinate. From 5 (1
     * meter) to 1 (10,000 meter).
     */
    public void setAccuracy(int value) {
        accuracy = value;
        mgrs = null;
    }

    /**
     * Set the MGRS parameters from a MGRS coordinate string.
     *
     * @param mgrsString
     *            an UPPERCASE coordinate string is expected.
     */
    protected void decode(String mgrsString) throws NumberFormatException {

        if (mgrsString == null || mgrsString.length() == 0) {
            throw new NumberFormatException("MGRSPoint coverting from nothing");
        }

        // Ensure an upper-case string
        mgrsString = mgrsString.toUpperCase();

        int length = mgrsString.length();

        String hunK = null;
        StringBuffer sb = new StringBuffer();
        char testChar;
        int i = 0;

        // get Zone number
        while (!Character.isLetter(testChar = mgrsString.charAt(i))) {
            if (i >= 2) {
                throw new NumberFormatException("MGRSPoint bad conversion from: " + mgrsString
                        + ", first two characters need to be a number between 1-60.");
            }
            sb.append(testChar);
            i++;
        }

        zone_number = Integer.parseInt(sb.toString());

        if (zone_number < 1 || zone_number > 60) {
            throw new NumberFormatException("MGRSPoint bad conversion from: " + mgrsString
                    + ", first two characters need to be a number between 1-60.");
        }

        if (i == 0 || i + 3 > length) {
            // A good MGRS string has to be 4-5 digits long,
            // ##AAA/#AAA at least.
            throw new NumberFormatException(
                    "MGRSPoint bad conversion from: " + mgrsString + ", MGRS string must be at least 4-5 digits long");
        }

        zone_letter = mgrsString.charAt(i++);

        // Should we check the zone letter here? Why not.
        if (zone_letter <= 'A' || zone_letter == 'B' || zone_letter == 'Y' || zone_letter >= 'Z' || zone_letter == 'I'
                || zone_letter == 'O') {
            throw new NumberFormatException(
                    "MGRSPoint zone letter " + zone_letter + " not handled: " + mgrsString);
        }

        hunK = mgrsString.substring(i, i += 2);

        // Validate, check the zone, make sure each letter is between A-Z, not I
        // or O
        char char1 = hunK.charAt(0);
        char char2 = hunK.charAt(1);
        if (char1 < 'A' || char2 < 'A' || char1 > 'Z' || char2 > 'Z' || char1 == 'I' || char2 == 'I' || char1 == 'O'
                || char2 == 'O') {
            throw new NumberFormatException(
                    "MGRSPoint bad conversion from " + mgrsString + ", invalid 100k designator");
        }

        int set = get100kSetForZone(zone_number);

        float east100k = getEastingFromChar(char1, set);
        float north100k = getNorthingFromChar(char2, set);

        // We have a bug where the northing may be 2000000 too low.
        // How do we know when to roll over?

        while (north100k < getMinNorthing(zone_letter)) {
            north100k += 2000000;
        }

        // calculate the char index for easting/northing separator
        int remainder = length - i;

        if (remainder % 2 != 0) {
            throw new NumberFormatException(
                    "MGRSPoint has to have an even number \nof digits after the zone letter and two 100km letters - front \nhalf for easting meters, second half for \nnorthing meters"
                            + mgrsString);
        }

        int sep = remainder / 2;

        float sepEasting = 0f;
        float sepNorthing = 0f;

        if (sep > 0) {
            float accuracyBonus = 100000f / (float) Math.pow(10, sep);
            String sepEastingString = mgrsString.substring(i, i + sep);
            sepEasting = Float.parseFloat(sepEastingString) * accuracyBonus;
            String sepNorthingString = mgrsString.substring(i + sep);
            sepNorthing = Float.parseFloat(sepNorthingString) * accuracyBonus;
        }

        easting = sepEasting + east100k;
        northing = sepNorthing + north100k;
    }

    /**
     * Create the mgrs string based on the internal MGRS parameter settings,
     * should be called if the accuracy changes.
     *
     * @param digitAccuracy
     *            The number of digits to use for the northing and easting
     *            numbers. 5 digits reflect a 1 meter accuracy, 4 - 10 meter, 3
     *            - 100 meter, 2 - 1000 meter, 1 - 10,000 meter.
     */
    public void resolve(int digitAccuracy) {
        setAccuracy(digitAccuracy);
        resolve();
    }

    /**
     * Create the mgrs string based on the internal MGRS parameter settings,
     * using the accuracy set in the MGRSPoint.
     */
    public void resolve() {
        if (zone_letter == 'Z') {
            mgrs = "Latitude limit exceeded";
        } else {
            StringBuffer sb = new StringBuffer(Integer.toString(zone_number)).append(zone_letter)
                    .append(get100kID(easting, northing, zone_number));
            StringBuffer seasting = new StringBuffer(Integer.toString((int) easting));
            StringBuffer snorthing = new StringBuffer(Integer.toString((int) northing));

            while (accuracy + 1 > seasting.length()) {
                seasting.insert(0, '0');
            }

            // We have to be careful here, the 100k values shouldn't
            // be
            // used for calculating stuff here.

            while (accuracy + 1 > snorthing.length()) {
                snorthing.insert(0, '0');
            }

            while (snorthing.length() > 6) {
                snorthing.deleteCharAt(0);
            }
            try {
                sb.append(seasting.substring(1, accuracy + 1)).append(snorthing.substring(1, accuracy + 1));
                mgrs = sb.toString();
            } catch (IndexOutOfBoundsException ioobe) {
                mgrs = null;
            }
        }
    }

    /**
     * Given a MGRS/UTM zone number, figure out the MGRS 100K set it is in.
     */
    protected int get100kSetForZone(int i) {
        int set = i % NUM_100K_SETS;
        if (set == 0)
            set = NUM_100K_SETS;
        return set;
    }

    /**
     * Provided so that extensions to this class can provide different origin
     * letters, in case of different ellipsoids. The int[] represents all of the
     * first letters in the bottom left corner of each set box, as shown in an
     * MGRS 100K box layout.
     */
    protected int[] getOriginColumnLetters() {
        return originColumnLetters;
    }

    /**
     * Provided so that extensions to this class can provide different origin
     * letters, in case of different ellipsoids. The int[] represents all of the
     * first letters in the bottom left corner of each set box, as shown in an
     * MGRS 100K box layout.
     */
    protected void setOriginColumnLetters(int[] letters) {
        originColumnLetters = letters;
    }

    /**
     * Provided so that extensions to this class can provide different origin
     * letters, in case of different ellipsoids. The int[] represents all of the
     * second letters in the bottom left corner of each set box, as shown in an
     * MGRS 100K box layout.
     */
    protected int[] getOriginRowLetters() {
        return originRowLetters;
    }

    /**
     * Provided so that extensions to this class can provide different origin
     * letters, in case of different ellipsoids. The int[] represents all of the
     * second letters in the bottom left corner of each set box, as shown in an
     * MGRS 100K box layout.
     */
    protected void setOriginRowLetters(int[] letters) {
        originRowLetters = letters;
    }

    /**
     * Get the two letter 100k designator for a given MGRS/UTM easting, northing
     * and zone number value.
     */
    protected String get100kID(double easting, double northing, int zone_number) {
        int set = get100kSetForZone(zone_number);
        int setColumn = ((int) easting / 100000);
        int setRow = ((int) northing / 100000) % 20;
        return get100kID(setColumn, setRow, set);
    }

    /**
     * Given the first letter from a two-letter MGRS 100k zone, and given the
     * MGRS table set for the zone number, figure out the easting value that
     * should be added to the other, secondary easting value.
     */
    protected float getEastingFromChar(char e, int set) {
        int[] baseCol = getOriginColumnLetters();
        // colOrigin is the letter at the origin of the set for the
        // column
        int curCol = baseCol[set - 1];
        float eastingValue = 100000f;
        boolean rewindMarker = false;

        while (curCol != e) {
            curCol++;
            if (curCol == I)
                curCol++;
            if (curCol == O)
                curCol++;
            if (curCol > Z) {
                if (rewindMarker) {
                    throw new NumberFormatException("Bad character: " + e);
                }
                curCol = A;
                rewindMarker = true;
            }
            eastingValue += 100000f;
        }
        return eastingValue;
    }

    /**
     * Given the second letter from a two-letter MGRS 100k zone, and given the
     * MGRS table set for the zone number, figure out the northing value that
     * should be added to the other, secondary northing value. You have to
     * remember that Northings are determined from the equator, and the vertical
     * cycle of letters mean a 2000000 additional northing meters. This happens
     * approx. every 18 degrees of latitude. This method does *NOT* count any
     * additional northings. You have to figure out how many 2000000 meters need
     * to be added for the zone letter of the MGRS coordinate.
     *
     * @param n
     *            second letter of the MGRS 100k zone
     * @param set
     *            the MGRS table set number, which is dependent on the MGRS/UTM
     *            zone number.
     */
    protected float getNorthingFromChar(char n, int set) {

        if (n > 'V') {
            throw new NumberFormatException("MGRSPoint given invalid Northing " + n);
        }

        int[] baseRow = getOriginRowLetters();
        // rowOrigin is the letter at the origin of the set for the
        // column
        int curRow = baseRow[set - 1];
        float northingValue = 0f;
        boolean rewindMarker = false;

        while (curRow != n) {
            curRow++;
            if (curRow == I)
                curRow++;
            if (curRow == O)
                curRow++;
            // fixing a bug making whole application hang in this loop
            // when 'n' is a wrong character
            if (curRow > V) {
                if (rewindMarker) { // making sure that this loop ends
                    throw new NumberFormatException("Bad character: " + n);
                }
                curRow = A;
                rewindMarker = true;
            }
            northingValue += 100000f;
        }

        return northingValue;
    }

    /**
     * Get the two-letter MGRS 100k designator given information translated from
     * the MGRS/UTM northing, easting and zone number.
     *
     * @param setColumn
     *            the column index as it relates to the MGRS 100k set
     *            spreadsheet, created from the MGRS/UTM easting. Values are
     *            1-8.
     * @param setRow
     *            the row index as it relates to the MGRS 100k set spreadsheet,
     *            created from the MGRS/UTM northing value. Values are from
     *            0-19.
     * @param set
     *            the set block, as it relates to the MGRS 100k set spreadsheet,
     *            created from the MGRS/UTM zone. Values are from 1-60.
     * @return two letter MGRS 100k code.
     */
    protected String get100kID(int setColumn, int setRow, int set) {

        int[] baseCol = getOriginColumnLetters();
        int[] baseRow = getOriginRowLetters();

        // colOrigin and rowOrigin are the letters at the origin of
        // the set
        int colOrigin = baseCol[set - 1];
        int rowOrigin = baseRow[set - 1];

        // colInt and rowInt are the letters to build to return
        int colInt = colOrigin + setColumn - 1;
        int rowInt = rowOrigin + setRow;
        boolean rollover = false;

        if (colInt > Z) {
            colInt = colInt - Z + A - 1;
            rollover = true;
        }

        if (colInt == I || (colOrigin < I && colInt > I) || ((colInt > I || colOrigin < I) && rollover)) {
            colInt++;
        }
        if (colInt == O || (colOrigin < O && colInt > O) || ((colInt > O || colOrigin < O) && rollover)) {
            colInt++;

            if (colInt == I) {
                colInt++;
            }
        }

        if (colInt > Z) {
            colInt = colInt - Z + A - 1;
        }

        if (rowInt > V) {
            rowInt = rowInt - V + A - 1;
            rollover = true;
        } else {
            rollover = false;
        }

        if (rowInt == I || (rowOrigin < I && rowInt > I) || ((rowInt > I || rowOrigin < I) && rollover)) {
            rowInt++;
        }

        if (rowInt == O || (rowOrigin < O && rowInt > O) || ((rowInt > O || rowOrigin < O) && rollover)) {
            rowInt++;
            if (rowInt == I) {
                rowInt++;
            }
        }
        if (rowInt > V) {
            rowInt = rowInt - V + A - 1;
        }
        String twoLetter = (char) colInt + "" + (char) rowInt;
        return twoLetter;
    }

    /**
     * Testing method, used to print out the MGRS 100k two letter set tables.
     */
    protected void print100kSets() {
        StringBuffer sb = null;
        for (int set = 1; set <= 6; set++) {
            System.out.println("-------------\nFor 100K Set " + set + ":\n-------------\n");
            for (int i = 19; i >= 0; i -= 1) {
                sb = new StringBuffer(Integer.toString(i * 100000)).append("\t| ");

                for (int j = 1; j <= 8; j++) {
                    sb.append(" ").append(get100kID(j, i, set));
                }

                sb.append(" |");
                System.out.println(sb);
            }
        }
    }

    /**
     * The function getMinNorthing returns the minimum northing value of a MGRS
     * zone.
     *
     * portted from Geotrans' c Latitude_Band_Value structure table. zoneLetter
     * : MGRS zone (input)
     */

    protected float getMinNorthing(char zoneLetter) throws NumberFormatException {
        float northing;
        switch (zoneLetter) {
            case 'C':
                northing = 1100000.0f;
                break;
            case 'D':
                northing = 2000000.0f;
                break;
            case 'E':
                northing = 2800000.0f;
                break;
            case 'F':
                northing = 3700000.0f;
                break;
            case 'G':
                northing = 4600000.0f;
                break;
            case 'H':
                northing = 5500000.0f;
                break;
            case 'J':
                northing = 6400000.0f;
                break;
            case 'K':
                northing = 7300000.0f;
                break;
            case 'L':
                northing = 8200000.0f;
                break;
            case 'M':
                northing = 9100000.0f;
                break;
            case 'N':
                northing = 0.0f;
                break;
            case 'P':
                northing = 800000.0f;
                break;
            case 'Q':
                northing = 1700000.0f;
                break;
            case 'R':
                northing = 2600000.0f;
                break;
            case 'S':
                northing = 3500000.0f;
                break;
            case 'T':
                northing = 4400000.0f;
                break;
            case 'U':
                northing = 5300000.0f;
                break;
            case 'V':
                northing = 6200000.0f;
                break;
            case 'W':
                northing = 7000000.0f;
                break;
            case 'X':
                northing = 7900000.0f;
                break;
            default:
                northing = -1.0f;
        }
        if (northing >= 0.0) {
            return northing;
        } else {
            throw new NumberFormatException("Invalid zone letter: " + zone_letter);
        }

    }

    public double[] toLatLon() {
        if (this.zone_number >= 0 && this.zone_number <= 60) {
            double k0 = 0.9996D;
            double a = OLEllipsoid.WGS_84.radius;
            double eccSquared = OLEllipsoid.WGS_84.eccsq;
            double e1 = (1.0D - Math.sqrt(1.0D - eccSquared)) / (1.0D + Math.sqrt(1.0D - eccSquared));
            double x = this.easting - 500000.0D;
            double y = this.northing;
            if (this.zone_letter == 83) {
                y = this.northing - 1.0E7D;
            }

            double LongOrigin = (double) ((this.zone_number - 1) * 6 - 180 + 3);
            double eccPrimeSquared = eccSquared / (1.0D - eccSquared);
            double M = y / k0;
            double mu = M / (a * (1.0D - eccSquared / 4.0D - 3.0D * eccSquared * eccSquared / 64.0D
                    - 5.0D * eccSquared * eccSquared * eccSquared / 256.0D));
            double phi1Rad = mu + (3.0D * e1 / 2.0D - 27.0D * e1 * e1 * e1 / 32.0D) * Math.sin(2.0D * mu)
                    + (21.0D * e1 * e1 / 16.0D - 55.0D * e1 * e1 * e1 * e1 / 32.0D) * Math.sin(4.0D * mu)
                    + 151.0D * e1 * e1 * e1 / 96.0D * Math.sin(6.0D * mu);
            double N1 = a / Math.sqrt(1.0D - eccSquared * Math.sin(phi1Rad) * Math.sin(phi1Rad));
            double T1 = Math.tan(phi1Rad) * Math.tan(phi1Rad);
            double C1 = eccPrimeSquared * Math.cos(phi1Rad) * Math.cos(phi1Rad);
            double R1 = a * (1.0D - eccSquared)
                    / Math.pow(1.0D - eccSquared * Math.sin(phi1Rad) * Math.sin(phi1Rad), 1.5D);
            double D = x / (N1 * k0);
            double lat = phi1Rad - N1 * Math.tan(phi1Rad) / R1 * (D * D / 2.0D
                    - (5.0D + 3.0D * T1 + 10.0D * C1 - 4.0D * C1 * C1 - 9.0D * eccPrimeSquared) * D * D * D * D / 24.0D
                    + (61.0D + 90.0D * T1 + 298.0D * C1 + 45.0D * T1 * T1 - 252.0D * eccPrimeSquared - 3.0D * C1 * C1)
                    * D * D * D * D * D * D / 720.0D);
            lat = Math.toDegrees(lat);
            double lon = (D - (1.0D + 2.0D * T1 + C1) * D * D * D / 6.0D
                    + (5.0D - 2.0D * C1 + 28.0D * T1 - 3.0D * C1 * C1 + 8.0D * eccPrimeSquared + 24.0D * T1 * T1) * D
                    * D * D * D * D / 120.0D)
                    / Math.cos(phi1Rad);
            lon = LongOrigin + Math.toDegrees(lon);
            double[] point = new double[2];
            point[0] = lat;
            point[1] = lon;
            return point;
        } else {
            return null;
        }
    }
}
