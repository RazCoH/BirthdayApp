package com.example.birthdayapp.di

import com.example.birthdayapp.presentation.screens.birthday.BirthDayScreenVM
import com.example.birthdayapp.presentation.screens.ip_details.IPDetailsScreenVM
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val viewModelsModule = module {
    viewModel { (hostIp: String) -> BirthDayScreenVM(get(),hostIp) }
    viewModel { IPDetailsScreenVM(get()) }
}