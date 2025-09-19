package com.nextlevel.subscription.infrastructure.configuration.usecases

import com.nextlevel.subscription.application.subscription.impl.DefaultCreateSubscription
import com.nextlevel.subscription.domain.account.AccountGateway
import com.nextlevel.subscription.domain.plan.PlanGateway
import com.nextlevel.subscription.domain.subscription.SubscriptionGateway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
class SubscriptionUseCaseConfig {

    @Bean
    fun createSubscription(
        planGateway: PlanGateway,
        accountGateway: AccountGateway,
        subscriptionGateway: SubscriptionGateway
    ): DefaultCreateSubscription = DefaultCreateSubscription(planGateway, accountGateway, subscriptionGateway)
}