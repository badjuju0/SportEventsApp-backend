package com.SportEventsApp.features.getNames

import kotlinx.serialization.Serializable

@Serializable
data class NamesReceiveRemote(
    val email: String

)

@Serializable
data class NamesResponseRemote(
    val firstName:String,
    val secondName:String,
)