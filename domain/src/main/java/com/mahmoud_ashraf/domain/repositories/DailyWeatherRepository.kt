package com.mahmoud_ashraf.domain.repositories

import com.mahmoud_ashraf.domain.models.DailyWeather
import io.reactivex.Single

interface DailyWeatherRepository {

    fun getDailyWeather(
        cityName: String,
         cnt: String
    ):  Single<List<DailyWeather>>

}