package com.nextlevel.subscription.application.plan

import com.nextlevel.subscription.application.UseCase
import com.nextlevel.subscription.domain.plan.PlanId

abstract class ChangePlan : UseCase<ChangePlan.Input, ChangePlan.Output>() {

    interface Input {
        val planId: Long
        val name: String
        val description: String
        val currency: String
        val price: Double
        val active: Boolean
    }

    interface Output {
        val planId: PlanId
    }
}