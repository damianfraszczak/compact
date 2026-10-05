/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils;

import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.regexp.shared.SplitResult;
// Provides string utilities.

public class StringUtils {
    public static String format(final String format, final Object... args) {
        final RegExp regex = RegExp.compile("%[a-z]");
        final SplitResult split = regex.split(format);
        final StringBuffer msg = new StringBuffer();
        for (int pos = 0; pos < split.length() - 1; ++pos) {
            msg.append(split.get(pos));
            msg.append(getNotNull(args[pos]));
        }
        msg.append(split.get(split.length() - 1));
        return msg.toString();
    }

    /**
     * @param object
     * @return
     */
    public static String getNotNull(Object object) {
        if (object == null)
            return "";
        else
            return object.toString();
    }

    /**
     * @param text
     * @return
     */
    public static boolean isNullOrEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }
}
