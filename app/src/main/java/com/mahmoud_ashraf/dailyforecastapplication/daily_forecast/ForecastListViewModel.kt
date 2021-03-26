package com.mahmoud_ashraf.dailyforecastapplication.daily_forecast

import com.mahmoud_ashraf.dailyforecastapplication.base.BaseViewModel
import com.mahmoud_ashraf.dailyforecastapplication.base.StateLiveData
import com.mahmoud_ashraf.data.exceptions.ForecastCustomException
import com.mahmoud_ashraf.domain.models.DailyWeather
import com.mahmoud_ashraf.domain.usecases.GetWeatherByCityNameUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class ForecastListViewModel(private val getWeatherByCityNameUseCase: GetWeatherByCityNameUseCase) :
    BaseViewModel() {

    private val _weatherLiveData by lazy { StateLiveData<List<DailyWeather>>() }
    val weatherLiveData = _weatherLiveData

    fun getWeatherByCityName(cityName: String) {
        getWeatherByCityNameUseCase(cityName, cnt = "16")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _weatherLiveData.postLoading() }
            .subscribe({
                _weatherLiveData.postSuccess(it)
            }, { throwable ->
                when (throwable) {
                    is ForecastCustomException -> {
                        if ((throwable.data as List<*>?).isNullOrEmpty().not())
                            _weatherLiveData.postWarning((throwable.data as List<DailyWeather>))
                        else _weatherLiveData.postError(throwable)

                    }
                    else -> _weatherLiveData.postError(throwable)
                }

            }).addTo(compositeDisposable)
    }


}