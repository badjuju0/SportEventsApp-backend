package com.SportEventsApp.features.createApplication

import com.SportEventsApp.database.applications.ApplicationDTO
import com.SportEventsApp.database.events.EventTitle
import kotlinx.serialization.Serializable

@Serializable
data class ApplicationReceiveRemote(
    //val id: String,
    val fio: String,
    val age: String,
    val phoneNumber: String,
    val teamName: String? = null,
    val approve: String? = null,
    val eventTitle: String
)

@Serializable
data class ApplicationResponseRemote(
    val answer:String
)
@Serializable
data class ApplicationsResponseRemote(
    val applications:List<ApplicationDTO>

)