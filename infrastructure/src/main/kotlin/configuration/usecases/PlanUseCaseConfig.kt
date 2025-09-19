package com.nextlevel.subscription.infrastructure.configuration.usecases

import com.nextlevel.subscription.application.plan.ChangePlan
import com.nextlevel.subscription.application.plan.CreatePlan
import com.nextlevel.subscription.application.plan.impl.DefaultChangePlan
import com.nextlevel.subscription.application.plan.impl.DefaultCreatePlan
import com.nextlevel.subscription.domain.plan.PlanGateway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
class PlanUseCaseConfig {

    @Bean
    fun createPlan(planGateway: PlanGateway): CreatePlan = DefaultCreatePlan(planGateway)

    @Bean
    fun changePlan(planGateway: PlanGateway): ChangePlan = DefaultChangePlan(planGateway)
}