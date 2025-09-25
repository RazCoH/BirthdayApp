package com.example.birthdayapp.presentation.bottom_sheets.take_photo_bottom_sheet

import android.content.Context
import android.net.Uri

sealed interface TakePhotoIntents {
    data class OnPermissionGrantedWith(val compositionContext: Context) : TakePhotoIntents
    data object OnPermissionDenied : TakePhotoIntents
    data class OnImageSavedWith(val compositionContext: Context) : TakePhotoIntents
    data object OnImageSavingCanceled : TakePhotoIntents
    data class OnFinishPickingImagesWith(
        val compositionContext: Context,
        val imageUri: Uri
    ) : TakePhotoIntents
}