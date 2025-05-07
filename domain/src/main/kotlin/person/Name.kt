package com.nextlevel.subscription.domain.person

import com.nextlevel.subscription.domain.ValueObject

data class Name(val firstname: String, val lastname: String) : ValueObject {
    init {
        require(firstname.isNotBlank()) { "first name must not be empty" }
        require(lastname.isNotBlank()) { "last name must not be empty" }
    }

    fun fullName(): String = "$firstname $lastname"

    override fun toString(): String = "$firstname $lastname"
}