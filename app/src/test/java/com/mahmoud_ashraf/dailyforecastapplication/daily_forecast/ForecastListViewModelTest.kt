package com.mahmoud_ashraf.dailyforecastapplication.daily_forecast

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mahmoud_ashraf.dailyforecastapplication.base.StateData
import com.mahmoud_ashraf.dailyforecastapplication.core.RxImmediateSchedulerRule
import com.mahmoud_ashraf.data.exceptions.ForecastCustomException
import com.mahmoud_ashraf.domain.models.DailyWeather
import com.mahmoud_ashraf.domain.usecases.GetWeatherByCityNameUseCase
import com.nhaarman.mockito_kotlin.any
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ForecastListViewModelTest {

    // to test rx java with different schedulers
    @get:Rule
    var rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    //swaps the background executor used by the Architecture Components
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var weatherDataObserver: Observer<StateData<List<DailyWeather>>?>

    @Captor
    lateinit var weatherDataCaptor: ArgumentCaptor<StateData<List<DailyWeather>>?>


    @Test
    fun `getWeatherByCityName() with cityName then fire loading and success with data`() {

        // arrange
        val cityName = "cairo"
        val data = listOf(DailyWeather(date = 11525252, temp = 12.0))
        val getWeatherByCityNameUseCase = Mockito.mock(GetWeatherByCityNameUseCase::class.java)
        Mockito.`when`(getWeatherByCityNameUseCase(any(), any())).thenReturn(
            Single.just(
                data
            )
        )

        val viewModel = ForecastListViewModel(getWeatherByCityNameUseCase)

        viewModel.weatherLiveData.observeForever(weatherDataObserver)

        // act
        viewModel.getWeatherByCityName(cityName)

        //assert
        Mockito.verify(weatherDataObserver, Mockito.times(2))
            .onChanged(weatherDataCaptor.capture())
        val values = weatherDataCaptor.allValues
        val expectedResponse = listOf(DailyWeather(date = 11525252, temp = 12.0))
        assertEquals(StateData.DataStatus.LOADING, values[0]?.status)
        assertEquals(StateData.DataStatus.SUCCESS, values[1]?.status)
        assertEquals(expectedResponse, values[1]?.data)

        viewModel.weatherLiveData.removeObserver(weatherDataObserver)

    }

    @Test
    fun `getWeatherByCityName() with cityName then fire loading and warning with data`() {

        // arrange
        val cityName = "cairo"
        val data = listOf(DailyWeather(date = 11525252, temp = 12.0))
        val getWeatherByCityNameUseCase = Mockito.mock(GetWeatherByCityNameUseCase::class.java)
        Mockito.`when`(getWeatherByCityNameUseCase(any(), any())).thenReturn(
            Single.error(
                ForecastCustomException(data)
            )
        )

        val viewModel = ForecastListViewModel(getWeatherByCityNameUseCase)

        viewModel.weatherLiveData.observeForever(weatherDataObserver)

        // act
        viewModel.getWeatherByCityName(cityName)

        //assert
        Mockito.verify(weatherDataObserver, Mockito.times(2))
            .onChanged(weatherDataCaptor.capture())
        val values = weatherDataCaptor.allValues
        val expectedResponse = listOf(DailyWeather(date = 11525252, temp = 12.0))
        assertEquals(StateData.DataStatus.LOADING, values[0]?.status)
        assertEquals(StateData.DataStatus.WARNING, values[1]?.status)
        assertEquals(expectedResponse, values[1]?.data)

        viewModel.weatherLiveData.removeObserver(weatherDataObserver)

    }

    @Test
    fun `getWeatherByCityName() with cityName then fire loading and Error`() {

        // arrange
        val cityName = "cairo"
        val data = listOf(DailyWeather(date = 11525252, temp = 12.0))
        val getWeatherByCityNameUseCase = Mockito.mock(GetWeatherByCityNameUseCase::class.java)
        Mockito.`when`(getWeatherByCityNameUseCase(any(), any())).thenReturn(
            Single.error(
                Exception()
            )
        )

        val viewModel = ForecastListViewModel(getWeatherByCityNameUseCase)

        viewModel.weatherLiveData.observeForever(weatherDataObserver)

        // act
        viewModel.getWeatherByCityName(cityName)

        //assert
        Mockito.verify(weatherDataObserver, Mockito.times(2))
            .onChanged(weatherDataCaptor.capture())
        val values = weatherDataCaptor.allValues
        assertEquals(StateData.DataStatus.LOADING, values[0]?.status)
        assertEquals(StateData.DataStatus.ERROR, values[1]?.status)

        viewModel.weatherLiveData.removeObserver(weatherDataObserver)

    }


}