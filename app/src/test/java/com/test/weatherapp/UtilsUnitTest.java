package com.test.weatherapp;

import com.test.weatherapp.util.Utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilsUnitTest {

    @Test
    public void getTimeTest() {
        assertEquals("10:00", Utils.getTime(1497776400000L));
        assertEquals("22:00", Utils.getTime(1497819600000L));
        assertEquals("07:00", Utils.getTime(1497852000000L));
    }


    @Test
    public void getDateTest() {
        assertEquals("Monday, 19/06/17", Utils.getDate(1497830400000L));
        assertEquals("Wednesday, 21/06/17", Utils.getDate(1498003200000L));
    }

}