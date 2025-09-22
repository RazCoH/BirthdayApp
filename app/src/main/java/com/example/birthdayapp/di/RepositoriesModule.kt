package com.example.birthdayapp.di

import com.example.birthdayapp.data.repositories.BirthdayRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single { BirthdayRepository(get()) }
}