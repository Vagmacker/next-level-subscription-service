package com.nextlevel.subscription.application.plan.impl

import com.nextlevel.subscription.application.plan.ChangePlan
import com.nextlevel.subscription.domain.exceptions.DomainException
import com.nextlevel.subscription.domain.money.Money
import com.nextlevel.subscription.domain.plan.Plan
import com.nextlevel.subscription.domain.plan.PlanCommand
import com.nextlevel.subscription.domain.plan.PlanGateway
import com.nextlevel.subscription.domain.plan.PlanId

class DefaultChangePlan(private val planGateway: PlanGateway) : ChangePlan() {
    override fun execute(input: Input): Output {
        val id = PlanId(input.planId)
        val plan = planGateway.planOfId(id) ?: throw DomainException.notFound(Plan::class, id)

        plan.execute(
            PlanCommand.ChangePlan(
                name = input.name,
                description = input.description,
                price = Money(input.price, input.currency),
                active = input.active
            )
        )

        planGateway.save(plan)
        return StdChangeOutput(plan.id)
    }
}

data class StdChangeOutput(override val planId: PlanId) : ChangePlan.Output