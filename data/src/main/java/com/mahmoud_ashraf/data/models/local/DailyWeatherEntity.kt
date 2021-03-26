package com.mahmoud_ashraf.data.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_weather")
data class DailyWeatherEntity(
    @ColumnInfo(name = "time") val timeInMilliseconds: Long?,
    @ColumnInfo(name = "temp") val temp: Double?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "weather_id") val id: Long = 0L)