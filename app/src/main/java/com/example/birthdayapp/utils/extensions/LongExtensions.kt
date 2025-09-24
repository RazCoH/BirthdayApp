package com.example.birthdayapp.utils.extensions

import kotlinx.datetime.*
import kotlinx.datetime.number
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant.Companion.fromEpochMilliseconds


@OptIn(ExperimentalTime::class)
fun Long.calculateBabyAgeInMonths(): Int {
    val birthDate = fromEpochMilliseconds(this)
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date

    val currentDate = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date

    // Calculate months manually since periodUntil returns DatePeriod
    val years = currentDate.year - birthDate.year
    var months = currentDate.month.number - birthDate.month.number

    // Adjust if current day is before birth day
    if (currentDate.day < birthDate.day) {
        months--
    }

    return years * 12 + months
}
