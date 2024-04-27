package com.SportEventsApp.database.applications

import com.SportEventsApp.database.events.EventTitle
import com.SportEventsApp.database.events.Events
import com.SportEventsApp.database.events.EventsDTO
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Applications: Table(){
    private val id = Applications.varchar("id", 50)
    private val fio = Applications.varchar("fio", 50)
    private val age = Applications.varchar("age", 50)
    private val phoneNumber = Applications.varchar("phoneNumber", 50)
    private val teamName = Applications.varchar("teamName", 50).nullable()
    private val approve = Applications.varchar("approve", 50).nullable()
    private val eventTitle = Applications.varchar("eventTitle", 50)


    fun insert (applicationDTO: ApplicationDTO){
        transaction {
            Applications.insert {
                it[Applications.id] = applicationDTO.id
                it[fio] = applicationDTO.fio
                it[age] = applicationDTO.age
                it[phoneNumber] = applicationDTO.phoneNumber
                it[teamName] = applicationDTO.teamName
                it[approve] = applicationDTO.approve
                it[eventTitle] = applicationDTO.eventTitle

            }
        }
    }

    fun fetchApplications(id:String): ApplicationDTO?{
        return  try {
            transaction {
                val applicationModel = Applications.select{Applications.id.eq(id)}.single()
                ApplicationDTO(
                    id = applicationModel[Applications.id],
                    fio = applicationModel[Applications.fio],
                    age = applicationModel[Applications.age],
                    phoneNumber = applicationModel[Applications.phoneNumber],
                    teamName = applicationModel[Applications.teamName],
                    approve = applicationModel[Applications.approve],
                    eventTitle = applicationModel[Applications.eventTitle],

                )
            }
        }catch (e:Exception){null}
    }

    fun fetchAllApplications(title:String): List<ApplicationDTO> {
        return  try {
            transaction {
                val allApplications = selectAll().where{ Applications.eventTitle eq title }.toList()
                    .map {
                        ApplicationDTO(
                            id = it[Applications.id],
                            fio = it[Applications.fio],
                            age = it[Applications.age],
                            phoneNumber = it[Applications.phoneNumber],
                            teamName = it[Applications.teamName],
                            approve = it[Applications.approve],
                            eventTitle = it[Applications.eventTitle],




                            )
                    }
                return@transaction allApplications
            }
        }catch (e:Exception){
            emptyList()
        }
    }

}