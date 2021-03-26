package com.mahmoud_ashraf.dailyforecastapplication


import android.app.Application
import com.mahmoud_ashraf.dailyforecastapplication.di.viewModelModule
import com.mahmoud_ashraf.data.di.networkModule
import com.mahmoud_ashraf.data.di.repositoryModule
import com.mahmoud_ashraf.data.di.roomModule
import com.mahmoud_ashraf.domain.di.usecaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            // Koin logger
            androidLogger()
            // inject Android context
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    roomModule,
                    repositoryModule,
                    usecaseModule,
                    viewModelModule
                )
            )
        }
    }


}