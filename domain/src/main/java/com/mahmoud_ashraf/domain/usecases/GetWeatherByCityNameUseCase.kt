package com.mahmoud_ashraf.domain.usecases

import com.mahmoud_ashraf.domain.repositories.DailyWeatherRepository

class GetWeatherByCityNameUseCase(private val dailyWeatherRepository: DailyWeatherRepository) {
    operator fun invoke(cityName: String, cnt: String) =
        dailyWeatherRepository.getDailyWeather(cityName, cnt)
}