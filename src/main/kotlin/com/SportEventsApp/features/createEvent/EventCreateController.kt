package com.SportEventsApp.features.createEvent

import com.SportEventsApp.database.events.Events
import com.SportEventsApp.database.events.EventsDTO
import com.SportEventsApp.database.tokens.TokenDTO
import com.SportEventsApp.database.tokens.Tokens
import com.SportEventsApp.database.users.UserDTO
import com.SportEventsApp.database.users.Users
import com.SportEventsApp.features.login.LoginReceiveRemote
import com.SportEventsApp.features.login.LoginResponseRemote
import com.SportEventsApp.features.register.RegisterReceiveRemote
import com.SportEventsApp.features.register.RegisterResponseRemote
import com.SportEventsApp.utils.isValidEmail
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.util.*

class EventCreateController(private val call:ApplicationCall) {
    suspend  fun createNewEvent(){
        val eventReceiveRemote = call.receive<EventReceiveRemote>()
//        if (!registerReceiveRemote.email.isValidEmail(registerReceiveRemote.email)){
//            call.respond(HttpStatusCode.BadRequest, "Email  is nor valid")
//        }
        val eventDTO = Events.fetchEvents(eventReceiveRemote.title)

        if (eventDTO != null){
            call.respond(HttpStatusCode.Conflict, "event already exists")
        } else{
            //val id = UUID.randomUUID().toString()

            try {
                Events.insert(
                    EventsDTO(
                        id = UUID.randomUUID().toString(),
                        sportType = eventReceiveRemote.sportType,
                        title = eventReceiveRemote.title,
                        dates = eventReceiveRemote.dates,
                        location = eventReceiveRemote.location,
                        organizer = eventReceiveRemote.organizer,
                        phoneNumber = eventReceiveRemote.phoneNumber,
                        owner = eventReceiveRemote.owner
                    )
                )
                call.respond(EventResponseRemote(answer = "Event created successfully"))
            }catch (e: ExposedSQLException){
                call.respond(HttpStatusCode.Conflict, "event already exists")
            }



        }

    }
}