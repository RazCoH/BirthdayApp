package com.example.birthdayapp.presentation.bottom_sheets.take_photo_bottom_sheet

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.birthdayapp.R
import com.example.birthdayapp.utils.Constants.Strings.CHOOSE_OPTION_TXT
import com.example.birthdayapp.utils.Constants.Strings.PICK_FROM_GALLERY_TXT
import com.example.birthdayapp.utils.Constants.Strings.TAKE_PHOTO_TXT
import com.example.birthdayapp.utils.Error
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TakePhotoBottomSheet(
    vm: TakePhotoBottomSheetVM = koinViewModel(),
    onPickImage: (ImageBitmap) -> Unit,
    onError: (Error) -> Unit,
    onDismiss: () -> Unit
) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val currentContext = LocalContext.current

    val viewState: TakePhotoState by vm.takePhotoState.collectAsStateWithLifecycle()

    viewState.selectedPicture?.let {
        onPickImage(it)
        vm.resetStateTakePhoto()
        onDismiss.invoke()
    }

    // launches photo picker
    val pickImageFromAlbumLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                vm.onReceive(TakePhotoIntents.OnFinishPickingImagesWith(currentContext, it))
            }
        }

    // launches camera
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { isImageSaved ->
            if (isImageSaved) {
                vm.onReceive(TakePhotoIntents.OnImageSavedWith(currentContext))
            } else {
                vm.onReceive(TakePhotoIntents.OnImageSavingCanceled)
            }
        }

    // launches camera permissions
    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { permissionGranted ->
            if (permissionGranted) {
                vm.onReceive(TakePhotoIntents.OnPermissionGrantedWith(currentContext))
            } else {
                vm.onReceive(TakePhotoIntents.OnPermissionDenied)
            }
        }

    LaunchedEffect(key1 = viewState.tempFileUrl) {
        viewState.tempFileUrl?.let {
            cameraLauncher.launch(it)
        }
    }

    LaunchedEffect(Unit) {
        vm.takePhotoEvents.collectLatest {
            when (val event = it) {
                is TakePhotoEvent.ShowError -> {
                    onError.invoke(event.error)
                }
            }
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = CHOOSE_OPTION_TXT,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            )

            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = "Camera"
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = TAKE_PHOTO_TXT,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        pickImageFromAlbumLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_gallery),
                    contentDescription = "Gallery"
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = PICK_FROM_GALLERY_TXT,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}