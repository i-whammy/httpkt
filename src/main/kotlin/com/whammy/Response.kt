package com.whammy

import java.io.OutputStream
import java.nio.charset.StandardCharsets
import java.util.*

class Response(
    private val status: Int,
    private val mime: String,
    private val path: ByteArray
) {
    private val CRLF = "\r\n"
    fun write(output: OutputStream) {
        val response = "HTTP/1.1 " + status + CRLF +
                "Date: " + Date() + CRLF +
                "Server: SimpleKotlinHttpServer" + CRLF +
                "Content-Type: " + mime + CRLF +
                "Content-Length: " + path.size + CRLF +
                "Connection: Close" + CRLF +
                CRLF
        output.write(response.toByteArray(StandardCharsets.UTF_8))
        output.write(path)
        output.flush()
    }}