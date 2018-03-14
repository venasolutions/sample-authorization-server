package org.vena.example.security

import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import org.vena.example.service.AccountService
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class OAuth2SsoAuthenticationSuccessHandler(private val accountService: AccountService) : AuthenticationSuccessHandler {
    private val baseImpl = SavedRequestAwareAuthenticationSuccessHandler()

    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        accountService.saveOAuth2Account(authentication as OAuth2Authentication)
        baseImpl.onAuthenticationSuccess(request, response, authentication)
    }
}
