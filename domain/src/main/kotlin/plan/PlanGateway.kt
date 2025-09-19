package com.nextlevel.subscription.domain.plan

interface PlanGateway {
    fun nextId(): PlanId
    fun planOfId(id: PlanId): Plan?
    fun save(plan: Plan): Plan
    fun allPlans(): List<Plan>
}