package com.nextlevel.subscription.domain.account.idp

import com.nextlevel.subscription.domain.Identifier
import com.nextlevel.subscription.domain.exceptions.DomainException

@JvmInline
value class UserId(override val value: String) : Identifier<String> {
    init {
        require(value.isNotBlank()) { throw DomainException.with("userId must not be empty") }
    }

    override fun toString(): String = value
}