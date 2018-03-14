package org.vena.example.service

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.vena.example.model.Account

interface AccountService : UserDetailsService {
    fun saveOAuth2Account(oAuth2Authentication: OAuth2Authentication): Account
}
