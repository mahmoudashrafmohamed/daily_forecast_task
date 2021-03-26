package com.mahmoud_ashraf.dailyforecastapplication.base

import com.mahmoud_ashraf.data.exceptions.ForecastCustomException

class StateData<T> {

    var status: DataStatus?= null
        private set

    var data: T? = null
        private set

    var error: Throwable? = null
        private set

    fun loading(): StateData<T> {
        status = DataStatus.LOADING
        data = null
        error = null
        return this
    }

    fun success(data: T): StateData<T> {
        status = DataStatus.SUCCESS
        this.data = data
        error = null
        return this
    }

    fun warning(data: T): StateData<T> {
        status = DataStatus.WARNING
        this.data = data
        this.error = null
        return this
    }

    fun error(error: Throwable?): StateData<T> {
        status = DataStatus.ERROR
        data = null
        this.error = error
        return this
    }


    enum class DataStatus {
    SUCCESS, ERROR, LOADING, WARNING
    }


}