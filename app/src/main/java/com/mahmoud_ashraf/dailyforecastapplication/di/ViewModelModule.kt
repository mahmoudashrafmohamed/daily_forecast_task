package com.mahmoud_ashraf.dailyforecastapplication.di

import com.mahmoud_ashraf.dailyforecastapplication.daily_forecast.ForecastListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ForecastListViewModel(get())
    }

}