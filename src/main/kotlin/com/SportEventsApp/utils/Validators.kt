package com.SportEventsApp.utils

//fun String.isValidEmail(): Boolean = true

const val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
fun String.isValidEmail(email: String): Boolean {
    return email.matches(emailRegex.toRegex())
}