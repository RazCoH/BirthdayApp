package com.example.birthdayapp.data.models

import com.example.birthdayapp.R
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BirthdayItem(
    val name: String = "",
    private val dob: Long? = null,
    val theme: BirthDayTheme = BirthDayTheme.PELICAN
) {

}

@Serializable
enum class BirthDayTheme {
    @SerialName("pelican")
    PELICAN,

    @SerialName("fox")
    FOX,

    @SerialName("elephant")
    ELEPHANT;

    val backgroundImage: Int
        get() = when (this) {
            PELICAN -> R.drawable.bg_pelican
            FOX -> R.drawable.bg_fox
            ELEPHANT -> R.drawable.bg_elephant
        }

    val profileImagePlaceHolder: Int
        get() = when(this){
            PELICAN -> R.drawable.ic_placeholder_blue
            FOX -> R.drawable.ic_image_placeholder_green
            ELEPHANT -> R.drawable.ic_image_placeholder_yellow
        }

    val cameraIcon: Int
        get() = when(this){
            PELICAN -> R.drawable.ic_camera_blue
            FOX -> R.drawable.ic_camera_green
            ELEPHANT -> R.drawable.ic_camera_yellow
        }
}
