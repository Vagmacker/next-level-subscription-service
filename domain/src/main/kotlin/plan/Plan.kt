package com.nextlevel.subscription.domain.plan

import java.time.Instant
import com.nextlevel.subscription.domain.money.Money
import com.nextlevel.subscription.domain.AggregateRoot
import com.nextlevel.subscription.domain.exceptions.DomainException

class Plan private constructor(
    id: PlanId,
    version: Int,
    name: String,
    description: String,
    price: Money,
    active: Boolean,
    createdAt: Instant,
    updatedAt: Instant,
    deletedAt: Instant? = null
) : AggregateRoot<PlanId>(id) {

    init {
        require(name.isNotBlank()) { throw DomainException.with("name must not be empty") }
        require(name.length <= 100) { throw DomainException.with("name must be less than 100 characters") }
        require(description.isNotBlank()) { throw DomainException.with("description must not be empty") }
        require(description.length <= 1000) { throw DomainException.with("description must be less than 1000 characters") }
    }

    var version: Int = version
        private set

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
        private const val INIT_VERSION = 0

        fun newPlan(
            id: PlanId, name: String, description: String, price: Money, active: Boolean = true
        ): Plan {
            val now = Instant.now()
            return Plan(id, INIT_VERSION, name, description, price, active, now, now)
        }

        fun with(
            id: PlanId,
            version: Int,
            name: String,
            description: String,
            price: Money,
            active: Boolean,
            createdAt: Instant,
            updatedAt: Instant,
            deletedAt: Instant?
        ) = Plan(id, version, name, description, price, active, createdAt, updatedAt, deletedAt)
    }

    fun execute(cmd: PlanCommand) {
        when (cmd) {
            is PlanCommand.ChangePlan -> apply {
                name = cmd.name
                price = cmd.price
                description = cmd.description
            }

            is PlanCommand.InactivatePlan -> apply {
                active = false
                deletedAt = deletedAt ?: Instant.now()
            }

            is PlanCommand.ActivatePlan -> apply {
                active = true
                deletedAt = null
            }
        }
        version++
        updatedAt = Instant.now()
    }

    fun withId(id: PlanId) = Plan(id, version, name, description, price, active, createdAt, updatedAt, deletedAt)
}