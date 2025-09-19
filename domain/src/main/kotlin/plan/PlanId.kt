package com.nextlevel.subscription.domain.plan

import com.nextlevel.subscription.domain.Identifier

@JvmInline
value class PlanId(override val value: Long?) : Identifier<Long> {
    companion object {
        fun empty(): PlanId {
            return PlanId(null)
        }
    }
}