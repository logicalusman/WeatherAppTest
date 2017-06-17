package com.test.weatherapp.comm.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Usman
 */

public class ForecastData {

    @SerializedName("dt")
    public long timestamp;
    @SerializedName("dt_txt")
    public String dateTimeText;
    @SerializedName("main")
    public TemperatureInfo temperatureInfo;
    @SerializedName("weather")
    public List<WeatherInfo> weatherInfo;

}