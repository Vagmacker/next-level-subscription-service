package com.nextlevel.subscription.application.plan

import com.nextlevel.subscription.application.UseCase
import com.nextlevel.subscription.domain.money.Money
import com.nextlevel.subscription.domain.plan.Plan
import com.nextlevel.subscription.domain.plan.PlanId

abstract class CreatePlan : UseCase<CreatePlan.Input, CreatePlan.Output>() {
    interface Input {
        val name: String
        val description: String
        val price: Double
        val currency: String
        val active: Boolean
    }

    interface Output {
        val planId: PlanId
    }

    fun Input.toDomain(id: PlanId): Plan = Plan.newPlan(
        id = id,
        name = name,
        description = description,
        price = Money(price, currency),
        active = active
    )
}