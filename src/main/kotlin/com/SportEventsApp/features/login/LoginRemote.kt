package com.SportEventsApp.features.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginReceiveRemote(
    val email: String,
    val password: String
)

@Serializable
data class LoginResponseRemote(
    val token:String,
    val firstName:String,
    val secondName:String,
)

@Serializable
data class TokenReceive(
    val token: String
)
@Serializable
data class TokenResponse(
    val answer: String
)