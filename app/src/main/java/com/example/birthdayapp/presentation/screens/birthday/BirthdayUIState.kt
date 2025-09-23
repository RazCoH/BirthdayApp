package com.example.birthdayapp.presentation.screens.birthday

import com.example.birthdayapp.data.models.BirthdayItem
import com.example.birthdayapp.utils.Error

sealed interface BirthdayUIState {
    data object Loading : BirthdayUIState
    data class ShowError(val error: Error) : BirthdayUIState
    data class ShowBirthdayUI(val data: BirthdayItem): BirthdayUIState
}