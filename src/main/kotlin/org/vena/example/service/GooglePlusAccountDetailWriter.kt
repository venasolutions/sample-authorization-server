package org.vena.example.service

import org.vena.example.model.Account

object GooglePlusAccountDetailWriter : AccountDetailWriter {

    override fun createAccount(username: String, details: Map<*, *>): Account {
        val email: String = details["email"] as String
        val firstName: String = details["given_name"] as String
        val lastName: String = details["family_name"] as String

        return Account(username = username, email = email, firstName = firstName, lastName = lastName)
    }
}