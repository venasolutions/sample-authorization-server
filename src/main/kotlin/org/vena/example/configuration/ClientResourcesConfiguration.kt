package org.vena.example.configuration

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.NestedConfigurationProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails

sealed class ClientResources {

    @NestedConfigurationProperty
    val client = AuthorizationCodeResourceDetails()

    @NestedConfigurationProperty
    val resource = ResourceServerProperties()
}

class FacebookConfig : ClientResources()

class GooglePlusConfig : ClientResources()

@Configuration
class ClientResourcesConfiguration {

    @Bean
    @ConfigurationProperties("facebook")
    fun facebook() = FacebookConfig()

    @Bean
    @ConfigurationProperties("google_plus")
    fun googlePlus() = GooglePlusConfig()
}