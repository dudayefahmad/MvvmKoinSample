package com.example.mvvmkoinsample

import android.app.Application
import com.example.mvvmkoinsample.di.appModule
import com.example.mvvmkoinsample.di.networkModule
import com.example.mvvmkoinsample.di.repositoryModule
import com.example.mvvmkoinsample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppSample : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppSample)
            modules(arrayListOf(appModule, viewModelModule, repositoryModule, networkModule))
        }
    }
}