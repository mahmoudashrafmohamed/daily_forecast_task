package com.mahmoud_ashraf.data.mappers

import com.mahmoud_ashraf.data.models.local.DailyWeatherEntity
import com.mahmoud_ashraf.data.models.remote.DailyInfo
import com.mahmoud_ashraf.data.models.remote.DailyWeatherResponse
import com.mahmoud_ashraf.data.models.remote.Temp
import com.mahmoud_ashraf.domain.models.DailyWeather


fun List<DailyWeatherEntity>.mapToRemoteResponse() = DailyWeatherResponse(list = this.map {
    DailyInfo(temp = Temp(day = it.temp), dt = it.timeInMilliseconds)
})

fun DailyWeatherResponse.mapToEntity() = this.list?.map {
    DailyWeatherEntity(timeInMilliseconds = it.dt, temp = it.temp?.day)
}

fun DailyWeatherResponse.mapToDomain() = this.list?.map {
    DailyWeather(date = it.dt, temp = it.temp?.day)
}
