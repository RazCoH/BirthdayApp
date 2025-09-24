package com.example.birthdayapp.utils

sealed class Error (open val message: String = Constants.Errors.GENERAL_ERROR) {
    data object NoConnectionError : Error(Constants.Errors.ERROR_NO_CONNECTION)
    data object GeneralError : Error()
    data object MissingAgeError : Error(Constants.Errors.NO_DATE_IP)
    data class Exception(override val message: String) : Error(message)
    data object InvalidIP : Error(Constants.Errors.ERROR_INVALID_IP)
}