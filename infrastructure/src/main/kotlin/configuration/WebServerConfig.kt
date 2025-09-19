package com.nextlevel.subscription.infrastructure.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

@Configuration(proxyBeanMethods = false)
@ComponentScan("com.nextlevel.subscription")
class WebServerConfig {

    @Bean
    fun localValidatorFactoryBean() = LocalValidatorFactoryBean()
}