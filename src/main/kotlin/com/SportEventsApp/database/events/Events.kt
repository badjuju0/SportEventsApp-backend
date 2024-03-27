package com.SportEventsApp.database.events

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Events: Table() {
    private val id = Events.varchar("id", 50)
    private val sportType = Events.varchar("sportType", 30)
    private val title = Events.varchar("title", 30)
    private val dates = Events.varchar("dates", 30)
    private val location = Events.varchar("location", 30)
    private val organizer = Events.varchar("organizer", 30)
    private val phoneNumber = Events.varchar("phoneNumber", 30)



    fun insert (eventsDTO:EventsDTO){
        transaction {
            Events.insert {
                it[id] = eventsDTO.id
                it[sportType] = eventsDTO.sportType
                it[title] = eventsDTO.title
                it[dates] = eventsDTO.dates
                it[location] = eventsDTO.location
                it[organizer] = eventsDTO.organizer
                it[phoneNumber] = eventsDTO.phoneNumber
            }
        }
    }


    fun fetchEvents(id:String): EventsDTO?{
        return  try {
            transaction {
                val eventModel = Events.select{Events.id.eq(id)}.single()
                EventsDTO(
                    id = eventModel[Events.id],
                    sportType = eventModel[Events.sportType],
                    title = eventModel[Events.title],
                    dates = eventModel[Events.dates],
                    location = eventModel[Events.location],
                    organizer = eventModel[Events.organizer],
                    phoneNumber = eventModel[Events.phoneNumber],

                    )
            }
        }catch (e:Exception){null}
    }

    fun fetchAllEvents(): List<EventTitle> {
        return  try {
            transaction {
                val allEvents = selectAll().toList()
                    .map {
                        EventTitle(
                            title = it[Events.title]
                        )
                    }


//                val eventTitles =
//                    EventTitles(
//                    title = allEvents.map {
//                        it[title]
//                    }
//                )

                return@transaction allEvents
            }
        }catch (e:Exception){
            emptyList()
        }
    }

}