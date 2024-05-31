package com.SportEventsApp.features.login
import com.SportEventsApp.cache.InMemoryCache
import com.SportEventsApp.cache.TokenCache
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import io.ktor.server.application.*
import io.ktor.server.request.*
import java.util.UUID

fun Application.configureLoginRouting() {
    routing {

        post("/login") {
            val loginController = LoginController(call)
            loginController.performLogin()
        }
        post("/checkToken") {
            val loginController = LoginController(call)
            loginController.checkToken()
        }
    }
}