package com.nextlevel.subscription.infrastructure.configuration

import com.nextlevel.subscription.infrastructure.json.Json
import org.springframework.boot.jackson.JsonComponent
import org.springframework.context.annotation.Bean

@JsonComponent
class ObjectMapperConfig {

    @Bean
    fun objectMapper() = Json.mapper()
}