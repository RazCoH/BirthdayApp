package com.example.birthdayapp.presentation.screens.ip_details

import com.example.birthdayapp.utils.Error

sealed interface IPDetailsEvent {
    data class ShowError(val error: Error, val ts:Long) : IPDetailsEvent
    data class NavigateToNextScreen(val hostIP: String) : IPDetailsEvent
}