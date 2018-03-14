package org.vena.example.service

import org.vena.example.model.Account

interface AccountDetailWriter {
    fun createAccount(username: String, details: Map<*, *>): Account
}