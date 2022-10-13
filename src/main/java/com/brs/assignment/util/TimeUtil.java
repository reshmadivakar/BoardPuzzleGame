package com.brs.assignment.util;

import java.util.concurrent.TimeUnit;

/**
 * Util class added for handling operations related to time, date, month, day etc.
 */
public class TimeUtil
{
    /**
     * API to convert the time taken to HH MM SS readable format.
     *
     * @param millis
     *
     * @return time in HHMMSS format
     */
    public static String convertMillisecondsToHHMMSSFormat(long millis)
    {
        return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }
}
