package com.mahmoud_ashraf.data.di


import com.mahmoud_ashraf.data.local.WeatherDao
import com.mahmoud_ashraf.data.remote.ForecastApi
import com.mahmoud_ashraf.data.repositories.DailyWeatherRepositoryImp
import com.mahmoud_ashraf.domain.repositories.DailyWeatherRepository
import org.koin.dsl.module


val repositoryModule = module {
    single {
        provideWeatherRepository(
            get(),get()
        )
    }
}

fun provideWeatherRepository(remoteDataSource : ForecastApi,localDataSource : WeatherDao): DailyWeatherRepository {
    return DailyWeatherRepositoryImp(remoteDataSource,localDataSource)
}