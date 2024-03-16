package com.SportEventsApp.features.login

import com.SportEventsApp.cache.InMemoryCache
import com.SportEventsApp.cache.TokenCache
import com.SportEventsApp.database.tokens.TokenDTO
import com.SportEventsApp.database.tokens.Tokens
import com.SportEventsApp.database.users.Users
import com.SportEventsApp.features.getNames.NamesResponseRemote
import com.SportEventsApp.features.register.RegisterResponseRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class LoginController (private val call:ApplicationCall){
    suspend fun performLogin(){
        val receive = call.receive<LoginReceiveRemote>()
        val userDTO = Users.fetchUser(receive.email)

        if (userDTO==null){call.respond(HttpStatusCode.BadRequest, "user not found")}
        else{
            if (userDTO.password == receive.password){
                val token = UUID.randomUUID().toString()
                Tokens.insert(
                    TokenDTO(rowId = UUID.randomUUID().toString(), email = receive.email,
                    token = token
                )
                )
                call.respond(LoginResponseRemote(firstName = userDTO.firstName, secondName = userDTO.secondName,token = token))

            }
            else{
                call.respond(HttpStatusCode.BadRequest, "invalid password")
            }
        }
    }
}