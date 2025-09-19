package com.nextlevel.subscription

import com.nextlevel.subscription.infrastructure.Main
import org.springframework.boot.fromApplication
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.PostgreSQLContainer

class TestApplication {

    @Bean
    @ServiceConnection
    fun postgresContainer(): PostgreSQLContainer<*> = PostgreSQLContainer<Nothing>("postgres:15.4")

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            fromApplication<Main>()
                .with(TestApplication::class.java)
                .run(*args)
        }
    }
}