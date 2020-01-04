package com.whammy

import java.nio.file.Files
import java.nio.file.Paths

class Request(val method: String, val path: String, val version: String)

class RequestHandler {

    private val contentType = "text/html;charset=utf8"

    fun handleRequest(request: Request): Response {
        val path = Paths.get("public", request.path)
        println(path.toAbsolutePath().toString())
        if (!path.startsWith("public/")) {
            return Response(403, contentType, Files.readAllBytes(Paths.get("public", "403.html")))
        }
        else if (Files.isRegularFile(path) && Files.exists(path)) {
            return Response(200, contentType, Files.readAllBytes(path))
        }
        else if (Files.isDirectory(path) && Files.exists(path.resolve("index.html"))) {
            return Response(200, contentType, Files.readAllBytes(Paths.get("public", "index.html")))
        }
        return Response(404, contentType, Files.readAllBytes(Paths.get("public", "404.html")))
    }
}