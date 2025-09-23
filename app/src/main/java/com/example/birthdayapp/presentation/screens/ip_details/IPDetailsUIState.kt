package com.example.birthdayapp.presentation.screens.ip_details

import com.example.birthdayapp.utils.Error

sealed interface IPDetailsUIState {
    data class ShowError(val error: Error, val ts:Long) : IPDetailsUIState
    data class NavigateToNextScreen(val hostIP: String) : IPDetailsUIState
}