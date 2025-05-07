package com.nextlevel.subscription.domain.person

import com.nextlevel.subscription.domain.ValueObject
import com.nextlevel.subscription.domain.exceptions.DomainException

@JvmInline
value class Email(val value: String) : ValueObject {
    init {
        require(value.isNotBlank()) { throw DomainException.with("email must not be empty") }
        require(isValidEmail(value)) { throw DomainException.with("email is invalid") }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^(.+)@(\\S+)$".toRegex()
        return emailRegex.matches(email)
    }

    override fun toString(): String = value
}
