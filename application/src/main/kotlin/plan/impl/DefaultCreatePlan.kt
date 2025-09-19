package com.nextlevel.subscription.application.plan.impl

import com.nextlevel.subscription.application.plan.CreatePlan
import com.nextlevel.subscription.domain.plan.PlanGateway
import com.nextlevel.subscription.domain.plan.PlanId

class DefaultCreatePlan(private val planGateway: PlanGateway) : CreatePlan() {

    override fun execute(input: Input): Output =
        planGateway.nextId()
            .let { id -> input.toDomain(id) }
            .let(planGateway::save)
            .let { plan -> StdOutput(plan.id) }
}

data class StdOutput(override val planId: PlanId) : CreatePlan.Output