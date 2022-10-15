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
     * @param millis time in milliseconds
     *
     * @return time in HHMMSS format
     *
     * @link https://stackoverflow.com/a/9027379
     */
    public static String convertMillisecondsToHHMMSSFormat(long millis)
    {
        return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }
}
