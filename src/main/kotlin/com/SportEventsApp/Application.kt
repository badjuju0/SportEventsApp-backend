package com.SportEventsApp

import com.SportEventsApp.features.createApplication.configureApplicationRouting
import com.SportEventsApp.features.createEvent.configureEventCreateRouting
import com.SportEventsApp.features.getEvents.configureGetEventsRouting

import com.SportEventsApp.features.login.configureLoginRouting
import com.SportEventsApp.features.register.configureRegisterRouting
import com.SportEventsApp.plugins.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database

fun main() {
   Database.connect(
        url = System.getenv("DB_URL"),
        driver = "org.postgresql.Driver",
        user = System.getenv("DB_USER"),
        password = System.getenv("DB_PASSWORD")
    )
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureRegisterRouting()
    configureLoginRouting()
    configureSerialization()
    configureGetEventsRouting()
    configureEventCreateRouting()
    configureApplicationRouting()
}
