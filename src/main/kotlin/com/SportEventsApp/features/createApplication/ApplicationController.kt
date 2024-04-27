package com.SportEventsApp.features.createApplication

import com.SportEventsApp.database.applications.ApplicationDTO
import com.SportEventsApp.database.applications.Applications
import com.SportEventsApp.database.events.Events
import com.SportEventsApp.features.getEvents.EventsResponseRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.util.*

class ApplicationController(private val call: ApplicationCall) {
    suspend  fun createApplication(){
        val applicationReceiveRemote = call.receive<ApplicationReceiveRemote>()
        try {
            Applications.insert(
                ApplicationDTO(
                    id = UUID.randomUUID().toString(),
                    fio = applicationReceiveRemote.fio,
                    age = applicationReceiveRemote.age,
                    phoneNumber = applicationReceiveRemote.phoneNumber,
                    teamName = applicationReceiveRemote.teamName,
                    approve = applicationReceiveRemote.approve,
                    eventTitle = applicationReceiveRemote.eventTitle,
                )
            )
            call.respond(ApplicationResponseRemote(answer = "Application created successfully"))
        }catch (e: ExposedSQLException){
            call.respond(HttpStatusCode.Conflict, "error")
        }
    }

    suspend fun performGetApplications(){
        val applicationRoute = call.parameters["eventId"].toString()
        val applicationsDTO = Applications.fetchAllApplications(applicationRoute)
        call.respond(ApplicationsResponseRemote(applications = applicationsDTO))

    }

}