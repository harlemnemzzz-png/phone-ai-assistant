package com.phoneai.assistant.network

import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class SocketManager {
    private lateinit var socket: Socket
    var onConnectListener: (() -> Unit)? = null
    var onDisconnectListener: (() -> Unit)? = null

    fun connect(serverUrl: String) {
        try {
            socket = IO.socket(serverUrl)
            socket.on(Socket.EVENT_CONNECT) {
                onConnectListener?.invoke()
            }
            socket.on(Socket.EVENT_DISCONNECT) {
                onDisconnectListener?.invoke()
            }
            socket.connect()
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }

    fun setAlarm(time: String, label: String) {
        socket.emit("set_alarm", mapOf(
            "time" to time,
            "label" to label,
            "id" to System.currentTimeMillis()
        ))
    }

    fun getAlarms(callback: (List<String>) -> Unit) {
        socket.emit("get_alarms") { data ->
            callback(emptyList())
        }
    }

    fun disconnect() {
        if (::socket.isInitialized) {
            socket.disconnect()
        }
    }
}
