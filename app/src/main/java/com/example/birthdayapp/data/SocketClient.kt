package com.example.birthdayapp.data

import com.example.birthdayapp.data.models.SocketResult
import com.example.birthdayapp.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class SocketClient {

    val client = HttpClient(CIO) {
        install(WebSockets) {
            pingIntervalMillis = Constants.Network.PING_INTERVAL
        }
    }


    inline fun <reified T> observeSocket(
        httpMethod: HttpMethod = HttpMethod.Get,
        host:String,
        port:Int,
        path: String
    ): Flow<SocketResult> = flow {
        client.webSocket(
            method = httpMethod,
            host = host,
            port = port,
            path = path
        ) {
            send(Frame.Text(Constants.Network.SOCKET_MSG))
            val frame = incoming.receive()
            if (frame is Frame.Text) {
                val text = frame.readText()
                try {
                    val parsed = Json.decodeFromString<T>(text)
                    emit(SocketResult.Success(parsed))
                } catch (e: Exception) {
                    emit(SocketResult.Failure(e))
                }
            }
            close()
        }
    }
}