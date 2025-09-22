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
        const val PORT = 8080
    }

    object Errors {
        const val ERROR_NO_CONNECTION = "No Network connection, Please go to settings. "
        const val ERROR_INVALID_IP = "Invalid IP, please try again."
        const val GENERAL_ERROR = "Something went wrong\nPlease try again later"
    }

    object Strings {

        //Error Dialog
        const val ERROR_DIALOG_BTN_TXT = "Close"
        const val ERROR_DIALOG_TITLE_TXT = "Opps..."

        //IP Details Screen
        const val IP_DETAILS_TITLE_TXT = "Please fill the IP from Nanit server app."
        const val IP_DETAILS_IP_PLACEHOLDER_TXT = "IP"
        const val IP_DETAILS_CONTINUE_BTN_TXT = "Continue"
        const val IP_DETAILS_NO_DETAILS_BTN_TXT = "I see no IP in the Nanit server app"
    }
}