package com.nextlevel.subscription.domain.validation.handler

import com.nextlevel.subscription.domain.validation.Error
import com.nextlevel.subscription.domain.exceptions.DomainException
import com.nextlevel.subscription.domain.validation.ValidationHandler

class Notification private constructor(override val errors: MutableList<Error>) : ValidationHandler {

    companion object {
        fun create(): Notification = Notification(mutableListOf())

        fun create(t: Throwable): Notification = create(Error(t.message))

        fun create(errors: MutableList<Error>): Notification = Notification(errors)

        fun create(error: Error): Notification = Notification(mutableListOf(error))
    }

    override fun append(error: Error): Notification {
        errors.add(error)
        return this
    }

    override fun append(validator: ValidationHandler): Notification {
        errors.addAll(validator.errors)
        return this
    }

    override fun <T> validate(validation: ValidationHandler.Validation<T>): T {
        try {
            return validation.validate()
        } catch (e: Throwable) {
            errors.add(Error(e.message ?: ""))
        } catch (e: DomainException) {
            errors.addAll(e.errors)
        }

        return null as T
    }
}