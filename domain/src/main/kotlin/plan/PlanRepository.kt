package com.nextlevel.subscription.domain.plan

interface PlanRepository {
    fun nextId(): PlanId
    fun planOfId(id: PlanId): Plan?
    fun save(plan: Plan): Plan
    fun allPlans(plan: Plan)
}