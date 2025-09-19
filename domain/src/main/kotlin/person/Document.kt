package com.nextlevel.subscription.domain.person

import com.nextlevel.subscription.domain.ValueObject
import com.nextlevel.subscription.domain.exceptions.DomainException

sealed interface Document : ValueObject {
    val number: String
    val type: String

    companion object {
        fun create(document: String, type: String): Document = DocumentFactory.create(document, type)
    }

    data class Cpf(override val number: String) : Document {
        init {
            require(number.isNotBlank()) { throw DomainException.with("cpf must not be empty") }
            require(number.length == 11) { throw DomainException.with("cpf is invalid") }
        }

        override val type: String = "cpf"
    }

    data class Cnpj(override val number: String) : Document {
        init {
            require(number.isNotBlank()) { throw DomainException.with("cnpj must not be empty") }
            require(number.length == 14) { throw DomainException.with("cnpj is invalid") }
        }

        override val type: String = "cnpj"
    }
}

object DocumentFactory {
    fun create(value: String, type: String): Document {
        return when (type) {
            "cpf" -> Document.Cpf(value)
            "cnpj" -> Document.Cnpj(value)
            else -> throw DomainException.with("document type is invalid")
        }
    }
}