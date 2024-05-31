package com.SportEventsApp.database.tokens

import ch.qos.logback.core.subst.Token
import com.SportEventsApp.database.users.UserDTO
import com.SportEventsApp.database.users.Users
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens : Table(){
    private val email = Tokens.varchar("email", 30)
    private val id = Tokens.varchar("id", 50)
    private val token = Tokens.varchar("token", 50)


    fun insert(tokenDTO: TokenDTO){
        transaction {
            Tokens.insert {
                it[id] = tokenDTO.rowId
                it[email] = tokenDTO.email
                it[token] = tokenDTO.token
            }

        }
    }

    fun fetchToken(token:String): TokenDTO? {
        return try {
            transaction {
                val tokenModel = Tokens.select{ Tokens.token.eq(token) }.single()
                TokenDTO(
                    email = tokenModel[Tokens.email],
                    rowId  = tokenModel[Tokens.id],
                    token = tokenModel[Tokens.token]
                )
            }

        } catch (e:Exception){null}

    }

}