package com.SportEventsApp.features.createEvent

import kotlinx.serialization.Serializable

@Serializable
data class EventReceiveRemote(
    //val id: String,
    val sportType: String,
    val title: String,
    val dates: String,
    val location: String,
    val organizer: String,
    val phoneNumber: String
)

@Serializable
data class EventResponseRemote(
val answer:String
)