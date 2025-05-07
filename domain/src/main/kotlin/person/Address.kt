package com.nextlevel.subscription.domain.person

import com.nextlevel.subscription.domain.ValueObject
import com.nextlevel.subscription.domain.exceptions.DomainException

data class Address(
    val zipcode: String,
    val street: String,
    val city: String,
    val state: String,
    val country: String
) : ValueObject {
    init {
        require(street.isNotBlank()) { throw DomainException.with("street must not be empty") }
        require(city.isNotBlank()) { throw DomainException.with("city must not be empty") }
        require(state.isNotBlank()) { throw DomainException.with("state must not be empty") }
        require(country.isNotBlank()) { throw DomainException.with("country must not be empty") }
        require(zipcode.isNotBlank()) { throw DomainException.with("zipcode must not be empty") }
    }
}