package org.vena.example.security

import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedPrincipalExtractor
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor

private val extendKeys = arrayOf("sub")

class OAuth2PrincipalExtractor(private val usernameMapper: (String) -> String) : PrincipalExtractor {

    private val fixedPrincipalExtractor = FixedPrincipalExtractor()

    override fun extractPrincipal(map: Map<String, Any>): Any? {
        val principal = extendKeys
                .firstOrNull { map.containsKey(it) }
                ?.let { map[it] }
                ?: fixedPrincipalExtractor.extractPrincipal(map)

        return if (principal is String) usernameMapper(principal) else principal
    }
}
