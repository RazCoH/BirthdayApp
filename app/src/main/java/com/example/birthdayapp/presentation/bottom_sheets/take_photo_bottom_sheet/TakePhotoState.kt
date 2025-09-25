package com.example.birthdayapp.presentation.bottom_sheets.take_photo_bottom_sheet

import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap


data class TakePhotoState(
    /**
     * holds the URL of the temporary file which stores the image taken by the camera.
     */
    val tempFileUrl: Uri? = null,

    /**
     * holds the image taken by camera or selected pictures from the gallery.
     */
    val selectedPicture: ImageBitmap? = null,
)
