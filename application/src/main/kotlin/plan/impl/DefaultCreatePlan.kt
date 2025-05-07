package com.nextlevel.subscription.application.plan.impl

import com.nextlevel.subscription.domain.plan.Plan
import com.nextlevel.subscription.domain.money.Money
import com.nextlevel.subscription.domain.plan.PlanGateway
import com.nextlevel.subscription.application.plan.CreatePlan

class DefaultCreatePlan(private val planGateway: PlanGateway) : CreatePlan() {

    override fun execute(input: Input): Output {
        return Output(planGateway.save(newPlanWith(input)).id)
    }

    private fun newPlanWith(input: Input): Plan = Plan.newPlan(
        id = planGateway.nextId(),
        name = input.name,
        description = input.description,
        price = Money(input.price, input.currency),
        active = input.active
    )
}