package com.example.birthdayapp.di

import com.example.birthdayapp.presentation.screens.birthday.BirthDayScreenVM
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val viewModelsModule = module {
    viewModel { BirthDayScreenVM(get()) }
}