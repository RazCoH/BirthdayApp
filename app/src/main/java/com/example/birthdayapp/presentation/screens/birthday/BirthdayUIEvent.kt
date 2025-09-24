package com.example.birthdayapp.presentation.screens.birthday

import com.example.birthdayapp.utils.Error

sealed interface BirthdayUIEvent {
    data class ShowError(val error: Error) : BirthdayUIEvent
}