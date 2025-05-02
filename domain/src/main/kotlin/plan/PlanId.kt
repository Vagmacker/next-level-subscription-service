package com.nextlevel.subscription.domain.plan

import com.nextlevel.subscription.domain.Identifier

data class PlanId(val value: Long) : Identifier<Long> {

    override fun value(): Long {
        return value
    }
}