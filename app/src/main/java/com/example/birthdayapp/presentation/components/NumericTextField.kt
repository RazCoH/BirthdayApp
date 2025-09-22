package com.example.birthdayapp.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import io.ktor.websocket.Frame

@Composable
fun NumericTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = ""
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        label = { Frame.Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}
