package com.example.birthdayapp.data.models

sealed interface SocketResult {
    data class Success<T>(val result: T) : SocketResult
    data class Failure(val exception: Exception) : SocketResult
}