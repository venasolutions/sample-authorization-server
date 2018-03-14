package org.vena.example.configuration

import org.hibernate.SessionFactory
import org.hibernate.jpa.HibernateEntityManagerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JpaConfiguration {

    @Bean
    fun sessionFactory(managerFactory: HibernateEntityManagerFactory): SessionFactory = managerFactory.sessionFactory
}
