package com.example.birthdayapp.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BirthdayItem(
    val name: String? = "",
    private val dob: Long? = null,
    val theme: BirthDayTheme? = null
)

@Serializable
enum class BirthDayTheme {
    @SerialName("pelican")
    PELICAN,
    @SerialName("fox")
    FOX,
    @SerialName("elephant")
    ELEPHANT
}
