package com.example.birthdayapp.data.models

import com.example.birthdayapp.utils.Error

sealed interface SocketResult {
    data class Success<T>(val result: T) : SocketResult
    data class Failure(val error: Error) : SocketResult
}