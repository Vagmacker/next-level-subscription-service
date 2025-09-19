package com.nextlevel.subscription.infrastructure.configuration.usecases

import com.nextlevel.subscription.application.account.CreateAccount
import com.nextlevel.subscription.application.account.impl.DefaultCreateAccount
import com.nextlevel.subscription.domain.account.AccountGateway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
class AccountUseCaseConfig {

    @Bean
    fun createAccount(accountGateway: AccountGateway): CreateAccount = DefaultCreateAccount(accountGateway)
}