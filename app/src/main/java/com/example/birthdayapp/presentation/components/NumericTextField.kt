package com.example.birthdayapp.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun NumericTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholderText: String = "",
    maxLength: Int? = null,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = { newValue ->
            maxLength?.let {
                if (newValue.length <= maxLength) {
                    onValueChange(newValue)
                }
            } ?: run {
                onValueChange(newValue)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        placeholder = { Text(placeholderText) },
        singleLine = true,
        maxLines = 1
    )
}
