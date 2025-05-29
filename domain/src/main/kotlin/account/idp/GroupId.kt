package com.nextlevel.subscription.domain.account.idp

import com.nextlevel.subscription.domain.Identifier
import com.nextlevel.subscription.domain.exceptions.DomainException

@JvmInline
value class GroupId(override val value: String) : Identifier<String> {
    init {
        require(value.isNotBlank()) { throw DomainException.with("groupId must not be empty") }
    }

    override fun toString(): String = value
}