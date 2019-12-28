package com.whammy

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket

class SimpleHttpKotlinServer {
    fun run() {
        val port = 8000
        val serverSocket = ServerSocket(port)

        while (true) {
            val socket = serverSocket.accept()
            val inputStream = socket.getInputStream()
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val line = bufferedReader.readLine()
            val outputStream = socket.getOutputStream()
            outputStream.write(line.toByteArray())
            outputStream.flush()
            inputStream.close()
            outputStream.close()
        }
    }
}