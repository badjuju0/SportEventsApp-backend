package com.SportEventsApp.features.getNames

import com.SportEventsApp.features.login.LoginController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureGetNamesRouting() {
    routing {

        get("/getNames") {
            val getNamesController = GetNamesController(call)
            getNamesController.performGetNames()



        }
    }
}