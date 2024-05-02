package com.SportEventsApp.features.createApplication

import com.SportEventsApp.features.createApplication.ApplicationController
import com.SportEventsApp.features.getEvents.GetEventsController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureApplicationRouting() {
    routing {

        post("/application") {
            val applicationController = ApplicationController(call)
            applicationController.createApplication()
        }

        get("/getApplications/{title}") {
            val getApplicationsController = ApplicationController(call)
            getApplicationsController.performGetApplications()

        }

        post("/approveApplication/{id}") {
            val applicationController = ApplicationController(call)
            applicationController.performApproveApplication()
        }

        post("/dismissApplication/{id}") {
            val applicationController = ApplicationController(call)
            applicationController.performDismissApplication()
        }

        get("/participants/{title}") {
            val getApplicationsController = ApplicationController(call)
            getApplicationsController.performGetParticipants()

        }

    }
}