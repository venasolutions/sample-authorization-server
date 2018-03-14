package org.vena.example.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.vena.example.extensions.kotlin.configure

@Configuration
@Controller
@SessionAttributes("authorizationRequest")
class WebMvcConfiguration : WebMvcConfigurerAdapter() {

    override fun addViewControllers(registry: ViewControllerRegistry) = configure(registry) {
        addViewController("/login").setViewName("login")
    }
}
