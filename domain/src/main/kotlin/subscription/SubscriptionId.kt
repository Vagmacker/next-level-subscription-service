package com.nextlevel.subscription.domain.subscription

import com.nextlevel.subscription.domain.Identifier
import com.nextlevel.subscription.domain.exceptions.DomainException

@JvmInline
value class SubscriptionId(override val value: String) : Identifier<String> {
    init {
        require(value.isNotBlank()) { throw DomainException.with("subscriptionId must not be empty") }
    }

    override fun toString(): String = value
}