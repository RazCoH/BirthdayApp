package com.example.birthdayapp.presentation.screens.birthday

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.birthdayapp.data.models.BirthdayItem
import com.example.birthdayapp.data.models.SocketResult
import com.example.birthdayapp.presentation.dialogs.ErrorDialog
import com.example.birthdayapp.utils.Constants
import com.example.birthdayapp.utils.Error
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BirthDayScreen(vm: BirthDayScreenVM = koinViewModel(), hostIP: String) {
    val state by vm.birthdayState.collectAsState()


    when (val s = state) {
        is SocketResult.Failure -> ShowErrorDialog(s.error.message)


        is SocketResult.Success<*> -> {
            (s.result as? BirthdayItem)?.let {

            } ?: run {
                ShowErrorDialog(Error.GeneralError.message)
            }
        }

        null -> {}
    }

    vm.observeBirthdayUpdates(host = hostIP)
}

@Composable
fun ShowErrorDialog(message: String) {
    var showDialog by remember { mutableStateOf(true) }
    if (showDialog) {
        ErrorDialog(
            Constants.Strings.ERROR_DIALOG_TITLE_TXT,
            message,
            Constants.Strings.ERROR_DIALOG_BTN_TXT
        ) {
            showDialog = false
        }
    }
}