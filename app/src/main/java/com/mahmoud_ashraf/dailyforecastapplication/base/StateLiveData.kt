package com.mahmoud_ashraf.dailyforecastapplication.base

import androidx.lifecycle.MutableLiveData

class StateLiveData<T> : MutableLiveData<StateData<T>?>() {

    fun postLoading() {
        value = StateData<T>().loading()
    }

    fun postSuccess(data: T) {
        value = StateData<T>().success(data)
    }

    fun postWarning(data: T) {
        value = StateData<T>().warning(data)
    }

    fun postError(throwable: Throwable?) {
        value = StateData<T>().error(throwable)
    }




}