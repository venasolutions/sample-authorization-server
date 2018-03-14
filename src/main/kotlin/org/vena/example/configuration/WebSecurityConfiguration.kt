package org.vena.example.configuration

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.filter.CompositeFilter
import org.vena.example.extensions.kotlin.configure
import org.vena.example.security.AppAuthenticationEntryPoint
import org.vena.example.security.OAuth2PrincipalExtractor
import org.vena.example.security.OAuth2SsoAuthenticationSuccessHandler
import org.vena.example.service.AccountService
import javax.sql.DataSource

@Configuration
@EnableOAuth2Client
class WebSecurityConfiguration(private val oauth2ClientContext: OAuth2ClientContext,
                               private val dataSource: DataSource,
                               private val facebookConfig: FacebookConfig,
                               private val googlePlusConfig: GooglePlusConfig,
                               private val accountService: AccountService) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) = configure(auth) {
        userDetailsService(accountService)
        jdbcAuthentication().dataSource(dataSource)
    }

    override fun configure(http: HttpSecurity) = configure(http) {
        antMatcher("/**")
                .authorizeRequests().antMatchers("/", "/health", "/login**", "/logout**", "/assets/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .and().exceptionHandling().authenticationEntryPoint(AppAuthenticationEntryPoint("/login"))
                .and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter::class.java)
    }

    @Bean
    fun oauth2ClientFilterRegistration(filter: OAuth2ClientContextFilter) = FilterRegistrationBean().apply {
        this.filter = filter
        order = -100
    }

    private fun ssoFilter() = CompositeFilter().apply {
        val facebookFilter = facebookConfig.filter("/login/facebook") { userId -> "$userId@facebook.com" }
        val googlePlusFilter = googlePlusConfig.filter("/login/google_plus") { userId -> "$userId@google.com" }

        setFilters(listOf(facebookFilter, googlePlusFilter))
    }

    private fun ClientResources.filter(path: String, usernameMapper: (String) -> String) = OAuth2ClientAuthenticationProcessingFilter(path).apply {
        val template = OAuth2RestTemplate(this@filter.client, oauth2ClientContext)

        setRestTemplate(template)
        setTokenServices(UserInfoTokenServices(this@filter.resource.userInfoUri, this@filter.client.clientId).apply {
            setRestTemplate(template)
            setPrincipalExtractor(OAuth2PrincipalExtractor(usernameMapper))
        })
        setAuthenticationSuccessHandler(OAuth2SsoAuthenticationSuccessHandler(accountService))
    }
}