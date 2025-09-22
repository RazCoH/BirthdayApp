package com.example.birthdayapp.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.birthdayapp.presentation.navigation.AppNavigation
import com.example.birthdayapp.presentation.screens.birthday.BirthDayScreen
import com.example.birthdayapp.presentation.screens.ip_details.IPDetailsScreen
import com.example.birthdayapp.presentation.ui.theme.BirthdayAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BirthdayAppTheme {
                AppNavigation()
            }
        }
    }
}