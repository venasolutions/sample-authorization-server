package org.vena.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.support.SpringBootServletInitializer

@SpringBootApplication
class AuthorizationServerApplication : SpringBootServletInitializer() {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(AuthorizationServerApplication::class.java, *args)
        }
    }
}
