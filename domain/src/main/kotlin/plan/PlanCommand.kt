package com.nextlevel.subscription.domain.plan

import com.nextlevel.subscription.domain.money.Money

sealed interface PlanCommand {
    data class ChangePlan(
        var name: String,
        var description: String,
        var price: Money,
        var active: Boolean
    ) : PlanCommand

    class InactivatePlan() : PlanCommand

    class ActivatePlan() : PlanCommand
}