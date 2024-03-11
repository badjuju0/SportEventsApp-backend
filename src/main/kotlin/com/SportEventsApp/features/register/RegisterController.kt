package com.SportEventsApp.features.register

import com.SportEventsApp.cache.InMemoryCache
import com.SportEventsApp.cache.TokenCache
import com.SportEventsApp.database.tokens.TokenDTO
import com.SportEventsApp.database.tokens.Tokens
import com.SportEventsApp.database.users.UserDTO
import com.SportEventsApp.database.users.Users
import com.SportEventsApp.utils.isValidEmail
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.util.*

class RegisterController(private val call:ApplicationCall) {
    suspend  fun registerNewUser(){
        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()
        if (!registerReceiveRemote.email.isValidEmail(registerReceiveRemote.email)){
            call.respond(HttpStatusCode.BadRequest, "Email  is nor valid")
        }
        val userDTO = Users.fetchUser(registerReceiveRemote.email)

        if (userDTO != null){
            call.respond(HttpStatusCode.Conflict, "user already exists")
        } else{
            val token = UUID.randomUUID().toString()

            try {
                Users.insert(
                    UserDTO(
                        email = registerReceiveRemote.email,
                        firstName = registerReceiveRemote.firstName,
                        secondName = registerReceiveRemote.secondName,
                        password = registerReceiveRemote.password
                    )
                )
            }catch (e:ExposedSQLException){
                call.respond(HttpStatusCode.Conflict, "user already exists")
            }


            Tokens.insert(TokenDTO(rowId = UUID.randomUUID().toString(), email = registerReceiveRemote.email,
                token = token
                )
            )
            call.respond(RegisterResponseRemote(token = token))
        }

    }
}