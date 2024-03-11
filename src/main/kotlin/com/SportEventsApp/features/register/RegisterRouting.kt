package com.SportEventsApp.features.register

import com.SportEventsApp.cache.InMemoryCache
import com.SportEventsApp.cache.TokenCache
import com.SportEventsApp.features.login.LoginReceiveRemote
import com.SportEventsApp.features.login.LoginResponseRemote
import com.SportEventsApp.utils.isValidEmail
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureRegisterRouting() {
    routing {

        post("/register") {
            val registerController = RegisterController(call)
            registerController.registerNewUser()



        }
    }
}