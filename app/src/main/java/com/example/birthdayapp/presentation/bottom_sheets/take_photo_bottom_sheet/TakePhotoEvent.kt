package com.example.birthdayapp.presentation.bottom_sheets.take_photo_bottom_sheet

import com.example.birthdayapp.utils.Error

sealed interface TakePhotoEvent {
    data class ShowError(val error: Error) : TakePhotoEvent
}