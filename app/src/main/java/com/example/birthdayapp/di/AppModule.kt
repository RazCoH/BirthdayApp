package com.example.birthdayapp.di

import com.example.birthdayapp.MainApplication
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single { androidApplication() as? MainApplication }
}