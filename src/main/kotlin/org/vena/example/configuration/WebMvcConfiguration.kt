package org.vena.example.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.vena.example.extensions.kotlin.configure
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
@Controller
@SessionAttributes("authorizationRequest")
class WebMvcConfiguration : WebMvcConfigurerAdapter() {

    override fun addViewControllers(registry: ViewControllerRegistry) = configure(registry) {
        addViewController("/login").setViewName("login")
        addViewController("/login-email").setViewName("login-email")
        addViewController("/oauth/confirm_access").setViewName("authorize")
    }

    @GetMapping("/logout")
    fun exit(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication,
             @RequestParam("redirect_uri") redirectUri: String) {
        SecurityContextLogoutHandler().logout(request, response, authentication)
        response.sendRedirect(redirectUri)
    }
}
