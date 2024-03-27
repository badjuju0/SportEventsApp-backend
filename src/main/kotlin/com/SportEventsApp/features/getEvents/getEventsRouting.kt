package com.SportEventsApp.features.getEvents

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureGetNamesRouting() {
    routing {

        get("/getEvents") {
            val getEventsController = GetEventsController(call)
            getEventsController.performGetEvents()



        }
    }
}