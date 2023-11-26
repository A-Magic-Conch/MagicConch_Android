package com.android.magicconch.clothpage.seasonpage.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("getVilageFcst")
    Call<WeatherResponse> getWeather(
            @Query("ServiceKey") String serviceKey,
            @Query("nx") String nx,
            @Query("ny") String ny,
            @Query("numOfRows") String numOfRows,
            @Query("base_date") String baseDate,
            @Query("base_time") String baseTime,
            @Query("dataType") String type
    );
}

