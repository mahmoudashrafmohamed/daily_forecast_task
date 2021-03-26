package com.mahmoud_ashraf.data.models.remote
import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName


@Keep
data class DailyWeatherResponse(
    @SerializedName("list")
    val list: List<DailyInfo>?
)

@Keep
data class DailyInfo(
//    @SerializedName("clouds")
//    val clouds: Int?,
//    @SerializedName("deg")
//    val deg: Int?,
    @SerializedName("dt")
    val dt: Long?,
//    @SerializedName("feels_like")
//    val feelsLike: FeelsLike?,
//    @SerializedName("humidity")
//    val humidity: Int?,
//    @SerializedName("pop")
//    val pop: Double?,
//    @SerializedName("pressure")
//    val pressure: Int?,
//    @SerializedName("speed")
//    val speed: Double?,
//    @SerializedName("sunrise")
//    val sunrise: Int?,
//    @SerializedName("sunset")
//    val sunset: Int?,
    @SerializedName("temp")
    val temp: Temp?,
//    @SerializedName("weather")
//    val weather: List<Weather>?
)

@Keep
data class FeelsLike(
    @SerializedName("day")
    val day: Double?,
    @SerializedName("eve")
    val eve: Double?,
    @SerializedName("morn")
    val morn: Double?,
    @SerializedName("night")
    val night: Double?
)

@Keep
data class Temp(
    @SerializedName("day")
    val day: Double?
    //,
//    @SerializedName("eve")
//    val eve: Double?,
//    @SerializedName("max")
//    val max: Double?,
//    @SerializedName("min")
//    val min: Double?,
//    @SerializedName("morn")
//    val morn: Double?,
//    @SerializedName("night")
//    val night: Double?
)

@Keep
data class Weather(
    @SerializedName("description")
    val description: String?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("main")
    val main: String?
)