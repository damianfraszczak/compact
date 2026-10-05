/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.response.capability;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
// Provides response utilities.


public class ResponseUtils {
    public static final String DOUBLE_NAME = "double";
    public static final String INTEGER_NAME = "int";
    public static final String ONE_OF_OPTION = "One of values %s";
    public static final String LIST_OF_OPTIONS = "List of values %s";
    public static final String STRING_NAME = "String";
    public static final String BOOLEAN_NAME = "boolean";
    public static final String IF_SEPCIFIC_TYPE = "if is ";
    public static final String LONG_NAME = "long";

    public static List<String> oneOfOptionsAsList(Object[] array) {
        return Arrays.asList(oneOfOptions(array));
    }

    public static List<String> listOfOptionsAsList(Object[] array) {
        return Arrays.asList(listOfOptions(array));
    }

    public static String oneOfOptions(Object[] array) {
        return formatArrayValues(ONE_OF_OPTION, array);
    }

    public static String listOfOptions(Object[] array) {
        return formatArrayValues(LIST_OF_OPTIONS, array);
    }

    public static String formatArrayValues(String format, Object[] array) {
        return String.format(format, arrayToString(array));
    }

    public static String arrayToString(Object[] array) {
        return Arrays.asList(array).stream().map(x -> x.toString()).collect(Collectors.joining(", "));
    }
}
