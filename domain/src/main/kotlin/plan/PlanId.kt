package com.nextlevel.subscription.domain.plan

import com.nextlevel.subscription.domain.Identifier
import com.nextlevel.subscription.domain.exceptions.DomainException

@JvmInline
value class PlanId(override val value: Long?) : Identifier<Long> {
    init {
        requireNotNull(value) { DomainException.with("planId must not be null") }
    }
}