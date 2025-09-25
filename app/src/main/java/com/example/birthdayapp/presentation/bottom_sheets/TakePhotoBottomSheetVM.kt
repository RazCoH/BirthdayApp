package com.example.birthdayapp.presentation.bottom_sheets

import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.birthdayapp.utils.Error
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.File

class TakePhotoBottomSheetVM : ViewModel() {

    private val _takePhotoState: MutableStateFlow<TakePhotoState> = MutableStateFlow(TakePhotoState())

    val takePhotoState: StateFlow<TakePhotoState>
        get() = _takePhotoState

    private val _takePhotoEvents = MutableSharedFlow<TakePhotoEvent>()
    val takePhotoEvents = _takePhotoEvents.asSharedFlow()

    fun onReceive(intent: TakePhotoIntents) = viewModelScope.launch {
        when (intent) {
            is TakePhotoIntents.OnPermissionGrantedWith -> onPermissionGrantedWith(intent)

            is TakePhotoIntents.OnPermissionDenied -> {}

            is TakePhotoIntents.OnFinishPickingImagesWith -> onFinishPickingImagesWith(intent)

            is TakePhotoIntents.OnImageSavedWith -> onImageSavedWith(intent)

            is TakePhotoIntents.OnImageSavingCanceled -> {
                _takePhotoState.value = TakePhotoState()
            }
        }
    }

    private fun onPermissionGrantedWith(intent: TakePhotoIntents.OnPermissionGrantedWith){
        // Create an empty image file in the app's cache directory
        val tempFile = File.createTempFile(
            "temp_image_file_", /* prefix */
            ".jpg", /* suffix */
            intent.compositionContext.cacheDir  /* cache directory */
        )

        // Create sandboxed url for this temp file - needed for the camera API
        val uri = FileProvider.getUriForFile(intent.compositionContext,
            "${intent.compositionContext.packageName}.provider",
            tempFile
        )
        _takePhotoState.value = TakePhotoState(tempFileUrl = uri)
    }

    private suspend fun onFinishPickingImagesWith(intent: TakePhotoIntents.OnFinishPickingImagesWith) {
        val inputStream = intent.compositionContext.contentResolver.openInputStream(intent.imageUri)
        val bytes = inputStream?.readBytes()
        inputStream?.close()

        if (bytes != null) {
            val bitmapOptions = BitmapFactory.Options()
            bitmapOptions.inMutable = true
            val selectedImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.size, bitmapOptions)
            _takePhotoState.value = TakePhotoState(
                selectedPicture = selectedImage?.asImageBitmap(),
                tempFileUrl = null
            )
        } else {
            _takePhotoEvents.emit(TakePhotoEvent.ShowError(Error.LoadingImageFailed))
        }
    }

    private fun onImageSavedWith(intent: TakePhotoIntents.OnImageSavedWith) {
        val tempImageUrl = _takePhotoState.value.tempFileUrl
        if (tempImageUrl != null) {
            val source =
                ImageDecoder.createSource(intent.compositionContext.contentResolver, tempImageUrl)
            _takePhotoState.value = TakePhotoState(
                tempFileUrl = null,
                selectedPicture = ImageDecoder.decodeBitmap(source).asImageBitmap()
            )
        }
    }
}