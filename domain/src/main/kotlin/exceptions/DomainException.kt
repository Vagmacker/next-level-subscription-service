package com.nextlevel.subscription.domain.exceptions

import com.nextlevel.subscription.domain.Identifier
import com.nextlevel.subscription.domain.AggregateRoot
import com.nextlevel.subscription.domain.validation.Error

open class DomainException(
    message: String,
    val errors: MutableList<Error>
) : NoStacktraceException(message) {

    companion object {
        fun with(message: String): DomainException {
            return DomainException(message, mutableListOf(Error(message)))
        }

        fun notFound(aggClass: Class<out AggregateRoot<*>>, id: Identifier<*>): DomainException {
            return with("${aggClass.canonicalName} with id ${id.value} was not found")
        }

        fun with(error: Error): DomainException {
            return DomainException(error.message, mutableListOf(error))
        }

        fun with(errors: MutableList<Error>): DomainException {
            return DomainException("", errors)
        }
    }
}