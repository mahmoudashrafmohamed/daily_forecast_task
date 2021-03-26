package com.mahmoud_ashraf.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mahmoud_ashraf.data.models.local.DailyWeatherEntity

@Database(entities = [DailyWeatherEntity::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}