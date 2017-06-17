package com.test.weatherapp.comm;

import com.test.weatherapp.comm.data.GetForecastResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Each method indicates an API endpoint.
 *
 * @author Usman
 */

public interface ApiEndpoints {


    /**
     * Get the weather forecast data against the given city id.
     *
     * @param cityId
     * @return
     */
    @GET("/data/2.5/forecast?appid=4c59fa5458109c9cfe7678d794160515&units=metric")
    public Call<GetForecastResponse> getForecast(@Query("id") String cityId);


}
