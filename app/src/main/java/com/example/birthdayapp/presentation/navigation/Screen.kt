package com.example.birthdayapp.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Screen : NavKey {
    @Serializable
    data class BirthdayScreen(val ip: String) : Screen

    @Serializable
    data object IPDetailsScreen : Screen
}