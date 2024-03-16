package com.SportEventsApp.features.getNames

import com.SportEventsApp.database.tokens.TokenDTO
import com.SportEventsApp.database.tokens.Tokens
import com.SportEventsApp.database.users.Users
import com.SportEventsApp.features.login.LoginReceiveRemote
import com.SportEventsApp.features.login.LoginResponseRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class GetNamesController (private val call: ApplicationCall){
    suspend fun performGetNames(){
        val emailRoute = call.parameters["email"].toString()
//        val receive = call.receive<NamesReceiveRemote>()
        val userDTO = Users.fetchUser(emailRoute)


        if (userDTO==null){call.respond(HttpStatusCode.BadRequest, "user not found")}


        else{
            if (userDTO.email == emailRoute){


                call.respond(NamesResponseRemote(firstName = userDTO.firstName, secondName = userDTO.secondName))

            }
            else{
                call.respond(HttpStatusCode.BadRequest, "invalid password")
            }
        }
    }
}