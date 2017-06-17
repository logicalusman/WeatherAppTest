package com.test.weatherapp.comm.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Usman
 */

public class GetForecastResponse {

    @SerializedName("cod")
    public String code;
    public double message;
    @SerializedName("cnt")
    public int count;
    @SerializedName("list")
    public List<ForecastData> forecastDataList;


}
