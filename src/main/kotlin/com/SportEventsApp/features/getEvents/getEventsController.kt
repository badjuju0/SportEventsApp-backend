package com.SportEventsApp.features.getEvents


import com.SportEventsApp.database.events.Events
import com.SportEventsApp.database.events.EventsDTO
import com.SportEventsApp.database.users.Users
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

class GetEventsController (private val call: ApplicationCall){
    suspend fun performGetEvents(){
        val titleRoute = call.parameters["title"].toString()
//        val receive = call.receive<NamesReceiveRemote>()
        val eventDTO = Events.fetchAllEvents()

        call.respond(EventsResponseRemote(titles = Events.fetchAllEvents()))

    }
}