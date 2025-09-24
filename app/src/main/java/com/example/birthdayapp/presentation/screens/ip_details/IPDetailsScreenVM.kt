package com.example.birthdayapp.presentation.screens.ip_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.birthdayapp.utils.DeviceInfoProvider
import com.example.birthdayapp.utils.Error
import com.example.birthdayapp.utils.extensions.isValidIPAddress
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class IPDetailsScreenVM(private val deviceInfoProvider: DeviceInfoProvider) : ViewModel() {

    private val _ipDetailsEvent = MutableSharedFlow<IPDetailsEvent>()
    val ipDetailsEvent = _ipDetailsEvent.asSharedFlow()

    fun checkValidation(ipNumber: String) {
        viewModelScope.launch {
            if (ipNumber.isValidIPAddress()) {
                _ipDetailsEvent.emit(IPDetailsEvent.NavigateToNextScreen(ipNumber))
            } else {
                _ipDetailsEvent.emit(
                    IPDetailsEvent.ShowError(
                        Error.InvalidIP,
                        System.currentTimeMillis()
                    )
                )
            }
        }
    }

    fun getNetworkDetailsProgrammatically() {
        viewModelScope.launch {
            deviceInfoProvider.getLocalIpAddress()?.let {
                _ipDetailsEvent.emit(IPDetailsEvent.NavigateToNextScreen(it))
            } ?: run {
                _ipDetailsEvent.emit(
                    IPDetailsEvent.ShowError(
                        Error.GeneralError,
                        System.currentTimeMillis()
                    )
                )
            }
        }
    }

}