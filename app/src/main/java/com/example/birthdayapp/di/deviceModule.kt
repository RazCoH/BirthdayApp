package com.example.birthdayapp.di

import com.example.birthdayapp.utils.DeviceInfoProvider
import org.koin.dsl.module

val deviceModule = module {
    factory { DeviceInfoProvider() }
}