package com.test.weatherapp.util;

import android.support.annotation.NonNull;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility methods.
 *
 * @author Usman
 */

public class Utils {

    /**
     * Converts the given timestamp into HH:mm format. Note that this method expects the provided
     * timestamp in millisecs.
     *
     * @param timestamp
     * @return
     */
    public static String getTime(@NonNull Long timestamp) {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date(timestamp);
        return timeFormat.format(date);
    }

    /**
     * Converts the given timestamp into DayName, dd/MM/yy format, e.g. Saturday, 17/06/17
     * <p>
     * Note that this method expects the provided timestamp in millisecs.
     *
     * @param timestamp
     * @return
     */
    public static String getDate(@NonNull Long timestamp) {
        DateFormat dayNameFormat = new SimpleDateFormat("EEEE");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Date date = new Date(timestamp);
        return String.format("%s, %s", dayNameFormat.format(date), dateFormat.format(date));
    }

}
