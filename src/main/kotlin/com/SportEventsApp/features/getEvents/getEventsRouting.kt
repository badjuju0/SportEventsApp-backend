package com.SportEventsApp.features.getEvents

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureGetEventsRouting() {
    routing {

        get("/getEvents") {
            val getEventsController = GetEventsController(call)
            getEventsController.performGetEvents()

        }

        get("/getEvents/{title}") {
            val getEventsController = GetEventsController(call)
            getEventsController.performGetEvent()

        }
    }
}