package tiagoarauo00.dev.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import tiagoarauo00.dev.Models.Priority
import tiagoarauo00.dev.Models.TaskRepository
import tiagoarauo00.dev.routes.taskRoutes

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello, Ktor!")
        }
        staticResources("/static", "static")
        taskRoutes()
    }
}
