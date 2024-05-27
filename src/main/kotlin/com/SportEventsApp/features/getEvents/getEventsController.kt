package com.SportEventsApp.features.getEvents


import com.SportEventsApp.database.events.Events
import com.SportEventsApp.database.events.EventsDTO
import com.SportEventsApp.database.tokens.TokenDTO
import com.SportEventsApp.database.tokens.Tokens
import com.SportEventsApp.database.users.Users
import com.SportEventsApp.features.login.LoginResponseRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class GetEventsController (private val call: ApplicationCall){

    suspend fun performGetEvent(){
        val titleRoute = call.parameters["title"].toString()
        val eventDTO = Events.fetchEvents(titleRoute)
        if (eventDTO==null){call.respond(HttpStatusCode.BadRequest, "event not found")}
        else{
            if (eventDTO.title == titleRoute){

                call.respond(EventResponseRemote(
                    id = eventDTO.id,
                    sportType = eventDTO.sportType,
                    dates = eventDTO.dates,
                    location = eventDTO.location,
                    organizer = eventDTO.organizer,
                    phoneNumber = eventDTO.phoneNumber,
                    owner = eventDTO.owner,
                ))

            }


        }
    }

    suspend fun performGetEvents(){
        val eventsDTO = Events.fetchAllEvents()
        call.respond(EventsResponseRemote(titles = Events.fetchAllEvents()))

    }
}