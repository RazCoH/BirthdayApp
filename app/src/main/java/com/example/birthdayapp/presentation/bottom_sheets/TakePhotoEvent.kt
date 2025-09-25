package com.example.birthdayapp.presentation.bottom_sheets

import com.example.birthdayapp.utils.Error

sealed interface TakePhotoEvent {
    data class ShowError(val error: Error) : TakePhotoEvent
}