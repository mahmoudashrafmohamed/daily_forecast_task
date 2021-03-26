package com.mahmoud_ashraf.domain.di


import com.mahmoud_ashraf.domain.repositories.DailyWeatherRepository
import com.mahmoud_ashraf.domain.usecases.GetWeatherByCityNameUseCase
import org.koin.dsl.module


val usecaseModule = module {

    single {
        provideGetNotesUseCase(
            get()
        )
    }


}

fun provideGetNotesUseCase(weatherRepository: DailyWeatherRepository): GetWeatherByCityNameUseCase {
    return GetWeatherByCityNameUseCase(weatherRepository)
}
