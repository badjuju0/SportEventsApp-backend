package com.SportEventsApp.features.createEvent

import kotlinx.serialization.Serializable
import javax.print.attribute.standard.DialogOwner

@Serializable
data class EventReceiveRemote(
    //val id: String,
    val sportType: String,
    val title: String,
    val dates: String,
    val location: String,
    val organizer: String,
    val phoneNumber: String,
    val owner: String

)

@Serializable
data class EventResponseRemote(
val answer:String
)