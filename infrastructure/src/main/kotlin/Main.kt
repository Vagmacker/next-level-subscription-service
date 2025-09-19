package com.nextlevel.subscription.infrastructure

import com.nextlevel.subscription.infrastructure.configuration.WebServerConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.AbstractEnvironment

@SpringBootApplication
class Main

fun main(args: Array<String>) {
    System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "development");
    runApplication<WebServerConfig>(*args)
}