package com.SportEventsApp.features.getEvents

import com.SportEventsApp.database.events.EventTitle

import kotlinx.serialization.Serializable

@Serializable
data class EventsReceiveRemote(
    val title:String
)


@Serializable
data class EventResponseRemote(
    val id: String,
    val sportType: String,
    val dates: String,
    val location: String,
    val organizer: String,
    val phoneNumber: String,
    val owner: String

)
@Serializable
data class EventsResponseRemote(
    val titles:List<EventTitle>

)
