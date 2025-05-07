package com.nextlevel.subscription.application.plan

import com.nextlevel.subscription.application.UseCase
import com.nextlevel.subscription.domain.plan.PlanId

abstract class CreatePlan : UseCase<CreatePlan.Input, CreatePlan.Output>() {
    data class Input(
        val name: String,
        val description: String,
        val price: Double,
        val currency: String,
        val active: Boolean
    )

    data class Output(
        val planId: PlanId
    )
}