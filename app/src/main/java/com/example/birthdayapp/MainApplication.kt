package com.example.birthdayapp

import android.app.Application
import com.example.birthdayapp.di.appModule
import com.example.birthdayapp.di.deviceModule
import com.example.birthdayapp.di.networkModule
import com.example.birthdayapp.di.repositoriesModule
import com.example.birthdayapp.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(
                appModule,
                repositoriesModule,
                networkModule,
                viewModelsModule,
                deviceModule
            )
        }
    }
}