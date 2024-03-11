package com.SportEventsApp.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Users : Table(){
    private val email = Users.varchar("email", 30)
    private val firstName = Users.varchar("firstName", 30)
    private val secondName = Users.varchar("secondName", 30)
    private val password = Users.varchar("password", 30)


    fun insert(userDTO: UserDTO){
        transaction {
            Users.insert {
                it[email] = userDTO.email
                it[firstName] = userDTO.firstName
                it[secondName] = userDTO.secondName
                it[password] = userDTO.password
            }

        }
    }

    fun fetchUser(email:String): UserDTO? {
        return try {
            transaction {
                val userModel = Users.select{ Users.email.eq(email) }.single()
                UserDTO(
                    email = userModel[Users.email],
                    firstName = userModel[firstName],
                    secondName = userModel[secondName],
                    password = userModel[password]
                )
            }

        } catch (e:Exception){null}

    }
}