package com.example.birthdayapp.data.models

import androidx.compose.ui.graphics.Color
import com.example.birthdayapp.R
import com.example.birthdayapp.presentation.ui.theme.ElephantYellow
import com.example.birthdayapp.presentation.ui.theme.FoxGreen
import com.example.birthdayapp.presentation.ui.theme.PelicanBlue
import com.example.birthdayapp.utils.Constants.Strings.BD_MONTHS_TXT
import com.example.birthdayapp.utils.Constants.Strings.BD_YEARS_TXT
import com.example.birthdayapp.utils.extensions.calculateBabyAgeInMonths
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BirthdayItem(
    val name: String = "",
    private val dob: Long? = null,
    val theme: BirthDayTheme = BirthDayTheme.PELICAN
) {

    fun getAge(): Age? {
        getAgeType()?.let { ageType ->
            when (ageType) {
                is AgeType.Months -> {
                    val asset: Int? = when (ageType.value) {
                        0 -> R.drawable.ic_zero
                        1 -> R.drawable.ic_one
                        2 -> R.drawable.ic_two
                        3 -> R.drawable.ic_three
                        4 -> R.drawable.ic_four
                        5 -> R.drawable.ic_five
                        6 -> R.drawable.ic_six
                        7 -> R.drawable.ic_seven
                        8 -> R.drawable.ic_eight
                        9 -> R.drawable.ic_nine
                        10 -> R.drawable.ic_ten
                        11 -> R.drawable.ic_eleven
                        12 -> R.drawable.ic_twelve
                        else -> null
                    }
                    asset?.let {
                        return Age(BD_MONTHS_TXT, it)
                    }
                }

                is AgeType.Years -> {
                    val asset: Int? = when (ageType.value) {
                        1 -> R.drawable.ic_one
                        2 -> R.drawable.ic_two
                        3 -> R.drawable.ic_three
                        4 -> R.drawable.ic_four
                        5 -> R.drawable.ic_five
                        6 -> R.drawable.ic_six
                        7 -> R.drawable.ic_seven
                        8 -> R.drawable.ic_eight
                        9 -> R.drawable.ic_nine
                        else -> null
                    }
                    asset?.let {
                        return Age(BD_YEARS_TXT, it)
                    }
                }
            }
        }
        return null
    }

    private fun getAgeType(): AgeType? {
        dob?.calculateBabyAgeInMonths()?.let { ageImMonths ->
            return if (ageImMonths >= 0 && ageImMonths <= 12) {
                AgeType.Months(ageImMonths)
            } else {
                AgeType.Years(ageImMonths / 12)
            }
        }
        return null
    }
}

data class Age(val ageLabel: String, val ageAsset: Int)

sealed class AgeType(open val value: Int) {
    data class Months(override val value: Int) : AgeType(value)
    data class Years(override val value: Int) : AgeType(value)
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
        get() = when (this) {
            PELICAN -> R.drawable.ic_placeholder_blue
            FOX -> R.drawable.ic_image_placeholder_green
            ELEPHANT -> R.drawable.ic_image_placeholder_yellow
        }

    val cameraIcon: Int
        get() = when (this) {
            PELICAN -> R.drawable.ic_camera_blue
            FOX -> R.drawable.ic_camera_green
            ELEPHANT -> R.drawable.ic_camera_yellow
        }

    val backgroundColor: Color
        get() = when (this) {
            PELICAN -> PelicanBlue
            FOX -> FoxGreen
            ELEPHANT -> ElephantYellow
        }
}
