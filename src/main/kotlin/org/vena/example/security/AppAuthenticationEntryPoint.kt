package org.vena.example.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AppAuthenticationEntryPoint(loginPage: String) : AuthenticationEntryPoint {
    private val baseImpl = LoginUrlAuthenticationEntryPoint(loginPage)

    override fun commence(request: HttpServletRequest, response: HttpServletResponse, authException: AuthenticationException) =
            DefaultRedirectStrategy().sendRedirect(request, response, baseImpl.loginFormUrl + "?" + request.queryString)
}