package com.example.birthdayapp.utils

fun String.isValidIPAddress(): Boolean {
    val parts = split(".")
    if (parts.size != 4) return false

    for (part in parts) {
        // Must be a number
        val num = part.toIntOrNull() ?: return false
        // Must be in 0..255
        if (num !in 0..255) return false
        // Prevent leading zeros like "01"
        if (part.length > 1 && part.startsWith("0")) return false
    }

    return true
}