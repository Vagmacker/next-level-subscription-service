package com.nextlevel.subscription.domain.plan

import com.nextlevel.subscription.domain.AggregateRoot
import com.nextlevel.subscription.domain.money.Money
import java.time.Instant

class Plan private constructor(
    id: PlanId,
    name: String,
    description: String,
    price: Money,
    active: Boolean,
    createdAt: Instant,
    updatedAt: Instant,
    deletedAt: Instant? = null
) : AggregateRoot<PlanId>(id) {

    var name: String = name
        private set

    var description: String = description
        private set

    var price: Money = price
        private set

    var active: Boolean = active
        private set

    var createdAt: Instant = createdAt
        private set

    var updatedAt: Instant = updatedAt
        private set

    var deletedAt: Instant? = deletedAt
        private set

    companion object {
        fun newPlan(
            id: PlanId,
            name: String,
            description: String,
            price: Money,
            active: Boolean = true
        ): Plan {
            val now = Instant.now()
            return Plan(id, name, description, price, active, now, now)
        }
    }

    fun execute(cmd: PlanCommand) {
        when (cmd) {
            is PlanCommand.ChangePlan -> apply(cmd)
            is PlanCommand.InactivatePlan -> apply(cmd)
            is PlanCommand.ActivatePlan -> apply(cmd)
        }
        this.updatedAt = Instant.now()
    }

    private fun apply(cmd: PlanCommand.ChangePlan) {
        this.name = cmd.name
        this.price = cmd.price
        this.description = cmd.description
    }

    private fun apply(cmd: PlanCommand.ActivatePlan) {
        this.active = true
        this.deletedAt = null
    }

    private fun apply(cmd: PlanCommand.InactivatePlan) {
        this.active = false
        this.deletedAt = deletedAt ?: Instant.now()
    }
}