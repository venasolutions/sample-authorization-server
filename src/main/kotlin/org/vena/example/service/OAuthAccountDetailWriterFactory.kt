package org.vena.example.service

import org.springframework.stereotype.Component
import org.vena.example.configuration.FacebookConfig
import org.vena.example.configuration.GooglePlusConfig

interface OAuthAccountDetailWriterFactory {
    fun getAccountDetailWriter(clientId: String): AccountDetailWriter
}

@Component
class OAuthAccountDetailWriterFactoryImpl(private val facebookConfig: FacebookConfig,
                                          private val googlePlusConfig: GooglePlusConfig) : OAuthAccountDetailWriterFactory {

    override fun getAccountDetailWriter(clientId: String) = when (clientId) {
        facebookConfig.client.clientId -> FacebookAccountDetailWriter
        googlePlusConfig.client.clientId -> GooglePlusAccountDetailWriter
        else -> throw UnsupportedOperationException()
    }
}