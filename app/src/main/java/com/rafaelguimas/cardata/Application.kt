package com.rafaelguimas.cardata

import android.app.Application
import com.rafaelguimas.cardata.di.useCaseModule
import com.rafaelguimas.cardata.di.viewModelModule
import com.rafaelguimas.data.di.carTypeModule
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            listOf(
                viewModelModule,
                useCaseModule,
                carTypeModule
            )
        }
    }
}