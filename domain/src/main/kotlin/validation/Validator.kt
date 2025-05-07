package com.nextlevel.subscription.domain.validation

abstract class Validator protected constructor(
    private val handler: ValidationHandler
) {
    abstract fun validate()
}