package com.whammy

import java.net.ServerSocket

class SimpleHttpKotlinServer {
    fun run() {
        val port = 8000
        val serverSocket = ServerSocket(port)

        while (true) {
            val socket = serverSocket.accept()
            WorkerThread(socket, RequestHandler()).start()
        }
    }
}