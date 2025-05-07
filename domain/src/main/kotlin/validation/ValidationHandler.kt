package com.nextlevel.subscription.domain.validation

interface ValidationHandler {
    val errors: MutableList<Error>

    fun append(error: Error): ValidationHandler
    fun append(validator: ValidationHandler): ValidationHandler

    fun hasErrors(): Boolean {
        return errors.isNotEmpty()
    }

    fun firstError(): Error? {
        return if (hasErrors()) errors.first() else null
    }

    fun <T> validate(validation: Validation<T>): T

    interface Validation<T> {
        fun validate(): T
    }
}