package com.example.birthdayapp.utils

object Constants {
    object Time {
        const val SECOND = 1000L
        const val MINUTE = 60 * SECOND
    }

    object Network {
        const val TIME_OUT = 10 * Time.SECOND
        const val PING_INTERVAL = 5 * Time.SECOND
        const val BIRTHDAY_PATH = "/nanit"
        const val SOCKET_MSG = "HappyBirthDay"
    }
}