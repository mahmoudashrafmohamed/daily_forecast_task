package com.mahmoud_ashraf.domain.usecases

import com.mahmoud_ashraf.domain.models.DailyWeather
import com.mahmoud_ashraf.domain.repositories.DailyWeatherRepository
import com.nhaarman.mockito_kotlin.any
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito
import java.net.UnknownHostException


class GetWeatherByCityNameUseCaseTest {

    @Test
    fun `GetWeatherByCityNameUseCase_invoke() with cityName,cnt then return Single of ListOf DailyWeather `() {
        // arrange
        val date = 111L
        val temp = 547.0
        val cityName = "cairo"
        val cnt = "16"
        val repository = Mockito.mock(DailyWeatherRepository::class.java)
        Mockito.`when`(repository.getDailyWeather(any(), any())).thenReturn(
            Single.just(
                listOf(DailyWeather(date = date,temp =temp ))
            )
        )

        val getWeatherByCityNameUseCase = GetWeatherByCityNameUseCase(repository)

        // act
        val resultObserver = getWeatherByCityNameUseCase(cityName,cnt).test()

        // assert
        resultObserver.assertValue(listOf(DailyWeather(date = date,temp =temp )))
        resultObserver.dispose()
    }

    @Test
    fun `GetWeatherByCityNameUseCase_invoke() with cityName,cnt then return Single of Exception `() {
        // arrange
        val cityName = "cairo"
        val cnt = "16"
        val exception = UnknownHostException()
        val repository = Mockito.mock(DailyWeatherRepository::class.java)
        Mockito.`when`(repository.getDailyWeather(any(), any())).thenReturn(
            Single.error(
           exception
            )
        )

        val getWeatherByCityNameUseCase = GetWeatherByCityNameUseCase(repository)

        // act
        val resultObserver = getWeatherByCityNameUseCase(cityName,cnt).test()

        // assert
        resultObserver.assertError(exception)
        resultObserver.dispose()
    }

}