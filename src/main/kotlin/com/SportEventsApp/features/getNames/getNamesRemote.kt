package com.SportEventsApp.features.getNames

import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

//@Serializable
//data class NamesReceiveRemote(
//    val email:String
//
//)

@Serializable
data class NamesResponseRemote(
    val firstName:String,
    val secondName:String
)