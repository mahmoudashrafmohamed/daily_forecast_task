package com.mahmoud_ashraf.data.remote

import com.mahmoud_ashraf.data.models.remote.DailyWeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {

    @GET(DAILY_WEATHER_API)
    fun getDailyWeather(
        @Query("q") cityName: String,
        @Query("cnt") Cnt: String
    ): Single<DailyWeatherResponse>


    companion object {
        const val DAILY_WEATHER_API = "/data/2.5/forecast/daily"
    }

}