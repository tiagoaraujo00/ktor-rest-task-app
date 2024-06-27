package tiagoarauo00.dev.routes

import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import tiagoarauo00.dev.Models.Priority
import tiagoarauo00.dev.Models.Task
import tiagoarauo00.dev.Models.TaskRepository

fun Route.taskRoutes() {
    route("/tasks") {
        get {
            val tasks = TaskRepository.getTasks()
            call.respond(tasks)
        }
        get("byName/{taskName}") {
            val taskName = call.parameters["taskName"]
            if (taskName == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }
            val task = TaskRepository.taskByName(taskName)
            if (task == null) {
                call.respond(HttpStatusCode.NotFound)
                return@get
            }
        }
        get("byPriority/{priority}") {
            val priority = call.parameters["priority"]
            if (priority == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }
            try {
                val priority = Priority.valueOf(priority)
                val tasks = TaskRepository.taskByPriority(priority)

                if (tasks.isEmpty()) {
                    call.respond(HttpStatusCode.NotFound)
                    return@get
                }
                call.respond(tasks)

            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
        post {
            try {
                val task = call.receive<Task>()
                TaskRepository.addTask(task)
                call.respond(HttpStatusCode.NoContent)
            } catch (ex: IllegalStateException) {
                call.respond(HttpStatusCode.BadRequest)
            } catch (ex: JsonConvertException) {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}