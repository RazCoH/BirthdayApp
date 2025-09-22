package com.example.birthdayapp.presentation.screens.ip_details

import androidx.lifecycle.ViewModel
import com.example.birthdayapp.utils.DeviceInfoProvider
import com.example.birthdayapp.utils.Error
import com.example.birthdayapp.utils.isValidIPAddress
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class IPDetailsScreenVM(private val deviceInfoProvider: DeviceInfoProvider) : ViewModel() {

    private val _ipDetailsUIState = MutableStateFlow<IPDetailsUIState?>(null)
    val ipDetailsUIState: StateFlow<IPDetailsUIState?> = _ipDetailsUIState

    fun checkValidation(ipNumber: String) {
        if (ipNumber.isValidIPAddress()){
            _ipDetailsUIState.value = IPDetailsUIState.NavigateToNextScreen(ipNumber)
        }else{
            _ipDetailsUIState.value = IPDetailsUIState.ShowError(Error.InvalidIP)
        }
    }

    fun getNetworkDetailsProgrammatically() {
        deviceInfoProvider.getLocalIpAddress()?.let {
            _ipDetailsUIState.value = IPDetailsUIState.NavigateToNextScreen(it)
        }?:run {
            _ipDetailsUIState.value = IPDetailsUIState.ShowError(Error.GeneralError)
        }
    }

}