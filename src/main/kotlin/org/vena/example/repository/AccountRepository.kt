package org.vena.example.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.vena.example.model.Account

interface AccountRepository : JpaRepository<Account, Long> {

    fun existsByUsername(username: String): Boolean

    fun findByUsername(username: String): Account?
}

