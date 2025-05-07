package com.nextlevel.subscription.domain.account

import com.nextlevel.subscription.domain.Identifier
import com.nextlevel.subscription.domain.exceptions.DomainException

@JvmInline
value class AccountId(override val value: String) : Identifier<String> {
    init {
        require(value.isNotBlank()) { DomainException.with("accountId must not be null") }
    }

    override fun toString(): String = value
}