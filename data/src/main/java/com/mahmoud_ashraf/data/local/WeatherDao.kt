package com.mahmoud_ashraf.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mahmoud_ashraf.data.models.local.DailyWeatherEntity
import io.reactivex.Single

@Dao
interface WeatherDao {

    @Insert(onConflict = REPLACE)
    fun saveWeatherData(list: List<DailyWeatherEntity>)

    @Query("SELECT * FROM daily_weather")
    fun getWeatherData(): Single<List<DailyWeatherEntity>>

    @Query("DELETE FROM daily_weather")
    fun deleteAll(): Single<Int>

}

