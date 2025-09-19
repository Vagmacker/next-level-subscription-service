package com.nextlevel.subscription.infrastructure.configuration

import com.nextlevel.subscription.infrastructure.jdbc.DatabaseClient
import com.nextlevel.subscription.infrastructure.jdbc.JdbcClientAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.simple.JdbcClient

@Configuration(proxyBeanMethods = false)
class JdbcConfig {

    @Bean
    fun databaseClient(jdbcClient: JdbcClient): DatabaseClient {
        return JdbcClientAdapter(jdbcClient)
    }
}