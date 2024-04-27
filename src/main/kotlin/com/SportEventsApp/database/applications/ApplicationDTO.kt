package com.SportEventsApp.database.applications

import kotlinx.serialization.Serializable

@Serializable
class ApplicationDTO (
    val id: String,
    val fio: String,
    val age: String,
    val phoneNumber: String,
    val teamName: String? = null,
    val approve: String? = null,
    val eventTitle: String
)

