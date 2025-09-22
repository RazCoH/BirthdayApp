package com.example.birthdayapp.di

import com.example.birthdayapp.data.SocketClient
import org.koin.dsl.module

val networkModule = module {
    single { SocketClient(get()) }
}