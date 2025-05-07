package com.nextlevel.subscription.domain.validation

data class Error(val property: String, val message: String) {
    constructor(message: String) : this(message, "")
}