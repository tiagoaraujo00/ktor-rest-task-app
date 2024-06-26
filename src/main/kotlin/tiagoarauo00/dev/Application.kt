package tiagoarauo00.dev

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import tiagoarauo00.dev.plugins.*

fun main() {
    embeddedServer(Netty, port = 9191, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}
