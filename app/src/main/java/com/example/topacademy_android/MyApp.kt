package com.example.topacademy_android

import android.app.Application
import com.example.topacademy_android.di.appModule
import com.example.topacademy_android.di.dataModule
import com.example.topacademy_android.di.domainModule
import com.example.topacademy_android.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApp)
            modules(appModule, dataModule, domainModule, viewModelModule)
        }
    }
}