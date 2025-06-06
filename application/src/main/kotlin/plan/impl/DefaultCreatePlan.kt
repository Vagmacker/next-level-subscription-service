package com.nextlevel.subscription.application.plan.impl

import com.nextlevel.subscription.application.plan.CreatePlan
import com.nextlevel.subscription.domain.plan.PlanGateway

class DefaultCreatePlan(private val planGateway: PlanGateway) : CreatePlan() {

    override fun execute(input: Input): Output =
        planGateway.nextId()
            .let { id -> input.toDomain(id) }
            .let(planGateway::save)
            .let { Output(it.id) }
}