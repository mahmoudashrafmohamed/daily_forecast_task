package com.mahmoud_ashraf.data.repositories

import com.mahmoud_ashraf.data.exceptions.ForecastCustomException
import com.mahmoud_ashraf.data.local.WeatherDao
import com.mahmoud_ashraf.data.mappers.mapToDomain
import com.mahmoud_ashraf.data.mappers.mapToEntity
import com.mahmoud_ashraf.data.mappers.mapToRemoteResponse
import com.mahmoud_ashraf.data.remote.ForecastApi
import com.mahmoud_ashraf.domain.models.DailyWeather
import com.mahmoud_ashraf.domain.repositories.DailyWeatherRepository
import io.reactivex.Single
import io.reactivex.internal.operators.completable.CompletableFromAction

class DailyWeatherRepositoryImp(
    private val remoteDataSource: ForecastApi,
    private val localDataSource: WeatherDao
) : DailyWeatherRepository {

    override fun getDailyWeather(cityName: String, cnt: String): Single<List<DailyWeather>> {
        return remoteDataSource.getDailyWeather(cityName, cnt)
            .flatMap { dailyWeatherResponse ->
                CompletableFromAction {
                    localDataSource.saveWeatherData(
                        list = dailyWeatherResponse.mapToEntity() ?: emptyList()
                    )
                }.andThen(Single.just(dailyWeatherResponse))
            }


            .onErrorResumeNext { t ->
                t.printStackTrace()
                localDataSource.getWeatherData().flatMap {
                    val weatherRemoteResponse = it.mapToRemoteResponse()
                    Single.error(ForecastCustomException(data = weatherRemoteResponse.mapToDomain()))
                }
            }
            .map {
                it.mapToDomain()
            }
    }
}




