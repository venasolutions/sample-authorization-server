package org.vena.example.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.vena.example.model.Account
import org.vena.example.repository.AccountRepository
import javax.security.auth.login.AccountNotFoundException

@Service
@Transactional
class AccountServiceImpl(private val oAuthAccountDetailWriterFactory: OAuthAccountDetailWriterFactory,
                         private val accountRepository: AccountRepository) : AccountService {

    override fun doesAccountExists(username: String): Boolean = accountRepository.existsByUsername(username)

    override fun findAccountByUsername(username: String) = findAccount(username)

    override fun deleteAccountByUsername(username: String): Account = findAccount(username).apply {
        accountRepository.delete(this)
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val account = accountRepository.findByUsername(username)
                ?: throw UsernameNotFoundException("Could not find account with username $username!")


        return with(account) {
            User.withUsername(username)
                    .password(password)
                    .authorities("USER")
                    .build()
        }
    }

    override fun saveOAuth2Account(oAuth2Authentication: OAuth2Authentication): Account {
        val userAuthentication = oAuth2Authentication.userAuthentication
        val details = userAuthentication.details as Map<*, *>
        val accountWriter = oAuthAccountDetailWriterFactory.getAccountDetailWriter(oAuth2Authentication.oAuth2Request.clientId)
        val username = userAuthentication.principal as String

        return accountRepository.findByUsername(username)
                ?: accountRepository.save(accountWriter.createAccount(username, details))
    }

    private fun findAccount(username: String) = accountRepository.findByUsername(username)
            ?: throw AccountNotFoundException(username)
}
