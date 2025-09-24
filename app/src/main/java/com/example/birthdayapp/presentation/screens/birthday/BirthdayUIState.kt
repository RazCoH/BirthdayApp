package com.example.birthdayapp.presentation.screens.birthday

import com.example.birthdayapp.data.models.BirthdayItem

sealed interface BirthdayUIState {
    data object Loading : BirthdayUIState
    data class ShowBirthdayUI(val data: BirthdayItem): BirthdayUIState
}