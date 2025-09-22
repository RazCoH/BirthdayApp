package com.example.birthdayapp.data.repositories

import com.example.birthdayapp.data.SocketClient
import com.example.birthdayapp.data.models.BirthdayItem
import com.example.birthdayapp.data.models.SocketResult
import com.example.birthdayapp.utils.Constants
import kotlinx.coroutines.flow.Flow

class BirthdayRepository(private val socketClient: SocketClient) {

    fun observeBirthdaySocket(
        host: String,
    ): Flow<SocketResult> {

        return socketClient.observeSocket<BirthdayItem>(
            host = host,
            path = Constants.Network.BIRTHDAY_PATH
        )
    }
}