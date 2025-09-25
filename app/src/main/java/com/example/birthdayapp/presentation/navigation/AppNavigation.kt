package com.example.birthdayapp.presentation.navigation

import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.birthdayapp.presentation.activities.MainActivity
import com.example.birthdayapp.presentation.screens.birthday.BirthDayScreen
import com.example.birthdayapp.presentation.screens.ip_details.IPDetailsScreen

@Composable
fun AppNavigation(activity: MainActivity) {

    /* Define a backStack with entry value witch is the ip details screen */
    val backStack = rememberNavBackStack<Screen>(Screen.IPDetailsScreen)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Screen.IPDetailsScreen> {
                IPDetailsScreen { ip ->
                    backStack.removeLastOrNull()
                    backStack.add(Screen.BirthdayScreen(ip))
                }

            }
            entry<Screen.BirthdayScreen> {
                BirthDayScreen(hostIP = it.ip)
                /* Define activity as portrait when BirthDayScreen is open. */
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }
    )
}