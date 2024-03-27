package com.SportEventsApp.features.createEvent

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureEventCreateRouting() {
    routing {

        post("/createEvent") {
            val eventCreateController = EventCreateController(call)
            eventCreateController.createNewEvent()



        }
    }
}