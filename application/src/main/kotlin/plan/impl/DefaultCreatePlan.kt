package com.nextlevel.subscription.application.plan.impl

import com.nextlevel.subscription.domain.plan.Plan
import com.nextlevel.subscription.domain.money.Money
import com.nextlevel.subscription.domain.plan.PlanId
import com.nextlevel.subscription.domain.plan.PlanRepository
import com.nextlevel.subscription.application.plan.CreatePlan

class DefaultCreatePlan(private val planRepository: PlanRepository) : CreatePlan() {

    override fun execute(input: Input): Output {
        val aPlan = planRepository.save(newPlanWith(input))
        return StdOutput(aPlan.id())
    }

    private fun newPlanWith(input: Input): Plan {
        val planId = planRepository.nextId()
        return Plan.newPlan(
            planId,
            input.name,
            input.description,
            Money(input.price, input.currency),
            input.active
        )
    }

    data class StdOutput(private val planId: PlanId) : Output {
        override fun planId(): PlanId = planId
    }
}