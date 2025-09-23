package com.example.birthdayapp.presentation.screens.ip_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.birthdayapp.presentation.components.NumericTextField
import com.example.birthdayapp.presentation.dialogs.ErrorDialog
import com.example.birthdayapp.utils.Constants
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun IPDetailsScreen(vm: IPDetailsScreenVM = koinViewModel(), onContinue: (String) -> Unit) {
    var ipNumber by remember { mutableStateOf("") }
    var errorMessage:String? by remember { mutableStateOf(null) }
    val uiState by vm.ipDetailsUIState.collectAsState()


        when(val uiState = uiState){
            is IPDetailsUIState.NavigateToNextScreen -> {
                LaunchedEffect(Unit) {
                    onContinue.invoke(uiState.hostIP)
                }
            }
            is IPDetailsUIState.ShowError -> {
                LaunchedEffect(uiState.ts) {
                    errorMessage = uiState.error.message
                }
            }
            null -> {}
        }


    errorMessage?.let {
        ErrorDialog(
            Constants.Strings.ERROR_DIALOG_TITLE_TXT,
            it,
            Constants.Strings.ERROR_DIALOG_BTN_TXT
        ) {
            errorMessage = null
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 80.dp, 16.dp, 28.dp)
    ) {

        Text(
            text = Constants.Strings.IP_DETAILS_TITLE_TXT,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        NumericTextField(
            value = ipNumber,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { ipNumber = it },
            label = Constants.Strings.IP_DETAILS_IP_PLACEHOLDER_TXT
        )

        Spacer(modifier = Modifier.height(42.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { vm.checkValidation(ipNumber) }) {
            Text(Constants.Strings.IP_DETAILS_CONTINUE_BTN_TXT)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { vm.getNetworkDetailsProgrammatically() }) {
            Text(Constants.Strings.IP_DETAILS_NO_DETAILS_BTN_TXT)
        }

    }
}

