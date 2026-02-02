/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils;

import com.google.gwt.i18n.client.DateTimeFormat;

import java.util.Date;
// Provides date utilities.


public class DateUtils {

    public static Date getDateTimeFromDateAndTime(Date date, Date time) {
        DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy/MM/dd HH:mm:ss");
        DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyy/MM/dd");
        DateTimeFormat timeFormat = DateTimeFormat.getFormat("HH:mm:ss");
        String dateFormatString = dateFormat.format(date);
        String timeFormatString = timeFormat.format(time);
        return dateTimeFormat.parse(dateFormatString + " " + timeFormatString);
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy/MM/dd HH:mm:ss");
        return dateTimeFormat.format(date);
    }

    public static String formatOnlyDate(Date date) {
        return formatOnlyDate(date, "");
    }

    public static String formatOnlyDate(Date date, String defaultVal) {
        if (date == null) {
            return defaultVal;
        }
        DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy/MM/dd");
        return dateTimeFormat.format(date);
    }

    public static String formatOnlyTime(Date date) {
        return formatOnlyTime(date, "");
    }
    public static String formatOnlyTime(Long dateTime, String defaultVal) {
        return dateTime == null ? defaultVal : formatOnlyTime(new Date(dateTime));
    }
    public static String formatOnlyTime(Date date, String defaultVal) {
        if (date == null) {
            return defaultVal;
        }
        DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("HH:mm");
        return dateTimeFormat.format(date);
    }
}
