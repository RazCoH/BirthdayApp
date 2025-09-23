package com.example.birthdayapp.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.example.birthdayapp.presentation.navigation.AppNavigation
import com.example.birthdayapp.presentation.ui.theme.BirthdayAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BirthdayAppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = getStatusBarHeight(), bottom = getNavigationBarHeight())
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun getNavigationBarHeight(): Dp {
    val insets = WindowInsets.navigationBars
    val density = LocalDensity.current
    return with(density) { insets.getBottom(density).toDp() }
}

@Composable
fun getStatusBarHeight(): Dp {
    val insets = WindowInsets.statusBars
    val density = LocalDensity.current
    return with(density) { insets.getTop(density).toDp() }
}