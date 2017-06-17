package com.test.weatherapp.comm.data;

import com.google.gson.annotations.SerializedName;

/**
 * @author Usman
 */

public class TemperatureInfo {

    @SerializedName("temp")
    public double temperature;
    @SerializedName("temp_min")
    public double temperatureMin;
    @SerializedName("temp_max")
    public double temperatureMax;
    public double pressure;
    @SerializedName("sea_level")
    public double seaLevel;

}
