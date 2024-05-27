package com.SportEventsApp.database.events

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Query

class EventsDTO (
    val id: String,
    val sportType: String,
    val title: String,
    val dates: String,
    val location: String,
    val organizer: String,
    val phoneNumber: String,
    val owner: String
)
@Serializable
class EventTitle(
    val title: String
)
