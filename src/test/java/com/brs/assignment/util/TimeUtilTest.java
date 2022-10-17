package com.brs.assignment.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class added for Time formats testing.
 */
class TimeUtilTest
{

    @Test
    @DisplayName("Test convert milliseconds to hh:mm:ss format")
    void convertMillisecondsToHHMMSSFormat()
    {
        long timeInMillis = 1000;
        String result = TimeUtil.convertMillisecondsToHHMMSSFormat(timeInMillis);
        assertEquals("00:00:01", result);
        timeInMillis = 60000;
        result = TimeUtil.convertMillisecondsToHHMMSSFormat(timeInMillis);
        assertEquals("00:01:00", result);
        timeInMillis = 3600000;
        result = TimeUtil.convertMillisecondsToHHMMSSFormat(timeInMillis);
        assertEquals("01:00:00", result);
    }
}