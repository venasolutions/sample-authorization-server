package org.vena.example.service

import org.vena.example.model.Account

object FacebookAccountDetailWriter : AccountDetailWriter {

    override fun createAccount(username: String, details: Map<*, *>): Account {
        val email: String = details["email"] as String
        val firstName: String = details["first_name"] as String
        val lastName: String = details["last_name"] as String

        return Account(username = username, email = email, firstName = firstName, lastName = lastName)
    }
}