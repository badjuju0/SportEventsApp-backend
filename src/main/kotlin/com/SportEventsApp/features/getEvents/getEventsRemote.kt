package com.SportEventsApp.features.getEvents

import com.SportEventsApp.database.events.EventTitle

import kotlinx.serialization.Serializable

//@Serializable
//data class NamesReceiveRemote(
//    val email:String
//
//)

@Serializable
data class EventsResponseRemote(
    val titles:List<EventTitle>

)
