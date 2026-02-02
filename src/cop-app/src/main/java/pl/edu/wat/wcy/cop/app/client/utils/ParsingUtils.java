/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils;
// Provides parsing utilities.

public class ParsingUtils {

    public final static int ERROR_PARSE_INT = Integer.MIN_VALUE;
    public final static double ERROR_PARSE_DOUBLE = Double.MIN_VALUE;

    /**
     *
     * @param value
     * @return
     */
    public static int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception ex) {
            return ERROR_PARSE_INT;
        }
    }

    /**
     *
     * @param value
     * @return
     */
    public static double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception ex) {
            return ERROR_PARSE_DOUBLE;
        }
    }

    /**
     * @param value
     * @return
     */
    public static boolean isValid(double value) {
        return value != ERROR_PARSE_DOUBLE;
    }
}
