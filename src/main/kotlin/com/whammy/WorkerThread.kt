package com.whammy

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket

class WorkerThread(private val socket: Socket, private val handler: RequestHandler) : Thread() {
    override fun run () {
        socket.getInputStream().use { inputStream ->
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val (method, path, version) = bufferedReader.readLine().split(" ")
            val request = Request(method, path, version)
            socket.getOutputStream().use { outputStream ->
                handler.handleRequest(request).write(outputStream)
            }
        }
    }
}